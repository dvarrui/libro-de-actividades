#include <SDL/SDL.h>
#include "pantalla.h"
#include "frames.h"

Pantalla::Pantalla() {
	//Inicializar los atributos de la clase
	ancho=640;
	alto=480;
	profundidadcolor=24;
	strcpy(titulo,"ejemplo03-sprite");
	frames = new Frames();
	
	//De forma predeterminada se definen los siguientes sprites
	sprite[0].inicializar(frames,1); sprite[0].add_frame_id(0);
	sprite[0].setx(0);sprite[0].sety(0);
	
	sprite[1].inicializar(frames,1); sprite[1].add_frame_id(1);
	sprite[1].setx(65);sprite[1].sety(0);
	
	sprite[2].inicializar(frames,1); sprite[2].add_frame_id(2);
	sprite[2].setx(0);sprite[2].sety(65);
	
	sprite[3].inicializar(frames,1); sprite[3].add_frame_id(3);
	sprite[3].setx(65);sprite[3].sety(65);
	
	sprite[4].inicializar(frames,1); sprite[4].add_frame_id(4);
	sprite[4].setx(2);sprite[4].sety(2);
	

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
	//De forma predeterminada se define el siguiente fondo
	pintar_sprite(sprite[0]);
	pintar_sprite(sprite[1]);
	pintar_sprite(sprite[2]);
	pintar_sprite(sprite[3]);
	pintar_sprite(sprite[4]);
}

void Pantalla::finalize() {
	frames->unload();
}

void Pantalla::pintar_sprite(Sprite &sp) {
	SDL_Rect dest;
	dest.x=sp.getx();
	dest.y=sp.gety();
	SDL_BlitSurface(sp.get_frame(),NULL,screen,&dest); 
}
