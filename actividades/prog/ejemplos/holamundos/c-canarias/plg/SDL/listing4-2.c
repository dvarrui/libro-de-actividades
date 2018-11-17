/* Direct pixel access with SDL */

#include <SDL/SDL.h>
#include <stdio.h>
#include <stdlib.h>

Uint16 CreateHicolorPixel(SDL_PixelFormat *fmt, Uint8 red, Uint8 green,
			  Uint8 blue)
{
  Uint16 value;

  /* This series of bit shifts uses the information from the SDL_Format 
     structure to correctly compose a 16-bit pixel value from 8-bit
     red, green and blue data */

  value = ((red >> fmt->Rloss) << fmt->Rshift) +
    ((green >> fmt->Gloss) << fmt->Gshift) +
    ((blue >> fmt->Bloss) << fmt->Bshift);

  return value;
}

int main()
{
  SDL_Surface *screen;
  Uint16 *raw_pixels;
  int x, y;

  /* Initialise SDL's video system and check for errors */

  if (SDL_Init(SDL_INIT_VIDEO) != 0)
    {
      printf("Unable to initialise SDL: %s\n", SDL_GetError());
      exit(EXIT_FAILURE);
      // PFJ - Changed from return 1; to exit(EXIT_FAILURE);
    }

  /* Make sure SDL_Quit gets called when the program exits */

  atexit(SDL_Quit);

  /* Attempt to set a 256x256 hicolor (16-bit) video mode. This will 
     set some type of 16-bit mode, but we won't know which particular
     pixel format ahead of time. If the video card can't handle hicolor 
     modes, SDL will emulate it */

  screen = SDL_SetVideoMode(256, 256, 16, 0);
  if (screen == NULL)
    {
      printf("Unable to set video mode: %s\n", SDL_GetError());
      exit(EXIT_FAILURE);
      // PFJ - Changed from return 1; to exit(EXIT_FAILURE);
    }

  /* Video memory can be strange, and it's sometimes necessary to "lock" 
     it before it can be modified. SDL abstracts this with the 
     SDL_LockSurface function */

  SDL_LockSurface(screen);

  /* Get a pointer to the video surface's memory */

  raw_pixels = (Uint16 *)screen->pixels;

  /* We can now safely write to the video surface. We'll draw a nice 
     gradient pattern by varying our red and blue components along the X 
     and Y axes. Notice the formula used to calculate the offset into the 
     framebuffer for each pixel.

     (The pitch is the number of bytes per scanline in memory) */

  for (x = 0; x < 256; ++x)
    {
      for (y = 0; y < 256; ++y)
	{
	  Uint16 pixel_color;
	  int offset;
	  pixel_color = CreateHicolorPixel(screen->format, x, 0, y);
	  offset = (screen->pitch / 2 * y + x);
	  raw_pixels[offset] = pixel_color;
	}
    }

  /* We've finished the drawing, so unlock the surface */

  SDL_UnlockSurface(screen);

  /* Inform SDL that the screen has been changed. This is necessary 
     because SDL's screen surface is not always the real framebuffer; 
     it is sometimes emulated behind the scenes */

  SDL_UpdateRect(screen, 0, 0, 0, 0);

  /* Pause for a few seconds as the viewer gasps in awe */

  SDL_Delay(3000);
}

