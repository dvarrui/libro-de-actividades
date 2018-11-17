
#include <stdio.h>
#include <stdlib.h>
#include <SDL/SDL.h>
#include <SDL/SDL_ttf.h>


int main(int argc, char *argv[]) {

	SDL_Color bgcolor,fgcolor;
	SDL_Rect rectangulo;
	SDL_Surface *screen, *ttext;
	TTF_Font *fuente;
	const char texto[14]="Hola Mundo...";
	char msg[14];
	SDL_Event event;
	int done=0;

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
	
	atexit(SDL_Quit);
	
	//Inicializar ttf
	if(TTF_Init()<0) {
		printf("No pude iniciar SDL_ttf: %s\n",SDL_GetError());
		exit(1);
	}
	
	atexit(TTF_Quit);
	
	//cargar la fuente
	fuente=TTF_OpenFont("FreeSans.ttf",20);
	if (!fuente) {
		printf("No se pudo cargar fichero TTF");
		exit(1);
	}
	
	//Inicializar colores para el texto
	fgcolor.r=200;
	fgcolor.g=200;
	fgcolor.b=200;
	
	bgcolor.r=255;
	bgcolor.g=0;
	bgcolor.b=0;
	
	sprintf(msg,"%s",texto);
	ttext=TTF_RenderText_Shaded(fuente,msg,fgcolor,bgcolor);
	rectangulo.y=100;
	rectangulo.x=100;
	rectangulo.w=ttext->w;
	rectangulo.h=ttext->h;
	
	SDL_SetColorKey(ttext,SDL_SRCCOLORKEY|SDL_RLEACCEL,SDL_MapRGB(ttext->format,255,0,0));
	
	SDL_BlitSurface(ttext,NULL,screen,&rectangulo);
	SDL_Flip(screen);
	
	TTF_CloseFont(fuente);
	SDL_FreeSurface(ttext);
	
	//Esperamos
	printf("Pulsar para parar\n");
	
	done=0;
	while(!done) {
		if (SDL_PollEvent(&event)==0) {
			if (event.type==SDL_KEYDOWN)
				done=1;
		}
	}

	return 0;
}
