<?
	//Autor: David Vargas <dvargas@canarias.org>
	if (isset($_GET["objetivo"])) $objetivo = $_GET["objetivo"];
	else $objetivo = "index";

	$id_padre = $mapping[$objetivo][0];
	$contenido = "cnt/" . $mapping[$objetivo][1];
	$template = "rec/pln/" . $mapping[$objetivo][2];
	$nombre = $mapping[$objetivo][3];
	$descripcion = $mapping[$objetivo][4]; 
 	include($contenido); 

	echo '<br>';
   echo '<i>David Vargas Ruiz</i><br>';
	echo '<i>dvargas@canarias.org</i>'; 
?>