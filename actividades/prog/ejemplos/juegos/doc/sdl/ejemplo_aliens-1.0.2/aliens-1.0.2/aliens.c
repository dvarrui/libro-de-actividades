/*
    ALIENS: A silly little game demonstrating the SDL and mixer libraries
    Copyright (C) 1998  Sam Lantinga

    This program is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program; if not, write to the Free Software
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA

    Sam Lantinga
    5635-34 Springhouse Dr.
    Pleasanton, CA 94588 (USA)
    slouken@devolution.com
*/

#include <stdlib.h>
#include <stdio.h>
#include <time.h>

#include "SDL.h"
#include "SDL_mixer.h"
#include "SDL_image.h"

#ifdef macintosh
#define DIR_SEP	":"
#define DIR_CUR ":"
#else
#define DIR_SEP	"/"
#define DIR_CUR	""
#endif
#define DATAFILE(X)	DIR_CUR "data" DIR_SEP X

#define	FRAMES_PER_SEC	50
#define PLAYER_SPEED	4
#define MAX_SHOTS	3
#define SHOT_SPEED	6
#define MAX_ALIENS	30
#define ALIEN_SPEED	5
#define ALIEN_ODDS	(1*FRAMES_PER_SEC)
#define EXPLODE_TIME	4

typedef struct {
	int alive;
	int facing;
	int x, y;
	SDL_Surface *image;
} object;

/**/
SDL_Surface *screen;
SDL_Surface *background;
/**/
object player;
int reloading;
object shots[MAX_SHOTS];
/**/
object aliens[MAX_ALIENS];
/**/
object explosions[MAX_ALIENS+1];
/**/
#define MAX_UPDATES	3*(1+MAX_SHOTS+MAX_ALIENS)
int numupdates;
SDL_Rect srcupdate[MAX_UPDATES];
SDL_Rect dstupdate[MAX_UPDATES];
struct blit {
	SDL_Surface *src;
	SDL_Rect *srcrect;
	SDL_Rect *dstrect;
} blits[MAX_UPDATES];
/**/

#if defined(PLAY_MOD) || defined(PLAY_MID)
Mix_Music *music;
#endif
enum {
	MUSIC_WAV,
	SHOT_WAV,
	EXPLODE_WAV,
	NUM_WAVES
};
Mix_Chunk *sounds[NUM_WAVES];

SDL_Surface *LoadImage(char *datafile, int transparent)
{
	SDL_Surface *image, *surface;

	image = IMG_Load(datafile);
	if ( image == NULL ) {
		fprintf(stderr, "Couldn't load image %s: %s\n",
					datafile, IMG_GetError());
		return(NULL);
	}
	if ( transparent ) {
		/* Assuming 8-bit BMP image */
		SDL_SetColorKey(image, (SDL_SRCCOLORKEY|SDL_RLEACCEL),
						*(Uint8 *)image->pixels);
	}
	surface = SDL_DisplayFormat(image);
	SDL_FreeSurface(image);
	return(surface);
}
int LoadData(void)
{
	int i;

	/* Load sounds */
#if defined(PLAY_MOD) || defined(PLAY_MID)
#if defined(PLAY_MOD)
	music = Mix_LoadMUS(DATAFILE("music.it"));
#elif defined(PLAY_MID)
	music = Mix_LoadMUS(DATAFILE("music.mid"));
#endif
	if ( music == NULL ) {
		fprintf(stderr, "Warning: Couldn't load music: %s\n",
							Mix_GetError());
	}
#else
	sounds[MUSIC_WAV] = Mix_LoadWAV(DATAFILE("music.wav"));
#endif
	sounds[SHOT_WAV] = Mix_LoadWAV(DATAFILE("shot.wav"));
	sounds[EXPLODE_WAV] = Mix_LoadWAV(DATAFILE("explode.wav"));

	/* Load graphics */
	player.image = LoadImage(DATAFILE("player.gif"), 1);
	if ( player.image == NULL ) {
		return(0);
	}
	shots[0].image = LoadImage(DATAFILE("shot.gif"), 0);
	if ( shots[0].image == NULL ) {
		return(0);
	}
	for ( i=1; i<MAX_SHOTS; ++i ) {
		shots[i].image = shots[0].image;
	}
	aliens[0].image = LoadImage(DATAFILE("alien.gif"), 1);
	if ( aliens[0].image == NULL ) {
		return(0);
	}
	for ( i=1; i<MAX_ALIENS; ++i ) {
		aliens[i].image = aliens[0].image;
	}
	explosions[0].image = LoadImage(DATAFILE("explosion.gif"), 1);
	for ( i=1; i<MAX_ALIENS+1; ++i ) {
		explosions[i].image = explosions[0].image;
	}
	background = LoadImage(DATAFILE("background.gif"), 0);

	/* Set up the update rectangle pointers */
	for ( i=0; i<MAX_UPDATES; ++i ) {
		blits[i].srcrect = &srcupdate[i];
		blits[i].dstrect = &dstupdate[i];
	}
	return(1);
}

