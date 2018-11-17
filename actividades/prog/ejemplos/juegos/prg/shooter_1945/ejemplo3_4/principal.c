

#include <stdlib.h>
#include <SDL/SDL.h>

SDL_Surface* superficie;
SDL_Event event;
SDL_AudioSpec* deseado;
SDL_AudioSpec* obtenido;
int done;

//Declaración de la función de retro llamada
void retrollamada(void* userdata,Uint8* buffer,int len);

int main(int argc, char *argv[]) {

	//Iniciar SDL
	if (SDL_Init(SDL_INIT_VIDEO|SDL_INIT_AUDIO)<0) {
		printf("No se pudo iniciar SDL: %s\n",SDL_GetError());
	}
	
	//Activamos modo de vídeo
	if((superficie=SDL_SetVideoMode(640,480,0,SDL_ANYFORMAT))==NULL) {
		printf("No se puede inicializar el modo gráfico: %s\n",SDL_GetError());
		exit(1);
	}
	
	//Alojamos espacio para las estructuras
	deseado= new SDL_AudioSpec();
	obtenido= new SDL_AudioSpec();
		
	//Especificaciones deseadas
	deseado->freq=11025;
	deseado->format=AUDIO_S16SYS;
	deseado->channels=1;
	deseado->samples=4096;
	deseado->callback=retrollamada;
	deseado->userdata=NULL;
	
	//Abrimos el dispositivo de audio
	if(SDL_OpenAudio(deseado,obtenido)<0) {
		printf("No pude abrir el audio: %s\n",SDL_GetError());
		delete deseado;
		delete obtenido;
		exit(1);
	}
	
	atexit(SDL_CloseAudio);
	
	//Empieza a sonar
	SDL_PauseAudio(0);
	
	//Esperamos
	done=0;
	while(!done) {
		if (SDL_PollEvent(&event)==0) {
			if (event.type==SDL_QUIT)
				done=1;
		}
	}

	delete deseado;
	
	return 0;
}

//Función de retrollamada
void retrollamada(void* userdata,Uint8* buffer,int len) {
	int i;
	for(i=0;i<len;i++)
		buffer[i]=rand()%256;
}
