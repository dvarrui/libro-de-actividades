#include <SDL/SDL.h>
#include <SDL/SDL_thread.h>
#include <stdlib.h>
#include <time.h>
#include <math.h>
#include <string.h>
#include <assert.h>
#include "gamedefs.h"
#include "particle.h"
#include "background.h"
#include "resources.h"
#include "scripting.h"
#include "network.h"
#include "music.h"
#include "audio.h"
#include "weapon.h"
#include "status.h"

/* Player data */
enum { OPP_COMPUTER, OPP_NETWORK } opponent_type;

SDL_mutex *player_mutex;        /* lock on player data, so the networking
				   code can access it safely in the background */
player_t player;		/* the player sitting at the local keyboard */
player_t opponent;		/* scripted or networked opponent */

/* The current camera position */
static int camera_x, camera_y;	/* position of 640x480 viewport within world */

SDL_Surface *screen;		/* global for convenience */

/* Network link structure */
net_link_t netlink;
int network_ok = 1;

/* These variables are set by the network thread. The main loop should copy
   them into the corresponding player structures at its earliest convenience.
   This is necessary because the network thread is decoupled from the main
   loop, and the values could get overwritten otherwise. */
int local_player_hit = 0;         /* local player has been hit */
int local_player_dead = 0;        /* local player has been destroyed */
int network_opponent_respawn = 0; /* remote player has respawned */

/* The main loop can set these variables to indicate various events to the
   network thread. The network thread will reset them to 0 after processing. */
int network_opponent_hit = 0;     /* remote player has been hit, subtract shields */
int network_opponent_dead = 0;    /* remote player has been destroyed */
int local_player_respawn = 0;     /* local player has respawned */

/* Network thread */
SDL_Thread *network_thread;

/* Other misc. global variables */
int fullscreen = 0;
int hwsurface = 0;
int doublebuf = 0;

double time_scale = 0;

SDL_Thread *music_update_thread = NULL;

/* ==========
   Prototypes
   ========== */

static unsigned int getrandom();
static void initrandom();
static void DrawPlayer(player_p p);
static void InitPlayer(player_p p);
static void UpdatePlayer(player_p p);
static void PlayGame();

/* This is a simple custom pseudorandom number generator. It's not a very
   good one, but it's sufficient for our purposes. Never trust the rand()
   included with the C library. Its quality varies between implementations,
   and it's easy to run into patterns within the generated numbers. At least
   this one is consistent. */
static Sint32 seed = 0;

static void initrandom()
{
    seed = time(NULL);
}

static unsigned int getrandom()
{
    Sint32 p1 = 1103515245;
    Sint32 p2 = 12345;
    seed = (seed*p1+p2) % 2147483647;
    return (unsigned)seed/3;
}

/* =======
   Drawing
   ======= */

/* Draws the given player to the screen. */
static void DrawPlayer(player_p p)
{
    SDL_Rect src, dest;
    int angle;
	
    /* Calculate the player's new screen coordinates. */
    p->screen_x = (int)p->world_x - camera_x;
    p->screen_y = (int)p->world_y - camera_y;

    /* If the player isn't on the screen, don't draw anything. */
    if (p->screen_x < -PLAYER_WIDTH/2 ||
	p->screen_x >= SCREEN_WIDTH+PLAYER_WIDTH/2)
	return;
	
    if (p->screen_y < -PLAYER_HEIGHT/2 ||
	p->screen_y >= SCREEN_HEIGHT+PLAYER_HEIGHT/2)
	return;
	
    /* Calculate drawing coordinates. */
    angle = p->angle;
    if (angle < 0) angle += 360;
    src.x = PLAYER_WIDTH * (angle / 4);
    src.y = 0;
    src.w = PLAYER_WIDTH;
    src.h = PLAYER_HEIGHT;
    dest.x = p->screen_x - PLAYER_WIDTH/2;
    dest.y = p->screen_y - PLAYER_HEIGHT/2;
    dest.w = PLAYER_WIDTH;
    dest.h = PLAYER_HEIGHT;
	
    /* Draw the sprite. */
    SDL_BlitSurface(ship_strip,&src,screen,&dest);
}

/* =============
   Player Update
   ============= */

