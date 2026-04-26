<?
	/*
	*	Fichero: mapping.php
	*	F.Creación: 20060501
	*	Versión: 20080813
	*	Autor: David Vargas Ruiz
	*	Descripción: Contiene un registro por cada uno de los ficheros de contenidos.
	*/
	//---------------------------------------------------
	//BEGIN.MAPPING
	//
	//[Código] = clave padre, fichero de contenidos, plantilla, nombre, descripción
	//
	$mapping['index'] = array('','index.cnt', 
	'plantilla_04.php', 'Principal', 'Alb&uacute;m de fotos de Aar&oacute;n');

	//##############
	//Fotos variadas
	//
	//$mapping['fotos.variadas'] = array('index','variadas/index.cnt',
	//'plantilla_04.php','Variadas','Fotos variadas');


	//###########	
	//Vídeos
	//$mapping['psp.index'] = array('index',
	//'psp/index.cnt', 'plantilla_06.php', 'PSP', 
	//'Portable Sony Playstation');
	
	//#################
	//Fotos del bautizo
	$mapping['fotos.bautizo'] = array('index',
	'bautizo/index.cnt', 'plantilla_04.php', 'Bautizo', 
	'Fotos del Bautizo (Xerachi)');

	$mapping['fotos.bautizo2'] = array('index',
	'bautizo2/index.cnt', 'plantilla_04.php', 'Bautizo', 
	'Fotos del Bautizo (Daniel)');

	//-----------------------
	//Pie de página
	$mapping['piedepagina.condicionesdeuso'] = array('index','piedepagina/condicionesdeuso.cnt', 
	'plantilla_04.php', "Condiciones de uso", "Condiciones de uso");
	$mapping['piedepagina.contacto'] = array('index','piedepagina/contacto.cnt', 
	'plantilla_04.php', 'Contacto', 'Informaci&oacute;n de Contacto', " > ");
	$mapping["piedepagina.creditos"] = array('index',"piedepagina/creditos.cnt", 
	"plantilla_04.php", "Cr&eacute;ditos", "Cr&eacute;ditos", " > ");
	$mapping["piedepagina.mapaweb"] = array('index',"piedepagina/mapaweb.cnt", 
	"plantilla_04.php", "Mapa del sitio web", "Mapa del sitio web", " > ");

	//-----------------------
	// Error
	$mapping['error'] = array('index', "piedepagina/error.cnt", 
	"plantilla_01.php", 'Error', 'Error', ' | ');
	//-----------------------
	// Buscar
	$mapping['buscador'] = array('index', "piedepagina/buscar.cnt", 
	"plantilla_01.php", 'Resultado de la b&uacute;squeda', 
	'Resultado de la b&uacute;squeda');
	//-----------------------
	// Buscar
	$mapping['imprimir'] = array('index', 'piedepagina/imprimir.cnt', 
	'plantilla_imprimir.php', 'Imprimir', 'Imprimir');

	//
	//END.MAPPING
	//---------------------------------------------------
?> 
