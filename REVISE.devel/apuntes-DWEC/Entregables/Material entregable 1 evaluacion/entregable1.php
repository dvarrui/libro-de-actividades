<?php
// Esta cabecera la ponemos para que peticiones AJAX se acepten desde fuera del dominio.
// Sin ella, no funcionaria las peticiones al hosting hispabyte.
// Esta cabecera NO debe estar si haceis peticiones AJAX entre un mismo dominio
header("Access-Control-Allow-Origin: *");
// Obtenemos la accion por el metodo post.
$nombre=$_POST["nombre"];

// Si el nombre tiene letras par es ERROR, si es impar es OK
if(strlen($nombre)%2==0){
	echo("ERROR");
}else{
	echo("OK");
}


?>