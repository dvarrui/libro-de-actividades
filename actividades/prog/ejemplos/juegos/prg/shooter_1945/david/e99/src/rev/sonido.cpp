#include <SDL/SDL_mixer.h>
#include <unistd.h> //Se usa para la función sleep()

FILE *f;
Mix_Chunk *sonido;
int canal;


	//Iniciamos el SDL_mixer
	if(Mix_OpenAudio(22050,AUDIO_S16, 2, 4096)) {
		printf("No se puede iniciar SDL_mixer %s\n", Mix_GetError());
		exit(1);
	}
	atexit(Mix_CloseAudio);

	//cargamos el sonido
	sonido = Mix_LoadWAV("rec/explosion.wav");
	if(sonido==NULL) {
		printf("No se puede cargar el sonido: %s\n", Mix_GetError());
		exit(1);
	}
	
		canal = Mix_PlayChannel(-1,sonido,0);	
	sleep(1);
