/* Audio mixing with SDL */

#include <SDL/SDL.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h> // missing from the book version
// assert.h removed - it's not used!

// Structure for loaded sounds
typedef struct sound_s {
  Uint8 *samples;  // raw PCM sample data
  Uint32 length;   // size of sound data in bytes
} sound_t, *sound_p;

// Structure for a currently playing sound
typedef struct playing_s {
  int active;       // 1 if this sound should be played
  sound_p sound;    // sound data to play
  Uint32 position;  // current position in the sound buffer
} playing_t, *playing_p;

// Array for all active sound effects
#define MAX_PLAYING_SOUNDS    10
playing_t playing[MAX_PLAYING_SOUNDS];

/* The higher this is, the louder each currently playing sound will be. 
   However, high values may cause distortion if too many sounds are playing.
   Experiment with this value */

#define VOLUME_PER_SOUND   SDL_MIX_MAXVOLUME / 2

/* This function is called by the SDL whenever the soundcard needs more 
   samples to play. It might be called from a separate thread, so we should 
   be careful what we touch. */

void AudioCallback(void *user_data, Uint8 *audio, int length)
{
  int i;
  
  // clear the audio buffer so we can mix samples into it
  memset(audio, 0, length);

  // mix in each sound
  for (i = 0; i < MAX_PLAYING_SOUNDS; ++i)
    {
      if (playing[i].active)
	{
	  Uint8 *sound_buf;
	  Uint32 sound_len;

	  // Locate this sound's current buffer position
	  sound_buf = playing[i].sound->samples;
	  sound_buf += playing[i].position;

	  // Determine the number of samples to mix
	  if ((playing[i].position + length) > playing[i].sound->length)
	      sound_len = playing[i].sound->length - playing[i].position;
	  else
	    sound_len = length;
	
	  // Mix this sound into the stream
	  SDL_MixAudio(audio, sound_buf, sound_len, VOLUME_PER_SOUND);

	  // Update the sound buffer's position
	  playing[i].position += length;

	  // Have we reached the end of the sound?
	  if (playing[i].position >= playing[i].sound->length)
	    playing[i].active = 0; // mark as inactive
	}
    }
}

/* This function loads a sound with SDL_LoadWAV and converts it to the 
   specified sample format. Returns 0 on success and 1 on failure */

int LoadAndConvertSound(char *filename, SDL_AudioSpec *spec, 
			sound_p sound)
{
  SDL_AudioCVT cvt;     // format conversion structure
  SDL_AudioSpec loaded; // format of loaded data
  Uint8 *new_buf;

  // Load the WAV file in it's original format
  if (SDL_LoadWAV(filename, &loaded, &sound->samples, &sound->length) == NULL)
    {
      printf("Unable to load sound: %s\n", SDL_GetError());
      return 1;
    }

  /* Build a conversion structure for converting the samples. This structure 
     contains the data SDL needs to quickly convert between sample formats */

  if (SDL_BuildAudioCVT(&cvt, loaded.format, loaded.channels, loaded.freq,
			spec->format, spec->channels, spec->freq) < 0)
    {
      printf("Unable to convert sound: %s\n", SDL_GetError());
      return 1;
    }

  /* Since converting PCM samples can result in more data (for instance,
     converting 8-bit mono to 16-bit stereo), we need to allocate a new 
     buffer for the converted data. Fortunately, SDL_BuildAudioCVT 
     supplied the necessary information */

  cvt.len = sound->length;
  new_buf = (Uint8 *) malloc(cvt.len * cvt.len_mult);
  if (new_buf == NULL)
    {
      printf("Memory allocation for conversion failed\n");
      SDL_FreeWAV(sound->samples);
      return 1;
    }

  // Copy the sound samples into the new buffer

  memcpy(new_buf, sound->samples, sound->length);

  // Perform the conversion on the new buffer
  cvt.buf = new_buf;
  if (SDL_ConvertAudio(&cvt) < 0)
    {
      printf("Audio conversion error: %s\n", SDL_GetError());
      free(new_buf);
      SDL_FreeWAV(sound->samples);
      return 1;
    }

  // Swap the converted data for the original
  SDL_FreeWAV(sound->samples);
  sound->samples = new_buf;
  sound->length = sound->length * cvt.len_mult;

  // Success!

  printf("%s was loaded and converted successfully\n", filename);

  return 0;
}

// Removes all currently playing sounds

void ClearPlayingSounds(void)
{
  int i;
  for (i = 0; i < MAX_PLAYING_SOUNDS; ++i)
    playing[i].active = 0;
}

