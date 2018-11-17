<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Curso JSP (Java Server Pages)</title>
</head>
<body bgcolor=white>

<table border="0">
<tr>
<td align=center>
<img src="../images/java_logo.gif">
</td>
<td>
<h1>JSP: Contador de Accesos</h1>
</td>
</tr>
</table>


<%! private int contador = 0; %>
Número de accesos a la página desde el arranque del servidor:
<%= ++contador %>

</BODY>
</HTML>
