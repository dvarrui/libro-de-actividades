// Variable array baneados
var baneados;
// Elementos de votos
var votos;




// Funcion que se ejecuta cuando se carga la pagina completamente
$(document).ready(function () {
    // Manejador del boton votos
    $("#botonVotos").click(function () {
        // Empezamos por obtener la lista de baneados. Segun se obtenga, se lanzara en cadena
        // los votos y se mostrara el resultado
        obtenerListadoBaneadoAjax();
    });
});

// Funcion que en funcion de los votos emitidos y los baneados, calcula los votos contando baneados y 
// sin contarlos. Se llama tras recibir el AJAX de obtenerVotosAjax
function calculaVotos() {
    // Array para los votos con todos y los votos sin los baneados
    var votoCalculado = [];
    var votoCalculadoBaneados = [];

    // recorremos los votos
    for (var i = 0; i < votos.length; i++) {
        // Si nuestro array de votos ya tiene la clave con el nombre de restaurante, sumamos.
        // en caso contrario, iniciamos a 1
        if (votoCalculado.hasOwnProperty(votos[i].restaurante)) {
            votoCalculado[votos[i].restaurante]++;
        } else {
            votoCalculado[votos[i].restaurante] = 1;

        }
        // Si nuestro array de votos ya tiene la clave con el nombre de restaurante, sumamos.
        // en caso contrario, iniciamos a 1
        if (votoCalculadoBaneados.hasOwnProperty(votos[i].restaurante)) {
            // Solo lo contamos si no esta en la lista de baneados
            if (baneados.indexOf(votos[i].usuario)==-1) {
                votoCalculadoBaneados[votos[i].restaurante]++;
            }
        } else {
            // Solo lo contamos si no esta en la lista de baneados
            if (baneados.indexOf(votos[i].usuario)==-1) {
                votoCalculadoBaneados[votos[i].restaurante]=1;
            }
        }
    }
    // Generamos el HTML con todos los tipos de votos
    var miHTML="<p style='border: 1px solid red; display:inline-block'>Votos sin baneados</p>";
    for (var k in votoCalculado) {
        miHTML+="<p>"+k+" "+votoCalculado[k]+"</p>";
    }
    miHTML+="<p style='border:1px solid red; display:inline-block'>Votos quitando baneados</p>";
    for (var k in votoCalculadoBaneados) {
        miHTML+="<p>"+k+" "+votoCalculadoBaneados[k]+"</p>";
    }
    // Establecemos ese HTML en el DIV
    $("#votosHTML").html(miHTML);

}



// Funcion para pedir los votos por AJAX. Se llama desde baneados
function obtenerVotosAjax() {
    // Enviamos por Ajax para pedir baneados
    $.ajax({
            method: "POST",
            url: "http://hispabyte.net/DWEC/EjemploUD10-3-1Restaurante.php",
            // data vacio porque no requiere enviar datos
            data: {}
        })
        .done(function (msg) {
            // Recibo el json con el objeto de los votos
            votos = JSON.parse(msg);

            // Tenemos los votos y los baneados, entonces calculamos
            calculaVotos();
        })
        .fail(function () {
            alert("Error");
        })
}


// Pide mediante AJAX los baneados. Al recibir la respuesta, pide los votos

function obtenerListadoBaneadoAjax() {
    // Enviamos por Ajax para pedir baneados
    $.ajax({
            method: "POST",
            url: "http://hispabyte.net/DWEC/EjemploUD10-3-1Baneados.php",
            // data vacio porque no requiere enviar datos
            data: {}
        }).done(function (msg) {
            // Recibo el json que incluye el array de baneados
            baneados = JSON.parse(msg);

            // una vez recibido, obtenemos el listado de votos AJAX (partiendo la base ya tenemos los baneados)
            obtenerVotosAjax();

        })
        .fail(function () {
            alert("Error");
        })
}