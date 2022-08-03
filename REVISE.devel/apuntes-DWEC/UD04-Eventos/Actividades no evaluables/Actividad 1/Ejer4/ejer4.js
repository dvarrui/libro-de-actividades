
//Numero aleatorio, minimo y maximo incluidos

function numeroAleatorio(min, max) {
  return Math.round(Math.random() * (max - min) + min);
}


// Funcion manejadora que cambia de color el fondo
function cambiarColor(evento){
	
	document.body.bgColor="rgb("+numeroAleatorio(0,255)+","+numeroAleatorio(0,255)+","+numeroAleatorio(0,255)+")";
}


//asignamos a calcular dni a onkeyup
document.ondblclick=cambiarColor;
