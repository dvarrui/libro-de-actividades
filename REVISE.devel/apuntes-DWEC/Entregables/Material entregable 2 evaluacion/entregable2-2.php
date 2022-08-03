<?php
// Esta cabecera la ponemos para que peticiones AJAX se acepten desde fuera del dominio.
// Sin ella, no funcionaria las peticiones al hosting hispabyte.
// Esta cabecera NO debe estar si haceis peticiones AJAX entre un mismo dominio
header("Access-Control-Allow-Origin: *");
class articulo {
	public $id;
	public $cat;
	public $nombre;
	public $unidades;
	public $precio;
}


$arrayTienda[0]=new articulo;
$arrayTienda[1]=new articulo;
$arrayTienda[2]=new articulo;
$arrayTienda[3]=new articulo;

$arrayTienda[0]->id=1;
$arrayTienda[0]->cat=1;
$arrayTienda[0]->nombre="Recopilatorio The Beatles";
$arrayTienda[0]->unidades=rand(1,100);
$arrayTienda[0]->precio=rand(100,10000);

$arrayTienda[1]->id=2;
$arrayTienda[1]->cat=2;
$arrayTienda[1]->nombre="Ropa retro";
$arrayTienda[1]->unidades=rand(1,100);
$arrayTienda[1]->precio=rand(100,10000);


$arrayTienda[2]->id=3;
$arrayTienda[2]->cat=2;
$arrayTienda[2]->nombre="Ropa molona";
$arrayTienda[2]->unidades=rand(1,100);
$arrayTienda[2]->precio=rand(100,10000);


$arrayTienda[3]->id=4;
$arrayTienda[3]->cat=1;
$arrayTienda[3]->nombre="Recopilatorio Bob Marley";
$arrayTienda[3]->unidades=rand(1,100);
$arrayTienda[3]->precio=rand(100,10000);



echo(json_encode($arrayTienda));


?>