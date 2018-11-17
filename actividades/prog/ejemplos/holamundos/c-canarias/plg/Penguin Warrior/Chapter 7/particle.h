#ifndef PARTICLE_H
#define PARTICLE_H

#include <SDL/SDL.h>

/* Particle data type. */
typedef struct particle_s {
	double x,y;		/* coordinates of the particle */
	double energy; 		/* velocity of the particle */
	double angle;		/* angle of the particle */
	int r, g, b;		/* color */
} particle_t, *particle_p;

void DrawParticles(SDL_Surface *dest, int camera_x, int camera_y);
/* Draws all active and visible particles to the given surface. */

void UpdateParticles(void);
/* Updates the position of each particle in the system, and removes particles
   that have lost their energy. This should typically be called once per frame. */

void CreateParticleExplosion(int x, int y, int r, int g, int b, int energy, int density);
/* Creates a particle explosion at the given (x,y) position in the world.
   Sets each particle to the given (r,g,b) color and assigns each a random
   energy less than the given value. Creates a number of particles
   proportional to the given density factor. */

#endif
