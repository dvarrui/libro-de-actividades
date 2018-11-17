#include <stdio.h>
#include <string.h> //Lo requiere strlen()

//#define DEBUG

struct registro {
	char *texto;
	struct registro *izq;
	struct registro *der;
};



/*
 * Crear un nuevo registro. Almacenar una línea de texto en memoria
 * e insertar un puntero a dicha línea en el nueo registro. Devolver
 * la dirección del nuevo registro.
 */
struct registro *crearNuevoRegistro03(char *linea) {
	char *malloc(); //???
	struct registro *ptr;
	
	ptr=(struct registro *) malloc(sizeof(struct registro)); /*obtener espacio para el registro*/
	ptr->texto=malloc(strlen(linea)+1); /*obtener espacio para la línea*/
	strcpy(ptr->texto,linea); /*copiar la línea en memoria*/
	ptr->izq = NULL; 
	ptr->der = NULL; /*inicializa los punteros izq y der*/
	
	return ptr;
}



/*
 * Añadir un registro conteniendo el teto indicado al árbol binario
 * apuntado por la raíz. Si es el primer registro (o sea, si root
 * es NULL), devolver la dirección del nuevo registro para usarlo 
 * como raíz. En otro caso, devolve el valor actual de la raíz.
 */
struct registro *addLinea03(struct registro *root, char *linea) {
	struct registro *padre;
	struct registro *ptr;
	struct registro *nuevo;
	
	#ifdef DEBUG
	printf(" [INFO] addLinea03: %s",linea);
	#endif
	
	if(root!=NULL) {
		/*la raíz existe; no es la primera entrada en el árbol*/
		ptr=root;
		padre=NULL;
		
		while(ptr!=NULL) { /*hasta que se obtenga un nodo vacío*/
			if (strcmp(linea,ptr->texto)<=0) {
				padre=ptr; /*guardar el puntero*/
				ptr=ptr->izq; /*ir a la izq*/
			} else {
				padre=ptr;
				ptr=ptr->der;
			}
		}
		
		nuevo=crearNuevoRegistro03(linea);

		if (strcmp(nuevo->texto,padre->texto)<=0) {
			padre->izq=nuevo;
		} else {
			padre->der=nuevo;
		}
		
		return(root);
	}
	/*
	 * Es la primera entrada en el árbol. Devolver su dirección
	 * para que se puedea asignar a la raíz.
	 */
	root=crearNuevoRegistro03(linea);
	 
	return root;
}


/*
 * Recorriendo el árbol binario y mostrando sus nodos.
 * 
 * PreOrder: Primero el padre y luego sus hijos
 * InOrder: Primero hijo izq, padre y luego hijo der
 * PostOrder: Primero los hijos y luego el padre
 */
void mostrarInOrder(struct registro *ptr) {
	
	if (ptr!=NULL) {
		mostrarInOrder(ptr->izq);
		printf(" Recorrido en InOrder : %s\n",ptr->texto);	
		mostrarInOrder(ptr->der);
	}
}


void mostrarPreOrder(struct registro *ptr) {
	
	if (ptr!=NULL) {
		printf(" Recorrido en PreOrder : %s\n",ptr->texto);	
		mostrarPreOrder(ptr->izq);
		mostrarPreOrder(ptr->der);
	}
}


void mostrarPostOrder(struct registro *ptr) {
	
	if (ptr!=NULL) {
		mostrarPostOrder(ptr->izq);
		mostrarPostOrder(ptr->der);
		printf(" Recorrido en PostOrder : %s\n",ptr->texto);			
	}
}

/*
 * Ordenar un archivo y enviar resultado a la salida estándar
 */
int ordenacionBinaria(char *fichero) {
	FILE *archivo;
	char linea[132];
	struct registro *root;
	root=NULL;
	
	if ((archivo=fopen(fichero,"r"))) {
		printf(" [INFO] ordenacionBinaria: leyendo \"%s\"\n",fichero);
		while (!feof(archivo)) { 
			fgets(linea,132,archivo);
			root=addLinea03(root,linea);
			
			#ifdef DEBUG
				mostrarInOrder(root);
			#endif
		}
		printf("\n\nResultado Final\n");
		mostrarInOrder(root);
		mostrarPreOrder(root);
		mostrarPostOrder(root);
	} else {
		printf(" [ERROR] ordenacionBinaria: No se puede abrir \"%s\"\n",fichero);
	}
	
	return 0;
}

