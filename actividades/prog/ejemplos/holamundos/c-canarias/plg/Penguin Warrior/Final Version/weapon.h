#ifndef WEAPON_H
#define WEAPON_H

#include "gamedefs.h"

/* Phasers have a virtually unlimited range. */
#define PHASER_RANGE (WORLD_WIDTH*2)

void DrawPhaserBeam(player_p source, SDL_Surface *surf, int vis_x, int vis_y);
/* Draws a phaser beam originating from the given ship.
   The screen_x, screen_y, and angle fields in the structure
   must be correct. */

int CheckPhaserHit(player_p source, player_p target);
/* Checks whether a phaser beam originating from the given
   player hits the given target. Requires the same data
   as DrawPhaserBeam. Returns 1 on hit, 0 on miss. */

#endif
