#include <math.h>
#include <stdio.h>
#include <stdlib.h>
#include "audio.h"
#include "resources.h"

/* Include the OpenAL headers. */
#include <AL/al.h>
#include <AL/alc.h>

/* Factor to control attenuation of audio.
   We'll divide all coordinates by this factor each time we update
   the source positions. OpenAL does provide a cleaner way to do this,
   but it changed recently. */
#define DISTANCE_FACTOR 50.0

/* We'll set this flag to 1 after audio has been successfully initialized. */
int audio_enabled = 0;

/* Our OpenAL context. This is just like an OpenGL context, if you're familiar
   with GL's workings. A context represents a combination of a particular
   output device, a sampling frequency, and so on. */
ALvoid *audio_context = NULL;

/* An output device. We'll set this to AL's default in InitAudio().  */
ALCdevice *audio_device = NULL;

/* Our sources. Sources are objects in 3D space that emit sound.
   We're ignoring the fact that there's no sound in space. */
static ALuint opponent_engine_source;
static ALuint opponent_phaser_source;
/* There is no player engine source; see note in StartAudio below. */
static ALuint player_phaser_source;

/* PFJ - Function added. Using goto is a recipe for disaster. This function
   removes that need */

static void error_cleanup(ALvoid *audio_context)
{
  alcMakeContextCurrent(NULL);
  alcDestroyContext(audio_context);
}

void InitAudio()
{
    int err;

    /* Create a context with whatever settings are available.
       We could replace NULL with a list of parameters. See the
       OpenAL spec for a current list.

       We use alcGetError instead of alGetError for error detection.
       This is because error conditions are stored within contexts,
       and it's pretty meaningless to retrieve an error code from
       something that does not yet exist. */
    audio_device = alcOpenDevice(NULL);
    if (audio_device == NULL)
	fprintf(stderr, "Warning: NULL device.\n");
    else
	fprintf(stderr, "Got a device.\n");
    audio_context = alcCreateContext(audio_device, NULL);

    // PFJ - alcGetError requires an argument! - the argument is the device!

    err = alcGetError(audio_device);
    if (err != ALC_NO_ERROR || audio_context == NULL) {
	fprintf(stderr, 
		"Unable to create an OpenAL context (%s). Audio disabled.\n", 
		alGetString(err));
	return;
    }

    /* Make sure we have a chance to clean up. */
    atexit(CleanupAudio);

    /* Now make the context current. The current context is the subject
       of all OpenAL API calls. Some calls will even segfault if there isn't
       a valid current context. */
    alcMakeContextCurrent(audio_context);
    // PFJ - added audio_device as the argument for alcGetError
    if (alcGetError(audio_device) != ALC_NO_ERROR) {
	fprintf(stderr, 
		"Unable to make OpenAL context current. Audio disabled.\n");
	error_cleanup(audio_context);
	return;
    }

    /* Good. Now create some sources (things that make noise). These will be
       given buffers later. Sources don't do anything until you associate
       them with buffers (which contain PCM sound data). */
    alGenSources(1, &opponent_engine_source);
    alGenSources(1, &opponent_phaser_source);
    alGenSources(1, &player_phaser_source);
    if (alGetError() != AL_NO_ERROR) {
	fprintf(stderr, "Unable to allocate audio sources. Audio disabled.\n");
	error_cleanup(audio_context);
	return;
    }

    /* Ready to go. */
    audio_enabled = 1;
    printf("Audio enabled. OpenAL information:\n");
    printf("  Version:    %s\n", alGetString(AL_VERSION));
    printf("  Renderer:   %s\n", alGetString(AL_RENDERER));
    printf("  Vendor:     %s\n", alGetString(AL_VENDOR));
    printf("  Extensions: %s\n", alGetString(AL_EXTENSIONS));
}

void CleanupAudio()
{

    /* If OpenAL is initialized, clean up. */
    if (audio_enabled) {
	/* Never try to destroy an active context. */
	alcMakeContextCurrent(NULL);

	alcDestroyContext(audio_context);
	alcCloseDevice(audio_device);
	audio_context = NULL;

	audio_enabled = 0;
    }

}

void UpdateAudio(player_p player, player_p opponent)
{
    ALfloat position[3];
    ALfloat velocity[3];
    ALfloat orientation[6];

    /* Is audio enabled? */
    if (!audio_enabled)
	return;

    /* The player is the listener. Set the listener's position to
       the player's position. */
    position[0] = (ALfloat)player->world_x / DISTANCE_FACTOR;
    position[1] = (ALfloat)player->world_y / DISTANCE_FACTOR;
    position[2] = (ALfloat)0.0;
    alListenerfv(AL_POSITION, position);

    /* Set the player's orientation in space. The first three values are
       the "up" vector (sticking out of the ship's cockpit), and the next
       three are the "at" vector (sticking out of the ship's nose). */
    orientation[0] = 0;
    orientation[1] = 0;
    orientation[2] = 1.0;
    orientation[3] = -cos(player->angle*PI/180.0);
    orientation[4] = sin(player->angle*PI/180.0);
    orientation[5] = 0;
    alListenerfv(AL_ORIENTATION, orientation);

    /* To properly simulate the Doppler effect, OpenAL needs to
       know the listener's velocity (as a vector). */
    velocity[0] = (ALfloat)player->velocity *
	cos(player->angle*PI/180.0) / DISTANCE_FACTOR;
    velocity[1] = (ALfloat)player->velocity *
	-sin(player->angle*PI/180.0) / DISTANCE_FACTOR;
    velocity[2] = (ALfloat)0.0;
    alListenerfv(AL_VELOCITY, velocity);

    /* The player's weapon is obviously at the location of the player.
       This source won't do anything until we add weapons to the game. */
    alSourcefv(player_phaser_source, AL_POSITION, position);
    alSourcefv(player_phaser_source, AL_VELOCITY, velocity);

    /* Now for the enemy's information. */
    position[0] = (ALfloat)opponent->world_x / DISTANCE_FACTOR;
    position[1] = (ALfloat)opponent->world_y / DISTANCE_FACTOR;
    position[2] = (ALfloat)0.0;
    alSourcefv(opponent_engine_source, AL_POSITION, position);
    alSourcefv(opponent_phaser_source, AL_POSITION, position);

    velocity[0] = (ALfloat)opponent->velocity *
	cos(opponent->angle*PI/180.0) / DISTANCE_FACTOR;
    velocity[1] = (ALfloat)opponent->velocity *
	-sin(opponent->angle*PI/180.0) / DISTANCE_FACTOR;
    velocity[2] = (ALfloat)0.0;
    alSourcefv(opponent_engine_source, AL_VELOCITY, velocity);
    alSourcefv(opponent_phaser_source, AL_VELOCITY, velocity);

}

void StartAudio()
{
    /* Activate the opponent's engine noise. We won't attach an engine noise
       to the player, because quite frankly it would be annoying, though perhaps
       a bit more realistic. */
    if (audio_enabled) {
	alSourcei(opponent_engine_source, AL_BUFFER, engine_sound.name); /* assign a buffer */
	alSourcei(opponent_engine_source, AL_LOOPING, 1);   /* enable looping */
	alSourcePlay(opponent_engine_source); /* set it to playback mode */
    }
}

void StopAudio()
{
    /* Stop all sources. */
    if (audio_enabled) {
	alSourceStop(opponent_engine_source);
	alSourceStop(opponent_phaser_source);
	alSourceStop(player_phaser_source);
    }
}
