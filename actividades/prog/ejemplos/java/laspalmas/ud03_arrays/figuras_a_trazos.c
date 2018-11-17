/*
 * Definir y trabajar con figuras compuestas por trazos
 * S=subir,B=bajar,I=izquierda y D=derecha
 * 
 */
 

#include <stdio.h>
#include <string.h> //Lo requiere strlen()

#define TAMANIO_VECTOR 10

//Variable global

struct tipoFigura {
	char *trazos;
	int escala;
	char primergiro;
	char *cadenalogo;
	char *cadenalogoescalada;
} *figura[TAMANIO_VECTOR];

int totalFiguras;


/* inicializar el vector */
void inicializarFiguraATrazos(void) {
	char *malloc(); //???
	int i;

	for(i=0;i<TAMANIO_VECTOR;i++) {
		figura[i] =(struct tipoFigura *) malloc(sizeof(struct tipoFigura));
		strcpy(figura[i]->trazos,"");
		figura[i]->escala=0;
		figura[i]->primergiro='*';
		figura[i]->cadenalogo=(char *) malloc(100);
		figura[i]->cadenalogoescalada=(char *) malloc(100);
	}
	totalFiguras=0;
}

/* procesar las figuras */
void procesarFiguraATrazos() {
	int i,j;
	char g;
	char *p;
	
	for(i=0;i<TAMANIO_VECTOR;i++) if (figura[i]->escala>0) totalFiguras++;
	
	for(i=0;i<totalFiguras;i++) {
		figura[i]->primergiro= *figura[i]->trazos;
		g=figura[i]->primergiro;
		
		for(j=1;j<strlen(figura[i]->trazos);j++) {
			p=figura[i]->trazos+j;
			if (g==*p) strcat(figura[i]->cadenalogo,"A");
			//else {g=*p; strcat(figura[i]->cadenalogo,*p);}	
		}
	}
}


/* principal */
void principalFigurarATrazos() {
	inicializarFiguraATrazos();
	strcpy(figura[0]->trazos,"DSIB");
	strcpy(figura[1]->trazos,"DDSSIIBB");
	strcpy(figura[2]->trazos,"DDDSSIIBB");
	strcpy(figura[3]->trazos,"SSSIIBBDD");
}
