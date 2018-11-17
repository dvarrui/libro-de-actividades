#include <math.h>
#include <stdlib.h>
#include "gamedefs.h"
#include "particle.h"

particle_t particles[MAX_PARTICLES];
int active_particles = 0;

static void AddParticle(particle_p particle);
static void DeleteParticle(int index);
static Uint16 CreateHicolorPixel(SDL_PixelFormat *fmt, Uint8 red, Uint8 green, Uint8 blue);

static void AddParticle(particle_p particle)
{
    /* If there are already too many particles, forget it. */
    if (active_particles >= MAX_PARTICLES) return;
	
    particles[active_particles] = *particle;
    active_particles++;
}

/* Removes a particle from the system (by index). */
static void DeleteParticle(int index)
{
    /* Replace the particle with the one at the end
       of the list, and shorten the list. */
    particles[index] = particles[active_particles-1];
    active_particles--;
}

/* Draws all active particles on the screen. */
void DrawParticles(SDL_Surface *dest, int camera_x, int camera_y)
{
    int i;
    Uint16 *pixels;
	
    /* Lock the target surface for direct access. */
    if (SDL_LockSurface(dest) != 0) return;
    pixels = (Uint16 *)dest->pixels;
	
    for (i = 0; i < active_particles; i++) {
	int x,y;
	Uint16 color;
		
	/* Convert world coords to screen coords. */
	x = particles[i].x - camera_x;
	y = particles[i].y - camera_y;
	if ((x < 0) || (x >= SCREEN_WIDTH)) continue;
	if ((y < 0) || (y >= SCREEN_HEIGHT)) continue;

	/* Find the color of this particle. */
	color = CreateHicolorPixel(dest->format,
				   particles[i].r,
				   particles[i].g,
				   particles[i].b);

	/* Draw the particle. */		
	pixels[(dest->pitch/2)*y+x] = color;
    }
	
    /* Release the screen. */
    SDL_UnlockSurface(dest);
}

/* Updates the position of each particle. Kills particles with
   zero energy. */
void UpdateParticles(void)
{
    int i;
	
    for (i = 0; i < active_particles; i++) {
	particles[i].x += particles[i].energy *
	    cos(particles[i].angle*PI/180.0) * time_scale;
	particles[i].y += particles[i].energy *
	    -sin(particles[i].angle*PI/180.0) * time_scale;
				 		
	/* Fade the particle's color. */
	particles[i].r--;
	particles[i].g--;
	particles[i].b--;
	if (particles[i].r < 0) particles[i].r = 0;
	if (particles[i].g < 0) particles[i].g = 0;
	if (particles[i].b < 0) particles[i].b = 0;

	/* If the particle has faded to black, delete it. */
	if ((particles[i].r + particles[i].g + particles[i].b) == 0) {
	    DeleteParticle(i);
			
	    /* DeleteParticle replaces the current particle with the one
	       at the end of the list, so we'll need to take a step back. */
	    i--;
	}
    }
}

/* Creates a particle explosion of the given relative size and position. */
void CreateParticleExplosion(int x, int y, int r, int g, int b, int energy, int density)
{
    int i;
    particle_t particle;
	
    /* Create a number of particles proportional to the size of the explosion. */
    for (i = 0; i < density; i++) {
		
	particle.x = x;
	particle.y = y;
	particle.angle = rand() % 360;
	particle.energy = (double)(rand() % (energy*1000)) / 1000.0;
		
	/* Set the particle's color. */
	particle.r = r;
	particle.g = g;
	particle.b = b;

	/* Add the particle to the particle system. */
	AddParticle(&particle);
    }
}

/* This is directly from another code listing. It creates a 16-bit pixel. */
static Uint16 CreateHicolorPixel(SDL_PixelFormat *fmt, Uint8 red, Uint8 green, Uint8 blue)
{
    Uint16 value;
	
    /* This series of bit shifts uses the information from the SDL_Format
       structure to correctly compose a 16-bit pixel value from 8-bit red,
       green, and blue data. */
    value = (((red >> fmt->Rloss) << fmt->Rshift) +
	     ((green >> fmt->Gloss) << fmt->Gshift) +
	     ((blue >> fmt->Bloss) << fmt->Bshift));

    return value;
}