void FreeData(void)
{
	int i;

	/* Free sounds */
#if defined(PLAY_MOD) || defined(PLAY_MID)
	Mix_FreeMusic(music);
#endif
	for ( i=0; i<NUM_WAVES; ++i ) {
		Mix_FreeChunk(sounds[i]);
	}

	/* Free graphics */
	SDL_FreeSurface(player.image);
	SDL_FreeSurface(shots[0].image);
	SDL_FreeSurface(aliens[0].image);
	SDL_FreeSurface(explosions[0].image);
	SDL_FreeSurface(background);
}

void CreateAlien(void)
{
	int i;

	/* Look for a free alien slot */
	for ( i=0; i<MAX_ALIENS; ++i ) {
		if ( ! aliens[i].alive )
			break;
	}
	if ( i == MAX_ALIENS ) {
		return;
	}

	/* Figure out which direction it travels */
	do {
		aliens[i].facing = (rand()%3)-1;
	} while ( aliens[i].facing == 0 );

	/* Figure out it's initial location */
	aliens[i].y = 0;
	if ( aliens[i].facing < 0 ) {
		aliens[i].x = screen->w-aliens[i].image->w-1;
	} else {
		aliens[i].x = 0;
	}
	aliens[i].alive = 1;
}

void DrawObject(object *sprite)
{
	struct blit *update;

	update = &blits[numupdates++];
	update->src = sprite->image;
	update->srcrect->x = 0;
	update->srcrect->y = 0;
	update->srcrect->w = sprite->image->w;
	update->srcrect->h = sprite->image->h;
	update->dstrect->x = sprite->x;
	update->dstrect->y = sprite->y;
	update->dstrect->w = sprite->image->w;
	update->dstrect->h = sprite->image->h;
}
void EraseObject(object *sprite)
{
	struct blit *update;
	int wrap;

	/* The background wraps horizontally across the screen */
	update = &blits[numupdates++];
	update->src = background;
	update->srcrect->x = sprite->x%background->w;
	update->srcrect->y = sprite->y;
	update->srcrect->w = sprite->image->w;
	update->srcrect->h = sprite->image->h;
	wrap = (update->srcrect->x+update->srcrect->w)-(background->w);
	if ( wrap > 0 ) {
		update->srcrect->w -= wrap;
	}
	update->dstrect->x = sprite->x;
	update->dstrect->y = sprite->y;
	update->dstrect->w = update->srcrect->w;
	update->dstrect->h = update->srcrect->h;

	/* Assuming sprites can only wrap across one background tile */
	if ( wrap > 0 ) {
		update = &blits[numupdates++];
		update->src = background;
		update->srcrect->x = 0;
		update->srcrect->y = sprite->y;
		update->srcrect->w = wrap;
		update->srcrect->h = sprite->image->h;
		update->dstrect->x =((sprite->x/background->w)+1)*background->w;
		update->dstrect->y = sprite->y;
		update->dstrect->w = update->srcrect->w;
		update->dstrect->h = update->srcrect->h;
	}
}
void UpdateScreen(void)
{
	int i;

	for ( i=0; i<numupdates; ++i ) {
		SDL_LowerBlit(blits[i].src, blits[i].srcrect,
						screen, blits[i].dstrect);
	}
	SDL_UpdateRects(screen, numupdates, dstupdate);
	numupdates = 0;
}

int Collide(object *sprite1, object *sprite2)
{
	if ( (sprite1->y >= (sprite2->y+sprite2->image->h)) ||
	     (sprite1->x >= (sprite2->x+sprite2->image->w)) ||
	     (sprite2->y >= (sprite1->y+sprite1->image->h)) ||
	     (sprite2->x >= (sprite1->x+sprite1->image->w)) ) {
		return(0);
	}
	return(1);
	     
}

