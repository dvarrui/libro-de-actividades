

#include <stdlib.h>
#include <SDL/SDL.h>

SDL_Surface* superficie;
SDL_Event event;
SDL_CD *cdrom;
int pista=1;
int done;

int main(int argc, char *argv[]) {

	//Iniciar SDL
	if (SDL_Init(SDL_INIT_VIDEO|SDL_INIT_CDROM)<0) {
		printf("No se pudo iniciar SDL: %s\n",SDL_GetError());
	}
	
	//Activamos modo de vídeo
	if((superficie=SDL_SetVideoMode(640,480,0,SDL_ANYFORMAT))==NULL) {
		printf("No se puede inicializar el modo gráfico: %s\n",SDL_GetError());
		exit(1);
	}
	
	//Abrimos el primer CDROM del sistema
	if((cdrom=SDL_CDOpen(0))<0) {
		printf("No pude abrir el cdrom: %s\n",SDL_GetError());
		exit(1);
	}
	
	//Status cd CD
	SDL_CDStatus(cdrom);
	
	//Iniciamos reproducción de la primera pista
	if ((SDL_CDPlay(cdrom,cdrom->track[pista].offset,cdrom->track[pista].length))<0) {
		printf("No puedo reproducir el CD\n");
		exit(1);
	}	
	
	//Esperamos
	printf("Pulsar para parar\n");
	
	done=0;
	while(!done) {
		if (SDL_PollEvent(&event)==0) {
			if (event.type==SDL_QUIT)
				done=1;
		}
	}

	//Detener el cdrom
	SDL_CDStop(cdrom);
	SDL_CDClose(cdrom);
	
	return 0;
}

//Función de retrollamada
void retrollamada(void* userdata,Uint8* buffer,int len) {
	int i;
	for(i=0;i<len;i++)
		buffer[i]=rand()%256;
}
