/* A simple framebuffer console example. */

#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <errno.h>
#include <string.h>
#include <unistd.h>
#include <asm/page.h>
#include <sys/mman.h>
#include <sys/ioctl.h>
#include <asm/page.h>
#include <linux/fb.h>

int main(int argc, char *argv[])
{
    char *fbname;
    int fbdev;
    struct fb_fix_screeninfo fixed_info;
    struct fb_var_screeninfo var_info;
    struct fb_cmap colormap;
    u_int16_t r, g, b;
    u_int8_t pixel_r = 0, pixel_g = 0, pixel_b = 0;
    int x, y;
    u_int32_t pixel_value;
    void *framebuffer;
    int framebuffer_size;
    int ppc_fix;

    /* Let the user specify an alternate framebuffer
       device on the command line. Default to
       /dev/fb0. */
    if (argc >= 2)
	fbname = argv[1];
    else
	fbname = "/dev/fb0";

    printf("Using framebuffer device %s.\n", fbname);

    /* Open the framebuffer device. */
    fbdev = open(fbname, O_RDWR);
    if (fbdev < 0) {
	printf("Error opening %s.\n", fbname);
	return 1;
    }

    /* Retrieve fixed screen info.
       This is information that never changes for
       this particular display. */
    if (ioctl(fbdev, FBIOGET_FSCREENINFO, &fixed_info) < 0) {
	printf("Unable to retrieve fixed screen info: %s\n",
	       strerror(errno));
	close(fbdev);
	return 1;
    }

    /* Print out some of the fixed info. */
    printf("Framebuffer ID:     %s\n", fixed_info.id);
    printf("Framebuffer type:   ");
    switch (fixed_info.type) {
    case FB_TYPE_PACKED_PIXELS: printf("packed pixels\n"); break;
    case FB_TYPE_PLANES: printf("planar (non-interleaved)\n"); break;
    case FB_TYPE_INTERLEAVED_PLANES: printf("planar (interleaved)\n"); break;
    case FB_TYPE_TEXT: printf("text (not a framebuffer)\n"); break;
    case FB_TYPE_VGA_PLANES: printf("planar (EGA/VGA)\n"); break;
    default: printf("no idea what this is\n");
    }
    printf("Bytes per scanline: %i\n", fixed_info.line_length);

    printf("Visual type:        ");
    switch (fixed_info.visual) {
    case FB_VISUAL_TRUECOLOR: printf("truecolor\n"); break;
    case FB_VISUAL_PSEUDOCOLOR: printf("pseudocolor\n"); break;
    case FB_VISUAL_DIRECTCOLOR: printf("directcolor\n"); break;
    case FB_VISUAL_STATIC_PSEUDOCOLOR: printf("fixed pseudocolor\n"); break;
    default: printf("other (mono perhaps)\n");
    }
    
    /* Now get the variable screen info.
       This contains more info about the current video mode.
       (Note that this is effectively fixed on some framebuffer
       devices -- many don't support mode switching.) */
    if (ioctl(fbdev, FBIOGET_VSCREENINFO, &var_info) < 0) {
	printf("Unable to retrieve variable screen info: %s\n",
	       strerror(errno));
	close(fbdev);
	return 1;
    }

    /* Print out some info. */
    printf("Bits per pixel:     %i\n", var_info.bits_per_pixel);
    printf("Resolution:         %ix%i (virtual %ix%i)\n",
	   var_info.xres, var_info.yres,
	   var_info.xres_virtual, var_info.yres_virtual);
    printf("Scrolling offset:   (%i,%i)\n",
	   var_info.xoffset, var_info.yoffset);
    printf("Red channel:        %i bits at offset %i\n",
	   var_info.red.length, var_info.red.offset);
    printf("Green channel:      %i bits at offset %i\n",
	   var_info.red.length, var_info.green.offset);
    printf("Blue channel:       %i bits at offset %i\n",
	   var_info.red.length, var_info.blue.offset);

    /* Now memory map the framebuffer.
       According to the SDL source code, it's necessary to
       compensate for a buggy mmap implementation on the
       PowerPC. This should not be a problem for other
       architectures. (This fix is lifted from SDL_fbvideo.c) */
    ppc_fix = (((long)fixed_info.smem_start) -
	       ((long) fixed_info.smem_start & ~(PAGE_SIZE-1)));
    framebuffer_size = fixed_info.smem_len + ppc_fix;
    framebuffer = mmap(NULL,
		       framebuffer_size,
		       PROT_READ | PROT_WRITE,
		       MAP_SHARED,
		       fbdev,
		       0);

    if (framebuffer == NULL) {
	printf("Unable to mmap framebuffer: %s\n",
	       strerror(errno));
	close(fbdev);
	return 1;
    }

    printf("Mapped framebuffer.\n");

    /* Ok, now we'll get ready to plot the pixel.
       We have several possible situations:
       a) The current mode is indexed (pseudocolor).
       We'll need to set a palette entry.
       b) The current mode is truecolor (packed pixel).
       No need to mess with the palette; there is none.
       c) The current mode is directcolor (per-channel palettes).
       We'll need to set a palette entry.
       d) The current mode is static pseudocolor
       (indexed, but with a fixed palette that we can't change).
       This might be found on VGA adapters in 16-color mode.
       We will IGNORE this case (gaming on 16-color devices
       leaves much to be desired, and it's a hassle). */

    /* Take the appropriate action based on the visual type. */
    if ((fixed_info.visual == FB_VISUAL_PSEUDOCOLOR) ||
	(fixed_info.visual == FB_VISUAL_DIRECTCOLOR)) {
	/* We want a white pixel.
	   Palette values are 16 bits each. */
	r = 0xFFFF;
	g = 0xFFFF;
	b = 0xFFFF;

	/* Set a single palette entry.
	   Hijack color 255. */
	colormap.start = 255;
	colormap.len = 1;
	colormap.red = &r;
	colormap.green = &g;
	colormap.blue = &b;
	colormap.transp = NULL;

	if (ioctl(fbdev, FBIOPUTCMAP, &colormap) < 0) {
	    printf("WARNING: unable to set colormap.\n");
	    /* This isn't really fatal, but our pixel
	       probably won't show up correctly. */
	}

	pixel_r = 255;
	pixel_g = 255;
	pixel_b = 255;
		
	/* This will work in both pseudocolor and
	   directcolor modes. */
	pixel_value = 0xFFFFFFFF;
		
    } else if (fixed_info.visual == FB_VISUAL_TRUECOLOR) {
	/* White pixel. */
	pixel_r = 0xFF;
	pixel_g = 0xFF;
	pixel_b = 0xFF;

	/* We used this same pixel packing technique
	   back when we were working with SDL. */
	pixel_value = (((pixel_r >> (8-var_info.red.length)) <<
			var_info.red.offset) +
		       ((pixel_g >> (8-var_info.green.length)) <<
			var_info.green.offset) +
		       ((pixel_b >> (8-var_info.blue.length)) <<
			var_info.blue.offset));
    } else {
	printf("Unsupported visual.\n");
	pixel_value = 0;
    }
	
    /* Now plot the pixel. The framebuffer interface allows
       some modes to use a larger framebuffer than the
       monitor's resolution (this is called a virtual resolution).
       In this case some of the framebuffer isn't visible. We
       can retrieve the starting coordinates of the visible
       rectangle from the variable info structure. */
	
    x = var_info.xres / 2 + var_info.xoffset;
    y = var_info.yres / 2 + var_info.yoffset;

    switch (var_info.bits_per_pixel) {
    case 8:
	*((u_int8_t *)framebuffer +
	  fixed_info.line_length * y + x) = (u_int8_t)pixel_value;
	break;
    case 16:
	*((u_int16_t *)framebuffer +
	  fixed_info.line_length/2 * y + x) = (u_int16_t)pixel_value;
	break;
    case 24:
	/* 24-bit modes are generally slower than others because
	   pixels are not aligned on word boundaries. This is why
	   32-bit modes often outperform 24-bit modes. */
	*((u_int8_t *)framebuffer +
	  (fixed_info.line_length * y + 3 * x)) = (u_int8_t)pixel_r;
	*((u_int8_t *)framebuffer +
	  (fixed_info.line_length * y + 3 * x) + 1) = (u_int8_t)pixel_g;
	*((u_int8_t *)framebuffer +
	  (fixed_info.line_length * y + 3 * x) + 2) = (u_int8_t)pixel_b;

	break;
    case 32:
	*((u_int32_t *)framebuffer +
	  fixed_info.line_length/4 * y + x) = (u_int32_t)pixel_value;
    default:
	printf("Unsupported depth.\n");
    }


    /* Close the fbdev. */
    munmap(framebuffer, framebuffer_size);
    close(fbdev);

    return 0;
}