void WaitFrame(void)
{
	static Uint32 next_tick = 0;
	Uint32 this_tick;

	/* Wait for the next frame */
	this_tick = SDL_GetTicks(); 
	if ( this_tick < next_tick ) {
		SDL_Delay(next_tick-this_tick);
	}
	next_tick = this_tick + (1000/FRAMES_PER_SEC);
}

/* This of course can be optimized :-) */
void RunGame(void)
{
	int i, j;
	SDL_Event event;
	Uint8 *keys;

	/* Paint the background */
	numupdates = 0;
	for ( i=0; i<screen->w; i += background->w ) {
		SDL_Rect dst;

		dst.x = i;
		dst.y = 0;
		dst.w = background->w;
		dst.h = background->h;
		SDL_BlitSurface(background, NULL, screen, &dst);
	}
	SDL_UpdateRect(screen, 0, 0, 0, 0);

	/* Initialize the objects */
	player.alive = 1;
	player.x = (screen->w - player.image->w)/2;
	player.y = (screen->h - player.image->h) - 1;
	player.facing = 0;
	DrawObject(&player);

	for ( i=0; i<MAX_SHOTS; ++i ) {
		shots[i].alive = 0;
	}
	for ( i=0; i<MAX_ALIENS; ++i ) {
		aliens[i].alive = 0;
	}
	CreateAlien();
	DrawObject(&aliens[0]);
	UpdateScreen();

	while ( player.alive ) {
		/* Wait for the next frame */
		WaitFrame();

		/* Poll input queue, run keyboard loop */
		while ( SDL_PollEvent(&event) ) {
			if ( event.type == SDL_QUIT )
				return;
		}
		keys = SDL_GetKeyState(NULL);

		/* Erase everything from the screen */
		for ( i=0; i<MAX_SHOTS; ++i ) {
			if ( shots[i].alive ) {
				EraseObject(&shots[i]);
			}
		}
		for ( i=0; i<MAX_ALIENS; ++i ) {
			if ( aliens[i].alive ) {
				EraseObject(&aliens[i]);
			}
		}
		EraseObject(&player);
		for ( i=0; i<MAX_ALIENS+1; ++i ) {
			if ( explosions[i].alive ) {
				EraseObject(&explosions[i]);
			}
		}

		/* Decrement the lifetime of the explosions */
		for ( i=0; i<MAX_ALIENS+1; ++i ) {
			if ( explosions[i].alive ) {
				--explosions[i].alive;
			}
		}

		/* Create new aliens */
		if ( (rand()%ALIEN_ODDS) == 0 ) {
			CreateAlien();
		}

		/* Create new shots */
		if ( ! reloading ) {
			if ( keys[SDLK_SPACE] == SDL_PRESSED ) {
				for ( i=0; i<MAX_SHOTS; ++i ) {
					if ( ! shots[i].alive ) {
						break;
					}
				}
				if ( i != MAX_SHOTS ) {
					shots[i].x = player.x +
					 (player.image->w-shots[i].image->w)/2;
					shots[i].y = player.y -
							  shots[i].image->h;
					shots[i].alive = 1;
					Mix_PlayChannel(SHOT_WAV,
							sounds[SHOT_WAV], 0);
				}
			}
		}
		reloading = (keys[SDLK_SPACE] == SDL_PRESSED);

		/* Move the player */
		player.facing = 0;
		if ( keys[SDLK_RIGHT] ) {
			++player.facing;
		}
		if ( keys[SDLK_LEFT] ) {
			--player.facing;
		}
		player.x += player.facing*PLAYER_SPEED;
		if ( player.x < 0 ) {
			player.x = 0;
		} else
		if ( player.x >= (screen->w-player.image->w) ) {
			player.x = (screen->w-player.image->w)-1;
		}

		/* Move the aliens */
		for ( i=0; i<MAX_ALIENS; ++i ) {
			if ( aliens[i].alive ) {
				aliens[i].x += aliens[i].facing*ALIEN_SPEED;
				if ( aliens[i].x < 0 ) {
					aliens[i].x = 0;
					aliens[i].y += aliens[i].image->h;
					aliens[i].facing = 1;
				} else
				if ( aliens[i].x >=
					   (screen->w-aliens[i].image->w) ) {
					aliens[i].x =
					   (screen->w-aliens[i].image->w)-1;
					aliens[i].y += aliens[i].image->h;
					aliens[i].facing = -1;
				}
			}
		}

		/* Move the shots */
		for ( i=0; i<MAX_SHOTS; ++i ) {
			if ( shots[i].alive ) {
				shots[i].y -= SHOT_SPEED;
				if ( shots[i].y < 0 ) {
					shots[i].alive = 0;
				}
			}
		}

		/* Detect collisions */
		for ( j=0; j<MAX_SHOTS; ++j ) {
			for ( i=0; i<MAX_ALIENS; ++i ) {
				if ( shots[j].alive && aliens[i].alive &&
					  Collide(&shots[j], &aliens[i]) ) {
					aliens[i].alive = 0;
					explosions[i].x = aliens[i].x;
					explosions[i].y = aliens[i].y;
					explosions[i].alive = EXPLODE_TIME;
					Mix_PlayChannel(EXPLODE_WAV,
							sounds[EXPLODE_WAV], 0);
					shots[j].alive = 0;
					break;
				}
			}
		}
		for ( i=0; i<MAX_ALIENS; ++i ) {
			if ( aliens[i].alive && Collide(&player, &aliens[i]) ) {
				aliens[i].alive = 0;
				explosions[i].x = aliens[i].x;
				explosions[i].y = aliens[i].y;
				explosions[i].alive = EXPLODE_TIME;
				player.alive = 0;
				explosions[MAX_ALIENS].x = player.x;
				explosions[MAX_ALIENS].y = player.y;
				explosions[MAX_ALIENS].alive = EXPLODE_TIME;
				Mix_PlayChannel(EXPLODE_WAV,
							sounds[EXPLODE_WAV], 0);
			}
		}

		/* Draw the aliens, shots, player, and explosions */
		for ( i=0; i<MAX_ALIENS; ++i ) {
			if ( aliens[i].alive ) {
				DrawObject(&aliens[i]);
			}
		}
		for ( i=0; i<MAX_SHOTS; ++i ) {
			if ( shots[i].alive ) {
				DrawObject(&shots[i]);
			}
		}
		if ( player.alive ) {
			DrawObject(&player);
		}
		for ( i=0; i<MAX_ALIENS+1; ++i ) {
			if ( explosions[i].alive ) {
				DrawObject(&explosions[i]);
			}
		}
		UpdateScreen();

		/* Loop the music */
#if defined(PLAY_MOD) || defined(PLAY_MID)
		if ( ! Mix_PlayingMusic() ) {
			Mix_PlayMusic(music, 0);
		}
#else
		if ( ! Mix_Playing(MUSIC_WAV) ) {
			Mix_PlayChannel(MUSIC_WAV, sounds[MUSIC_WAV], 0);
		}
#endif

		/* Check for keyboard abort */
		if ( keys[SDLK_ESCAPE] == SDL_PRESSED ) {
			player.alive = 0;
		}
	}

	/* Wait for the player to finish exploding */
	while ( Mix_Playing(EXPLODE_WAV) ) {
		WaitFrame();
	}
	Mix_HaltChannel(-1);
	return;
}

main(int argc, char *argv[])
{
	/* Initialize the SDL library */
	if ( SDL_Init(SDL_INIT_AUDIO|SDL_INIT_VIDEO) < 0 ) {
		fprintf(stderr, "Couldn't initialize SDL: %s\n",SDL_GetError());
		exit(2);
	}
	atexit(SDL_Quit);

	/* Open the audio device */
	if ( Mix_OpenAudio(11025, AUDIO_U8, 1, 512) < 0 ) {
		fprintf(stderr,
		"Warning: Couldn't set 11025 Hz 8-bit audio\n- Reason: %s\n",
							SDL_GetError());
	}

	/* Open the display device */
	screen = SDL_SetVideoMode(640, 480, 0, SDL_SWSURFACE|SDL_FULLSCREEN);
	if ( screen == NULL ) {
		fprintf(stderr, "Couldn't set 640x480 video mode: %s\n",
							SDL_GetError());
		exit(2);
	}

	/* Initialize the random number generator */
	srand(time(NULL));

	/* Load the music and artwork */
	if ( LoadData() ) {
		/* Run the game */
		RunGame();

		/* Free the music and artwork */
		FreeData();
	}

	/* Quit */
	Mix_CloseAudio();
	exit(0);
}
