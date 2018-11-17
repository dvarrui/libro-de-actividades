#ifndef PANTALLA_H_
#define PANTALLA_H_

#include <SDL/SDL.h>

class Pantalla {
public:
	int ancho;
	int alto;
	int profundidadcolor;
	char titulo[100];
	
private:
	SDL_Surface *screen;

public:
	Pantalla();
	
};

#endif /* PANTALLA_H_ */