/* Initializes the given player. */
static void InitPlayer(player_p p)
{
    p->world_x = getrandom() % WORLD_WIDTH;
    p->world_y = getrandom() % WORLD_HEIGHT;
    p->accel = 0;
    p->velocity = 0;
    p->angle = 0;
    p->charge = 0;
    p->firing = 0;
    p->shields = 100;
    UpdatePlayer(p);
}

/* Calculates a player's new world coordinates based on the camera
   and the player's velocity. Adds acceleration to velocity. Uses simple
   trigonometry to update the world coordinates. */
static void UpdatePlayer(player_p p)
{
    float angle;
	
    angle = (float)p->angle;
	
    SDL_LockMutex(player_mutex);

    p->velocity += p->accel * time_scale;
    if (p->velocity > PLAYER_MAX_VELOCITY) p->velocity = PLAYER_MAX_VELOCITY;
    if (p->velocity < PLAYER_MIN_VELOCITY) p->velocity = PLAYER_MIN_VELOCITY;
	
    p->world_x += p->velocity * cos(angle*PI/180.0) * time_scale;
    p->world_y += p->velocity * -sin(angle*PI/180.0) * time_scale;
				
    /* Make sure the player doesn't slide off the edge of the world. */
    if (p->world_x < 0) p->world_x = 0;
    if (p->world_x >= WORLD_WIDTH) p->world_x = WORLD_WIDTH-1;
    if (p->world_y < 0) p->world_y = 0;
    if (p->world_y >= WORLD_HEIGHT) p->world_y = WORLD_HEIGHT-1;

    SDL_UnlockMutex(player_mutex);
}

/* This routine first sends a network packet, then waits for one to
   arrive. It should be called once per frame.
   Returns 0 on success, -1 on network error. */
static int UpdateNetworkPlayer()
{
    net_pkt_t incoming, outgoing;
    int firing, hit, dead, respawn;

    /* Compose a packet. Don't hold the data lock any longer
       than necessary (don't want to hold up the main loop). */
    SDL_LockMutex(player_mutex);

    if (network_opponent_hit) {
	network_opponent_hit--;
	hit = 1;
    } else  
	hit = 0;

    CreateNetPacket(&outgoing, &player, hit,
		    network_opponent_dead,
		    local_player_respawn);

    network_opponent_dead = 0;
    local_player_respawn = 0;

    SDL_UnlockMutex(player_mutex);

    /* Send it. */
    if (WriteNetgamePacket(&netlink, &outgoing) != 0) {
	fprintf(stderr, "Unable to write network packet.\n");
	return -1;
    }

    /* Receive a packet from the remote host. */
    if (ReadNetgamePacket(&netlink, &incoming) != 0) {
	fprintf(stderr, "Unable to read network packet.\n");
	return -1;
    }

    /* Update our copy of the opponent's data. */
    SDL_LockMutex(player_mutex);	
    InterpretNetPacket(&incoming, &opponent.world_x, &opponent.world_y,
		       &opponent.angle, &opponent.velocity,
		       &firing, &hit, &dead, &respawn);
    opponent.firing = (double)firing;
    local_player_hit += hit;
    if (dead)
	local_player_dead = 1;
    if (respawn)
	network_opponent_respawn = 1;

    SDL_UnlockMutex(player_mutex);

    return 0;
}

static int NetworkThread(void *arg)
{
    network_ok = 1;

    /* Avoid compiler warning. */
    arg += 0;

    for (;;) {
	if (UpdateNetworkPlayer() != 0) {
	    printf("Network error.\n");
	    network_ok = 0;
	    break;
	}
		
	/* A slight speed brake. */
	SDL_Delay(10);
    }
       
    return 0;
}

/* Returns 1 if the given player can fire, 0 otherwise. */
int CanPlayerFire(player_p p)
{
    if ((p->charge >= PHASER_CHARGE_FIRE) && (p->firing == 0))
	return 1;
    return 0;
}

/* Turns on a phaser beam. Test CanPlayerFire first. */
void FirePhasers(player_p p)
{
    p->charge -= PHASER_CHARGE_FIRE;
    if (p->charge < 0) p->charge = 0;
    p->firing = PHASER_FIRE_TIME;
    
    /* Play the appropriate sound. */
    if (p == &player)
	    StartPlayerPhaserSound();
    else
	    StartOpponentPhaserSound();
}

