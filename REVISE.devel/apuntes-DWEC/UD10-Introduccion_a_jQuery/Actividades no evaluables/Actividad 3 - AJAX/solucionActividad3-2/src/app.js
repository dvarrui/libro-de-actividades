// Array con los amigos
var votos = [];



// Pedimos con una peticion AJAX los votos
function obtenerAmigos() {

	// Enviamos por Ajax para pedir baneados
	$.ajax({
			method: "POST",
			// URL del sitio 
			url: "http://hispabyte.net/DWEC/EjemploUD10-3-2Amigos.php",
			// Data con los datos a enviar
			data: {}
			// Caso de manejo de respuesta AJAX
		}).done(function (msg) {
			// Procesamos la respuesta
			amigos = JSON.parse(msg);

		})
		.fail(function () {
			alert("Error");
		})



}


//Funcion para mostrar las votaciones en el div del id pasado y con los votos del array 
function calcular(arrayAmigos) {
	var i, j;
	var arrayAsociativoAmigos = [];
	// Usamos arrayAsociativo para ver cuento tiene cada amigo.
	// Inicialmente ponemos a todos a cero
	for (i = 0; i < arrayAmigos.length; i++) {
		arrayAsociativoAmigos[arrayAmigos[i].nombre] = 0;
	}

	// Inicialmente ponemos a todos a cero
	for (i = 0; i < arrayAmigos.length; i++) {
		euros = arrayAmigos[i].euros;
		nAmigos = arrayAmigos[i].arrayReparto.length;
		// Si no reparte a nadie, pasamos
		if (nAmigos == 0)
			continue;

		// Obtenemos resto de la division del dinero entre amigos
		resto = euros % nAmigos;
		// Restamos el resto a la cantidad a repartir
		euros = euros - resto;
		//Restyamos los euros que se repartiran
		arrayAsociativoAmigos[arrayAmigos[i].nombre] -= euros;
		// sumamos a cada amigo la parte que le toca
		for (j = 0; j < arrayAmigos[i].arrayReparto.length; j++) {

			arrayAsociativoAmigos[arrayAmigos[i].arrayReparto[j]] += (euros / nAmigos);
		}
	}




	// Vaciamos el DIV de la respuesta
	$("#resReparto").html("");
	//Imprimimos el contenido del array asociativo
	for (var key in arrayAsociativoAmigos) {
		//alert(key);
		$("#resReparto").html($("#resReparto").html() + key + " " + arrayAsociativoAmigos[key] + "<br>");
	}

}



//Mostramos los votos
function peticiones() {
	calcular(amigos);
}


// Al carga la pagina obtenemos votos y baneados

// Funcion que se ejecuta cuando se carga la pagina completamente
$(document).ready(function () {

	// Manejador del boton, que hara la peticion AJAX
	$("#peticiones").click(function () {
		peticiones();
	});
	// Funcion para obtener los amigos en el juego
	obtenerAmigos();
});