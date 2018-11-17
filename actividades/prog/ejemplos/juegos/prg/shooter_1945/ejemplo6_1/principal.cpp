/***
 * ejemplo6_1
 ***/

#include <stdio.h>
#include <stdlib.h>
#include <SDL/SDL.h>
#include "csprite.h"
#include <SDL/SDL_mixer.h>
#include <unistd.h>

//Mapa de tiles
int mapa[100]= {0,0,0,0,0,0,0,0,0,0,
				0,0,0,0,0,2,0,0,0,0,
				0,0,1,0,0,0,0,1,0,0,
				2,0,0,0,0,0,0,0,0,0,
				0,0,0,0,1,0,0,0,2,0,
				0,0,0,0,0,0,0,0,0,0,
				0,2,0,0,0,0,0,0,0,0,
				0,0,0,0,0,0,0,1,0,0,
				0,0,1,0,0,0,0,0,0,0,
				0,0,0,0,0,0,0,0,0,0};
				
SDL_Surface *screen;
CFrame fnave, fmalo;
CFrame tile1, tile2, tile3;
CSprite nave(1), malo(1), suelo[3];
SDL_Rect rectangulo;
SDL_Joystick *joystick;
int joyx, joyy;
int done=0;
Mix_Chunk *sonido;
int canal;

struct minave {
	int x,y;
} jugador;

struct naveenemiga {
	int x,y;
} enemigo;

void drawScene(SDL_Surface *screen) {
	int i,j,x,y,t;
	
	//Dibujar escenario
	for(i=0;i<10;i++) {
		for(j=0;j<10;j++) {
			t=mapa[i*10+j];
			x=j*64;
			y=(i-1)*64;
			
			suelo[t].setx(x);
			suelo[t].sety(y);
			suelo[t].draw(screen);
		}
	}
	
	//Dibuja nave
	nave.setx(jugador.x);
	nave.sety(jugador.y);
	nave.draw(screen);
	
	//Dibuja enemigo
	malo.setx(enemigo.x);
	malo.sety(enemigo.y);
	malo.draw(screen);
	
	//colisión?
	if(malo.colision(nave)==TRUE) {
		done=1;
	}
	SDL_Flip(screen);
}

void inicializa() {
	jugador.x=300;
	jugador.y=300;
	enemigo.x=100;
	enemigo.y=100;
}

void finaliza() {
	nave.finalize();
	malo.finalize();
	suelo[0].finalize();
	suelo[1].finalize();
	suelo[2].finalize();	
	
	if(SDL_JoystickOpened(0)) {
		SDL_JoystickClose(joystick);
	}
}

int initSprites() {
	int err=0;
	
	fnave.load("nave.bmp");
	nave.addframe(fnave);
	
	fmalo.load("enemigo.bmp");
	malo.addframe(fmalo);
	
	tile1.load("tile1.bmp");
	suelo[0].addframe(tile1);
	
	tile2.load("tile2.bmp");
	suelo[1].addframe(tile2);

	tile3.load("tile3.bmp");
	suelo[2].addframe(tile3);

	return 0;
}

int main(int argc, char *argv[]) {
	SDL_Event event;
	Uint8 *keys;
	
	//Iniciar SDL
	if (SDL_Init(SDL_INIT_VIDEO|SDL_INIT_JOYSTICK)<0) {
		printf("No se pudo iniciar SDL: %s\n",SDL_GetError());
		return 1;
	}
	
	screen=SDL_SetVideoMode(640,480,24,SDL_HWSURFACE|SDL_DOUBLEBUF);
	if(screen==NULL) {
		printf("No se puede inicializar el modo gráfico: %s\n",SDL_GetError());
		return 1;
	}

	if (SDL_NumJoysticks()>=1) {
		joystick = SDL_JoystickOpen(0);
		SDL_JoystickEventState(SDL_ENABLE);
	}
	
	SDL_WM_SetCaption("Ejemplo6_1",NULL);
	atexit(SDL_Quit);
	
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
	
	inicializa();
	initSprites();
	
	while(done==0) {
		drawScene(screen);
		keys=SDL_GetKeyState(NULL);
		SDL_JoystickUpdate();
		joyx=SDL_JoystickGetAxis(joystick,4);
		joyy=SDL_JoystickGetAxis(joystick,5);
		
		int d=0;
		
		if ((keys[SDLK_UP]    || joyy<-d)&&(jugador.y>0))   {jugador.y=jugador.y-5;}
		if ((keys[SDLK_DOWN]  || joyy>d) &&(jugador.y<460)) {jugador.y=jugador.y+5;}
		if ((keys[SDLK_LEFT]  || joyx<-d)&&(jugador.x>0))   {jugador.x=jugador.x-5;}
		if ((keys[SDLK_RIGHT] || joyx>d) &&(jugador.x<620)) {jugador.x=jugador.x+5;}
		
		while(SDL_PollEvent(&event)) {
			if (event.type==SDL_QUIT) {done=1;}
			if (event.type==SDL_KEYDOWN||event.type==SDL_JOYBUTTONDOWN) {
				if(event.key.keysym.sym==SDLK_ESCAPE) {
					done=1;
				}
			}
		}
	}
	canal = Mix_PlayChannel(-1,sonido,0);	
	sleep(1);
	
	finaliza();
	return 0;
}
