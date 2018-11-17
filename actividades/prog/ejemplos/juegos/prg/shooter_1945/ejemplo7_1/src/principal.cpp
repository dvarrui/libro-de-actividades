/***
 * ejemplo7_1
 ***/

#include <stdio.h>
#include <stdlib.h>
#include <SDL/SDL.h>
#include "csprite.h"
#include <SDL/SDL_mixer.h>
#include <unistd.h> //Se usa para la función sleep()

#define MAXMAP	400
#define MAXBALAS 8
#define APPVERSION "ejemplo7_1"

SDL_Surface *screen;
CFrame fnave, fmalo, tile1, tile2, tile3, labala;
CFrame ex1, ex2, ex3, ex4, ex5, ex6, ex7;
CSprite nave(1), malo(1), suelo[3], mibala(1), explode(8);
SDL_Rect rectangulo;
SDL_Joystick *joystick;
char mapa[401];
int joyx, joyy;
int done=0;
int indice, indice_in;
FILE *f;
Mix_Chunk *sonido;
int canal;

struct minave {
	int x,y;
} jugador;

struct naveenemiga {
	int x,y, estado;
} enemigo;

struct disparo {
	int x,y;
} bala[MAXBALAS+1];

struct explosion {
	int activo,x,y,nframe;
}

void muevenave() {
	if(enemigo.estado==1) {
		enemigo.x=enemigo.x+2;
		if (enemigo.x>600) enemigo.estado=2;
	}
	
	if (enemigo.estado==2) {
		enemigo.x=enemigo.x-2;
		if(enemigo.x<40) enemigo.estado=1;
	}
}

void muevebalas() {
	int i;
	for(i=0;i<=MAXBALAS;i++) {
		if(bala[i].x!=0) {
			bala[i].y=bala[i].y-5;
			if(bala[i].y<0) {
				bala[i].x=0;
			}
		}
	}
}
	
void drawScene(SDL_Surface *screen) {
	int i,j,x,y,t;
	
	//Dibujar escenario
	indice_in+=2;
	if (indice_in>=64) {
		indice_in=0;
		indice-=10;
	}
	
	if (indice<=0) {
		indice=MAXMAP-100;
		indice_in=0;
	}
	
	for(i=0;i<10;i++) {
		for(j=0;j<10;j++) {
			t=mapa[indice+(i*10+j)];
			x=j*64;
			y=(i-1)*64+indice_in;
			
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
	
	//Disparos
	for(i=0;i<=MAXBALAS;i++) {
		if(bala[i].x!=0) {
			mibala.setx(bala[i].x);
			mibala.sety(bala[i].y);
			mibala.draw(screen);
		}
	}
	
	if (exp.activo==1) {
		explode.selframe(exp.nframe);
		explode.setx(exp.x);
		explode.sety(exp.y);
		explode.draw(screen);
		exp.nframe=exp.nframe+1;
		if (exp.nframe>=7) {
			exp.activo=0;
			done=1;
		}
	}
	
	//colisión?
	if(malo.colision(nave)==TRUE) {
		done=1;
	}
	
	if (malo.colision(mibala)==TRUE) {
		enemigo.estado=0;
		exp.activo=1;
		exp.nframe=1;
		exp.x=enemigo.x;
		exp.y=enemigo.y;
	}
	
	SDL_Flip(screen);
}

void inicializa() {
	int c;
	
	jugador.x=300;
	jugador.y=300;
	enemigo.x=100;
	enemigo.y=100;
	
	indice=MAXMAP-100;
	indice_in=0;
	
	if((f=fopen("rec/mapa.map","r"))!=NULL) {
		c=fread(mapa,MAXMAP,1,f);
		fclose(f);
	}
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
	
	fnave.load("rec/nave.bmp");	nave.addframe(fnave);
	fmalo.load("rec/enemigo.bmp"); malo.addframe(fmalo);
	tile1.load("rec/tile1.bmp"); suelo[0].addframe(tile1);
	tile2.load("rec/tile2.bmp"); suelo[1].addframe(tile2);
	tile3.load("rec/tile3.bmp"); suelo[2].addframe(tile3);

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
	
	SDL_WM_SetCaption("Ejemplo6_2",NULL);
	atexit(SDL_Quit);
	
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
