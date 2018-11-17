#include <stdio.h>
#include <stdlib.h>
#include <sndfile.h>
#include "gamedefs.h"
#include "resources.h"
#include "audio.h"

SDL_Surface *ship_strip;	/* rotating ship in 2-degree increments */
SDL_Surface *front_star_tiles;	/* for the parallaxing star layer */
SDL_Surface *back_star_tiles;	/* for the star background */
int num_star_tiles;		/* derived from the width of the loaded 
				   strips */

sound_t engine_sound;           /* sound resources */
sound_t phaser_sound;

int LoadSoundFile(char *filename, sound_p sound);

void LoadGameData(void)
{
    SDL_Surface *tmp;
	
    /* The player's ship is stored as a 8640x96 image.
       This strip contains 90 individual images of the ship, rotated in
       four-degree increments. Take a look at fighter.bmp in an image
       viewer to see exactly what this means. */
    tmp = SDL_LoadBMP("fighter.bmp");
    if (tmp == NULL) {
	fprintf(stderr, "Unable to load ship image: %s\n", SDL_GetError());
	exit(EXIT_FAILURE);
    }
	
    /* Set the color key on the ship animation strip to black.
       Enable RLE acceleration for a performance boost. */
    SDL_SetColorKey(tmp, SDL_SRCCOLORKEY|SDL_RLEACCEL, 0);
	
    /* Optimize the entire strip for fast display. */
    ship_strip = SDL_DisplayFormat(tmp);
    if (ship_strip == NULL) {
	/* We were unable to convert the image (for some foul reason),
	   but we can still use the copy we already have. This will
	   involve a performance loss. However, this should never happen. */
	ship_strip = tmp;
    } else SDL_FreeSurface(tmp);
	
    /* Now load the star tiles. Each tile is 64x64, assembled into a strip.
       We'll derive the number of tiles from the width of the loaded bitmap. */
    tmp = SDL_LoadBMP("back-stars.bmp");
    if (tmp == NULL) {
	fprintf(stderr, "Unable to load background tiles: %s\n", 
		SDL_GetError());
	exit(EXIT_FAILURE);
    }
	
    /* Determine how many star tiles are in the strip. We'll assume that the
       foreground and background strips contain the same number of stars. */
    num_star_tiles = tmp->w / 64;
    num_star_tiles = 4;
	
    /* Attempt to optimize this strip for fast blitting. */
    back_star_tiles = SDL_DisplayFormat(tmp);
    if (back_star_tiles == NULL)
	back_star_tiles = tmp;
    else	SDL_FreeSurface(tmp);	

    /* Load the front (parallaxing) set of star tiles. */
    tmp = SDL_LoadBMP("front-stars.bmp");
    if (tmp == NULL) {
	printf("Unable to load parallax tiles: %s\n", SDL_GetError());
	exit(EXIT_FAILURE);
    }
	
    /* Set a black color key and request RLE acceleration. */
    SDL_SetColorKey(tmp, SDL_SRCCOLORKEY | SDL_RLEACCEL, 0);

    /* Attempt to optimize this strip for fast blitting. */
    front_star_tiles = SDL_DisplayFormat(tmp);
    if (front_star_tiles == NULL)
	front_star_tiles = tmp;
    else	SDL_FreeSurface(tmp);	

    /* Load sound data. */
    if (audio_enabled) {
	if (LoadSoundFile("engine.wav", &engine_sound) != 0) {
	    printf("Unable to load engine.wav.\n");
	    exit(EXIT_FAILURE);
	}
		
	if (LoadSoundFile("phaser.wav", &phaser_sound) != 0) {
	    printf("Unable to load phaser.wav.\n");
	    exit(EXIT_FAILURE);
	}
    }
}

void UnloadGameData(void)
{
    if (ship_strip != NULL) {
	SDL_FreeSurface(ship_strip);
	ship_strip = NULL;
    }
    if (front_star_tiles != NULL) {
	SDL_FreeSurface(front_star_tiles);
	front_star_tiles = NULL;
    }
    if (back_star_tiles != NULL) {
	SDL_FreeSurface(back_star_tiles);
	back_star_tiles = NULL;
    }
}

/* PFJ - small routine to remove silly goto's */
static void loadsound_cleanup(SNDFILE *file, short *sbuff, u_int8_t *buff8)
{
  sf_close(file);
  free(sbuff);
  free(buff8);
}

