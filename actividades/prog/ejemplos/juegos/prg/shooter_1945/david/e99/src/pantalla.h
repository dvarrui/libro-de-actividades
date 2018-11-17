#ifndef PANTALLA_H_
#define PANTALLA_H_

#include "frames.h"
#include "sprite.h"

//Pantalla
class Pantalla {
public:
	int ancho;
	int alto;
	int profundidadcolor;
	char titulo[100];
	int indice, indice_in;
	Frames *frames;
	Sprite sprite[10];
	
private:
	SDL_Surface *screen;
	SDL_Rect dest;

public:
	Pantalla();
	void pintar();
	void finalize();
	
private:
	void pintar_fondo();
	void pintar_sprite(Sprite &sp);
};

#endif /* PANTALLA_H_ */
