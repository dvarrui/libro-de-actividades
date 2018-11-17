/***
 * ejemplo5_1
 ***/

#include <stdio.h>
#include <stdlib.h>
#include <SDL/SDL.h>
#include "csprite.h"

SDL_Surface *screen;
CFrame fnave, fmalo;
CSprite nave(1), malo(1);
SDL_Rect rectangulo;
SDL_Joystick *joystick;
int joyx, joyy;
int done=0;

struct minave {
	int x,y;
} jugador;

struct naveenemiga {
	int x,y;
} enemigo;

void drawScene(SDL_Surface *screen) {
	//Borramos nave
	rectangulo.x=nave.getx();
	rectangulo.y=nave.gety();
	rectangulo.w=nave.getw();
	rectangulo.h=nave.geth();
	SDL_FillRect(screen,&rectangulo,SDL_MapRGB(screen->format,0,0,0));
	
	//Dibuja nave
	nave.setx(jugador.x);
	nave.sety(jugador.y);
	nave.draw(screen);
	
	//Dibuja enemigo
	malo.setx(enemigo.x);
	malo.sety(enemigo.y);
	malo.draw(screen);
	
	//colisión?
	if(malo.colision(nave)==TRUE) {
		done=1;
	}
	SDL_Flip(screen);
}

void inicializa() {
	jugador.x=300;
	jugador.y=300;
	enemigo.x=100;
	enemigo.y=100;
}

void finaliza() {
	nave.finalize();
	malo.finalize();
	
	if(SDL_JoystickOpened(0)) {
		SDL_JoystickClose(joystick);
	}
}

int initSprites() {
	int err=0;
	
	fnave.load("nave.bmp");
	nave.addframe(fnave);
	
	fmalo.load("enemigo.bmp");
	malo.addframe(fmalo);
	
	return 0;
}

int main(int argc, char *argv[]) {
	SDL_Event event;
	Uint8 *keys;
	
	//Iniciar SDL
	if (SDL_Init(SDL_INIT_VIDEO|SDL_INIT_JOYSTICK)<0) {
		printf("No se pudo iniciar SDL: %s\n",SDL_GetError());
		return 1;
	}
	
	screen=SDL_SetVideoMode(640,480,24,SDL_HWSURFACE|SDL_DOUBLEBUF);
	if(screen==NULL) {
		printf("No se puede inicializar el modo gráfico: %s\n",SDL_GetError());
		return 1;
	}

	if (SDL_NumJoysticks()>=1) {
		joystick = SDL_JoystickOpen(0);
		SDL_JoystickEventState(SDL_ENABLE);
	}
	
	SDL_WM_SetCaption("Ejemplo5_1",NULL);
	atexit(SDL_Quit);
	inicializa();
	initSprites();
	
	while(done==0) {
		drawScene(screen);
		keys=SDL_GetKeyState(NULL);
		SDL_JoystickUpdate();
		joyx=SDL_JoystickGetAxis(joystick,0);
		joyy=SDL_JoystickGetAxis(joystick,1);
		
		if ((keys[SDLK_UP]    || joyy<-10)&&(jugador.y>0))   {jugador.y=jugador.y-5;}
		if ((keys[SDLK_DOWN]  || joyy>10) &&(jugador.y<460)) {jugador.y=jugador.y+5;}
		if ((keys[SDLK_LEFT]  || joyx<-10)&&(jugador.x>0))   {jugador.x=jugador.x-5;}
		if ((keys[SDLK_RIGHT] || joyx>10) &&(jugador.x<620)) {jugador.x=jugador.x+5;}
		
		while(SDL_PollEvent(&event)) {
			if (event.type==SDL_QUIT) {done=1;}
			if (event.type==SDL_KEYDOWN||event.type==SDL_JOYBUTTONDOWN) {
				if(event.key.keysym.sym==SDLK_ESCAPE) {
					done=1;
				}
			}
		}
	}
	
	finaliza();
	return 0;
}
