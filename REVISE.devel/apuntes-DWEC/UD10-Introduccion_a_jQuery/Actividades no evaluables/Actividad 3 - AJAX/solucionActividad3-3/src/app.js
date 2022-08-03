//Array de los premios
var numPrem = [];
// Limite de numeros a pedir
var LIMITE = 20;



// Funcion que se ejecuta cuando se carga la pagina completamente
$(document).ready(function () {
    for (var i = 0; i <= LIMITE; i++) {
        // Enviamos por Ajax para pedir baneados
        $.ajax({
                method: "GET",
                // URL del sitio OMDBAPI
                url: "http://hispabyte.net/DWEC/sustitutoAPILoteria.php",
                // Data con los datos a enviar
                data: {
                    n: i
                }
                // Caso de manejo de respuesta AJAX
            }).done(function (msg) {
                var busqueda;
                // La API es mala, gastamos eval (peligroso, injeccion de codigo JAVASCRIPT)
                // Este guarda en busqueda el objeto
                eval(msg);
                // De la busqueda, guardamos un array donde se asocia a cada numero su premio
                numPrem[parseInt(busqueda.numero)] = parseInt(busqueda.premio);




            })
            .fail(function () {
                alert("Error");
            })
    }


    // Manejador del boton, que hara la peticion AJAX
    $("#boton").click(function () {

        calcularCosasLoteria();
    });
});


function sumaPremiosUsuario(premios) {
    var tmp = premios.split(",");

    var suma = 0;
    for (var i = 0; i < tmp.length; i++) {
        suma = suma + parseInt(tmp[i]);
    }
    return suma;
}

function esPrimo(n) {

    if (n == 1)
        return false;
    if (n == 2)
            return true;
    if (n % 2 == 0)
        return false;


    for (var i = 3; i <= Math.sqrt(n); i += 2) {
        if (n % i == 0)
            return false;
    }
    return true;
}

function esPalindromo(n) {

    var cad = n.toString();
    return cad == cad.split("").reverse().join("");

}

function sumaPremiosPrimos() {

    var suma = 0;
    for (var i = 0; i <= LIMITE; i++) {
        if (esPrimo(i))
            suma = suma + numPrem[i];
    }
    return suma;
}

function sumaPremiosPalindromos() {

    var suma = 0;
    for (var i = 0; i <= LIMITE; i++) {
        if (esPalindromo(i))
            suma = suma + numPrem[i];
    }
    return suma;
}

function sumaPremiosUsuario(premios) {
    var tmp = premios.split(",");

    var suma = 0;
    for (var i = 0; i < tmp.length; i++) {
        suma = suma + numPrem[parseInt(tmp[i])];
    }
    return suma;
}



function calcularCosasLoteria() {


    $("#resultado").html("");
    $("#resultado").append("<p>Hemos calculado numeros hasta el " + LIMITE + " </p>");

    $("#resultado").append("<p>Suma premios decimos primos " + sumaPremiosPrimos() + " </p>");
    $("#resultado").append("<p>Suma premios decimos palindromos " + sumaPremiosPalindromos() + " </p>");
    var premiosUsuario = prompt("Dime decimos separados por ,", "1,2,5");
    $("#resultado").append("<p>Suma premios decimos usuario " + sumaPremiosUsuario(premiosUsuario) + " </p>");

}