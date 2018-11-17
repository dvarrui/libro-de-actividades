/* Simple blitting with colorkeys in SDL */

#include <SDL/SDL.h>
#include <stdio.h>
#include <stdlib.h>

int main()
{
  SDL_Surface *screen;
  SDL_Surface *background;
  SDL_Surface *image;
  SDL_Rect src, dest;
  Uint32 colorkey;

  /* Initialise the SDL video system and check for errors */
  if (SDL_Init(SDL_INIT_VIDEO) != 0)
    {
      printf("Unable to initialise SDL: %s\n", SDL_GetError());
      exit(EXIT_FAILURE);
      // PFJ - Changed from return 1;
    }

  /* Ensure SDL_Quit is called when the program exits */
  atexit(SDL_Quit);

  /* Attempt to set a 640x480 hicolor (16-bit) video mode */
  screen = SDL_SetVideoMode(640, 480, 16, 0);
  if (screen == NULL)
    {
      printf("Unable to set video mode: %s\n", SDL_GetError());
      exit(EXIT_FAILURE);
      // PFJ - Changed from return 1;
    }

  /* Load bitmap files */
  background = SDL_LoadBMP("background.bmp");
  if (background == NULL)
    {
      printf("Unable to load bitmap\n");
      exit(EXIT_FAILURE);
      // PFJ - Changed from return 1;
    }

  image = SDL_LoadBMP("penguin.bmp");
  if (image == NULL)
    {
      printf("Unable to load bitmap penguin.bmp\n");
      exit(EXIT_FAILURE);
      // PFJ - Changed from return 1;
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

  /* Draw Tux without the colorkey */

  src.x = 0;
  src.y = 0;
  src.w = image->w;
  src.h = image->h;
  dest.x = 30;
  dest.y = 90;
  dest.w = image->w;
  dest.h = image->h;
  SDL_BlitSurface(image, &src, screen, &dest);

  /* The penguin is stored on a blue background. We can use the SDL_MapRGB 
     function to obtain the correct pixel value for pure blue */

  colorkey = SDL_MapRGB(image->format, 0, 0, 255);

  /* We'll now enable this surface's colorkey amd draw it again. To turn off 
     the colorkey again, we should replace the SDL_SRCCOLORKEY flag with
     zero */

  SDL_SetColorKey(image, SDL_SRCCOLORKEY, colorkey);
  src.x = 0;
  src.y = 0;
  src.w = image->w;
  src.h = image->h;
  dest.x = screen->w - image->w - 30;
  dest.y = 90;
  dest.w = image->w;
  dest.h = image->h;
  SDL_BlitSurface(image, &src, screen, &dest);

  /* Ask SDL to update the entire screen */
  SDL_UpdateRect(screen, 0, 0, 0, 0);

  /* Pause for a bit */
  SDL_Delay(3000);

  /* Free the image memory banks */
  SDL_FreeSurface(background);
  SDL_FreeSurface(image);
}

