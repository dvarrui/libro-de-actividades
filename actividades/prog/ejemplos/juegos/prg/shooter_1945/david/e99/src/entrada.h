#ifndef ENTRADA_H_
#define ENTRADA_H_

#include <SDL/SDL.h>

class Entrada {
private:	
	SDL_Joystick *padstick;
	SDL_Joystick *joystick;
	int joyx, joyy;
	int padx, pady;
	Uint8 *keys;
	SDL_Event event;

public:
	Entrada();
	void pulsar_tecla();
	void finalize();
};
		
#endif /* ENTRADA_H_ */
