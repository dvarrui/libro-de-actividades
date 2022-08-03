<?php
// Esta cabecera la ponemos para que peticiones AJAX se acepten desde fuera del dominio.
// Sin ella, no funcionaria las peticiones al hosting hispabyte.
// Esta cabecera NO debe estar si haceis peticiones AJAX entre un mismo dominio
header("Access-Control-Allow-Origin: *");

function obtenerVotos(){
	//Abrimos fichero y leemos primera linea
	$fichero=fopen("votos.txt","r");
	$votos=fgets($fichero);
	fclose($fichero);
	return $votos;
}

function aumentarVotos(){
	// Obtenemos votos
	$votos=obtenerVotos();
	$votos=$votos+1;
	// Nos cargamos el fichero introduciendo el nuevo valor de los votos
	$fichero=fopen("votos.txt","w");
	fprintf($fichero,"%s",$votos);
	fclose($fichero);
	return $votos;
}



// Obtenemos la accion por el metodo post.
$accion=$_POST["accion"];
// Leemos el fichero de votos y los mostremos
if($accion=="nVotos"){
	$res=obtenerVotos();
	echo($res);
}
// Aumentamos los votos en el fichero de votos y los mostramos
else if($accion=="votar"){
	$res=aumentarVotos();
	echo($res);
}
else{
	echo("No se envio ninguna accion correcta");
}
	
?> 
