<html>
<head>
<title>JSP: Hola Usuario</title>
</head>
<body bgcolor=white>

<table border="0">
<tr>
<td align=center>
<img src="../images/java_logo.gif">
</td>
<td>
<h1>JSP: Hola Usuario</h1>
</td>
</tr>
</table>

¡Hola &lt <%=request.getParameter("usuario") %> &gt <br>

Tu nombre es:<br>
<% String nombre = request.getParameter("usuario");
	for (int i=0;i<10;i++) {
%>
	[<%=i %>]=<b><%= nombre %></b><br>
<% }
%>
</body>
</html>
