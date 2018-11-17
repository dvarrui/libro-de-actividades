
#include <stdio.h>
#include <stdlib.h>
#include <SDL/SDL.h>
#include <SDL/SDL_mixer.h>


int main(int argc, char *argv[]) {

	SDL_Surface *image, *screen;
	SDL_Rect dest;
	SDL_Event event;
	Mix_Music *musica;
	int canal;
	int done=0;

	atexit(SDL_Quit);

	//Iniciar SDL
	if (SDL_Init(SDL_INIT_VIDEO|SDL_INIT_AUDIO)<0) {
		printf("No se pudo iniciar SDL: %s\n",SDL_GetError());
	}
	
	screen=SDL_SetVideoMode(640,480,24,SDL_HWSURFACE|SDL_DOUBLEBUF);
	if(screen==NULL) {
		printf("No se puede inicializar el modo gráfico: %s\n",SDL_GetError());
		exit(1);
	}
	
	SDL_WM_SetCaption("Ejemplo4_4",NULL);
	
	//Iniciamos el SDL_mixer
	if(Mix_OpenAudio(22050,AUDIO_S16, 2, 4096)) {
		printf("No se puede iniciar SDL_mixer %s\n", Mix_GetError());
		exit(1);
	}
	
	atexit(Mix_CloseAudio);
	
	//cargamos el sonido
	musica = Mix_LoadMUS("paradise_city.ogg");
	if(musica==NULL) {
		printf("No se puede cargar la música: %s\n", Mix_GetError());
		exit(1);
	}
	
	canal = Mix_PlayMusic(musica,-1);
	
	//Esperamos
	printf("\nPulsar para parar\n");
	
	done=0;
	while(!done) {
		if (SDL_PollEvent(&event)==0) {
			if (event.type==SDL_KEYDOWN)
				done=1;
		}
	}
	
	Mix_HaltMusic();
	Mix_FreeMusic(musica);
	
	return 0;
}
