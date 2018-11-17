<!--Proyecto TENERIFE-->
<!--Fecha:Tue Feb 07 22:55:26 WET 2006-->
<?
   include("../conexion/conectardb.php");
   $conexion = conectar_bd();
?>
<html><head>
<title>Agregar catalogos</title></head>
<link rel="stylesheet" type="text/css" href="../../css/principal.css">
<SCRIPT LANGUAGE="JavaScript">
   function redireccionar() {
      setTimeout("location.href='../index.htm'", 1000);
   }
</SCRIPT>
</head>
<body onload="redireccionar()">
<?
   $consulta= "INSERT INTO catalogos(cod_catalogo,des_catalogo,cod_catalogo_padre,peso) VALUES ('{$HTTP_POST_VARS['codCatalogo']}','{$HTTP_POST_VARS['desCatalogo']}','{$HTTP_POST_VARS['codCatalogoPadre']}','{$HTTP_POST_VARS['peso']}'";
   $query = mysql_query($consulta, $conexion);
   if (mysql_errno($conexion))
	    echo "No se pudo agregar";
   else
	    echo "Registro creado correctamente";
?>
<p><a href="../index.htm">Indice</a></p>
</body>
</html>
