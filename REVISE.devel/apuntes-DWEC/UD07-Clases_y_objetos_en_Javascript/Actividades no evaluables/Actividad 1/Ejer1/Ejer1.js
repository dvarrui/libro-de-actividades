// Definimos la clase alumno

function alumno(id,nombre, notaMedia){
	// Atributos clase alumno
	this.id=id;
	this.nombre=nombre;
	this.notaMedia=notaMedia;

	// Funciones clase alumno

	function consultarNota(){
	   alert("El alumno "+this.nombre+" tiene de nota "+this.notaMedia);
    }
	this.consultarNota=consultarNota;

	// Cambia la nota media
	function cambiarNotaMedia(nuevaNota){
		this.notaMedia=nuevaNota;
	}
	this.cambiarNotaMedia=cambiarNotaMedia;


}

// Definimos la clase Colegio


function colegio(nombre,nAulas,nAlumnos) {
	// Atributos del colegio
	this.nombre=nombre;
	this.nAulas=nAulas;
	// Aqui guardaremos Array de alumnos
	this.arrayAlumnos=new Array();
	
	// DEfinicion y asignacion de metodos de la clase


	// MArca la habitacion recibida como parametro como ocupada
	function consultarNotaMedia(n){
		this.arrayAlumnos[n].consultarNota();
	}
	this.consultarNotaMedia=consultarNotaMedia;

	
	// MArca la habitacion recibida como parametro como libre
	function cambiarNotaMedia(n,nuevaNota){
		this.arrayAlumnos[n].cambiarNotaMedia(nuevaNota);
	}
	this.cambiarNotaMedia=cambiarNotaMedia;

	// Codigo inicializador del colegio	
	for(i=0;i<nAlumnos;i++){
		// Creamos alumnos con id i, nombre Alumnoi y media 5.0
		this.arrayAlumnos[i]=new alumno(i,"Alumno"+i,5.0);
	}


}

// Creo un cole

var miCole=new colegio("Ceed",10,10);

// Consulto
miCole.consultarNotaMedia(0);
miCole.consultarNotaMedia(2);
miCole.cambiarNotaMedia(0,10.0);
miCole.cambiarNotaMedia(2,7.5);
miCole.consultarNotaMedia(0);
miCole.consultarNotaMedia(2);
