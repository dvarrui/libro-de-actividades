<?
	//--------------------------------------------------
	//Definiciones de funciones
	//--------------------------------------------------
	include('rec/php/config.php');

	function getPagina($codigo, $ancla="") {
		if ($ancla) {
			$salida = "cargador.php?pagina=" . $codigo . "#" . $ancla; 
		} else {
			$salida = "cargador.php?pagina=" . $codigo; 
		}
		return $salida;
	}

	function echoPagina($codigo, $ancla="") {
		echo getPagina($codigo,$ancla);
		return true;
	}

	function getRecurso($url) {
		include('rec/php/config.php');
		//$salida = "http://www.iescesarmanrique.org/dwn/" . $url;
		//$salida = "http://localhost/cesarmanrique/dwn/" . $url;
		$salida = $gbl_directorio_dwn . $url;
		return $salida;
	}

	function echoRecurso($url) {
		echo getRecurso($url);
		return true;
	}


	function echoImprimir($codigo) {
		$salida = "cargador.php?pagina=imprimir&objetivo=" . $codigo; 
		echo $salida;
	}
?> 
