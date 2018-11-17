#include <SDL/SDL.h>
#include "pantalla.h"
#include "frames.h"

Pantalla::Pantalla() {
	ancho=640;
	alto=480;
	profundidadcolor=24;
	strcpy(titulo,"ejemplo02-frames");

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

void Pantalla::pintar() {
	pintar_fondo();
	SDL_Flip(screen);
}

void Pantalla::pintar_fondo() {
	SDL_Rect dest;
	
	//De forma predeterminada se pintan estos frames en la pantalla
	dest.x=0;
	dest.y=0;
	SDL_BlitSurface(frames.img[0],NULL,screen,&dest); 
	dest.x=frames.img[0]->w+1;
	dest.y=0;
	SDL_BlitSurface(frames.img[1],NULL,screen,&dest);
	dest.x=0;
	dest.y=frames.img[0]->h+1;
	SDL_BlitSurface(frames.img[2],NULL,screen,&dest);
	dest.x=frames.img[0]->w+1;
	dest.y=frames.img[0]->h+1;
	SDL_BlitSurface(frames.img[3],NULL,screen,&dest);
	dest.x=0;
	dest.y=0;
	SDL_BlitSurface(frames.img[4],NULL,screen,&dest);
}

void Pantalla::finalize() {
	frames.unload();
}
