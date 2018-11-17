#include <SDL/SDL.h>
#include "frames.h"

Frames::Frames() {
	//Contador del número de frames cargados
	numframes=0; 
	load_all_frames();
}

void Frames::load_all_frames() {
	//TODO: Esta parte variará entre escenas/partidas
	load_frame("rec/tile1.bmp");
	load_frame("rec/tile2.bmp");
	load_frame("rec/tile3.bmp");
	load_frame("rec/nave.bmp");
	load_frame("rec/enemigo.bmp");
}

void Frames::load_frame(const char *path) {
	img[numframes] = SDL_LoadBMP(path);
	prueba = SDL_LoadBMP(path);
	//Asignamos el color transparente
	SDL_SetColorKey(img[numframes],SDL_SRCCOLORKEY|SDL_RLEACCEL, SDL_MapRGB(img[numframes]->format,255,0,255));
	SDL_SetColorKey(prueba,SDL_SRCCOLORKEY|SDL_RLEACCEL, SDL_MapRGB(prueba->format,255,0,255));
	//TODO: ¿por qué da violación de segmento?
	//prueba = SDL_DisplayFormat(img[numframes]);
	//img[numframes]=prueba;
	 
	//prueba = SDL_DisplayFormat(prueba); 
	numframes++;
}

void Frames::unload() {
	int i;
	for (i=0;i<numframes;i++) SDL_FreeSurface(img[i]);
}

/*
//Crea un sprite con nf frames
CSprite::CSprite(int nf) {
	sprite = new CFrame[nf];
	nframes = nf;
	cont = 0;
}

//Crea un sprite con 1 frame
CSprite::CSprite() {
	int nf=1;
	sprite = new CFrame[nf];
	numframes = nf;
	cont = 0;
}

void CSprite::finalize() {
	int i;
	for (i=0;i<=numframes-1;i++) 
		sprite[i].unload();
}

void CSprite::addframe(CFrame frame) {
	if (cont<numframes) {
		sprite[cont]=frame;
		cont++;
	}
}

void CSprite::selframe(int nf) {
	if (nf<=numframes) 
		estado=nf;
}

int CSprite::colision(CSprite sp) {
	int w1,h1,w2,h2,x1,y1,x2,y2;
	int dx,dy,dw,dh;
	
	w1=getw(); 		h1=geth();
	w2=sp.getw(); 	h2=sp.geth();
	x1=getx(); 		y1=gety();
	x2=sp.getx();	y2=sp.gety();
	
	/*if (((x1+w1)>x2)&&((y1+h1)>y2)&&((x2+w2)>x1)&&((y2+h2)>y2)) {
		return TRUE;
	} else {
		return FALSE;
	}
	dw=w1-5;
	if (x1>x2) dw=w2-5;
	dh=h1-5;
	if (y1>y2) dh=h2-5;
	dx = x1-x2;
	if (dx<0) dx=dx*-1;
	dy = y1-y2;
	if (dy<0) dy=dy*-1;
	
	if (dx<=dw && dy<=dh) {
		return TRUE;
	} else {
		return FALSE;
	}
}
*/
