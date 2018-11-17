#ifndef AUDIO_H
#define AUDIO_H

#include "gamedefs.h"

/* 1 if OpenAL is initialized, 0 if not. If 0, do not try to create buffers
   or anything else that would require an AL context. */
extern int audio_enabled;

/* Initializes the audio system. */
void InitAudio(void);

/* Shuts down the audio system. Do NOT try to delete buffers after calling
   this; do so beforehand. Otherwise, there won't be an active AL context,
   and something will go "boom". */
void CleanupAudio(void);

/* Updates the positions of all audio sources. Call this each frame. */
void UpdateAudio(player_p player, player_p opponent);

/* Starts playback on all sources. Call this once before the game loop. */
void StartAudio(void);

/* Stops playback on all sources. Call this after exiting the game loop. */
void StopAudio(void);

/* Starts the phaser firing sound for the player or opponent. */
void StartPlayerPhaserSound(void);
void StartOpponentPhaserSound(void);

#endif
