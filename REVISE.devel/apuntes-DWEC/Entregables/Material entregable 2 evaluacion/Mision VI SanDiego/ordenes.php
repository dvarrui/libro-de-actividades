<?php
// Esta cabecera la ponemos para que peticiones AJAX se acepten desde fuera del dominio.
// Sin ella, no funcionaria las peticiones al hosting hispabyte.
// Esta cabecera NO debe estar si haceis peticiones AJAX entre un mismo dominio
header("Access-Control-Allow-Origin: *");

	if($_POST["accion"]=="pedirClave")
		echo("CANCAMUSA");
	else if($_POST["accion"]=="AtaqueCPU"){
		
		//echo($_POST["objeto"]."\n");
		$obj = json_decode($_POST["objeto"]);
		// Calcualdo el Cesar de CANCAMUSA con http://brianur.info/cifrado-caesar/
		
		/*
		echo(var_dump($obj)."\n");
		echo($obj->{'tipoAtaque'}."\n");
		echo($obj->{'IPDestino'}."\n");
		echo($obj->{'Clave'}."\n");
		*/
		
		if($obj->{'tipoAtaque'}=="DDOS" &&$obj->{'IPDestino'}=="256.256.256.256" && $obj->{'Clave'}=="DBODBNVTB"){
			echo("TODOOK");
		}else{
			echo("ERROR");
		}
	}
		
?>