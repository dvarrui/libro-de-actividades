<!--Proyecto TENERIFE-->
<!--Fecha:Tue Feb 07 22:55:26 WET 2006-->
<?
   include("../conexion/conectardb.php");
   $conexion = conectar_bd();
?>
<html><head>
<title>Borrar perfiles</title></head>
<link rel="stylesheet" type="text/css" href="../../css/principal.css">
<SCRIPT LANGUAGE="JavaScript">
   function redireccionar() {
      setTimeout("location.href='../index.htm'", 1000);
   }
</SCRIPT>
</head>
<body onload="redireccionar()">
<?
   $consulta= "DELETE FROM perfiles WHERE cod_perfil='{$HTTP_POST_VARS['codPerfil']}'";
   $query = mysql_query($consulta, $conexion);
   if (mysql_errno($conexion))
	    echo "No se pudo borrar";
   else
	    echo "Registro eliminado correctamente";
?>
<p><a href="../index.htm">Indice</a></p>
</body>
</html>
