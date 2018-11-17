<?
  //Autor: David Vargas <dvargas@canarias.org>
  
  //El cargador es el módulo central de este sitio web
  //Recibe peticiones de carga de página a través de la
  //variable 'pagina' y las muestra en el navegador del
  //cliente.
?>
<?
	//--------------------------------------------------
	//Definiciones de funciones
	include('rec/php/funciones.php');

	//---------------------------------------------------
	//Si no tenemos la variable página nos la inventamos 
	//por defecto usaremos la principal estos e pagina=index
	if (isset($_GET["pagina"])) $pagina = $_GET["pagina"];
	else $pagina = "index";

	//---------------------------------------------------
	//Definiciones de mapeo de todas las páginas
	//Cada página de la web tiene un registro dentro de mapping
	include('rec/php/mapping.php');
	//Si el mapeo falla, entonces no existe la página que se busca 
	//y redirimos a pagina=error
	if (!isset($mapping[$pagina])) $pagina = "error";

	//---------------------------------------------------------------
	//Preparamos las variables para confeccionar la página solicitada
	$id_padre = $mapping[$pagina][0];
	$contenido = "cnt/" . $mapping[$pagina][1];
	$plantilla = "rec/pln/" . $mapping[$pagina][2];
	$nombre = $mapping[$pagina][3];
	$descripcion = $mapping[$pagina][4];

	//-----------------------------------
	//Confeccionamos la página solicitada
	include($plantilla);
?>

<?
	//NOTA: consultar htmlentities
?> 
