#include <stdio.h>
#include <string.h> //Lo requiere strlen()

//#define DEBUG

struct registro {
	char *texto;
	struct registro *anterior;
	struct registro *siguiente;
};



/*
 * Crear un nuevo registro. Almacenar una línea de texto en memoria
 * e insertar un puntero a dicha línea en el nueo registro. Devolver
 * la dirección del nuevo registro.
 */
struct registro *crearNuevoRegistro02(char *linea) {
	char *malloc(); //???
	struct registro *ptr;
	
	ptr=(struct registro *) malloc(sizeof(struct registro)); /*obtener espacio para el registro*/
	ptr->texto=malloc(strlen(linea)+1); /*obtener espacio para la línea*/
	strcpy(ptr->texto,linea); /*copiar la línea en memoria*/
	ptr->anterior = NULL;
	ptr->siguiente = NULL;
	
	return ptr;
}



/*
 * Añadir un registro conteniendo el teto indicado al árbol binario
 * apuntado por la raíz. Si es el primer registro (o sea, si root
 * es NULL), devolver la dirección del nuevo registro para usarlo 
 * como raíz. En otro caso, devolve el valor actual de la raíz.
 */
struct registro *addLinea02(struct registro *root, char *linea) {
	struct registro *ptr0;
	struct registro *ptr1;
	struct registro *nuevo;
	
	#ifdef DEBUG
	printf(" [INFO] addLinea02: <%s\n",linea);
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
		
		nuevo=crearNuevoRegistro02(linea);
		nuevo->anterior=ptr0;
		nuevo->siguiente=ptr1;
		
		if (ptr0!=NULL) ptr0->siguiente=nuevo;
		if (ptr1!=NULL) ptr1->anterior=nuevo;
		if (nuevo->anterior==NULL) root=nuevo;

		#ifdef DEBUG
		char str1[100], str2[100];
		ptr0=nuevo->anterior;
		ptr1=nuevo->siguiente;
		if (ptr0!=NULL) {strcpy(str1,ptr0->texto);}	else {strcpy(str1,"NULL");}
		if (ptr1!=NULL) {strcpy(str2,ptr1->texto);}	else {strcpy(str2,"NULL");}
		printf(" [INFO] addLinea02: Creado entre %s y %s\n",str1,str2);
		#endif
		
		return(root);
	}
	/*
	 * Es la primera entrada en el árbol. Devolver su dirección
	 * para que se puedea asignar a la raíz.
	 */
	root=crearNuevoRegistro02(linea);
	
	#ifdef DEBUG
	printf(" [INFO] addLinea02: Creado registro raíz para %s\n",linea);
	#endif
	
	return root;
}



int getNumeroElementos02(struct registro *ptr) {
	int i=0;
	while(ptr!=NULL) {i++;ptr=ptr->siguiente;}
	return i;	
}



void mostrarOrdenacionListaDoble(struct registro *ptr) {
	struct registro *root;
	root=ptr;
	
	printf("\n Mostrando LISTA-DOBLE (asdendente)\n");
	while(ptr!=NULL) {	
		printf("   -> %s\n",ptr->texto);
		ptr=ptr->siguiente;
	}
	
	ptr=root;
	while(ptr->siguiente!=NULL) ptr=ptr->siguiente;
	
	printf("\n Mostrando LISTA-DOBLE (descendente)\n");
	while(ptr!=NULL) {	
		printf("   -> %s\n",ptr->texto);
		ptr=ptr->anterior;
	}
}

/*
 * Ordenar un archivo y enviar resultado a la salida estándar
 */
int ordenacionListaDoble(char *fichero) {
	FILE *archivo;
	char linea[132];
	struct registro *root;
	root=NULL;
	
	if ((archivo=fopen(fichero,"r"))) {
		#ifdef DEBUG
		printf(" [INFO] ordenacionListaDoble: leyendo \"%s\"\n",fichero);
		#endif
		
		while (!feof(archivo)) { 
			fgets(linea,132,archivo);
			root=addLinea02(root,linea);
			#ifdef DEBUG
			mostrarOrdenacionListaDoble(root);
			#endif
		}
		fclose(archivo);
		
		mostrarOrdenacionListaDoble(root);
		printf("\nNúmero de registros=%d\n",getNumeroElementos02(root));
	} else {
		printf(" [ERROR] ordenacionListaDoble: No se puede abrir \"%s\"\n",fichero);
	}
	
	#ifdef DEBUG
	printf(" [INFO] Fin del programa!\n");
	#endif
	
	return 1;
}

