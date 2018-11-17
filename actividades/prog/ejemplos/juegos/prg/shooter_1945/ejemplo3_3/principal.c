#include <stdio.h>
#include <stdlib.h>
#include <SDL/SDL.h>

int main(int argc, char *argv[]) {
	SDL_Surface *image, *screen;
	SDL_Rect dest;
	SDL_Event event;
	Sint16 joyx, joyy;
	SDL_Joystick *joystick;
	int done=0;
	int x,y,button;
	Uint8 *keys;

	atexit(SDL_Quit);
	
	//Iniciar SDL
	if (SDL_Init(SDL_INIT_VIDEO|SDL_INIT_JOYSTICK)<0) {
		printf("No se pudo iniciar SDL: %s\n",SDL_GetError());
	}
	
	//Activamos modo de vídeo
	screen=SDL_SetVideoMode(640,480,24,SDL_HWSURFACE);
	if(screen==NULL) {
		printf("No se puede inicializar el modo gráfico: %s\n",SDL_GetError());
		exit(1);
	}
	
	//Activar el Joystick
	if(SDL_NumJoysticks()>=1) {
		joystick=SDL_JoystickOpen(0);
		SDL_JoystickEventState(SDL_ENABLE);
	}
		
	//Cargamos gráfico
	image=SDL_LoadBMP("nave.bmp");
	if(image==NULL) {
		printf("No pude cargar gráfico: %s\n",SDL_GetError());
		exit(1);
	}
	
	//Definimos color para la trasparencia
	SDL_SetColorKey(image,SDL_SRCCOLORKEY|SDL_RLEACCEL,SDL_MapRGB(image->format,255,0,255));
	
	x=y=100;
	button=0;
	
	while(done==0) {
		//Borramos la pantalla
		dest.x=0;
		dest.y=0;
		dest.w=640;
		dest.h=480;
		SDL_FillRect(screen,&dest,SDL_MapRGB(screen->format,0,0,100));
		
		//Dibujar el gráfico
		dest.x=x;
		dest.y=y;
		dest.w=image->w;
		dest.h=image->h;
		SDL_BlitSurface(image,NULL,screen,&dest);
		
		//Mostramos la pantalla
		SDL_Flip(screen);

		//Lectura directa del joystick
		joyx=SDL_JoystickGetAxis(joystick,0);
		joyy=SDL_JoystickGetAxis(joystick,1);
		
		/*if (joyy<-10) {y-=5;}
		if (joyy>10) {y+=5;}
		if (joyx<-10) {x-=5;}
		if (joyx>10) {x+=5;}*/
		
		keys=SDL_GetKeyState(NULL);

		if(keys[SDLK_UP] && y>50) {y-=5;}
		if(keys[SDLK_DOWN] && y<430) {y+=5;}
		if(keys[SDLK_LEFT] && x>50) {x-=5;}
		if(keys[SDLK_RIGHT] && x<590) {x+=5;}
		
		//Lectura de eventos
		while(SDL_PollEvent(&event)) {
			if (event.type==SDL_JOYBUTTONDOWN)
				done=1;
			if (event.type==SDL_QUIT)
				done=1;
			//if (event.type==SDL_QUIT)
			//	done=1;
		}
	}

	//Liberamos superficie
	SDL_FreeSurface(image);

	//Cerramos el josytick
	if(SDL_JoystickOpened(0)) {
		SDL_JoystickClose(joystick);
	}
	
	return 0;
}
