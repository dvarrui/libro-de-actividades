/* Example of Bresenham line drawing with SDL. */

#include <SDL/SDL.h>
#include <stdlib.h>
#include <stdio.h>

/* Draws a line of the given color on surf from (x0, y0) to (x1, y1).
   Does not perform clipping against the edges of the surface.
   Uses the Bresenham line drawing algorithm.

   This version is slightly more optimized than the one described in
   the text, but the algorithm is the same. */
void DrawLine16(SDL_Surface *surf, int x0, int y0, int x1, int y1, Uint16 color)
{
    Uint16 *buffer;
    int drawpos;
    int xspan, yspan;
    int xinc, yinc;
    int sum;
    int i;

    /* If we need to lock this surface before drawing pixels, do so. */
    if (SDL_MUSTLOCK(surf)) {
	if (SDL_LockSurface(surf) < 0) {
	    printf("Error locking surface: %s\n", SDL_GetError());
	    abort();
	}
    }

    /* Get the surface's data pointer. */
    buffer = (Uint16 *)surf->pixels;
	
    /* Calculate the x and y spans of the line. */
    xspan = x1-x0+1;
    yspan = y1-y0+1;
	
    /* Figure out the correct increment for the major axis.
       Account for negative spans (x1 < x0, for instance). */
    if (xspan < 0) {
	xinc = -1;
	xspan = -xspan;
    } else xinc = 1;

    if (yspan < 0) {
	yinc = -surf->pitch/2;
	yspan = -yspan;
    } else yinc = surf->pitch/2;
	
    i = 0;
    sum = 0;
	
    /* This is our current offset into the buffer. We use this
       variable so that we don't have to calculate the offset at
       each step; we simply increment this by the correct amount.

       Instead of adding 1 to the x coordinate, we add one to drawpos.
       Instead of adding 1 to the y coordinate, we add the surface's
       pitch (scanline width) to drawpos. */
    drawpos = surf->pitch/2 * y0 + x0;
	
    /* Our loop will be different depending on the
       major axis. */
    if (xspan < yspan) {

	/* Loop through each pixel along the major axis. */
	for (i = 0; i < yspan; i++) {

	    /* Draw the pixel. */
	    buffer[drawpos] = color;

	    /* Update the incremental division. */
	    sum += xspan;

	    /* If we've reached the dividend, advance
	       and reset. */
	    if (sum >= yspan) {
		drawpos += xinc;
		sum -= yspan;
	    }

	    /* Increment the drawing position. */
	    drawpos += yinc;
	}

    } else {

	/* See comments above. This code is equivalent. */
	for (i = 0; i < xspan; i++) {

	    buffer[drawpos] = color;
			
	    sum += yspan;

	    if (sum >= xspan) {
		drawpos += yinc;
		sum -= xspan;
	    }

	    drawpos += xinc;
	}
    }

    /* Unlock the surface. */
    SDL_UnlockSurface(surf);
}

int main()
{
    SDL_Surface *screen;

    /* Fire up SDL. */
    if (SDL_Init(SDL_INIT_VIDEO) < 0) {
	printf("Error initializing SDL: %s\n", SDL_GetError());
	return 1;
    }
    atexit(SDL_Quit);
	
    /* Set a 640x480 video mode. Force 16-bit hicolor. */
    if ((screen = SDL_SetVideoMode(640, 480, 16, 0)) == NULL) {
	printf("Error setting a 640x480, 16-bit video mode: %s\n",
	       SDL_GetError());
	return 1;
    }
	
    /* Draw a diagonal line across the screen. */
    DrawLine16(screen, 0, 0, 639, 479, 0xFFFF);
    SDL_UpdateRect(screen, 0, 0, 0, 0);
	
    /* Pause. */
    SDL_Delay(5000);
	
    return 0;
}
