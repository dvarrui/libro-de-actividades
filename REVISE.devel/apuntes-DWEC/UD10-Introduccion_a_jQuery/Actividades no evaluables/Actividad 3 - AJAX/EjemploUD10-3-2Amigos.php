<?php
// Esta cabecera la ponemos para que peticiones AJAX se acepten desde fuera del dominio.
// Sin ella, no funcionaria las peticiones al hosting hispabyte.
// Esta cabecera NO debe estar si haceis peticiones AJAX entre un mismo dominio
header("Access-Control-Allow-Origin: *");

// Con una objeto pasaremos el JSON
// Creamos el JSON http://php.net/manual/es/ref.json.php
// Si es YES, definimos la variable disponible con YES
// Si es NO, definimos la variable disponible con NO y la variable alternativas con el array de alternativas

class amigo {
   var $nombre;
   var $euros;
   var $arrayReparto;
}


$amigos[0]=new amigo;

$amigos[0]->nombre="David";
$amigos[0]->euros="200";
$amigos[0]->arrayReparto=array("Laura","Miguel","Vicky");



$amigos[1]=new amigo;

$amigos[1]->nombre="Miguel";
$amigos[1]->euros="500";
$amigos[1]->arrayReparto=array("David");

$amigos[2]=new amigo;

$amigos[2]->nombre="Amador";
$amigos[2]->euros="150";
$amigos[2]->arrayReparto=array("Vicky","Miguel");



$amigos[3]=new amigo;

$amigos[3]->nombre="Laura";
$amigos[3]->euros="0";
$amigos[3]->arrayReparto=array("Amador","Vicky");



$amigos[4]=new amigo;

$amigos[4]->nombre="Vicky";
$amigos[4]->euros="0";
$amigos[4]->arrayReparto=array();


echo(json_encode($amigos));


?>