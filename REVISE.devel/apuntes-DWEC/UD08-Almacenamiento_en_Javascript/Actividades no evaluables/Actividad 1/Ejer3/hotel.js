// Definimos la clase habitacion

function habitacion(id,m2,libre){
	// Atributos clase habitacion
	this.id=id;
	this.m2=m2;
	this.libre=libre;

	// Funciones clase habitacion

	function consultar(){
		if(this.libre==true){
			alert("Habitacion "+this.id+ " esta libre");
		} else {
			alert("Habitacion "+this.id+" esta ocupada");
		}	
	}
	this.consultar=consultar;

	// MArca la habitacion como ocupada
	function ocupar(){
		this.libre=false;
	}
	this.ocupar=ocupar;

	// MArca la habitacion como libre
	function liberar(){
		this.libre=true;
	}
	this.liberar=liberar;

	// Codigo inicializacion (no hay en este ejemplo)

}

// Definimos la clase Hotel


function hotel(nombre,nhab) {
	// Atributos del hotel
	this.nombre=nombre;
	this.nhab=nhab;
	// Aqui guardaremos Array de habitaciones
	this.arrayHabs=new Array();
	
	// DEfinicion y asignacion de metodos de la clase


	// MArca la habitacion recibida como parametro como ocupada
	function ocuparHab(n){
		this.arrayHabs[n].ocupar();
	}
	this.ocuparHab=ocuparHab;

	
	// MArca la habitacion recibida como parametro como libre
	function liberarHab(n){
		this.arrayHabs[n].liberar();
	}
	this.liberarHab=liberarHab;

	function consultarHab(n){
		this.arrayHabs[n].consultar();
	}
	
	this.consultarHab=consultarHab;

	// Codigo inicializador del hotel	
	for(i=0;i<nhab;i++){
		// Creamos habitaciones con id i, 30 m2 y libres
		this.arrayHabs[i]=new habitacion(i,30,true);
	}


}

// Función para crear un hotel
function crearHotel(){
	var nombre=document.getElementById("nombreHotel").value;
	var habitaciones=parseInt(document.getElementById("numeroHab").value);
	// Recupero array de hoteles con localStorage
	var arrayHotelesJSON=localStorage.getItem("arrayHoteles");
	var arrayHoteles=JSON.parse(arrayHotelesJSON);
	
	// Si esta vacio, creamos el array
	if(arrayHoteles==null){
		arrayHoteles=new Array();
	}
	
	
	var h=new hotel(nombre,habitaciones);
	// anayadimos al final del array y guardamos
	arrayHoteles.push(h);
	localStorage.setItem("arrayHoteles",JSON.stringify(arrayHoteles));
	
	// Anyadido el hotel, recargamos
	recargarMuestraHoteles();
	
}

function recargarMuestraHoteles(){
	
	var i;
	// Recupero array de hoteles con localStorage
	var arrayHotelesJSON=localStorage.getItem("arrayHoteles");
	var arrayHoteles=JSON.parse(arrayHotelesJSON);
	
	
	
	var divHoteles=document.getElementById("divHoteles");
	// Vaciamos el divHoteles
	divHoteles.innerHTML="";
	
	
	// Si el array esta vacio acabamos la funcion ya que no hay nada que mostrar
	if(arrayHoteles==null){
		return;
	}
	
	// anayadimos un P por cada hotel
	for(i=0;i<arrayHoteles.length;i++){
		
		divHoteles.innerHTML+="<p>Nombre: "+arrayHoteles[i].nombre+" Habitaciones: "+arrayHoteles[i].nhab+"</p>";
	}

}

function eliminar(){
	localStorage.removeItem("arrayHoteles");
	recargarMuestraHoteles();
}



// Asociamos los eventos a las funciones

window.onload=function (){
    
    var bA=document.getElementById("botonAnyadir");
    var bE=document.getElementById("botonEliminar");
    
    bA.onclick=crearHotel;
    bE.onclick=eliminar;
    
   // Al cargar la pagina, recargamos los hoteles que estuvieran en localStorage
   recargarMuestraHoteles();
    
}

