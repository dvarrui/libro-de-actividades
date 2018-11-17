/* Simple mouse events with the SDL */

#include <SDL/SDL.h>
#include <stdio.h>
#include <stdlib.h>

int main()
{
  SDL_Surface *screen;
  SDL_Event event;

  // Initialise SDL and error check
  if (SDL_Init(SDL_INIT_VIDEO) != 0)
    {
      printf("Unable to initialise SDL: %s\n", SDL_GetError());
      exit(EXIT_FAILURE);
      // PFJ - Changed from return 1;
    }

  // Ensure SDL_Quit is called on termination
  atexit(SDL_Quit);

  // Attempt to set a 256x256 hicolor video mode
  screen = SDL_SetVideoMode(256, 256, 16, 0);
  if (screen == NULL)
    {
      printf("Unable to set video mode: %s\n", SDL_GetError());
      return 1;
    }

  /* Start the event loop. Keep reading events until there is an error, or 
     the user presses a mouse button */

  while(SDL_WaitEvent(&event) != 0)
    {
      /* SDL_WaitEvent has filled in our event structure with the next event. 
	 We check its type fied to find out what happened */
      switch(event.type)
	{
	  // The next two event types deal with mouse activity
	case SDL_MOUSEMOTION:
	  printf("Mouse motion ");
	  // SDL provides the current position
	  printf("New position is (%i, %i). ", event.motion.x,
		 event.motion.y);
	  // We can also get relative motion
	  printf("That is a (%i, %i) change.\n", event.motion.xrel,
		 event.motion.yrel);
	  break;

	case SDL_MOUSEBUTTONDOWN:
	  printf("Mouse button pressed. ");
	  printf("Button %i at (%i, %i)\n", event.button.button,
		 event.button.x, event.button.y);
	  break;

	  /* The SDL_QUIT event indicates that the windows "Close" button has 
	     been pressed. We can ignore this if we need to, but that tends to
	     make users rather impatient */
	case SDL_QUIT:
	  printf("Quit event. Bye\n");
	  exit(EXIT_SUCCESS);
	  // PFJ - Changed for portable exit
	}
    }
}

