#ifndef STATUS_H
#define STATUS_H

#include <SDL/SDL.h>
#include "gamedefs.h"

int InitStatusDisplay(void);
/* Initializes the status display system.
   Returns 0 on success, -1 on failure.
   Must be called after setting a video mode. */

void CleanupStatusDisplay(void);
/* Shuts down the status display system. */

void SetStatusMessage(const char *msg);
/* Sets the scrolling status message at the top
   of the display. The message will scroll by once,
   then disappear. */

void SetPlayerStatusInfo(int score, int shields, int charge);
void SetOpponentStatusInfo(int score, int shields);
/* Sets the player or opponent's on-screen status
   information. */

void UpdateStatusDisplay(SDL_Surface *screen);
/* Updates and redraws the status display. */

#endif
