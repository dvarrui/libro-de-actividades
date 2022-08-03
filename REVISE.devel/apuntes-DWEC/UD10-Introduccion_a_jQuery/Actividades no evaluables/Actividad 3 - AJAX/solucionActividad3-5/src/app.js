//Variable de la API Key. Aqui deberias poner tu propia API KEY obtenida de la web
var miApikey="XXXX";



// Funcion que se ejecuta cuando se carga la pagina completamente
$(document).ready(function () {
    // Manejador del boton, que hara la peticion AJAX
    $("#boton").click(function () {

        obtenerDatos();
    });
});






function obtenerDatos() {
    // Enviamos por Ajax para pedir baneados
    $.ajax({
            method: "GET",
            // URL del sitio OMDBAPI
            url: "http://www.omdbapi.com/",
            // Data con los datos a enviar
            data: {
                apikey:miApikey,
                s:$("#titulo").val(),
                type:$("#tipo").val(),
                y:$("#anyo").val()         
            }
            // Caso de manejo de respuesta AJAX
        }).done(function (msg) {
            // Vaciamos el HTML del Div
            $("#listaPeliculas").html("");
            // Para cada elemento, dentro de la respuesta de la busqueda, metemos un <p> con cada peli y su anyo
            for ( var v in msg.Search){
                $("#listaPeliculas").append("<p>"+msg.Search[v].Title+" - "+msg.Search[v].Year+"</p>");
            }

        })
        .fail(function () {
            alert("Error");
        })
}