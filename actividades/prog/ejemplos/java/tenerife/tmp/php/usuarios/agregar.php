<!--Proyecto TENERIFE-->
<!--Fecha:Tue Feb 07 22:55:26 WET 2006-->
<?
   include("../conexion/conectardb.php");
   $conexion = conectar_bd();
?>
<html><head>
<title>Agregar usuarios</title></head>
<link rel="stylesheet" type="text/css" href="../../css/principal.css">
<SCRIPT LANGUAGE="JavaScript">
   function redireccionar() {
      setTimeout("location.href='../index.htm'", 1000);
   }
</SCRIPT>
</head>
<body onload="redireccionar()">
<?
   $consulta= "INSERT INTO usuarios(cod_usuario,des_usuario,password,cod_perfil,fec_desde,fec_hasta,activo) VALUES ('{$HTTP_POST_VARS['codUsuario']}','{$HTTP_POST_VARS['desUsuario']}','{$HTTP_POST_VARS['password']}','{$HTTP_POST_VARS['codPerfil']}','{$HTTP_POST_VARS['fecDesde']}','{$HTTP_POST_VARS['fecHasta']}','{$HTTP_POST_VARS['activo']}'";
   $query = mysql_query($consulta, $conexion);
   if (mysql_errno($conexion))
	    echo "No se pudo agregar";
   else
	    echo "Registro creado correctamente";
?>
<p><a href="../index.htm">Indice</a></p>
</body>
</html>
