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
<h1>JSP: Saludos</h1>
</td>
</tr>
</table>

<H2>Saludos</H2>

<% if (Math.random() < 0.5) { %>
¡Hola!, ¿Qué tal estás?
<% } else { %>
¡Buenos días!
<% } %>

</BODY>
</HTML>
