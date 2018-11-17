/* Example of line clipping. */

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

/* Clips the line from (x0,y0) to (x1,y1) against the given left
   and right columns. Returns 1 if the line is at least partially
   between the two columns, or 0 if it is completely out of range. */
int ClipLineAgainstVerticals(int *x0, int *y0, int *x1, int *y1,
			     int left, int right)
{
    int a, b, c, d;
    int hspan, vspan;

    /* Handle completely vertical lines. */
    if (*x0 == *x1) {
	if (*x0 < left || *x0 > right)
	    return 0;
	return 1;
    }

    /* If both x coordinates are out of range, the line
       is completely invisible. Return 0 to indicate this. */
    if (((*x0 < left) && (*x1 < left)) ||
	((*x0 > right) && (*x1 > right)))
	return 0;

    /* Set (a,b) to the leftmost coordinate and (c,d)
       to the rightmost. This will simplify the rest of
       the routine. */
    if (*x0 < *x1) {
	a = *x0;
	b = *y0;
	c = *x1;
	d = *y1;
    } else {
	a = *x1;
	b = *y1;
	c = *x0;
	d = *y0;
    }

    /* Does the line straddle the left vertical? */
    if ((a < left) && (c >= left)) {
	hspan = c-a;
	vspan = d-b;
	a = left;
	b = d - (vspan * (c-left)) / hspan;
    }

    /* Does the line straddle the right vertical? */
    if ((a < right) && (c >= right)) {
	hspan = c-a;
	vspan = d-b;
	d = d - (vspan * (c-right)) / hspan;
	c = right;
    }

    /* Pass the clipped coordinates back to the caller. */
    *x0 = a;
    *y0 = b;
    *x1 = c;
    *y1 = d;

    /* Successful clip. */
    return 1;
}

/* Clips the line from (x0,y0) to (x1,y1) against the given top
   and bottom lines. Returns 1 if the line is at least partially
   between the two lines, or 0 if it is completely out of range. */
int ClipLineAgainstHorizontals(int *x0, int *y0, int *x1, int *y1,
			       int top, int bottom)
{
    int a, b, c, d;
    int hspan, vspan;

    /* Handle completely horizontal lines. */
    if (*y0 == *y1) {
	if (*y0 < top || *y0 > bottom)
	    return 0;
	return 1;
    }

    /* If both y coordinates are out of range, the line
       is completely invisible. Return 0 to indicate this. */
    if (((*y0 < top) && (*y1 < top)) ||
	((*y0 > bottom) && (*y1 > bottom)))
	return 0;

    /* Set (a,b) to the topmost coordinate and (c,d)
       to the bottommost. This will simplify the rest of
       the routine. */
    if (*y0 < *y1) {
	a = *x0;
	b = *y0;
	c = *x1;
	d = *y1;
    } else {
	a = *x1;
	b = *y1;
	c = *x0;
	d = *y0;
    }

    /* Does the line straddle the top line? */
    if ((b < top) && (d >= top)) {
	hspan = c-a;
	vspan = d-b;
	b = top;
	a = c - (hspan * (d-top)) / vspan;
    }

    /* Does the line straddle the bottom line? */
    if ((b < bottom) && (d >= bottom)) {
	hspan = c-a;
	vspan = d-b;
	c = c - (hspan * (d-bottom)) / vspan;
	d = bottom;
    }

    /* Pass the clipped coordinates back to the caller. */
    *x0 = a;
    *y0 = b;
    *x1 = c;
    *y1 = d;

    /* Successful clip. */
    return 1;
}

/* Clips the line from (x0,y0) to (x1,y1) against the rectangle from
   (left,top) to (right,bottom). Returns 1 if the line is visible,
   0 if not. */
int ClipLineAgainstRectangle(int *x0, int *y0, int *x1, int *y1,
			     int left, int top, int right, int bottom)
{
    if ((ClipLineAgainstHorizontals(x0, y0, x1, y1, top, bottom) == 0) ||
	(ClipLineAgainstVerticals(x0, y0, x1, y1, left, right) == 0))
	return 0;
    return 1;
}

int main()
{
    SDL_Surface *screen;
    int x0, y0, x1, y1;

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
	
    /* Clip a line against the screen and draw it. */
    x0 = -100;
    y0 = 100;
    x1 = 739;
    y1 = 800;

    if (ClipLineAgainstRectangle(&x0, &y0, &x1, &y1, 0, 0, 639, 479) < 0) {
	printf("Line is out of range.\n");
    }

    DrawLine16(screen, x0, y0, x1, y1, 0xFFFF);
    SDL_UpdateRect(screen, 0, 0, 0, 0);
	
    /* Pause. */
    SDL_Delay(5000);

    return 0;
}
