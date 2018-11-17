/* Simple joystick input with the SDL */

#include <SDL/SDL.h>
#include <stdio.h>
#include <stdlib.h>

int main()
{
  SDL_Event event;
  SDL_Joystick *js;
  int num_js, i, quit_flag;

  // Initialise SDL's joystick and video subsystems
  if (SDL_Init(SDL_INIT_JOYSTICK | SDL_INIT_VIDEO) != 0)
    {
      printf("Error: %s\n", SDL_GetError());
      exit(EXIT_FAILURE);
      // PFJ - Changed from return 1;
    }

  // Ensure SDL_Quit is called upon program termination
  atexit(SDL_Quit);

  // Find out if there are any joysticks available
  // This routines position in the code has been changed as it makes
  // more sense to check if there are any joysticks before creating the
  // window!
  num_js = SDL_NumJoysticks();
  printf("SDL recognises %s joystick(s) on this system\n", num_js);
  if (num_js == 0)
    exit(EXIT_FAILURE); // PFJ - Changed from return 1;

  // Create a 256x256 window so we can collect input levels
  if (SDL_SetVideoMode(256, 256, 16, 0) == NULL)
    {
      printf("Error: %s\n", SDL_GetError());
      exit(EXIT_FAILURE);
      // PFJ - Changed from return 1;
    }

  // Print out information for each joystick
  for (i = 0; i < num_js; ++i)
    {
      // Open the joystick
      js = SDL_JoystickOpen(i);
      if (js == NULL)
	printf("Unable to open joystick %i\n", i);
      else
	{
	  printf("Joystick %i\n", i);
	  printf("\tName :       %s\n", SDL_JoystickName(i));
	  printf("\tAxes :       %s\n", SDL_JoystickNumAxes(js));
	  printf("\tTrackballs : %i\n", SDL_JoystickNumBalls(js));
	  printf("\tButtons :    %i\n", SDL_JoystickNumButtons(js));
	  // close the joystick
	  SDL_JoystickClose(js);
	}
    }

  // The book used the first joystick for the demo. It really should 
  // have used the first available joystick. The following code does that
  // It also does a check to ensure that a joystick has been selected

  for (i = 0; i < num_js; ++i)
    {
      js = SDL_JoystickOpen(i);
      if (js != NULL)
	break;
    }

  if (i+1 == num_js && js == NULL)
    {
      printf("Unable to open any of the available joysticks\n");
      exit(EXIT_FAILURE);
    }

  // Loop until the user presses Q
  quit_flag = 0;

  while(SDL_WaitEvent(&event) != 0 && quit_flag == 0)
    {
      switch (event.type)
	{
	case SDL_KEYDOWN :
	  if (event.key.keysym.sym == SDLK_q)
	    {
	      printf("Q pressed. Exiting\n");
	      quit_flag = 1;
	    }
	  break;

	  /* This event is generated when an axis on an open joystick is moved.
	     Most joysticks have two axes, X and Y (which will be reported as 
	     axes 0 and 1) */

	case SDL_JOYAXISMOTION:
	  printf("Joystick %i, axis %i movement to %i\n", 
		 event.jaxis.which, event.jaxis.axis, event.jaxis.value);
	  break;

	  /* The SDL_JOYBUTTONUP and SDL_JOYBUTTONDOWN events are generated 
	     when the state of the joystick changes */

	case SDL_JOYBUTTONUP:
	  // fall through to SDL_JOYBUTTONDOWN
	case SDL_JOYBUTTONDOWN:
	  printf("Joystick %i button %i: %i\n", event.jbutton.which,
		 event.jbutton.button, event.jbutton.state);
	  break;
	}
    }
  // Close the joystick
  SDL_JoystickClose(js);
}

