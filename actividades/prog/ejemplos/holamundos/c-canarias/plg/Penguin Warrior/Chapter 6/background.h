#ifndef BACKGROUND_H
#define BACKGROUND_H

#include <SDL/SDL.h>

void InitBackground(void);
/* Initializes the background drawing system. */

void DrawBackground(SDL_Surface *dest, int camera_x, int camera_y);
/* Draws the background on the screen, with respect to the global
   "camera" position. The camera marks the 640x480 section of the
   world that we can see at any given time. This is usually in the
   vicinity of the player's ship. */

void DrawParallax(SDL_Surface *dest, int camera_x, int camera_y);
/* Same as above, but draws the upper (parallaxing) layer of the background. */

#endif
