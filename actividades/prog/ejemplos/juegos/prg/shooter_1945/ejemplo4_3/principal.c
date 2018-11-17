
#include <stdio.h>
#include <stdlib.h>
#include <SDL/SDL.h>
#include <SDL/SDL_mixer.h>


int main(int argc, char *argv[]) {

	SDL_Surface *image, *screen;
	SDL_Rect dest;
	SDL_Event event;
	Mix_Chunk *sonido;
	int canal;
	int done=0;

	atexit(SDL_Quit);

	//Iniciar SDL
	if (SDL_Init(SDL_INIT_VIDEO)<0) {
		printf("No se pudo iniciar SDL: %s\n",SDL_GetError());
	}
	
	screen=SDL_SetVideoMode(640,480,24,SDL_HWSURFACE|SDL_DOUBLEBUF);
	if(screen==NULL) {
		printf("No se puede inicializar el modo gráfico: %s\n",SDL_GetError());
		exit(1);
	}
	
	SDL_WM_SetCaption("Ejemplo4_3",NULL);
	
	//Iniciamos el SDL_mixer
	if(Mix_OpenAudio(22050,AUDIO_S16, 2, 4096)) {
		printf("No se puede iniciar SDL_mixer %s\n", Mix_GetError());
		exit(1);
	}
	
	atexit(Mix_CloseAudio);
	
	//cargamos el sonido
	sonido = Mix_LoadWAV("explosion.wav");
	if(sonido==NULL) {
		printf("No se puede cargar el sonido: %s\n", Mix_GetError());
		exit(1);
	}
	
	canal = Mix_PlayChannel(-1,sonido,0);
	
	//Esperamos
	printf("\nPulsar para parar\n");
	
	done=0;
	while(!done) {
		if (SDL_PollEvent(&event)==0) {
			if (event.type==SDL_KEYDOWN)
				done=1;
		}
	}
	
	Mix_FreeChunk(sonido);
	return 0;
}
