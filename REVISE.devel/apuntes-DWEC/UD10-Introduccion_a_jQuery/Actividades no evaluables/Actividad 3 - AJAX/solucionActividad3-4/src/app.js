// Funcion que se ejecuta cuando se carga la pagina completamente
$(document).ready(function () {
    // Llamamos a la función para obtener los datos
    obtenerDatos();
});


// Funcion a la que se llama para interactuar con la API de Codeforces mediante AJAX
function obtenerDatos() {
    // Enviamos por Ajax para pedir baneados
    $.ajax({
            method: "GET",
            // URL del sitio OMDBAPI
            url: "http://codeforces.com/api/contest.list",
            // Data con los datos a enviar
            data: {
                gym: true
            }
            // Caso de manejo de respuesta AJAX
        }).done(function (msg) {
            // Vaciamos el HTML del Div
            $("#campeonatos").html("");
            // Para cada elemento, dentro de la respuesta de la busqueda, metemos un <p> con cada peli y su anyo
            for (var v in msg.result) {
                // Si tiene la propiedad, usamos comparacion Lexicografica
                if (msg.result[v].hasOwnProperty("season") && msg.result[v].season > "2012-2013") {
                    // Si tiene la propiedad, convertimos los segundos desde 1 enero 1970 en objeto date
                    // y comprobamos que mes es (getMonth devuelve del 0 al 11, Mayo es el mes 4)
                    if (msg.result[v].hasOwnProperty("startTimeSeconds")){
                        // Unix timestamp son segundos, pero Javascript usa milisegundos por eso el * 1000
                        var fecha=new Date(msg.result[v].startTimeSeconds * 1000);  
                        //alert(fecha.toDateString());
                        if(fecha.getMonth() == 4) {
                            $("#campeonatos").append("<p>" + msg.result[v].name + " " +fecha.toDateString()+"</p>")
                        }
                    }
            
                }
            }
        })
        .fail(function () {
            alert("Error");
        })
}