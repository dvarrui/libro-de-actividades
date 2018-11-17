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
<h1>JSP: TAG holamundo</h1>
</td>
</tr>
</table>



<!-- HolaMundo.jsp -->
<%@ taglib uri="http://localhost:8080/tld/ejemplo.tld" prefix="ejemplos" %>

<H2>TAG holamundo</H2>
A continuación se muestra el resultado de la etiqueta personalizada:<br>
<i><ejemplos:holamundo/></i>

</BODY>
</HTML>
