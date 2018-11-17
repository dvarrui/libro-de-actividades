#ifndef GAMEDEFS_H
#define GAMEDEFS_H

/* Dimensions of the game window. */
#define SCREEN_WIDTH	640
#define SCREEN_HEIGHT	480

/* Number of particles allowed in the particle system. */
#define MAX_PARTICLES	10000

/* Dimensions of the player's ship. The graphics data must correspond to this. */
#define PLAYER_WIDTH	96
#define PLAYER_HEIGHT	96

/* Dimensions of the map tiles. */
#define TILE_WIDTH	64
#define TILE_HEIGHT	64

/* Total size (in pixels) of the complete playfield. */
#define WORLD_WIDTH	2000
#define WORLD_HEIGHT	2000

/* Limits on the player. */
#define PLAYER_MAX_VELOCITY	(15.0)
#define PLAYER_MIN_VELOCITY	(-5.0)

#define PLAYER_FORWARD_THRUST	(3)
#define PLAYER_REVERSE_THRUST	(-2)

/* These define the scrolling speeds of the front and back background
   layers, relative to the movement of the camera. */
#define PARALLAX_BACK_FACTOR	4
#define PARALLAX_FRONT_FACTOR	2

/* These define the sizes of the backgrond tile grids. We don't really need a one to one
   mapping between the size of the playing field and the size of the tile grids;
   we can wrap around at some point, and nobody will notice a difference. */
#define PARALLAX_GRID_WIDTH     100
#define PARALLAX_GRID_HEIGHT    100

#define PI (3.141592654F)

/* Our time scaling factor. This is defined in main.c, but
   it needs to be accessible to the other game modules. */
extern double time_scale;

#endif
