/* An example of framebuffer mode switching. */

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
#include "fbmodedb.h"

int main(int argc, char *argv[])
{
    char *fbname;
    int fbdev;
    struct fb_fix_screeninfo fixed_info;
    struct fb_var_screeninfo var_info, old_var_info;
    FbModeline *modelist;
    FbModeline *selected;
    u_int8_t pixel_r, pixel_g, pixel_b;
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

    /* Get the variable screen info. */
    if (ioctl(fbdev, FBIOGET_VSCREENINFO, &var_info) < 0) {
	printf("Unable to retrieve variable screen info: %s\n",
	       strerror(errno));
	close(fbdev);
	return 1;
    }

    /* Back up this info so we can restore it later. */
    old_var_info = var_info;

    /* Load the modes database. */
    modelist = FB_ParseModeDB("/etc/fb.modes");
    if (modelist == NULL) {
	printf("Unable to load /etc/fb.modes.\n");
	close(fbdev);
	return 1;
    }

    /* Switch into a 640x480 mode. Take the first one we find. */
    selected = modelist;
    while (selected != NULL) {
	if (selected->xres == 640 && selected->yres == 480)
	    break;
	selected = selected->next;
    }

    if (selected == NULL) {
	printf("No 640x480 modes found in /etc/fb.modes. That's odd.\n");
	FB_FreeModeDB(modelist);
	close(fbdev);
	return 1;
    }

    /* Copy the timing parameters into the variable info structure. */
    var_info.xres = selected->xres;
    var_info.yres = selected->yres;
    var_info.xres_virtual = var_info.xres;
    var_info.yres_virtual = var_info.yres;
    var_info.pixclock = selected->dotclock;
    var_info.left_margin = selected->left;
    var_info.right_margin = selected->right;
    var_info.upper_margin = selected->upper;
    var_info.lower_margin = selected->lower;
    var_info.hsync_len = selected->hslen;
    var_info.vsync_len = selected->vslen;

    /* Ask for 16bpp. */
    var_info.bits_per_pixel = 16;

    /* This is a bitmask of sync flags. */
    var_info.sync = selected->hsync * FB_SYNC_HOR_HIGH_ACT +
	selected->vsync * FB_SYNC_VERT_HIGH_ACT +
	selected->csync * FB_SYNC_COMP_HIGH_ACT +
	selected->extsync * FB_SYNC_EXT;

    /* This is a bitmask of mode attributes. */
    var_info.vmode = selected->laced * FB_VMODE_INTERLACED +
	selected->doublescan * FB_VMODE_DOUBLE;

    var_info.activate = FB_ACTIVATE_NOW;

    /* Set the mode with an ioctl. It may not accept the exact
       parameters we provide, in which case it will edit the
       structure. If our selection is completely unacceptable,
       the ioctl will fail. */
    if (ioctl(fbdev, FBIOPUT_VSCREENINFO, &var_info) < 0) {
	printf("Unable to set variable screen info: %s\n",
	       strerror(errno));
	close(fbdev);
	return 1;
    }

    printf("Mode switch ioctl succeeded.\n");
    printf("Got resolution %ix%i @ %ibpp.\n",
	   var_info.xres,
	   var_info.yres,
	   var_info.bits_per_pixel);

    /* Retrieve the fixed screen info. */
    if (ioctl(fbdev, FBIOGET_FSCREENINFO, &fixed_info) < 0) {
	printf("Unable to retrieve fixed screen info: %s\n",
	       strerror(errno));
	close(fbdev);
	return 1;
    }	

    /* Now memory-map the framebuffer.
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

    if ((fixed_info.visual == FB_VISUAL_TRUECOLOR) &&
	(var_info.bits_per_pixel == 16)) {
	/* White pixel. */
	pixel_r = 0xFF;
	pixel_g = 0xFF;
	pixel_b = 0xFF;

	/* We used this same pixel-packing technique
	   back when we were working with SDL. */
	pixel_value = (((pixel_r >> (8-var_info.red.length)) <<
			var_info.red.offset) +
		       ((pixel_g >> (8-var_info.green.length)) <<
			var_info.green.offset) +
		       ((pixel_b >> (8-var_info.blue.length)) <<
			var_info.blue.offset));

	/* Draw a pixel in the center of the screen. */
	x = var_info.xres / 2 + var_info.xoffset;
	y = var_info.yres / 2 + var_info.yoffset;

	*((u_int16_t *)framebuffer +
	  fixed_info.line_length/2 * y + x) = (u_int16_t)pixel_value;

    } else {
	printf("Unsupported visual. (Asked for 16bpp.)\n");
	pixel_value = 0;
    }

    /* Wait a few seconds. */
    sleep(5);

    /* Restore the old video mode. */
    old_var_info.activate = FB_ACTIVATE_NOW;
    if (ioctl(fbdev, FBIOPUT_VSCREENINFO, &old_var_info) < 0) {
	printf("Warning: Unable to restore video mode: %s\n",
	       strerror(errno));
    }

    /* Close the fbdev. */
    munmap(framebuffer, framebuffer_size);
    close(fbdev);

    return 0;

}
