<?


	function echoAlbumdeFotos($imagenes) {
		//--------------------------------------------------------
		//Si no tenemos la variable índice nos la inventamos con 0
		if (isset($_GET["indice"])) $indice = $_GET["indice"];
		else $indice = 0;

		if (isset($_GET["pagina"])) $pagina = $_GET["pagina"];
		else $pagina = 'error';


		//echo '<h2>Alb&uacute;m de fotos</h2>';
		$fichero = getRecurso($imagenes[$indice]);
		echo '<br>'; 

		echoNavegacionFotos($pagina,$indice,$imagenes);
		echo '<center><img border="1" alt="imagen_'.$indice.'" 
		src="'.$fichero.'" align="center"></center>';
		echoNavegacionFotos($pagina,$indice,$imagenes);
	}

	function echoNavegacionFotos($pagina,$indice,$imagenes) {
		//		
		//Esta función genera una barra de navegación para
		//un conjunto de imágenes recibidas desde una array.
		// 
		echo '<table align="center"><tr>';

		//Primera parte: Primera | Anterior		
		if ($indice==0) {
			//Primera imagen
			echo '<td>Primera</td>';
			echo '<td>&nbsp;|&nbsp;</td>';
			echo '<td>Anterior</td>';
		} else {
			echo '<td><a href="'.getPagina($pagina).'&indice=0">Primera<a></td>';
			echo '<td>&nbsp;|&nbsp;</td>';
			echo '<td><a href="'.getPagina($pagina).'&indice='.($indice-1).'">Anterior<a></td>';
		}

		//Esto aparece siempre
		echo '<td>&nbsp;|&nbsp;</td>';			
		echo '<td><b>Imagen '.($indice+1).' de '.count($imagenes).'</b></td>';
		echo '<td>&nbsp;|&nbsp;</td>';
					
		if ($indice==count($imagenes)-1) {
			//Última imagen
			echo '<td>Siguiente</td>';
			echo '<td>&nbsp;|&nbsp;</td>';
			echo '<td>&Uacute;ltima</td>';
		} else {					
			echo '<td><a href="'.getPagina($pagina).'&indice='.($indice+1).'">Siguiente</a></td>';
			echo '<td>&nbsp;|&nbsp;</td>';
			echo '<td><a href="'.getPagina($pagina).'&indice='.(count($imagenes)-1).'">&Uacute;ltima<a></td>';	
		}
		
		echo '</tr></table>';
	}
	
	
	function echoAlbumdeFotosConIndice($imagenes) {
		//--------------------------------------------------------
		$indice=0;
		while ($indice<count($imagenes)) {
				echo $indice.'-';
				$fichero = getRecurso($imagenes[$indice]);
				echo $fichero;
				echo '<br>';
				echo '<img border="1" alt="imagen_'.$indice.'" 
				src="'.$fichero.'" align="center">';
				
				$indice=$indice+1;
		}

		echo '<br>'; 
		echo '<img border="1" alt="imagen_'.$indice.'" 
		src="http://localhost/cesarmanrique/dwn/departamentos/electricidad/pgs/asadero/imagen_00.jpg" align="center">';
		
		echo '<table><tr>';
		
		if ($indice==0) {
			//Primera imagen
			echo '<td>Anterior</td>';
			echo '<td>&nbsp;|&nbsp;</td>';
			echo '<td>Imagen '.$indice.' de '.count($imagenes).' </td>';
			echo '<td>&nbsp;|&nbsp;</td>';
			//echo '<td><a href="'.'getPagina($pagina).'&indice='.$indice+1.'">Siguiente</a></td>';
		} elseif ($indice==count($imagenes)) {
			//Última imagen
			//echo '<td><a href="'.getPagina($pagina).'&indice='.$indice-1.'">Anterior<a></td>';
			echo '<td>Imagen '.$indice.' de '.count($imagenes).' </td>';
			echo '<td>Siguiente</td>';
		} else {
			//Entre medio
			//echo '<td><a href="'.getPagina($pagina).'&indice='.$indice-1.'">Anterior<a></td>';
			echo '<td>Imagen '.$indice.' de '.count($imagenes).'</td>';
			//echo '<td><a href="'.getPagina($pagina).'&indice='.$indice+1.'">Siguiente</a></td>';
		}
		echo '</tr></table>';
	}


	function echoAlbumdeFotosModo2($imagenes,$dir_fotos,$dir_diapositivas) {
		//--------------------------------------------------------
		//Si no tenemos la variable índice nos la inventamos con 0
		if (isset($_GET["indice"])) $indice = $_GET["indice"];
		else $indice = 0;

		if (isset($_GET["pagina"])) $pagina = $_GET["pagina"];
		else $pagina = 'error';


		//echo '<h2>Alb&uacute;m de fotos</h2>';
		$diapositiva = getRecurso($dir_diapositivas."d_".$imagenes[$indice]);
		$foto = getRecurso($dir_fotos.$imagenes[$indice]);
		echo '<br>'; 

		echoNavegacionFotos($pagina,$indice,$imagenes);
		echo '<a href="'.$foto.'">';
		echo '<center><img border="1" alt="diapositiva_'.$imagenes[$indice].'" 
		src="'.$diapositiva.'" align="center"></center>';
		echo '</a>';
		echoNavegacionFotos($pagina,$indice,$imagenes);
	}
	
?>

