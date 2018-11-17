#include <SDL/SDL.h>
#include "frames.h"

Frames::Frames() {
	numframes=0;
	//Se cargan de forma predeterminada estas imágenes
	load("rec/tile1.bmp");
	load("rec/tile2.bmp");
	load("rec/tile3.bmp");
	load("rec/nave.bmp");
	load("rec/enemigo.bmp");
}

void Frames::load(const char *path) {
	img[numframes] = SDL_LoadBMP(path);
	//Asignamos el color transparente
	SDL_SetColorKey(img[numframes],SDL_SRCCOLORKEY|SDL_RLEACCEL, SDL_MapRGB(img[numframes]->format,255,0,255));
	//TODO: ¿por qué da violación de segmento?
	//img[numframes] = SDL_DisplayFormat(img[numframes]); 
	numframes++;
}

void Frames::unload() {
	int i;
	for (i=0;i<numframes;i++) SDL_FreeSurface(img[i]);
}
