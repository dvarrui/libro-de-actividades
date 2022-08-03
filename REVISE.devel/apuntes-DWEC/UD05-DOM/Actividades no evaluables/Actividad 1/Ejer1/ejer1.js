//Inserta un elemento aleatorio
function insertar(evento){
	
	// Creamos el elemento LI y su texto por separado
	nuevoHijo=document.createElement("LI");
	nodoTexto=document.createTextNode(Math.random());
	// Unimos el texto al elemento LI
	nuevoHijo.appendChild(nodoTexto);
	// Añadimos el elemento LI a la UL
	document.getElementById("miLista").appendChild(nuevoHijo);
	
}

