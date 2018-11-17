#include <SDL/SDL.h>
#include "pantalla.h"
#include "frames.h"

Pantalla::Pantalla() {
	//Inicializar los atributos de la clase
	ancho=640;
	alto=480;
	profundidadcolor=24;
	frames = new Frames();
	sprite[0].inicializar(frames,1); sprite[0].add_frame_id(0);
	sprite[0].setx(0);sprite[0].sety(0);
	
	sprite[1].inicializar(frames,1); sprite[1].add_frame_id(1);
	sprite[1].setx(65);sprite[1].sety(0);
	
	sprite[2].inicializar(frames,1); sprite[2].add_frame_id(2);
	sprite[2].setx(0);sprite[2].sety(65);
	
	sprite[3].inicializar(frames,1); sprite[3].add_frame_id(3);
	sprite[3].setx(65);sprite[3].sety(65);
	
	sprite[4].inicializar(frames,1); sprite[4].add_frame_id(4);
	sprite[4].setx(2);sprite[4].sety(2);
	

	//Iniciar SDL
	if (SDL_Init(SDL_INIT_VIDEO|SDL_INIT_JOYSTICK)<0) {
		printf("No se pudo iniciar SDL: %s\n",SDL_GetError());
		exit(1);
	}
	
	screen=SDL_SetVideoMode(ancho,alto,profundidadcolor,SDL_HWSURFACE|SDL_DOUBLEBUF);
	if(screen==NULL) {
		printf("No se puede inicializar el modo gráfico: %s\n",SDL_GetError());
		exit(1);
	}
	
	SDL_WM_SetCaption("Ejemplo7_1b3 sprite",NULL);
	atexit(SDL_Quit);
}

void Pantalla::pintar() {
	pintar_fondo();
	SDL_Flip(screen);
}

void Pantalla::pintar_fondo() {
	SDL_Surface *img;

	pintar_sprite(sprite[0]);
	pintar_sprite(sprite[1]);
	pintar_sprite(sprite[2]);
	pintar_sprite(sprite[3]);
	pintar_sprite(sprite[4]);
}

void Pantalla::finalize() {
	frames->unload();
}

void Pantalla::pintar_sprite(Sprite &sp) {
	dest.x=sp.getx();
	dest.y=sp.gety();
	SDL_BlitSurface(sp.get_frame(),NULL,screen,&dest); 
}

/*
void Pantalla::pintar() {
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
	
}*/

/*
void CSprite::pintar(SDL_Surface *superficie) {
	SDL_Rect dest;
	dest.x=posx;
	dest.y=posy;
	SDL_BlitSurface(sprite[estado].img,NULL,superficie,&dest);
}
*/

