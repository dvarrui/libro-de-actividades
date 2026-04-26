<?
	//#########################
	//Definiciones de funciones
	//#########################

	function getC($codigo) {
	   include('rec/php/config.php');
		$salida = $gbl_directorio_c . $codigo; 
		return $salida;
	}

	function echoC($codigo) {
		echo getC($codigo);
		return true;
	}

	function getBash($codigo) {
	   include('rec/php/config.php');
		$salida = $gbl_directorio_bash . $codigo; 
		return $salida;
	}

	function echoBash($codigo) {
		echo getBash($codigo);
		return true;
	}

	function getJava($codigo) {
	   include('rec/php/config.php');
		$salida = $gbl_directorio_java . $codigo; 
		return $salida;
	}

	function echoJava($codigo) {
		echo getJava($codigo);
		return true;
	}

	function echoIncludeJava($codigo) {
		echo '<pre>';
		echo "<? include(\'" . getJava($codigo) ."\'); ?>";
		echo '</pre>';
		return true;
	}

	function getRuby($codigo) {
	   include('rec/php/config.php');
		$salida = $gbl_directorio_ruby . $codigo; 
		return $salida;
	}

	function echoRuby($codigo) {
		echo getRuby($codigo);
		return true;
	}

	function echoProgramas($programa) {
	   For ($i=0;$i<count($programa);$i++)
		{
   		if (substr($programa[$i],-2,2)=='.c') {
				echo '<p>'. $programa[$i] . '</p>';
				echo '<pre class=\'programaC\'>';
				//include(getC($programa[$i]));
				includePrograma(getC($programa[$i]));
				echo '</pre>';
   		} else if (substr($programa[$i],-5,5)=='.java') {
				echo '<p>' . $programa[$i] . '</p>';
				echo '<pre class=\'programaJava\'>';
				//include(getJava($programa[$i]));
				includePrograma(getJava($programa[$i]));
				echo '</pre>';
   		 } else if (substr($programa[$i],-3,3)=='.rb') {
				echo '<p>' . $programa[$i] . '</p>';
				echo '<pre class=\'programaRuby\'>';
				//include(getRuby($programa[$i]));
				includePrograma(getRuby($programa[$i]));
				echo '</pre>';
			} else if (substr($programa[$i],-3,3)=='.sh') {
				echo '<p>' . $programa[$i] . '</p>';
				echo '<pre class=\'programaBash\'>';
				//include(getBash($programa[$i]));
				includePrograma(getBash($programa[$i]));
				echo '</pre>';
			} else {
				echo '<p>' . $programa[$i] . '</p>';
				echo '<pre class=\'programaOtros\'>';
				//include(getBash($programa[$i]));
				includePrograma(getBash($programa[$i]));
				echo '</pre>';
			}
		}
		
		return true;
	}


	//#################################
	//Incluir un fichero de programa
	//
	function includePrograma($nombre_archivo) {
		//Lee un fichero que contiene un programa
		//Transforma las posibles etiquetas html en texto 
		//y lo muestra
		$fd = fopen($nombre_archivo, "r");
		$contenido = fread($fd, filesize($nombre_archivo));
		fclose($fd);
		echo htmlentities($contenido);
		return true;
	}
?> 
