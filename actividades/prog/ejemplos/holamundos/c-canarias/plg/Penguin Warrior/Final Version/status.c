#include <SDL/SDL.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "gamedefs.h"
#include "font5x5.h"   /* A simple 5x5 ASCII font. */

/* =====================
   LED display routines.
   ===================== */

typedef struct LED_Display {
    SDL_Surface *led_surface;
    int phys_w, phys_h;
    int virt_w, virt_h;
    int virt_x, virt_y;
    SDL_Surface *on_image;
    SDL_Surface *off_image;
} LED_Display;

static int LED_CreateDisplay(LED_Display *disp, int cols, int rows,
                             int vcols, int vrows, char *on, char *off);
static void LED_FreeDisplay(LED_Display *disp);
static void LED_DrawDisplay(LED_Display *disp, SDL_Surface *dest, int x, int y);
static void DrawChar5x5(SDL_Surface *dest, char ch, Uint8 color, int x, int y);

/* Initializes an LED display. The parameters are as follows:
   cols, rows - Physical size of the LED display, in LEDs.
   vcols, vrows - Size of the LED display's framebuffer, in LEDs.
                  The visible area of the display can be scrolled.
   on, off - Filenames of the bitmaps to use for the "on" LED and the "off" LED.
   Returns 0 on success, -1 on error. */
static int LED_CreateDisplay(LED_Display *disp, int cols, int rows,
                             int vcols, int vrows, char *on, char *off)
{
    SDL_Color c;
    int i;

    memset(disp, 0, sizeof (LED_Display));

    disp->led_surface = SDL_CreateRGBSurface(SDL_SWSURFACE, vcols, vrows, 
					     8, 0, 0, 0, 0);
    if (disp->led_surface == NULL)
	return -1;

    disp->virt_w = vcols;
    disp->virt_h = vrows;
    disp->phys_w = cols;
    disp->phys_h = rows;
    disp->virt_x = 0;
    disp->virt_y = 0;

    for (i = 0; i < 256; i++) {
	c.r = i;
	c.g = i;
	c.b = i;
	SDL_SetColors(disp->led_surface, &c, i, 1);
    }

    SDL_LockSurface(disp->led_surface);
    memset(disp->led_surface->pixels, 0, disp->led_surface->pitch * vrows);

    SDL_UnlockSurface(disp->led_surface);

    disp->on_image = SDL_LoadBMP(on);
    if (disp->on_image == NULL) return -1;

    disp->off_image = SDL_LoadBMP(off);
    if (disp->off_image == NULL) return -1;

    /* Add alpha for a nice overlay effect. */
    SDL_SetAlpha(disp->off_image, SDL_SRCALPHA, 128);

    return 0;
}

/* Frees an LED display's memory. */
static void LED_FreeDisplay(LED_Display *disp)
{
    if (disp->led_surface != NULL)
	SDL_FreeSurface(disp->led_surface);

    if (disp->on_image != NULL)
	SDL_FreeSurface(disp->on_image);

    if (disp->off_image != NULL)
	SDL_FreeSurface(disp->off_image);

    memset(disp, 0, sizeof (LED_Display));
}

/* Renders an LED display to an SDL surface, starting at (x,y) on the
   destination. */
static void LED_DrawDisplay(LED_Display *disp, SDL_Surface *dest, int x, int y)
{
    int row, col;
    SDL_Rect srcrect, destrect;
    Uint8 *leds;

    srcrect.w = disp->on_image->w;
    srcrect.h = disp->on_image->h;
    srcrect.x = 0;
    srcrect.y = 0;
    destrect = srcrect;

    SDL_LockSurface(disp->led_surface);
    leds = (Uint8 *)disp->led_surface->pixels;

    for (row = 0; row < disp->phys_h; row++) {
	for (col = 0; col < disp->phys_w; col++) {
	    int led;

	    destrect.x = col * disp->on_image->w + x;
	    destrect.y = row * disp->on_image->h + y;
	    led = leds[(row + disp->virt_y) * disp->led_surface->pitch + 
		       col + disp->virt_x];
	    if (led) {
		SDL_BlitSurface(disp->on_image, &srcrect,
				dest, &destrect);
	    } else {
		SDL_BlitSurface(disp->off_image, &srcrect,
				dest, &destrect);
	    }
	}
    }

    SDL_UnlockSurface(disp->led_surface);
}

/* Draws a 5x5 bitmapped character to the given 8-bit SDL surface. */
static void DrawChar5x5(SDL_Surface *dest, char ch, Uint8 color, int x, int y)
{
    char *data;
    Uint8 *pixels;
    int sx, sy;

    data = Font5x5[(int)ch];

    if (SDL_MUSTLOCK(dest))
	SDL_LockSurface(dest);
    pixels = (Uint8 *)dest->pixels;

    for (sy = 0; sy < 5; sy++) {
	for (sx = 0; sx < 5; sx++) {
	    if (data[5*sy+sx] != ' ') {
		pixels[dest->pitch*(y+sy)+x+sx] = color;
	    } else {
		pixels[dest->pitch*(y+sy)+x+sx] = 0;
	    }
	}
    }

    SDL_UnlockSurface(dest);
}


/* =====================================
   End of LED stuff.
   Back to the world of Penguin Warrior.
   ===================================== */

/* A temporary buffer for the characters currently on the display. */
#define SCROLLER_BUF_SIZE 10
char scroller_buf[SCROLLER_BUF_SIZE];

