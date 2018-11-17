
#include "sprite.h"
#include "frames.h"

Sprite::Sprite() {
	inicializar(NULL,1);
}

//Crea un sprite con 1 frame
Sprite::Sprite(Frames *fr) {
	inicializar(fr,1);
}

//Crea un sprite con nf frames
Sprite::Sprite(Frames *fr, int nf) {
	inicializar(fr,nf);
}

void Sprite::inicializar(Frames *fr, int nf) {
	frames = fr;
	sprite_frame_ids = new int[nf];
	numframes = nf;
	cont = 0;
}

void Sprite::add_frame_id(int id) {
	current_frame_id=cont;
	if (cont<numframes) {
		sprite_frame_ids[cont]=id;
		cont++;
	}
}

void Sprite::set_current_frame_id(int cf) {
	if (cf>=0 && cf<=numframes) 
		current_frame_id=cf;
}

int Sprite::get_frame_id() {
	return sprite_frame_ids[current_frame_id]; 
}
	
SDL_Surface* Sprite::get_frame() {
	//int id=sprite_frames[current_frame];
	//return frames->img[id];
	return frames->img[get_frame_id()];
}

int Sprite::getw() {
	int id=sprite_frame_ids[current_frame_id];
	return frames->img[id]->w;
}

int Sprite::geth() {
	int id=sprite_frame_ids[current_frame_id];
	return frames->img[id]->h;
}

int Sprite::colision(Sprite sp) {
	int w1,h1,w2,h2,x1,y1,x2,y2;
	int dx,dy,dw,dh;
	
	w1=getw(); 		h1=geth();
	w2=sp.getw(); 	h2=sp.geth();
	x1=getx(); 		y1=gety();
	x2=sp.getx();	y2=sp.gety();
	
	if (((x1+w1)>x2)&&((y1+h1)>y2)&&((x2+w2)>x1)&&((y2+h2)>y2)) {
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

