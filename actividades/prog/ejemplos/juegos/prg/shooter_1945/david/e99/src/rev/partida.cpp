
#define MAXMAP	400
char mapa[401];
int done=0;

Partida::Partida() {
};

void Partida::inicializarSDL() {
}

void Partida::inicializarPersonajes() {
	int c;
	
	jugador.x=300;
	jugador.y=300;
	enemigo.x=100;
	enemigo.y=100;
	
	indice=MAXMAP-100;
	indice_in=0;
	
	if((f=fopen("rec/mapa.map","r"))!=NULL) {
		c=fread(mapa,MAXMAP,1,f);
		fclose(f);
	}
}

void Partida::finalizar() {
	nave.finalize();
	malo.finalize();
	suelo[0].finalize();
	suelo[1].finalize();
	suelo[2].finalize();	
	
	if(SDL_JoystickOpened(0)) {
		SDL_JoystickClose(joystick);
	}
}