/* Adds a sound to the list of currently playing sounds. AudioCallback will
   start mixing this sound into the stream the next time it is called
   (probably in a fraction of a second) */

int PlaySound(sound_p sound)
{
  int i;

  // Find an empty slot for this sound
  for (i = 0; i < MAX_PLAYING_SOUNDS; ++i)
    {
      if (playing[i].active == 0)
	break;
    }

  // Report failure if there are no slots
  if (i == MAX_PLAYING_SOUNDS)
    return 1;

  /* The 'playing' structures are accessed by the audio callback, so we should 
     obtain a lock before we access them */

  SDL_LockAudio();
  playing[i].active = 1;
  playing[i].sound = sound;
  playing[i].position = 0;
  SDL_UnlockAudio();
  return 0;
}

int main()
{
  SDL_Surface *screen;
  SDL_Event event;
  int quit_flag = 0; // set this when it's exit time

  // Audio format specs
  SDL_AudioSpec desired, obtained;

  // Our loaded sounds and their formats
  sound_t cannon, explosion;

  // Initialise SDL Video and Audio subsystem. Video is required for events
  if (SDL_Init(SDL_INIT_VIDEO | SDL_INIT_AUDIO) != 0)
    {
      printf("Unable to initialise SDL: %s\n", SDL_GetError());
      exit(EXIT_FAILURE);
      // PFJ - Changed from return 1;
    }

  // Ensure SDL_Quit is called upon program termination
  atexit(SDL_Quit);

  /* We also need to call this before we exit. SDL_Quit does not properly 
     close the audio device for us */
  atexit(SDL_CloseAudio);

  // Attempt to create a 256x256 hicolor video mode
  screen = SDL_SetVideoMode(256, 256, 16, 0);
  if (screen == NULL)
    {
      printf("Unable to create video mode: %s\n", SDL_GetError());
      exit(EXIT_FAILURE);
      // PFJ - Changed from return 1;
    }

  /* Open the audio device. The sound driver will try to give us the requested 
     format, but it might not succeed. The 'obtained' structure will be 
     filled in with the actual format data */

  desired.freq = 44100;       // desired output sample rate
  desired.format = AUDIO_S16; // request signed 16 bit samples
  desired.samples = 4096;     // this is somewhat arbitary
  desired.channels = 2;       // ask for stereo
  desired.callback = AudioCallback;
  desired.userdata = NULL;    // we don't need this

  if (SDL_OpenAudio(&desired, &obtained) < 0)
    {
      printf("Unable to open audio device: %s\n", SDL_GetError());
      exit(EXIT_FAILURE);
      // PFJ - Changed from return 1;
    }

  // Load out sound files and convert them to the sound cards format

  if (LoadAndConvertSound("cannon.wav", &obtained, &cannon) != 0)
    {
      printf("Unable to load cannon.wav\n");
      exit(EXIT_FAILURE);
      // PFJ - Changed from return 1;
    }

  if (LoadAndConvertSound("explosion.wav", &obtained, &explosion) != 0)
    {
      printf("Unable to load explosion.wav\n");
      exit(EXIT_FAILURE);
      // PFJ - Changed from return 1;
    }

  // Clear the list of sounds playing
  ClearPlayingSounds();

  // SDL's audio is initially paused, start it.
  SDL_PauseAudio(0);

  printf("Press 'Q' to quit. C and E play sounds\n");

  /* Start the event loop. Keep reading events until there is an 
     event error or the quit flag is set */
  while(SDL_WaitEvent(&event) != 0 && quit_flag == 0)
    {
      SDL_keysym keysym;
      switch (event.type)
	{
	case SDL_KEYDOWN:
	  keysym = event.key.keysym;

	  // Is it Q?
	  if (keysym.sym == SDLK_q)
	    {
	      printf("Q pressed. Exiting\n");
	      quit_flag = 1;
	    }

	  // C fires a cannon shot
	  if (keysym.sym == SDLK_c)
	    {
	      printf("Firing cannon!\n");
	      PlaySound(&cannon);
	    }

	  // E plays the explosion
	  if (keysym.sym == SDLK_e)
	    {
	      printf("Kaboooom!\n");
	      PlaySound(&explosion);
	    }

	  break;

	case SDL_QUIT:
	  printf("Quit Event. Bye!\n");
	  quit_flag = 1;
	}
    }

  // pause and lock the sound system so we can safely delete our sound data
  SDL_PauseAudio(1);
  SDL_LockAudio();

  // Free our sounds before we exit, just to be safe
  free(cannon.samples);
  free(explosion.samples);

  /* At this point the output is paused and we know for certain that the 
     callback is not active, so we can safely unlock the audio system */
  SDL_UnlockAudio();
}

