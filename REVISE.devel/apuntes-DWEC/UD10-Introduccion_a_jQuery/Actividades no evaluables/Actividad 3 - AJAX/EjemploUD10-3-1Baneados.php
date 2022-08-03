<?php
// Esta cabecera la ponemos para que peticiones AJAX se acepten desde fuera del dominio.
// Sin ella, no funcionaria las peticiones al hosting hispabyte.
// Esta cabecera NO debe estar si haceis peticiones AJAX entre un mismo dominio
header("Access-Control-Allow-Origin: *");


// Con una objeto pasaremos el JSON
// Creamos el JSON http://php.net/manual/es/ref.json.php



$baneados[0]="Pedro";
$baneados[1]="Alberto";



echo(json_encode($baneados));



?>