#ifndef SPRITE_H_
#define SPRITE_H_

#include <SDL/SDL.h>
#include "frames.h"

#define TRUE 1
#define FALSE 0

//La clase Sprite es una secuencia de frames
class Sprite {
private:
	Frames *frames;
	int posx, posy;
	int *sprite_frame_ids;
	int current_frame_id;
	int numframes;
	int cont; //variable auxiliar utilizada para contabilidad lo frames cargados en el sprite

public:
	Sprite();
	Sprite(Frames *fr);
	Sprite(Frames *fr,int nf);
	void inicializar(Frames *fr, int nf);
	void finalize(); 
	void add_frame_id(int id);
	void set_current_frame_id(int cf);
	void setx(int x) { posx=x; }
	void sety(int y) { posy=y; }
	void addx(int c) { posx+=c; }
	void addy(int c) { posy+=c; }
	int get_frame_id();
	SDL_Surface* get_frame();
	int getx() { return posx; }
	int gety() { return posy; }
	int getw();
	int geth();
	int colision(Sprite sp);
};

#endif /* SPRITE_H_ */