/* Charge phasers by one increment. */
static void ChargePhasers(player_p p)
{
    p->charge += time_scale/30.0*PHASER_CHARGE_RATE;
    if (p->charge > PHASER_CHARGE_MAX)
	p->charge = PHASER_CHARGE_MAX;
}


/* Show a small explosion due to phaser damage. */
static void ShowPhaserHit(player_p p)
{
    CreateParticleExplosion(
	p->world_x, p->world_y, 255, 255, 255, 10, 300);
    CreateParticleExplosion(
	-		p->world_x, p->world_y, 255, 0, 0, 5, 100);
    CreateParticleExplosion(
	p->world_x, p->world_y, 255, 255, 0, 2, 50);
}


/* Show a large ship explosion. */
static void ShowShipExplosion(player_p p)
{
    CreateParticleExplosion(
	p->world_x, p->world_y, 255, 255, 255, 15, 3000);
    CreateParticleExplosion(
	p->world_x, p->world_y, 255, 0, 0, 10, 1000);
    CreateParticleExplosion(
	p->world_x, p->world_y, 255, 255, 0, 5, 500);
}


/* Destroy the opponent. */
static void KillOpponent()
{
    player.score++;
    ShowShipExplosion(&opponent);
    if (opponent_type == OPP_NETWORK) {
	/* Instruct the network thread to send a deathgram to the other end. */
	network_opponent_hit = 0;
	network_opponent_dead++;
    } else {
	/* Just reset the opponent -- the script doesn't care. A more advanced
	   AI system might need to be informed, though. */
	InitPlayer(&opponent);
    }
}


/* Destroy the local player. */
static void KillPlayer()
{
    ShowShipExplosion(&player);
    player.velocity = 0;
    player.accel = 0;
    opponent.score++;
}


/* Cause damage to the opponent. */
static void DamageOpponent()
{
    opponent.shields -= PHASER_DAMAGE;
    network_opponent_hit++;
    if (opponent.shields <= 0)
	KillOpponent();
}

/* Music update thread. Calls UpdateMusic approximately twenty times a second.
   This decouples the music system from the game loop. See the text for an
   explanation. */
int UpdateMusicThread(void *arg)
{
    /* Avoid compiler warning. */
    arg += 0;

    for (;;) {
	UpdateMusic();
	SDL_Delay(10);
    }

    return 0;
}


/* ==============
   Main Game Loop
   ============== */

