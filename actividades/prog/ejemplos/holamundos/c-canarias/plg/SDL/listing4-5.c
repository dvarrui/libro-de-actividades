/* Alpha blending in SDL */

#include <SDL/SDL.h>
#include <SDL/SDL_image.h>
#include <stdio.h>
#include <stdlib.h>

int main()
{
  SDL_Surface *screen;
  SDL_Surface *background;
  SDL_Surface *image_with_alpha;
  SDL_Surface *image_without_alpha;
  SDL_Rect src, dest;

  /* Initialise SDL video and test for errors */

  if (SDL_Init(SDL_INIT_VIDEO) != 0)
    {
      printf("Unable to initialise SDL: %s\n", SDL_GetError());
      exit(EXIT_FAILURE);
      // PFJ - Changed from return 1;
    }

  /* Ensure SDL_Quit is called when the program exits */

  atexit(SDL_Quit);

  /* Attempt to set a 320 x 200 hicolor video mode */
  screen = SDL_SetVideoMode(320, 200, 16, 0);
  if (screen == NULL)
    {
      printf("Unable to set video mode: %s\n", SDL_GetError());
      exit(EXIT_FAILURE);
      // PFJ - Changed from return 1;
    }

  /* Load the bitmap files. The first was created with an alpha channel and
     the second withoug. Note that IMG_Load is being used and not LoadBMP */

  image_with_alpha = IMG_Load("with-alpha.png");
  if (image_with_alpha == NULL)
    {
      printf("Unable to load image with alpha\n");
      exit(EXIT_FAILURE);
      // changed both lines - first for a more descriptive error
      // second from return 1;
    }

  image_without_alpha = IMG_Load("without-alpha.png");
  if (image_without_alpha == NULL)
    {
      printf("Unable to load image without alpha\n");
      exit(EXIT_FAILURE);
      // changed both lines for same reason as above
    } 

 background = IMG_Load("background.png");
  if (background == NULL)
    {
      printf("Unable to load the background\n");
      exit(EXIT_FAILURE);
      // changed both lines for same reason as above
    }

  /* Draw the background */

  src.x = 0;
  src.y = 0;
  src.w = background->w;
  src.h = background->h;
  dest.x = 0;
  dest.y = 0;
  dest.w = background->w;
  dest.h = background->h;
  SDL_BlitSurface(background, &src, screen, &dest);

  /* Draw the first image which has an alpha channel. This must be 
     specifically enabled for alpha blending */
  SDL_SetAlpha(image_with_alpha, SDL_SRCALPHA, 0);
  src.w = image_with_alpha->w;
  src.h = image_with_alpha->h;
  dest.w = src.w;
  dest.h = src.h;
  dest.x = 40;
  dest.y = 50;
  SDL_BlitSurface(image_with_alpha, &src, screen, &dest);

  /* Draw the second image which has no alpha channel. Instead, a 50%
     transparency factor will be applied for the entire surface */
  SDL_SetAlpha(image_without_alpha, SDL_SRCALPHA, 128);
  src.w = image_without_alpha->w;
  src.h = image_without_alpha->h;
  dest.w = src.w;
  dest.h = src.h;
  dest.x = 180;
  dest.y = 50;
  SDL_BlitSurface(image_without_alpha, &src, screen, &dest);

  /* Ask SDL to update the entire screen */
  SDL_UpdateRect(screen, 0, 0, 0, 0);

  /* Pause for applause */
  SDL_Delay(3000);

  /* Free memory */
  SDL_FreeSurface(background);
  SDL_FreeSurface(image_with_alpha);
  SDL_FreeSurface(image_without_alpha);
}
