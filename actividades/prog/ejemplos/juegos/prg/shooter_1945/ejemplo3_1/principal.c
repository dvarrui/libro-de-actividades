#include <stdio.h>
#include <stdlib.h>
#include <SDL/SDL.h>

int main(int argc, char *argv[]) {
	SDL_Surface *image, *screen;
	SDL_Rect dest;
	SDL_Event event;
	int done=0;
	
	atexit(SDL_Quit);
	
	//Iniciar SDL
	if (SDL_Init(SDL_INIT_VIDEO)<0) {
		printf("No se pudo iniciar SDL: %s\n",SDL_GetError());
	}
	
	//Activamos modo de vídeo
	screen=SDL_SetVideoMode(640,480,24,SDL_HWSURFACE);
	if(screen==NULL) {
		printf("No se puede inicializar el modo gráfico: %s\n",SDL_GetError());
		exit(1);
	}
	
	//Cargamos gráfico
	image=SDL_LoadBMP("nave.bmp");
	if(image==NULL) {
		printf("No pude cargar gráfico: %s\n",SDL_GetError());
		exit(1);
	}
	
	//Dibujar el gráfico
	dest.x=100;
	dest.y=100;
	dest.w=image->w;
	dest.h=image->h;
	SDL_BlitSurface(image,NULL,screen,&dest);
	
	//Mostramos la pantalla
	SDL_Flip(screen);
	
	//Liberar superficie
	SDL_FreeSurface(image);
	
	//Esperamos la pulsación de una tecla para salir
	while(done==0) {
		while(SDL_PollEvent(&event)) {
			if (event.type==SDL_KEYDOWN)
				done=1;
		}
	}
	
	return 0;
}