static void PlayGame()
{
    Uint8 *keystate;
    int quit = 0;
    int turn;
    int prev_ticks = 0, cur_ticks = 0; /* for keeping track of timing */
    int awaiting_respawn = 0;

    /* framerate counter variables */
    int start_time, end_time;
    int frames_drawn = 0;

    /* respawn timer */
    int respawn_timer = -1;
	
    prev_ticks = SDL_GetTicks();
	
    start_time = time(NULL);

    /* Reset the score counters. */
    player.score = 0;
    opponent.score = 0;

    /* Start sound playback. */
    StartAudio();
    StartMusic();

    /* Start the music update thread. */
    music_update_thread = SDL_CreateThread(UpdateMusicThread, NULL);
    if (music_update_thread == NULL) {
	printf("Unable to start music update thread.\n");
    }

    /* Start the game! */
    while ((quit == 0) && network_ok) {

	/* Determine how many milliseconds have passed since
	   the last frame, and update our motion scaling. */
	prev_ticks = cur_ticks;
	cur_ticks = SDL_GetTicks();
	time_scale = (double)(cur_ticks-prev_ticks)/30.0;
				
	/* Update SDL's internal input state information. */
	SDL_PumpEvents();
		
	/* Grab a snapshot of the keyboard. */
	keystate = SDL_GetKeyState(NULL);

	/* Lock the mutex so we can access the player's data. */
	SDL_LockMutex(player_mutex);
		
	/* If this is a network game, take note of variables
	   set by the network thread. These are handled differently
	   for a scripted opponent. */
	if (opponent_type == OPP_NETWORK) {

	    /* Has the opponent respawned? */
	    if (network_opponent_respawn) {
		printf("Remote player has respawned.\n");
		opponent.shields = 100;
		network_opponent_respawn = 0;
		awaiting_respawn = 0;
	    }
		
	    /* Has the local player been hit? */
	    if (local_player_hit) {
		local_player_hit--;
		player.shields -= PHASER_DAMAGE;
		ShowPhaserHit(&player);
				/* No need to check for death, the
				   other computer will tell us. */
	    }
		}

	/* Update phasers. */
	player.firing -= time_scale;
	if (player.firing < 0) player.firing = 0;
	opponent.firing -= time_scale;
	if (opponent.firing < 0) opponent.firing = 0;
	ChargePhasers(&player);

	/* If the local player is destroyed, the respawn timer will
	   start counting. During this time the controls are disabled
	   and explosion sequence occurs. */
	if (respawn_timer >= 0) {
	    respawn_timer++;

	    if (respawn_timer >= ((double)RESPAWN_TIME / time_scale)) {
		respawn_timer = -1;
		InitPlayer(&player);

				/* Set the local_player_respawn flag so the
				   network thread will notify the opponent
				   of the respawn. */
		local_player_respawn = 1;

		SetStatusMessage("GOOD LUCK, WARRIOR!");
	    }
	}

	/* Respond to input and network events, but not if we're in a respawn. */
	if (respawn_timer == -1) {
	    if (keystate[SDLK_q] || keystate[SDLK_ESCAPE]) quit = 1;
			
	    /* Left and right arrow keys control turning. */
	    turn = 0;
	    if (keystate[SDLK_LEFT]) turn += 10;
	    if (keystate[SDLK_RIGHT]) turn -= 10;
			
	    /* Forward and back arrow keys activate thrusters. */
	    player.accel = 0;
	    if (keystate[SDLK_UP]) player.accel = PLAYER_FORWARD_THRUST;
	    if (keystate[SDLK_DOWN]) player.accel = PLAYER_REVERSE_THRUST;
			
	    /* Spacebar fires phasers. */
	    if (keystate[SDLK_SPACE]) {

		if (CanPlayerFire(&player)) {

		    FirePhasers(&player);

		    /* If it's a hit, either notify the opponent
		       or exact the damage. Create a satisfying particle
		       burst. */
		    if (!awaiting_respawn &&
			CheckPhaserHit(&player,&opponent)) {

			ShowPhaserHit(&opponent);
			DamageOpponent();

			/* If that killed the opponent, set the
			   "awaiting respawn" state, to prevent multiple
			   kills. */
			if (opponent.shields <= 0 &&
			    opponent_type == OPP_NETWORK)
			    awaiting_respawn = 1;
       		    }
		}
	    }
			
	    /* Turn. */
	    player.angle += turn * time_scale;
	    if (player.angle < 0) player.angle += 360;
	    if (player.angle >= 360) player.angle -= 360;

	    /* If this is a network game, the remote player will
	       tell us if we've died. Otherwise we have to check
	       for failed shields. */
	    if (((opponent_type == OPP_NETWORK) && local_player_dead) ||
		(player.shields <= 0))
	    {
		printf("Local player has been destroyed.\n");
		local_player_dead = 0;
				
		/* Kaboom! */
		KillPlayer();
				
		/* Respawn. */
		respawn_timer = 0;
	    }
	}

	/* If this is a player vs. computer game, give the computer a chance. */
	if (opponent_type == OPP_COMPUTER) {
	    if (RunGameScript() != 0) {
		fprintf(stderr, "Ending game due to script error.\n");
		quit = 1;
	    }
			
	    /* Check for phaser hits against the player. */
	    if (opponent.firing) {
		if (CheckPhaserHit(&opponent,&player)) {
					
		    ShowPhaserHit(&player);
		    player.shields -= PHASER_DAMAGE;

		    /* Did that destroy the player? */
		    if (respawn_timer < 0 && player.shields <= 0) {
			KillPlayer();
			respawn_timer = 0;
		    }
		}
	    }

	    ChargePhasers(&opponent);
	    UpdatePlayer(&opponent);
	}

	/* Update the player's position. */
	UpdatePlayer(&player);

	/* Update the status information. */
	SetPlayerStatusInfo(player.score, player.shields, player.charge);
	SetOpponentStatusInfo(opponent.score, opponent.shields);

	/* Make the camera follow the player (but impose limits). */
	camera_x = player.world_x - SCREEN_WIDTH/2;
	camera_y = player.world_y - SCREEN_HEIGHT/2;
		
	if (camera_x < 0) camera_x = 0;
	if (camera_x >= WORLD_WIDTH-SCREEN_WIDTH)
	    camera_x = WORLD_WIDTH-SCREEN_WIDTH-1;
	if (camera_y < 0) camera_y = 0;
	if (camera_y >= WORLD_HEIGHT-SCREEN_HEIGHT)
	    camera_y = WORLD_HEIGHT-SCREEN_HEIGHT-1;

	/* Update the particle system. */
	UpdateParticles();

	/* Keep OpenAL happy. */
	UpdateAudio(&player, &opponent);
				
	/* Redraw everything. */
	DrawBackground(screen, camera_x, camera_y);
	DrawParallax(screen, camera_x, camera_y);
	DrawParticles(screen, camera_x, camera_y);
	if (opponent.firing)
	    DrawPhaserBeam(&opponent, screen, camera_x, camera_y);
	if (player.firing)
	    DrawPhaserBeam(&player, screen, camera_x, camera_y);

	if (respawn_timer < 0)
	    DrawPlayer(&player);
	if (!awaiting_respawn)
	    DrawPlayer(&opponent);
	UpdateStatusDisplay(screen);
		
	/* Release the mutex so the networking system can get it.
	   It doesn't stay unlocked for very long, but the networking
	   system should still have plenty of time. */
	SDL_UnlockMutex(player_mutex);
	
	/* Flip the page. */
	SDL_Flip(screen);

	frames_drawn++;
    }

    end_time = time(NULL);
    if (start_time == end_time) end_time++;

    /* Display the average framerate. */
    printf("Drew %i frames in %i seconds, for a framerate of %.2f fps.\n",
	   frames_drawn,
	   end_time-start_time,
	   (float)frames_drawn/(float)(end_time-start_time));


    /* Terminate the music update thread. */
    if (music_update_thread != NULL) {
	SDL_KillThread(music_update_thread);
	music_update_thread = NULL;
    }
	
    /* Stop audio playback. */
    StopAudio();
    StopMusic();
}


