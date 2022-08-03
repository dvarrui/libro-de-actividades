// Funcion que actualiza un elemento p con id p1, mostrando posicion del raton
function mostrarPosicion(evento){
	
	document.getElementById("p1").innerHTML="Posicion X "+evento.clientX+" Posicion Y "+evento.clientY;
	
}

// Asignamos un manejador a onmouseover
document.onmousemove=mostrarPosicion;


