#include <SDL/SDL.h>
#include "entrada.h"

//Espera hasta que se pulsa la tecla ESC
void Entrada::pulsar_tecla_esc() {
	SDL_Event event;
	Uint8 *keys;
	int fin=0;
	
	keys=SDL_GetKeyState(NULL);
		
	while(!fin) {
		while(SDL_PollEvent(&event)) {
			if (event.type==SDL_QUIT) {fin=1;}
			if (event.type==SDL_KEYDOWN) {
				if(event.key.keysym.sym==SDLK_ESCAPE) {
				 fin=1;
				}
			}
		}
	}
}
