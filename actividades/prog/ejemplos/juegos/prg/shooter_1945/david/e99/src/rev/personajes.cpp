CFrame fnave, fmalo, tile1, tile2, tile3, labala;
CFrame ex1, ex2, ex3, ex4, ex5, ex6, ex7;
CSprite nave(1), malo(1), suelo[3], mibala(1), explode(8);

#define MAXBALAS 8

struct minave {
	int x,y;
} jugador;

struct naveenemiga {
	int x,y, estado;
} enemigo;

struct disparo {
	int x,y;
} bala[MAXBALAS+1];

struct explosion {
	int activo,x,y,nframe;
}

void muevenave() {
	if(enemigo.estado==1) {
		enemigo.x=enemigo.x+2;
		if (enemigo.x>600) enemigo.estado=2;
	}
	
	if (enemigo.estado==2) {
		enemigo.x=enemigo.x-2;
		if(enemigo.x<40) enemigo.estado=1;
	}
}

void muevebalas() {
	int i;
	for(i=0;i<=MAXBALAS;i++) {
		if(bala[i].x!=0) {
			bala[i].y=bala[i].y-5;
			if(bala[i].y<0) {
				bala[i].x=0;
			}
		}
	}
}


int initSprites() {
	int err=0;
	
	fnave.load("rec/nave.bmp");	nave.addframe(fnave);
	fmalo.load("rec/enemigo.bmp"); malo.addframe(fmalo);
	tile1.load("rec/tile1.bmp"); suelo[0].addframe(tile1);
	tile2.load("rec/tile2.bmp"); suelo[1].addframe(tile2);
	tile3.load("rec/tile3.bmp"); suelo[2].addframe(tile3);

	return 0;
}

