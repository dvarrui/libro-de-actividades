#include <stdio.h>
#include <string.h> //Lo requiere strlen()

//#define DEBUG

struct registro {
	char *texto;
	struct registro *siguiente;
};



/*
 * Crear un nuevo registro. Almacenar una línea de texto en memoria
 * e insertar un puntero a dicha línea en el nueo registro. Devolver
 * la dirección del nuevo registro.
 */
struct registro *crearNuevoRegistro01(char *linea) {
	char *malloc(); //???
	struct registro *ptr;
	
	ptr=(struct registro *) malloc(sizeof(struct registro)); /*obtener espacio para el registro*/
	ptr->texto=malloc(strlen(linea)+1); /*obtener espacio para la línea*/
	strcpy(ptr->texto,linea); /*copiar la línea en memoria*/
	ptr->siguiente = NULL;
	
	return ptr;
}



/*
 * Añadir un registro conteniendo el teto indicado al árbol binario
 * apuntado por la raíz. Si es el primer registro (o sea, si root
 * es NULL), devolver la dirección del nuevo registro para usarlo 
 * como raíz. En otro caso, devolve el valor actual de la raíz.
 */
struct registro *addLinea01(struct registro *root, char *linea) {
	struct registro *ptr0;
	struct registro *ptr1;
	struct registro *nuevo;
	
	#ifdef DEBUG
	printf(" [INFO] addLinea: <%s\n",linea);
	#endif
	
	if(root!=NULL) {
		/*la raíz existe; no es la primera entrada en el árbol*/
		ptr0=NULL;
		ptr1=root;

		while(1) {
			if (ptr1==NULL) break;
			if (strcmp(linea,ptr1->texto)<=0) break;
			ptr0 = ptr1;
			ptr1 = ptr1->siguiente; 
		}
		
		nuevo=crearNuevoRegistro01(linea);
		nuevo->siguiente=ptr1;
		
		if (ptr0!=NULL) ptr0->siguiente=nuevo;
		if (ptr0==NULL) root=nuevo;
		
		return(root);
	}
	/*
	 * Es la primera entrada en el árbol. Devolver su dirección
	 * para que se puedea asignar a la raíz.
	 */
	root=crearNuevoRegistro01(linea);
	return root;
}



int getNumeroElementos01(struct registro *ptr) {
	int i=0;
	while(ptr!=NULL) {i++;ptr=ptr->siguiente;}
	return i;	
}


void mostrarOrdenacionBasica(struct registro *ptr) {
	printf("\n Mostrando LISTA encadenada\n");
	while(ptr!=NULL) {	
		printf("   -> %s\n",ptr->texto);
		ptr=ptr->siguiente;
	}
}



/*
 * Ordenar un archivo y enviar resultado a la salida estándar
 */
int ordenacionBasica(char *fichero) {
	FILE *archivo;
	char linea[132];
	struct registro *root;
	root=NULL;
	
	if ((archivo=fopen(fichero,"r"))) {
		#ifdef DEBUG
		printf(" [INFO] ordenacionBasica: leyendo \"%s\"\n",fichero);
		#endif
		
		while (!feof(archivo)) { 
			fgets(linea,132,archivo);
			root=addLinea01(root,linea);
			#ifdef DEBUG
			mostrarOrdenacionBasica(root);
			#endif
		}
		fclose(archivo);
		
		mostrarOrdenacionBasica(root);
		printf("\nNúmero de registros=%d\n",getNumeroElementos01(root));
	} else {
		printf(" [ERROR] ordenacionBasica: No se puede abrir \"%s\"\n",fichero);
	}
	
	#ifdef DEBUG
	printf(" [INFO] Fin del programa!\n");
	#endif
	
	return 1;
}

