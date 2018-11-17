/* Simple blitting with  SDL */

#include <SDL/SDL.h>
#include <stdio.h>
#include <stdlib.h>

int main()
{
  SDL_Surface *screen;
  SDL_Surface *image;
  SDL_Rect src, dest;

  /* Initialise and check for errors */

  if (SDL_Init(SDL_INIT_VIDEO) != 0)
    {
      printf("Could not initialise SDL: %s\n", SDL_GetError());
      exit(EXIT_FAILURE);
      // PFJ - Changed from return 1; to exit(EXIT_FAILURE);
    }

  /* Ensure SDL_Quit is called when the program ends */

  atexit(SDL_Quit);

  /* Attempt to set a 256 x 256 hicolor (16 bit) video mode. Since 256x256 
     is rarely a video mode, SDL will most likely emulate this resolution with 
     a different video mode. */

  screen = SDL_SetVideoMode(256, 256, 16, 0);
  if (screen == NULL)
    {
      printf("Could not set video mode: %s\n", SDL_GetError());
      exit(EXIT_FAILURE);
      // PFJ - Changed from return 1; to exit(EXIT_FAILURE);
    }

  /* Load the bitmap file. SDL_LoadBMP returns a pointer to a new surface 
     containing the loaded bitmap */

  image = SDL_LoadBMP("test-image.bmp");
  if (image == NULL)
    {
      printf("Unable to load bitmap\n");
      exit(EXIT_FAILURE);
      // PFJ - Changed from return 1; to exit(EXIT_FAILURE);
    }

  /* The SDL blitting function needs to know how much data to copy. We 
     provide this with SDL_Rect structures, which define the source and 
     destination rectangles. The areas should be same; SDL does not currently 
     handle image stretching */

  src.x = 0;
  src.y = 0;
  src.w = image->w; // copy the entire image
  src.h = image->h;

  dest.x = 0;
  dest.y = 0;
  dest.w = image->w;
  dest.h = image->h;

  /* Draw the bitmap to the screen. We are using a hicolor video mode, so we
     don't have to worry about colormap silliness. It is not necessary to lock 
     surfaces before blitting; SDL will handle that */

  SDL_BlitSurface(image, &src, screen, &dest);

  /* Ask SDL to update the entire screen */

  SDL_UpdateRect(screen, 0, 0, 0, 0);

  /* Pause for a bit */

  SDL_Delay(3000);

  /* Free memory */

  SDL_FreeSurface(image);
}

