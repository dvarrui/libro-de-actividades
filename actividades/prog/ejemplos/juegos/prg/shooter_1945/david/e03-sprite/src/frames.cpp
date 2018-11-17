#include <SDL/SDL.h>
#include "frames.h"

Frames::Frames() {
	//Contador del número de frames cargados
	numframes=0; 
	load_all_frames();
}

void Frames::load_all_frames() {
	//TODO: Se define de forma predeterminada
	load_frame("rec/tile1.bmp");
	load_frame("rec/tile2.bmp");
	load_frame("rec/tile3.bmp");
	load_frame("rec/nave.bmp");
	load_frame("rec/enemigo.bmp");
}

void Frames::load_frame(const char *path) {
	img[numframes] = SDL_LoadBMP(path);
	//Asignamos el color transparente
	SDL_SetColorKey(img[numframes],SDL_SRCCOLORKEY|SDL_RLEACCEL, SDL_MapRGB(img[numframes]->format,255,0,255));
	numframes++;
}

void Frames::unload() {
	int i;
	for (i=0;i<numframes;i++) SDL_FreeSurface(img[i]);
}
