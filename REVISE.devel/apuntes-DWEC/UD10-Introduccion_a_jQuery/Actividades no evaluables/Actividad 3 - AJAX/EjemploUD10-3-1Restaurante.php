<?php
// Esta cabecera la ponemos para que peticiones AJAX se acepten desde fuera del dominio.
// Sin ella, no funcionaria las peticiones al hosting hispabyte.
// Esta cabecera NO debe estar si haceis peticiones AJAX entre un mismo dominio
header("Access-Control-Allow-Origin: *");


// Con una objeto pasaremos el JSON
// Creamos el JSON http://php.net/manual/es/ref.json.php

class voto {

   var $restaurante;
   var $usuario;
}



$misVotos[0]=new voto;
$misVotos[0]->restaurante="El Bully";
$misVotos[0]->usuario="Pedro";
$misVotos[1]=new voto;
$misVotos[1]->restaurante="El Bully";
$misVotos[1]->usuario="Pablo";
$misVotos[2]=new voto;
$misVotos[2]->restaurante="La tasca";
$misVotos[2]->usuario="Pablo";
$misVotos[3]=new voto;
$misVotos[3]->restaurante="La tasca";
$misVotos[3]->usuario="Juan";
$misVotos[4]=new voto;
$misVotos[4]->restaurante="La Pepica";
$misVotos[4]->usuario="Alberto";



echo(json_encode($misVotos));



?>