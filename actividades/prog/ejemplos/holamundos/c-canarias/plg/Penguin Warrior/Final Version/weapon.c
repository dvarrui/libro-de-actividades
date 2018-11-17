#include <SDL/SDL.h>
#include <math.h>
#include <stdio.h>
#include <stdlib.h>
#include "weapon.h"

/* Draws a line from (x0,y0) to (x1,y1) on the given SDL surface.
   Assumes that all coordinates are valid. */
static void DrawLine16(SDL_Surface *surf, int x0, int y0, int x1, int y1, 
		       Uint16 color)
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
	    exit(EXIT_FAILURE); // was abort();
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
static int ClipLineAgainstVerticals(int *x0, int *y0, int *x1, int *y1,
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

    /* Final check for validity. */
    if ((a < left) || (c > right))
	return 0;

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
static int ClipLineAgainstHorizontals(int *x0, int *y0, int *x1, int *y1,
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

    /* Final check for validity. */
    if ((b < top) || (d > bottom))
	return 0;

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
static int ClipLineAgainstRectangle(int *x0, int *y0, int *x1, int *y1,
				    int left, int top, int right, int bottom)
{
    if ((ClipLineAgainstHorizontals(x0, y0, x1, y1, top, bottom) == 0) ||
	(ClipLineAgainstVerticals(x0, y0, x1, y1, left, right) == 0))
	return 0;
    return 1;
}


/* Calculates the starting and ending coordinates of a phaser beam fired
   from the given player's position and angle. */
static void CalcPhaserBeamCoords(player_p source, int *x0, int *y0, int *x1, 
				 int *y1)
{
    *x0 = source->world_x;
    *y0 = source->world_y;
    *x1 = (int)((double)PHASER_RANGE * cos(source->angle*PI/180.0)) + 
      source->world_x;
    *y1 = (int)((double)PHASER_RANGE * -sin(source->angle*PI/180.0)) + 
      source->world_y;
}


/* Draws a phaser beam as it would be fired from the given player.
   vis_x and vis_y give the starting coordinates of the 640x480 area
   of the world on the screen. */
void DrawPhaserBeam(player_p source, SDL_Surface *surf, int vis_x, int vis_y)
{
    int x0, y0, x1, y1;

    CalcPhaserBeamCoords(source, &x0, &y0, &x1, &y1);
    x0 -= vis_x;
    y0 -= vis_y;
    x1 -= vis_x;
    y1 -= vis_y;
    if (ClipLineAgainstRectangle(&x0, &y0, &x1, &y1, 0, 0,
				 SCREEN_WIDTH-1, SCREEN_HEIGHT-1) == 0)
	return;
    DrawLine16(surf, x0, y0, x1, y1, 0xFFFF);
}


int CheckPhaserHit(player_p source, player_p target)
{
    float v1x, v1y, v2x, v2y;
    float px, py, dist;
    int x0, y0, x1, y1;

    CalcPhaserBeamCoords(source, &x0, &y0, &x1, &y1);

    v1x = x1-x0;
    v1y = y1-y0;
    v2x = target->world_x - x0;
    v2y = target->world_y - y0;

    /* If the dot product is less than zero, the target is behind
       the source, so there cannot be a hit. */
    if (v1x * v2x + v1y * v2y < 0)
	return 0;

    px = v1x * (v1x * v2x + v1y * v2y) / (v1x * v1x + v1y * v1y);
    py = v1y * (v1x * v2x + v1y * v2y) / (v1x * v1x + v1y * v1y);

    dist = sqrt((v2x-px)*(v2x-px)+(v2y-py)*(v2y-py));

    if (dist < 50)
	return 1;

    return 0;
}