int main(int argc, char *argv[])
{
    enum { GAME_COMPUTER, GAME_NETWORK, GAME_UNKNOWN } game_type = GAME_UNKNOWN;
    char *remote_address = NULL;
    int remote_port;
    int i;
	
    if (argc < 2) {
	printf("Penguin Warrior\n");
	printf("Usage: pw [ mode ] [ option ... ]\n");
	printf("  Game modes (choose one):\n");
	printf("    --computer\n");
	printf("    --client <remote ip address>\n");
	printf("    --server <port number>\n");
	printf("  Display options, for tweaking and experimenting:\n");
	printf("    --fullscreen\n");
	printf("    --hwsurface  (use an SDL hardware surface if possible)\n");
	printf("    --doublebuf  (use SDL double buffering if possible)\n");
	printf("  The display options default to a windowed, software buffer.\n");
	exit(EXIT_FAILURE);
    }

    /* Process command line arguments. There are plenty of libraries for command
       line processing, but our needs are simple in this case. */
    for (i = 0; i < argc; i++) {

	/* Networked or scripted opponent? */
	if (!strcmp(argv[i], "--computer")) {
	    game_type = GAME_COMPUTER;
	    continue;
	} else if (!strcmp(argv[i], "--client")) {
	    game_type = GAME_NETWORK;
	    if (i + 2 >= argc) {
		printf("You must supply an IP address "\
		       "and port number for network games.\n");
		exit(EXIT_FAILURE);
	    }
	    remote_address = argv[i+1];
	    remote_port = atoi(argv[i+2]);
	    i++;
	    continue;

	} else if (!strcmp(argv[i], "--server")) {
	    game_type = GAME_NETWORK;
	    if (++i >= argc) {
		printf("You must supply a port number "\
		       "for --server.\n");
		exit(EXIT_FAILURE);
	    }
	    remote_port = atoi(argv[i]);
	    continue;
			
	    /* Display-related options */
	} else if (!strcmp(argv[i], "--hwsurface")) {
	    hwsurface = 1;
	} else if (!strcmp(argv[i], "--doublebuf")) {
	    doublebuf = 1;
	} else if (!strcmp(argv[i], "--fullscreen")) {
	    fullscreen = 1;
	}
    }

    /* If this is a network game, make a connection. */
    if (game_type == GAME_NETWORK) {

	/* If there's no remote address, the user selected
	   server mode. */
	if (remote_address == NULL) {
	    if (WaitNetgameConnection(remote_port,
				      &netlink) != 0) {
		printf("Unable to receive connection.\n");
		exit(EXIT_FAILURE);
	    }
	} else {
	    if (ConnectToNetgame(remote_address, remote_port,
				 &netlink) != 0) {
		printf("Unable to connect.\n");
		exit(EXIT_FAILURE);
	    }
	}

	opponent_type = OPP_NETWORK;
	printf("Playing in network game against %s.\n", netlink.dotted_ip);

    } else if (game_type == GAME_COMPUTER) {

	opponent_type = OPP_COMPUTER;
	printf("Playing against the computer.\n");

    } else {

	printf("No game type selected.\n");
	exit(EXIT_FAILURE);

    }
	
    /* Initialize our random number generator. */
    initrandom();

    /* Create a mutex to protect the player data. */
    player_mutex = SDL_CreateMutex();
    if (player_mutex == NULL) {
	fprintf(stderr, "Unable to create mutex: %s\n",
		SDL_GetError());
    }

    /* Start our scripting engine. */
    InitScripting();
    if (LoadGameScript("pw.tcl") != 0) {
	fprintf(stderr, "Exiting due to script error.\n");
	exit(EXIT_FAILURE);
    }

    /* Fire up SDL. */
    if (SDL_Init(SDL_INIT_VIDEO) < 0) {
	printf("Unable to initialize SDL: %s\n", SDL_GetError());
	exit(EXIT_FAILURE);
    }
    atexit(SDL_Quit);
	
    /* Set an appropriate 16-bit video mode. */
    if (SDL_SetVideoMode(SCREEN_WIDTH, SCREEN_HEIGHT, 16,
			 (hwsurface ? SDL_HWSURFACE : SDL_SWSURFACE) |
			 (doublebuf ? SDL_DOUBLEBUF : 0) |
			 (fullscreen ? SDL_FULLSCREEN : 0)) == NULL) {
	printf("Unable to set video mode: %s\n", SDL_GetError());
	exit(EXIT_FAILURE);
    }
	
    /* Save the screen pointer for later use. */
    screen = SDL_GetVideoSurface();
	
    /* Set the window caption to the name of the game. */
    SDL_WM_SetCaption("Penguin Warrior", "Penguin Warrior");
	
    /* Hide the mouse pointer. */
    SDL_ShowCursor(0);

    /* Initialize the status display. */
    if (InitStatusDisplay() < 0) {
	printf("Unable to initialize status display.\n");
	exit(EXIT_FAILURE);
    }
		
    /* Start the OpenAL-based audio system. */
    InitAudio();

    /* Initialize music and give the music system a file. */
    InitMusic();
    if (LoadMusic("reflux.ogg") < 0) {
	/* If that failed, don't worry about it. */
	printf("Unable to load reflux.ogg.\n");
    }

    /* Load the game's data into globals. */
    LoadGameData();

    /* Initialize the background starfield. */
    InitBackground();

    /* Start the network thread. */
    if (game_type == GAME_NETWORK) {
	network_thread = SDL_CreateThread(NetworkThread, NULL);
	if (network_thread == NULL) {
	    printf("Unable to start network thread: %s\n",
		   SDL_GetError());
	    exit(EXIT_FAILURE);
	}
    }
	
    /* Play! */
    InitPlayer(&player);
    InitPlayer(&opponent);
    PlayGame();

    /* Kill the network thread. */
    if (game_type == GAME_NETWORK) {
	SDL_KillThread(network_thread);
    }

    /* Close the network connection. */
    if (game_type == GAME_NETWORK)
	CloseNetgameLink(&netlink);

    /* Unhide the mouse pointer. */
    SDL_ShowCursor(1);
	
    /* Clean up the status display. */
    CleanupStatusDisplay();

    /* Unload data. */
    UnloadGameData();
	
    /* Shut down our scripting engine. */
    CleanupScripting();

    /* Get rid of the mutex. */
    SDL_DestroyMutex(player_mutex);

    /* Shut down audio. */
    CleanupMusic();
    CleanupAudio();

    return 0;
}
