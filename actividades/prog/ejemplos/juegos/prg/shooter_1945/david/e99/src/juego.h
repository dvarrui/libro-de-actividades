#ifndef JUEGO_H_
#define JUEGO_H_

#include "pantalla.h"
#include "entrada.h"

#define TRUE 1
#define FALSE 0

class Juego {
public:
	Pantalla pantalla;
	Entrada entrada;
	int fin_partida;
	
public:
	Juego();
	void iniciar();
	void finalize();
};

#endif /* JUEGO_H_ */
