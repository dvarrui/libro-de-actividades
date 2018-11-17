<!--Proyecto TENERIFE-->
<!--Fecha:Tue Feb 07 22:55:26 WET 2006-->
<?
   include("../conexion/conectardb.php");
   $conexion = conectar_bd();
?>
<html><head>
<title>Agregar personas</title></head>
<link rel="stylesheet" type="text/css" href="../../css/principal.css">
<SCRIPT LANGUAGE="JavaScript">
   function redireccionar() {
      setTimeout("location.href='../index.htm'", 1000);
   }
</SCRIPT>
</head>
<body onload="redireccionar()">
<?
   $consulta= "INSERT INTO personas(cod_persona,nombre,apellido1,apellido2,fecha_nacimiento,dni,telefono1,telefono2,direccion) VALUES ('{$HTTP_POST_VARS['codPersona']}','{$HTTP_POST_VARS['nombre']}','{$HTTP_POST_VARS['apellido1']}','{$HTTP_POST_VARS['apellido2']}','{$HTTP_POST_VARS['fechaNacimiento']}','{$HTTP_POST_VARS['dni']}','{$HTTP_POST_VARS['telefono1']}','{$HTTP_POST_VARS['telefono2']}','{$HTTP_POST_VARS['direccion']}'";
   $query = mysql_query($consulta, $conexion);
   if (mysql_errno($conexion))
	    echo "No se pudo agregar";
   else
	    echo "Registro creado correctamente";
?>
<p><a href="../index.htm">Indice</a></p>
</body>
</html>
