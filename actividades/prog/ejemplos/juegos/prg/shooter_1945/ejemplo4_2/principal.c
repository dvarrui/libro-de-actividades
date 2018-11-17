
#include <stdio.h>
#include <stdlib.h>
#include <SDL/SDL.h>
#include <SDL/SDL_image.h>


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
	
	screen=SDL_SetVideoMode(640,480,24,SDL_HWSURFACE|SDL_DOUBLEBUF);
	if(screen==NULL) {
		printf("No se puede inicializar el modo gráfico: %s\n",SDL_GetError());
		exit(1);
	}
	
	SDL_WM_SetCaption("Ejemplo4_1",NULL);
	
	//Cargamos un gráfico
	image = IMG_Load("Luna.jpg");
	if (image==NULL) {
		printf("No se puede cargar el gráfico: %s\n", SDL_GetError());
		exit(1);
	}

	//dónde lo dibujamos?	
	dest.x=0;
	dest.y=0;
	dest.w=image->w;
	dest.h=image->h;
	SDL_BlitSurface(image,NULL,screen,&dest);
	
	SDL_Flip(screen);	
	SDL_FreeSurface(image);
	
	//Esperamos
	printf("\nPulsar para parar\n");
	
	done=0;
	while(!done) {
		if (SDL_PollEvent(&event)==0) {
			if (event.type==SDL_KEYDOWN)
				done=1;
		}
	}

	return 0;
}
