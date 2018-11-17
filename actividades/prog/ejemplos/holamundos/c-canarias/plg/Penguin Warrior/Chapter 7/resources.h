#ifndef RESOURCES_H
#define RESOURCES_H

#include <SDL/SDL.h>

extern SDL_Surface *ship_strip;		/* rotating ship in 2-degree increments */
extern SDL_Surface *front_star_tiles;	/* for the parallaxing star layer */
extern SDL_Surface *back_star_tiles;	/* for the star background */
extern int num_star_tiles;		/* derived from the width of the loaded strips */

void LoadGameData(void);
/* Loads the game's resources. Exits the program on failure. */

void UnloadGameData(void);
/* Frees all of the game's data. */

#endif