/* Loads a sound file into a sound structure and sends the data to OpenAL.
   This code is mostly derived from libsndfile example code in Chapter 5. */
// LOTS of changes here in this code
int LoadSoundFile(char *filename, sound_p sound)
{
    SNDFILE *file;
    SF_INFO file_info;
    short *buffer_short = NULL;
    u_int8_t *buffer_8 = NULL;
    int16_t *buffer_16 = NULL;
    unsigned int i;
    int pcmbitwidth;

    /* Open the file and retrieve sample information. */
    file = sf_open(filename, SFM_READ , &file_info);
    if (file == NULL) {
	printf("Unable to open '%s'.\n", filename);
	return -1;
    }
	
    // <big snip of old code>
    pcmbitwidth = 0;

    if ((file_info.format & SF_FORMAT_SUBMASK) != SF_FORMAT_PCM_16)
      {
	printf("'%s' is not a PCM based audio file\n", filename);
	sf_close(file);
	return -1;
      }

    if (file_info.channels < 1 || file_info.channels > 2)
      {
	printf("'%s' bad channel count\n", filename);
	sf_close(file);
	return -1;
      }

    switch ((file_info.format & SF_FORMAT_SUBMASK) + 
	    (file_info.channels << 16))
      {
      case (SF_FORMAT_PCM_U8 + (1 << 16)):
	sound->format = AL_FORMAT_MONO8;
	pcmbitwidth = 8;
	break;
      case (SF_FORMAT_PCM_U8 + (2 << 16)):
	sound->format = AL_FORMAT_STEREO8;
	pcmbitwidth = 8;
	break;
      case (SF_FORMAT_PCM_16 + (1 << 16)):
	sound->format = AL_FORMAT_MONO16;
	pcmbitwidth = 16;
	break;
      case (SF_FORMAT_PCM_16 + (2 << 16)):
	sound->format = AL_FORMAT_STEREO16;
	pcmbitwidth = 16;
	break;
      default:
	printf("Unknown sample format in '%s'\n", filename);
	sf_close(file);
	return -1;
      }


    /* Allocate buffers. */
    buffer_short = (short *)malloc(file_info.frames *
				   file_info.channels * 
				   sizeof (short));

    buffer_8 = (u_int8_t *)malloc(file_info.frames *
				  file_info.channels *
				  pcmbitwidth / 8);

    buffer_16 = (int16_t *)buffer_8;

    if (buffer_short == NULL || buffer_8 == NULL) {
	printf("Unable to allocate enough memory for '%s'.\n", filename);
	loadsound_cleanup(file, buffer_short, buffer_8);
	return -1;
    }

    /* Read the entire sound file. */
    if (sf_readf_short(file,buffer_short,file_info.frames) < 0) {
	printf("Error while reading samples from '%s'.\n", filename);
	loadsound_cleanup(file, buffer_short, buffer_8);
	return -1;
    }
	
    /* Convert the data to the correct format. */
    for (i = 0; i < file_info.frames * file_info.channels; i++) {
	if (pcmbitwidth == 8) {
	    /* Convert the sample from a signed short to an unsigned byte */
	    buffer_8[i] = (u_int8_t)((short)buffer_short[i] + 128);
	} else {
	    buffer_16[i] = (int16_t)buffer_short[i];
	}
    }
	
    /* Fill in the sound data structure. */
    sound->freq = file_info.samplerate;
    sound->size = file_info.frames * file_info.channels * pcmbitwidth / 8;

    /* Give our sound data to OpenAL. */
    alGenBuffers(1, &sound->name);
    if (alGetError() != AL_NO_ERROR) {
	printf("Error creating an AL buffer name for %s.\n", filename);
	loadsound_cleanup(file, buffer_short, buffer_8);
	return -1;
    }

    alBufferData(sound->name, sound->format, buffer_8, sound->size, 
		 sound->freq);
    if (alGetError() != AL_NO_ERROR) {
	printf("Error sending buffer data to OpenAL for %s.\n", filename);
	loadsound_cleanup(file, buffer_short, buffer_8);
	return -1;
    }
	
    /* Close the file and return success. */
    sf_close(file);
    free(buffer_short);
    free(buffer_8);

    return 0;

}
