/*
 * Fichero: janoi.c
 * Torres de Janoi
 *
 * 100
 * 200
 * 300
 * 
 */

#include <stdio.h>

#define FILA_MIN 0
#define FILA_MAX 3
#define FILA_NUM 4
#define COL_MIN 0
#define COL_MAX 2
#define COL_NUM 3

//Variable global
int pos[FILA_NUM][COL_NUM];
int numeroMovimientos;

void mostrarJanoi() {
	int i,j;

	for(i=0;i<FILA_NUM;i++) {
		for(j=0;j<COL_NUM;j++)
			printf("%d",pos[i][j]);
		printf("\n");
	}
	printf("Nº de movimientos = %d\n",numeroMovimientos);
}



void inicializar() {
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
	mostrarJanoi();
}


/* Localiza y devuelve el valor de la fila de la ficha indicada */
int getFilaFicha(int ficha) {
	int i,j;

	for(i=0;i<FILA_NUM;i++)
		for(j=0;j<COL_NUM;j++)
			if (pos[i][j]==ficha) return i;
	printf(" [ERROR] getFilaFicha(%d)\n",ficha);
	return -1;
}


/* Localiza y devuelve el valor de la columna de la ficha indicada */
int getColFicha(int ficha) {
	int i,j;

	for(i=0;i<FILA_NUM;i++)
		for(j=0;j<COL_NUM;j++)
			if (pos[i][j]==ficha) return j;
	printf(" [ERROR] getColFicha(%d)\n",ficha);
	return -1;
}

/* Localiza y devuelve el número de al fila libre de la columna indicada */
int getFilaLibre(int col) {
	int i;
	for(i=FILA_MAX;i>=FILA_MIN;i--)
		if (pos[i][col]==0) return i;
	return -1;
}


void moverFicha(int ficha, int hasta) {
	//Pasa una ficha desde una columna hasta otra
	int fila,desde;
	fila=getFilaFicha(ficha);
	desde=getColFicha(ficha);

	if (desde==hasta) {
		printf(" [INFO] moverFicha(ficha %d, col %d): No hace nada!\n",ficha,hasta);
	} else if (getFilaLibre(hasta)==-1) {
		printf(" [ERROR] moverFicha(ficha %d, col %d): No hay espacio!\n",ficha,hasta);
	} else {
		printf(" [INFO] moverFicha(ficha %d, col %d):\n",ficha,hasta);
		pos[fila][desde]=0;
		pos[getFilaLibre(hasta)][hasta]=ficha;
		numeroMovimientos++; //Contar movimiento
	}

	mostrarJanoi();
}



int moverBloque(int ficha, int hasta) {
	//Mueve bloques desde una columna hasta otra
	int fila, desde;
	fila=getFilaFicha(ficha);
	desde=getColFicha(ficha);
	
	int temp=0;
	if (temp==0 && (desde==0||hasta==0)) temp=1;
	if (temp==1 && (desde==1||hasta==1)) temp=2;
	if (temp==2 && (desde==2||hasta==2)) temp=0;
	
	printf(" [INFO] moverBloque(ficha %d, col %d)\n",ficha,hasta);

	//Comprobar argumentos
	if (desde<COL_MIN||desde>COL_MAX||hasta<COL_MIN||hasta>COL_MAX) {
		printf(" [ERROR] moverBloque(ficha %d, col %d):Parámetros fuera de rango!\n",ficha,hasta);
		return 0;
	}
	if (desde==hasta) return 1;
	
	if (getFilaLibre(desde)==fila-1) {
		//Sólo hay una ficha en el bloque
		moverFicha(ficha,hasta);
	} else {
		//Hay varias fichas en el bloque
		moverBloque(ficha-1,temp);
		moverBloque(ficha,hasta);
		moverBloque(ficha-1,hasta); //
	}
	return 1;
}


int janoi() {
	inicializar();
	moverBloque(FILA_NUM,2);
	
	return 1;
}



