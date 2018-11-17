#include <SDL/SDL.h>
#include "entrada.h"

void Entrada::pulsar_tecla_esc() {
	Uint8 *keys;
	SDL_Event event;
	int fin=0;

	keys=SDL_GetKeyState(NULL);
		
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
