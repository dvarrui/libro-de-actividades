/*
 * Fichero: hanoi_iterativo.c
 * Torres de Janoi
 *
 * 100
 * 200
 * 300
 * 
 */

#include <stdio.h>

#define FILA_MIN 0
#define FILA_MAX 2
#define FILA_NUM 3
#define COL_MIN 0
#define COL_MAX 2
#define COL_NUM 3

//Variable global
int pos[FILA_NUM][COL_NUM];
int numeroMovimientos;

void mostrarHanoiIterativo() {
	int i,j;

	for(i=0;i<FILA_NUM;i++) {
		for(j=0;j<COL_NUM;j++)
			printf("%d",pos[i][j]);
		printf("\n");
	}
	printf("Nº de movimientos = %d\n",numeroMovimientos);
}



void inicializarHanoiIterativo() {
	//Inicializar el array de las torres
	printf(" [INFO] Inicializar\n");
	int i,j;

	for (i=0;i<FILA_NUM;i++) {
		for (j=0;j<COL_NUM;j++) {
			pos[i][j]=0;
		}
	}
	//pos[0][0]=1;pos[1][0]=2;pos[2][0]=3;
	for(i=FILA_NUM;i>0;i--) {
		pos[i-1][COL_MIN]=i;
	}
	numeroMovimientos=0;
	mostrarHanoiIterativo();
}


/* Localizar la columna de la ficha solicitada */
int getColumnaFichaHanoiIterativo(int ficha) {
	int i,j;
	//Primero buscamos las coordenadas de la ficha
	for(i=0;i<FILA_NUM;i++)
		for(j=0;j<COL_NUM;j++)
			if (pos[i][j]==ficha) return j;

	printf(" [ERROR] No se localiza la ficha %d\n",ficha);
	return -1;
}


/* Localizar la fila de la ficha solicitada */
int getFilaFichaHanoiIterativo(int ficha) {
	int i,j;
	//Primero buscamos las coordenadas de la ficha
	for(i=0;i<FILA_NUM;i++)
		for(j=0;j<COL_NUM;j++)
			if (pos[i][j]==ficha) return i;

	printf(" [ERROR] No se localiza la ficha %d\n",ficha);
	return -1;
}


/* Localizar la fila libre de la columna solicitada */
int getFilaLibreHanoiIterativo(int col) {
	int i;
	for(i=FILA_MAX;i>=0;i--)
			if (pos[i][col]==0) return i;
	return -1;
}


/* Comprueba si hemos llegado al final del Hanoi */
int isFinal(void) {
	int i;
	for(i=0;i<FILA_NUM;i++)
			if (pos[i][2]!=i+1) return 0;
	return 1;
}


/* Devuelve verdadero si Es posible mover el disco */
int isBloqueadaFicha(int ficha) {
	int fila,columna;

	fila = getFilaFichaHanoiIterativo(ficha);
	columna = getColumnaFichaHanoiIterativo(ficha);
		
	if (fila==0) return 1; //La ficha es la de más arriba
	if (pos[fila-1][columna]==0) return 1; //La ficha no tiene otra sobre ella
	return 0;
}


/* Comprueba si es posible mover la ficha a la columna indicada */
int isPosibleMoverFichaAColumna(int ficha, int col) {
	int libre = getFilaLibreHanoiIterativo(col);
		
	if (libre==-1) return 0;
	if (libre==FILA_MAX) return 1;
	if (pos[libre+1][col]>ficha) return 1;
	return 0;
}


/* Devuelve la siguiente columna a partir de la actual y del sentido elegido */
int getSiguienteColumna(int actual, int sentido) {
	actual = actual+sentido;
	if (actual<0) actual=2;
	if (actual>2) actual=0;
	return actual;
}


/* Mover la ficha a la columna indicada */
void moverFichaAColumna(int ficha, int col) {
	printf(" [INFO] Moviendo ficha %d a columna %d\n",ficha,col);
	int fila, columna;
	int i;
	int libre = -1;
	for(i=FILA_MAX;i>=FILA_MIN;i--) {
		if (pos[i][col]==0) {libre=i; break;}
	}
	
	fila = getFilaFichaHanoiIterativo(ficha);
	columna = getColumnaFichaHanoiIterativo(ficha);

	pos[libre][col] = pos[fila][columna];
	pos[fila][columna]=0;
	
	numeroMovimientos++;
}


/* Realiza todo el proceso de Hanoi Iterativo */
int moverBloqueHanoiIterativo(int ficha, int hasta) {
	int i;
	int col_actual, col_siguiente;
	int sentido=1;
	int cambio;
	
	//Si tenemos un número impar de fichas cambiamos el sentido
	if (FILA_NUM%2) sentido=-1;
	
	//Hasta que se llegue al final hacer
	while(!isFinal()&&numeroMovimientos<20) {
		//Para cada una de las fichas vamos a moverlas al siguiente hueco
		for(i=1;i<=FILA_NUM;i++) {
			if (isBloqueadaFicha(i)) {
				//Intentar mover la ficha i
				cambio=0;
				col_actual = getColumnaFichaHanoiIterativo(i);
				col_siguiente = getSiguienteColumna(col_actual,sentido);
				while(col_actual!=col_siguiente) {
					if (isPosibleMoverFichaAColumna(i,col_siguiente)) {
						//Es posible mover la ficha al siguiente hueco
						moverFichaAColumna(i,col_siguiente);
						//Cambiar de ficha
						col_siguiente=col_actual;
						cambio=1;
					} else {
						col_siguiente = getSiguienteColumna(col_siguiente,sentido);
					}
				}
				if (cambio==0) printf(" [INFO] No se puede mover la ficha %d\n",i);
				else mostrarHanoiIterativo();
			} else {
				printf(" [INFO] La ficha %d está bloqueada\n",i);
			}
		}
	}
	return 1;
}


int hanoi_iterativo() {
	inicializarHanoiIterativo();
	moverBloqueHanoiIterativo(FILA_NUM,2);
	
	return 1;
}



