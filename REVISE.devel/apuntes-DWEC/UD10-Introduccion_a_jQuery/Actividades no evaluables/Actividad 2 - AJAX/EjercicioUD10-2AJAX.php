<?php
// Esta cabecera la ponemos para que peticiones AJAX se acepten desde fuera del dominio.
// Sin ella, no funcionaria las peticiones al hosting hispabyte.
// Esta cabecera NO debe estar si haceis peticiones AJAX entre un mismo dominio
header("Access-Control-Allow-Origin: *");


// Establecemos semilla aleatoria para simular que sopa enviamos
srand((double)microtime()*1000000);
$numeroAleatorio = rand(0, 4);

class sopaLetras {
   var $sopa;
   var $palabras;
}

$sopaAEnviar=new sopaLetras;

if($numeroAleatorio==1){
	$sopaAEnviar->sopa[0]="HOLAA";
	$sopaAEnviar->sopa[1]="ADIOS";
	$sopaAEnviar->palabras[0]="ADIOS";
	$sopaAEnviar->palabras[1]="HOLA";
	$sopaAEnviar->palabras[2]="OLA";
}else if($numeroAleatorio==2){	
	$sopaAEnviar->sopa[0]="PROBANDO";
	$sopaAEnviar->sopa[1]="ALMOHADA";
	$sopaAEnviar->sopa[1]="ESTTAZRT";
	$sopaAEnviar->sopa[2]="ESTAAZRT";
		
	$sopaAEnviar->palabras[0]="PROBANDO";
	$sopaAEnviar->palabras[1]="ALMOHADA";
	$sopaAEnviar->palabras[2]="HADA";
	$sopaAEnviar->palabras[3]="BOTA";

}else if($numeroAleatorio==3){	
	$sopaAEnviar->sopa[0]="RTMJSON";
	$sopaAEnviar->sopa[1]="AJAAJAX";
	$sopaAEnviar->sopa[1]="RRTVAER";
	$sopaAEnviar->sopa[2]="EQSORTX";
		
	$sopaAEnviar->palabras[0]="RAR";
	$sopaAEnviar->palabras[1]="AJAX";
	$sopaAEnviar->palabras[2]="JSON";
	$sopaAEnviar->palabras[3]="QSORT";

}else{	
	$sopaAEnviar->sopa[0]="CCAM";
	$sopaAEnviar->sopa[1]="CAMA";
	$sopaAEnviar->sopa[2]="XLEE";
		
	$sopaAEnviar->palabras[0]="CAL";
	$sopaAEnviar->palabras[1]="CAMA";
	$sopaAEnviar->palabras[2]="AMA";

}

echo(json_encode($sopaAEnviar));



?>