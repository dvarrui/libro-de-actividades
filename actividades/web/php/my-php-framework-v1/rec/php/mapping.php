<?
	/*
	*	Fichero: mapping.php
	*	F.Creación: 20060501
	*	Versión: 20070514
	*	Autor: David Vargas Ruiz
	*	Descripción: Contiene un registro por cada uno de los ficheros de contenidos.
	*/
	//---------------------------------------------------
	//BEGIN.MAPPING
	//
	//[Código] = clave padre, fichero de contenidos, plantilla, nombre, descripción
	//
	$mapping['index'] = array('','index.cnt', 
	'plantilla_01.php', 'Principal', 'P&aacute;gina Principal - David Vargas');

	//
	//DIR
	//
	$mapping['dir.index'] = array('index','dir/index.cnt',
	'plantilla_01.php','DIR','M&oacute;dulo DIR');

	//DIR::UD::cui
	$mapping['dir.cui.index'] = array('dir.index','dir/cui/index.cnt',
	'plantilla_06.php','CUI','CUI: Interfaz de Usuario en modo Consola',);

	$mapping['dir.recuerdac'] = array('dir.cui.index','dir/cui/recuerdac.cnt',
	'plantilla_03.php','ANSI C','Recordar el lenguage de programaci&oacute;n C');

	$mapping['dir.cui.holamundo.index'] = array('dir.cui.index', 
	'dir/cui/holamundo/index.cnt', 'plantilla_06.php', 'HolaMundo', 
	'HolaMundo');

	$mapping['dir.cui.holamundo.1'] = array('dir.cui.holamundo.index', 
	'dir/cui/holamundo/holamundo1.cnt', 'plantilla_06.php', 'HolaMundo1', 
	'HolaMundo1');

	$mapping['dir.cui.holamundo.2'] = array('dir.cui.holamundo.index', 
	'dir/cui/holamundo/holamundo2.cnt', 'plantilla_06.php', 'HolaMundo2', 
	'HolaMundo2');

	$mapping['dir.cui.holamundo.3'] = array('dir.cui.holamundo.index', 
	'dir/cui/holamundo/holamundo3.cnt', 'plantilla_06.php', 'HolaMundo3', 
	'HolaMundo3');

	$mapping['dir.cui.holamundo.4'] = array('dir.cui.holamundo.index', 
	'dir/cui/holamundo/holamundo4.cnt', 'plantilla_06.php', 'HolaMundo4', 
	'HolaMundo4');

	$mapping['dir.cui.holamundo.5'] = array('dir.cui.holamundo.index', 
	'dir/cui/holamundo/holamundo5.cnt', 'plantilla_06.php', 'HolaMundo5', 
	'HolaMundo5');

	$mapping['dir.cui.holamundo.6'] = array('dir.cui.holamundo.index', 
	'dir/cui/holamundo/holamundo6.cnt', 'plantilla_06.php', 'HolaMundo6', 
	'HolaMundo6');

	$mapping['dir.cui.saludo.index'] = array('dir.cui.index', 
	'dir/cui/saludo/index.cnt', 'plantilla_01.php', 'Saludo', 
	'Saludo');

	$mapping['dir.cui.saludo.1'] = array('dir.cui.saludo.index', 
	'dir/cui/saludo/saludo1.cnt', 'plantilla_01.php', 'Saludo1', 
	'Saludo1');

	$mapping['dir.cui.saludo.2'] = array('dir.cui.saludo.index', 
	'dir/cui/saludo/saludo2.cnt', 'plantilla_01.php', 'Saludo2', 
	'Saludo2');

	//DIR::UD::gui
	$mapping['dir.gui.index'] = array('dir.index','dir/gui/index.cnt',
	'plantilla_06.php','GUI', 'Interfaz Gr&aacute;fica de Usuario');

	//DIR::UD::ve
	$mapping['dir.ve.index'] = array('dir.index', 'dir/ve/index.cnt', 
	'plantilla_06.php', 'VE', 'Visual Editor');


	//DIR::UD::cajas
	$mapping['dir.cajas.index'] = array('dir.index','dir/cajas/index.cnt',
	'plantilla_06.php','Cajas','Cajas o cuadros de di&aacute;logo',);

	$mapping['dir.cajas.directorios.index'] = array('dir.cajas.index',
	'dir/cajas/directorios/index.cnt',
	'plantilla_06.php','Directorios','Manipulaci&oacute;n de los directorios',);

	$mapping['dir.cajas.tree.1'] = array('dir.cajas.directorios.index',
	'dir/cajas/directorios/tree1.cnt',
	'plantilla_06.php','Tree','Ejemplos del programa Tree',);

	//##############
	//DIR::UD::multiples
	$mapping['dir.multiples.index'] = array('dir.index','dir/multiples/index.cnt',
	'plantilla_06.php','M&uacute;ltiples','M&uacute;ltiples ventanas',);

	$mapping['dir.multiples.hilos.index'] = array('dir.multiples.index',
	'dir/multiples/hilos/index.cnt',
	'plantilla_06.php','Hilos','Programaci&oacute;n de hilos',);

	$mapping['dir.multiples.hilos.1'] = array('dir.multiples.hilos.index',
	'dir/multiples/hilos/hilos1.cnt',
	'plantilla_06.php','Hilos1','Ejemplos del programa Hilos1',);
	
	$mapping['dir.multiples.hilos.2'] = array('dir.multiples.hilos.index',
	'dir/multiples/hilos/hilos2.cnt',
	'plantilla_06.php','Hilos2','Ejemplos del programa Hilos2',);

	//###########	
	//Módulo: PSP
	$mapping['psp.index'] = array('index',
	'psp/index.cnt', 'plantilla_06.php', 'PSP', 
	'Portable Sony Playstation');
	
	$mapping['psp.instalar'] = array('psp.index',
	'psp/instalar/index.cnt', 'plantilla_06.php', 'Instalar', 
	'Proceso de instalaci&oacute;n');

	$mapping['psp.enlaces'] = array('psp.index',
	'psp/enlaces.cnt', 'plantilla_06.php', 'Enlaces', 
	'Enlaces de Inter&eacute;s');

	//###########
	//Módulo: SIT
	$mapping['sit.index'] = array('index',
	'sit/index.cnt', 'plantilla_06.php', 'SIT', 
	'Sistemas Operativos');

	//-----------------------
	//Pie de página
	$mapping['piedepagina.condicionesdeuso'] = array('index','piedepagina/condicionesdeuso.cnt', 
	'plantilla_01.php', "Condiciones de uso", "Condiciones de uso");
	$mapping['piedepagina.contacto'] = array('index','piedepagina/contacto.cnt', 
	'plantilla_01.php', 'Contacto', 'Informaci&oacute;n de Contacto', " > ");
	$mapping["piedepagina.creditos"] = array('index',"piedepagina/creditos.cnt", 
	"plantilla_01.php", "Cr&eacute;ditos", "Cr&eacute;ditos", " > ");
	$mapping["piedepagina.mapaweb"] = array('index',"piedepagina/mapaweb.cnt", 
	"plantilla_01.php", "Mapa del sitio web", "Mapa del sitio web", " > ");

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
