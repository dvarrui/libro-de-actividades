#include <SDL/SDL.h>
#include "pantalla.h"

Pantalla::Pantalla() {
	ancho=640;
	alto=480;
	profundidadcolor=24;
	strcpy(titulo,"ejemplo01-pantalla");

	//Iniciar SDL
	if (SDL_Init(SDL_INIT_VIDEO|SDL_INIT_JOYSTICK)<0) {
		printf("No se pudo iniciar SDL: %s\n",SDL_GetError());
		exit(1);
	}
	
	screen=SDL_SetVideoMode(ancho,alto,profundidadcolor,SDL_HWSURFACE|SDL_DOUBLEBUF);
	if(screen==NULL) {
		printf("No se puede inicializar el modo gráfico: %s\n",SDL_GetError());
		exit(1);
	}
	
	SDL_WM_SetCaption(titulo,NULL);
	atexit(SDL_Quit);
}
