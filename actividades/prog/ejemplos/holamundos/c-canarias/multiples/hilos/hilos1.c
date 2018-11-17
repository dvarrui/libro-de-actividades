/**
 * gcc hilos1.c -lpthread
 */

#include <pthread.h>
#include <stdio.h>

#define NUM_HILOS 5
#define NUM_PASOS 50
#define NUM_RETARDO 10000
int I=0;

void *codigo_del_hilo(void *id)
{
	int i,j;
	for(i=0; i<NUM_PASOS; i++) {
		printf("Hilo %d: i = %d, I = %d\n",*(int *)id,i,I++);
		for(j=0; j<NUM_RETARDO; j++);
	}
	pthread_exit(id);
}

int main()
{
	int h;
	pthread_t hilos[NUM_HILOS];
	int id[NUM_HILOS] = {1,2,3,4,5};
	int error;
	int *salida;

	for(h=0; h<NUM_HILOS; h++) {
		error=pthread_create(&hilos[h],NULL,codigo_del_hilo,&id[h]);
		if (error) {
			fprintf(stderr,"Error %d: %s\n",error,strerror(error));
			return -1;
		}
	}

	for(h=0; h<NUM_HILOS; h++) {
		error=pthread_join(hilos[h], (void **)&salida);
		if(error)
			fprintf(stderr,"Error %d: %s\n",error,strerror(error));
		else
			printf("Hilo %d terminado\n",*salida);
	}
	return 0;
}