/* Message to scroll. This can be changed. */
const char *scroller_msg = "Welcome to Penguin Warrior";
int scroller_pos = 0;
int scroller_ticks = 0;

/* Various LED displays that appear on the Penguin Warrior screen. */
LED_Display player_score, player_shields, player_charge;
LED_Display opponent_score, opponent_shields;
LED_Display status_msg;

int InitStatusDisplay(void)
{
    if (LED_CreateDisplay(&player_score, 12, 5, 12, 5,
			  "led-red-on.bmp", "led-red-off.bmp") < 0)
	return -1;
    if (LED_CreateDisplay(&player_shields, 12, 1, 12, 1,
			  "led-red-on.bmp", "led-red-off.bmp") < 0)
	return -1;
    if (LED_CreateDisplay(&player_charge, 80, 1, 80, 1,
			  "led-red-on.bmp", "led-red-off.bmp") < 0)
	return -1;
    if (LED_CreateDisplay(&opponent_score, 12, 5, 12, 5,
			  "led-red-on.bmp", "led-red-off.bmp") < 0)
	return -1;
    if (LED_CreateDisplay(&opponent_shields, 12, 1, 12, 1,
			  "led-red-on.bmp", "led-red-off.bmp") < 0)
	return -1;
    if (LED_CreateDisplay(&status_msg, 56, 5, 66, 5,
			  "led-green-on.bmp", "led-green-off.bmp") < 0)
	return -1;

    memset(scroller_buf, 0, SCROLLER_BUF_SIZE);

    return 0;
}

void CleanupStatusDisplay(void)
{
    LED_FreeDisplay(&player_score);
    LED_FreeDisplay(&player_shields);
    LED_FreeDisplay(&player_charge);
    LED_FreeDisplay(&opponent_score);
    LED_FreeDisplay(&opponent_shields);
    LED_FreeDisplay(&status_msg);
}

void SetStatusMessage(const char *msg)
{
    scroller_pos = 0;
    scroller_msg = msg;
}

void SetPlayerStatusInfo(int score, int shields, int charge)
{
    char buf[3];
    Uint8 *pixels;
    int i;

    /* Set the score counter. */
    sprintf(buf, "%2i", score);
    DrawChar5x5(player_score.led_surface, buf[0], 1, 0, 0);
    DrawChar5x5(player_score.led_surface, buf[1], 1, 6, 0);

    /* Set the shield bar. */
    SDL_LockSurface(player_shields.led_surface);
    pixels = (Uint8 *)player_shields.led_surface->pixels;
    for (i = 0; i < 12; i++) {
	if (i < shields * 12 / 100)
	    pixels[i] = 1;
	else
	    pixels[i] = 0;
    }
    SDL_UnlockSurface(player_shields.led_surface);

    /* Set the phaser charge bar. */
    SDL_LockSurface(player_charge.led_surface);
    pixels = (Uint8 *)player_charge.led_surface->pixels;
    for (i = 0; i < 80; i++) {
	if (i < charge * 80 / PHASER_CHARGE_MAX)
	    pixels[i] = 1;
	else
	    pixels[i] = 0;
    }
    SDL_UnlockSurface(player_charge.led_surface);
}

void SetOpponentStatusInfo(int score, int shields)
{
    char buf[3];
    Uint8 *pixels;
    int i;

    /* Set the score counter. */
    sprintf(buf, "%2i", score);
    DrawChar5x5(opponent_score.led_surface, buf[0], 1, 0, 0);
    DrawChar5x5(opponent_score.led_surface, buf[1], 1, 6, 0);

    /* Set the shield bar. */
    SDL_LockSurface(opponent_shields.led_surface);
    pixels = (Uint8 *)opponent_shields.led_surface->pixels;
    for (i = 0; i < 12; i++) {
	if (i < shields * 12 / 100)
	    pixels[i] = 1;
	else
	    pixels[i] = 0;
    }
    SDL_UnlockSurface(opponent_shields.led_surface);
}

void UpdateStatusDisplay(SDL_Surface *screen)
{
    int i;

    /* Update the scroller.
       This is not linked to the global time_scale, since speed
       really doesn't matter. The only effect of a high framerate
       would be that the scrolling message would move faster. */
    if ((scroller_ticks % 6) == 0) {
	char ch;
	for (i = 0; i < SCROLLER_BUF_SIZE-1; i++) {
	    scroller_buf[i] = scroller_buf[i+1];
	}
	if (scroller_msg[scroller_pos] == '\0') {
	    ch = ' ';
	    scroller_pos--;
	} else {
	    ch = scroller_msg[scroller_pos];
	}
	scroller_pos++;
	scroller_buf[i] = ch;
	status_msg.virt_x = 0;
	for (i = 0; i < SCROLLER_BUF_SIZE; i++) {
	    DrawChar5x5(status_msg.led_surface, scroller_buf[i], 1, 6 * i, 0);
	}
    } else {
	status_msg.virt_x++;
    }

    scroller_ticks++;

    LED_DrawDisplay(&player_score, screen, 0, 0);
    LED_DrawDisplay(&player_shields, screen, 0, 48);
    LED_DrawDisplay(&player_charge, screen, 0, 471);
    LED_DrawDisplay(&opponent_score, screen, 544, 0);
    LED_DrawDisplay(&opponent_shields, screen, 544, 48);
    LED_DrawDisplay(&status_msg, screen, 96, 0);
}
