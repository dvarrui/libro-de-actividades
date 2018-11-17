/* Simple keyboard input with the SDL */

#include <SDL/SDL.h>
#include <stdio.h>
#include <stdlib.h>

int main()
{
  SDL_Surface *screen;
  SDL_Event event;

  // Initialise the SDL and check for errors
  if (SDL_Init(SDL_INIT_VIDEO) != 0)
    {
      printf("Unable to initialise SDL: %s\n", SDL_GetError());
      exit(EXIT_FAILURE);
      // PFJ - Changed from return 1;
    }

  // Ensure SDL_Quit is called on program termination
  atexit(SDL_Quit);

  // Attempt to set a 256 x 256 hicolor video mode
  screen = SDL_SetVideoMode(256, 256, 16, 0);
  if (screen == NULL)
    {
      printf("Unable to set video mode: %s\n", SDL_GetError());
      exit(EXIT_FAILURE);
      // PFJ - Changed from return 1;
    }

  printf ("Press 'Q' to quit\n");

  /* Start the event loop. Keep reading events until there is an error, or 
     the user presses "Q" or closes the window */
  // PFJ - Comment corrected from the version in the book!

  while(SDL_WaitEvent(&event) != 0)
    {
      SDL_keysym keysym;
      switch (event.type)
	{
	case SDL_KEYDOWN:
	  printf("Key pressed. ");
	  keysym = event.key.keysym;
	  printf("SDL keysym is %i. ", keysym.sym);
	  printf("(%s) ", SDL_GetKeyName(keysym.sym));

	  // report the left shift modifier
	  if (event.key.keysym.mod & KMOD_LSHIFT)
	    printf("Left shift is down\n");
	  else
	    printf("Left shift is up\n");

	  // Did the user press Q?
	  if (keysym.sym == SDLK_Q)
	    {
	      printf("'Q' pressed. Exiting\n");
	      exit(EXIT_SUCCESS);
	      // PFJ - Changed for portable exit
	    }
	  break;

	case SDL_KEYUP:
	  printf("Key released. ");
	  printf("SDL keysym is %i. ", keysym.sym);
	  printf("(%s) ", SDL_GetKeyName(keysym.sym));

	  if (event.key.keysym.mod & KMOD_LSHIFT)
	    printf("Left shift is down\n");
	  else
	    printf("Left shift is up\n");
	  break;

	case SDL_QUIT:
	  printf("Quit event. Bye\n");
	  exit(EXIT_SUCCESS);
	  // PFJ - Changed for portable exit
	}
    }
}

