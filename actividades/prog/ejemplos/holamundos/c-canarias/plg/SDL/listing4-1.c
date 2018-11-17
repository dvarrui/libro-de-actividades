/* Initialising SDL */

#include <SDL/SDL.h>
#include <stdio.h>
#include <stdlib.h>

int main()
{
  SDL_Surface *screen;

  /* Initialise SDL's video system and check for errors */

  if (SDL_Init(SDL_INIT_VIDEO) != 0)
    {
      printf("Unable to initialise SDL: %s\n", SDL_GetError());
      exit(EXIT_FAILURE);
      // PFJ - Changed from return 1; to exit(EXIT_FAILURE);
    }

  /* Make sure SDL_Quit gets called when the program exits */

  atexit(SDL_Quit);

  /* Attempt to set a 640 x 480 hicolor video mode */

  screen = SDL_SetVideoMode(640, 480, 16, SDL_FULLSCREEN);
  if (screen == NULL)
    {
      printf("Unable to set video mode: %s\n", SDL_GetError());
      exit(EXIT_FAILURE);
      // PFJ - Changed from return 1; to exit(EXIT_FAILURE);
    }

  /* If we got this far, everything worked */

  printf("Success!\n");
}

