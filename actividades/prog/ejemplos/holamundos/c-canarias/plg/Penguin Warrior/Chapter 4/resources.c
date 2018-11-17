#include <stdio.h>
#include <stdlib.h>
#include "gamedefs.h"
#include "resources.h"

SDL_Surface *ship_strip;	/* rotating ship in 2-degree increments */
SDL_Surface *front_star_tiles;	/* for the parallaxing star layer */
SDL_Surface *back_star_tiles;	/* for the star background */
int num_star_tiles;		/* derived from the width of the loaded strips */

void LoadGameData(void)
{
    SDL_Surface *tmp;
	
    /* The player's ship is stored as a 8640x96 image.
       This strip contains 90 individual images of the ship, rotated in
       four-degree increments. Take a look at fighter.bmp in an image
       viewer to see exactly what this means. */
    tmp = SDL_LoadBMP("fighter.bmp");
    if (tmp == NULL) {
	fprintf(stderr, "Unable to load ship image: %s\n", SDL_GetError());
	exit(EXIT_FAILURE); // PFJ - changed for portable exit
    }
	
    /* Set the color key on the ship animation strip to black.
       Enable RLE acceleration for a performance boost. */
    SDL_SetColorKey(tmp, SDL_SRCCOLORKEY|SDL_RLEACCEL, 0);
	
    /* Optimize the entire strip for fast display. */
    ship_strip = SDL_DisplayFormat(tmp);
    if (ship_strip == NULL) {
	/* We were unable to convert the image (for some foul reason),
	   but we can still use the copy we already have. This will
	   involve a performance loss. However, this should never happen. */
	ship_strip = tmp;
    } else SDL_FreeSurface(tmp);
	
    /* Now load the star tiles. Each tile is 64x64, assembled into a strip.
       We'll derive the number of tiles from the width of the loaded bitmap. */
    tmp = SDL_LoadBMP("back-stars.bmp");
    if (tmp == NULL) {
	fprintf(stderr, "Unable to load background tiles: %s\n", 
		SDL_GetError());
	exit(EXIT_FAILURE); // PFJ - changed for portable exit
    }
	
    /* Determine how many star tiles are in the strip. We'll assume that the
       foreground and background strips contain the same number of stars. */
    num_star_tiles = tmp->w / 64;
    num_star_tiles = 4;
	
    /* Attempt to optimize this strip for fast blitting. */
    back_star_tiles = SDL_DisplayFormat(tmp);
    if (back_star_tiles == NULL)
	back_star_tiles = tmp;
    else	SDL_FreeSurface(tmp);	

    /* Load the front (parallaxing) set of star tiles. */
    tmp = SDL_LoadBMP("front-stars.bmp");
    if (tmp == NULL) {
	printf("Unable to load parallax tiles: %s\n", SDL_GetError());
	exit(EXIT_FAILURE); // PFJ - changed for portable exit
    }
	
    /* Set a black color key and request RLE acceleration. */
    SDL_SetColorKey(tmp, SDL_SRCCOLORKEY | SDL_RLEACCEL, 0);

    /* Attempt to optimize this strip for fast blitting. */
    front_star_tiles = SDL_DisplayFormat(tmp);
    if (front_star_tiles == NULL)
	front_star_tiles = tmp;
    else
	SDL_FreeSurface(tmp);	
}

void UnloadGameData(void)
{
    if (ship_strip != NULL) {
	SDL_FreeSurface(ship_strip);
	ship_strip = NULL;
    }
    if (front_star_tiles != NULL) {
	SDL_FreeSurface(front_star_tiles);
	front_star_tiles = NULL;
    }
    if (back_star_tiles != NULL) {
	SDL_FreeSurface(back_star_tiles);
	back_star_tiles = NULL;
    }
}
