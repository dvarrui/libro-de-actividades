#ifndef MUSIC_H
#define MUSIC_H

#include "gamedefs.h"

/* 1 if the music system is enabled. */
extern int music_enabled;

/* Initializes the music system. Be sure to call InitAudio first. */
void InitMusic(void);

/* Shuts down the music system. */
void CleanupMusic(void);

/* Loads a music file for the player. Returns 0 on success, -1 on failure. */
int LoadMusic(char *filename);

/* Starts music playback if music is enabled and a file is loaded. */
void StartMusic(void);

/* Stops music playback. */
void StopMusic(void);

/* Keeps the music system fed with data. Call this frequently. */
void UpdateMusic(void);

#endif
