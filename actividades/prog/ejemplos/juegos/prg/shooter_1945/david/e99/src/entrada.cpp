#include <SDL/SDL.h>
#include "entrada.h"

Entrada::Entrada() {
	if (SDL_NumJoysticks()>=1) {
		padstick = SDL_JoystickOpen(1);
		joystick = SDL_JoystickOpen(0);
		SDL_JoystickEventState(SDL_ENABLE);
	}
}

void Entrada::pulsar_tecla() {
	keys=SDL_GetKeyState(NULL);
	int fin=0;
		
	while(!fin) {
		while(SDL_PollEvent(&event)) {
			if (event.type==SDL_QUIT) {fin=1;}
			if (event.type==SDL_KEYDOWN||event.type==SDL_JOYBUTTONDOWN) {
				if(event.key.keysym.sym==SDLK_ESCAPE) {
				 fin=1;
				}
			}
		}
	}
}

void Entrada::finalize() {
		if(SDL_JoystickOpened(0)) {
		SDL_JoystickClose(joystick);
	}
}

/*
	keys=SDL_GetKeyState(NULL);
	SDL_JoystickUpdate();
	joyx=SDL_JoystickGetAxis(joystick,4);
	joyy=SDL_JoystickGetAxis(joystick,5);
		
	int d=0;
		
	if ((keys[SDLK_UP]    || joyy<-d)&&(jugador.y>0))   {jugador.y=jugador.y-5;}
	if ((keys[SDLK_DOWN]  || joyy>d) &&(jugador.y<460)) {jugador.y=jugador.y+5;}
	if ((keys[SDLK_LEFT]  || joyx<-d)&&(jugador.x>0))   {jugador.x=jugador.x-5;}
	if ((keys[SDLK_RIGHT] || joyx>d) &&(jugador.x<620)) {jugador.x=jugador.x+5;}
		
	while(SDL_PollEvent(&event)) {
		if (event.type==SDL_QUIT) {done=1;}
		if (event.type==SDL_KEYDOWN||event.type==SDL_JOYBUTTONDOWN) {
		if(event.key.keysym.sym==SDLK_ESCAPE) {
				done=1;
			}
			}
		}
}*/


	
