
#include "pantalla.h"
#include "entrada.h"

int main(int argc, char *argv[]) {

	Pantalla pantalla;
	Entrada entrada;
	
	pantalla.pintar();
	entrada.pulsar_tecla_esc();
	
	pantalla.finalize();
	return 0;
}
