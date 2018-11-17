#include "juego.h"
#include "pantalla.h"
#include "entrada.h"

Juego::Juego() {
	fin_partida=FALSE;
}

void Juego::iniciar() {	
	pantalla.pintar();
	entrada.pulsar_tecla();
	finalize();
}

void Juego::finalize() {
	pantalla.finalize();
	entrada.finalize();
}
