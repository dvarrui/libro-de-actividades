<!--Proyecto TENERIFE-->
<!--Fecha:Tue Feb 07 22:55:26 WET 2006-->
<?
   include("../conexion/conectardb.php");
   $conexion = conectar_bd();
?>
<html><head>
<title>Agregar agendas</title></head>
<link rel="stylesheet" type="text/css" href="../../css/principal.css">
<SCRIPT LANGUAGE="JavaScript">
   function redireccionar() {
      setTimeout("location.href='../index.htm'", 1000);
   }
</SCRIPT>
</head>
<body onload="redireccionar()">
<?
   $consulta= "INSERT INTO agendas(cod_agenda,des_agenda) VALUES ('{$HTTP_POST_VARS['codAgenda']}','{$HTTP_POST_VARS['desAgenda']}'";
   $query = mysql_query($consulta, $conexion);
   if (mysql_errno($conexion))
	    echo "No se pudo agregar";
   else
	    echo "Registro creado correctamente";
?>
<p><a href="../index.htm">Indice</a></p>
</body>
</html>
