#ifndef PANTALLA_H_
#define PANTALLA_H_

#include <SDL/SDL.h>
#include "frames.h"

//Pantalla
class Pantalla {
public:
	int ancho;
	int alto;
	int profundidadcolor;
	char titulo[100];
	Frames frames;
	
private:
	SDL_Surface *screen;
	
public:
	Pantalla();
	void pintar();
	void finalize();
	
private:
	void pintar_fondo();
};


#endif /* PANTALLA_H_ */
