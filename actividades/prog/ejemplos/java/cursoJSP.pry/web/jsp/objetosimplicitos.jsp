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
<h1>JSP: Objetos Implícitos</h1>
</td>
</tr>
</table>

<ul>
<li>Nombre del host: <%= request.getRemoteHost() %></li>
<li>Identificador de la sesión: <%= session.getId() %></li>
<br>
<li>Conext Path: <%= request.getContextPath() %></li>
<li>Path Information: <%= request.getPathInfo() %></li>
<li>Query String: <%= request.getQueryString() %></li>
<li>Request Method: <%= request.getMethod() %></li>
<li>Servlet Path: <%= request.getServletPath() %></li>
</ul>
</body>
</html>
