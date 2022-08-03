// Variable global con el contenido de la sopa actual
var sopaGeneral;


// Funcion que pasando un texto, busca su posicion dentro de la sopa de letras actual
function imprimePosicion(texto){
    
    // Si es menos 1, caso "Ninguna seleccionada"
    if(texto=="-1"){
        alert("Ninguna seleccionada");
    }
    //Para otros casos...
    else{
        // Recorremos la matriz de la sopa de letras
        for(var i=0;i<sopaGeneral.length;i++){
            for(var j=0;j<sopaGeneral[i].length;j++){
                // Si el texto tiene N letras y en la matriz no se puede formar en horizontal una palabra de tamaño N, pasamos a la siguiente
                if(j+texto.length>sopaGeneral[i].length)
                    break;
                // Si hay una palabra candidata, sacamos un texto desde j a j+ texto.length
                var cadTmp=sopaGeneral[i].slice(j,j+texto.length);
                // Comparamos la cadena obtenida con la buscada. Si son iguales
                // se muestra el resultado y se acaba el programa
                if(cadTmp==texto){
                    alert("Posicion "+i+","+j);
                    return;
                }
            }
        }
    }
    alert("Cadena no encontrada");
}


// Funcion que genera el HTML de la sopa de letras dada
function generarTabla(datos){

    var tabla="<table>\n";

    for(var i=0;i<datos.length;i++){
        tabla=tabla+"<tr>\n";

        for(var j=0;j<datos[i].length;j++){
            tabla=tabla+"<td>"+datos[i][j]+"</td>\n";
        }
        tabla=tabla+"</tr>\n";
    }

    tabla=tabla+"</table>\n";
    return tabla;

}


// Funcion que genera el HTML del combobox de las palabras seleccionadas

function generarPalabras(datos){
    var combo="<select>\n";
    // Cada combo tiene su "onclick" con un parametro generado ad-hoc (-1 para ninguna seleccionada, el contenido de la palabra para el resto)
    combo=combo+"<option value='-1' onclick='imprimePosicion(\"-1\")' >Ninguna seleccionada</option>\n";
    for(var i=0;i<datos.length;i++){
        combo=combo+"<option   onclick='imprimePosicion(\""+datos[i]+"\")' value='"+datos[i]+"'>"+datos[i]+"</option>\n";
    }
    combo=combo+"</select>\n";
    return combo;
}


// Funcion que se ejecuta cuando se carga la pagina completamente
$(document).ready(function () {
    // Evento de hacer click en obtenerSopa
    $("#obtenerSopa").click( function(){

        // Enviamos por Ajax para pedir sopa y lista de palabras
        $.ajax({
            method:"POST",
            url:"http://hispabyte.net/DWEC/EjercicioUD10-2AJAX.php",
            // data vacio porque no requiere enviar datos
            data:{}
            })
            .done(function(msg) {
                // Recibo el json que incluye la sopa y la lista de palabras
                var x=JSON.parse(msg);                
                // Asignamos la sopa a la variable general
                sopaGeneral=x.sopa;
                // Generamos el HTML de cada elemento recibido

                var htmlTabla=generarTabla(x.sopa);
                var htmlPalabras=generarPalabras(x.palabras);

                

                // Se lo asignamos a los div correspondiente                
                $("#htmlTabla").html(htmlTabla);
                $("#htmlPalabras").html(htmlPalabras);


            })
            .fail(function() {
            alert("Error");
            })
            .always(function() {
            alert("Trabajo realizado");
            });
        }
    );
});