
function mostrar(cadena){
    cadenaPartida=cadena.split(":");
    alert("CP "+cadenaPartida[4]);
    alert("Apellidos "+cadenaPartida[1]);
    alert("Email "+cadenaPartida[3]);    
    alert("Email servidor "+cadenaPartida[3].split("@")[1]);
    
}


var c=prompt("Mete cadena","nonombre:apellidos:telefono:email@servidor:codigopostal");

mostrar(c);