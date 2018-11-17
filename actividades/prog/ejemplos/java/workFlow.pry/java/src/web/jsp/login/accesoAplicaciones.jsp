<!--
Nombre      : login/accesoAplicaciones.jsp
Descripción : Página de Comprobar login
Fec.Ult.Mod.: 25-08-2003
-->

<%@ page import = "totem.lib.WFBD" %>
<jsp:useBean id="vs_wfbd" class="totem.lib.WFBD" scope="session"/>

<%@ page import = "totem.lib.WFSesion" %>
<jsp:useBean id="vs_sesion" class="totem.lib.WFSesion" scope="session"/>

<%@ page import = "totem.utl.utlPropiedades" %>
<jsp:useBean id="vs_prop" class="totem.utl.utlPropiedades" scope="session"/>


<html>
<body>
<H1>TOTEM</H1><br>

<h2>Aplicaciones disponibles</h2>
<%
      if (vs_sesion.tienePermiso("APP.BANDEJA")) 
      { %> <a href="../bandeja/inicio.jsp">Bandeja de Tareas</a>,<% }
      if (vs_sesion.tienePermiso("APP.INFORMES")) 
      { %> <a href="../informes/inicio.jsp">Informes</a>,<% }
      if (vs_sesion.tienePermiso("APP.USUARIOS")) 
      { %> <a href="../usuarios/inicio.jsp">Gestión de usuarios</a>,<% }
      if (vs_sesion.tienePermiso("APP.CIRCUITOS")) 
      { %> <a href="../circuitos/inicio.jsp">Gestión de circuitos</a>,<% }
      if (vs_sesion.tienePermiso("APP.BASEDATOS")) 
      { %> <a href="../basedatos/inicio.jsp">Gestión de la base de datos</a><% }
%>
<br>
<h2>Datos de la Sesión</h2>
<%
   out.println("<b>Usuario:</b> "+vs_sesion.leeCodUsuario()+" ("+vs_sesion.leeNombre()+" "+vs_sesion.leeApellido1()
      +vs_sesion.leeApellido2()+")<br>");
   out.println("<b>Sesión anterior:</b> "+vs_sesion.leeFechaLastLogin()+"<br>");
   out.println("<b>Entorno:</b> "+vs_wfbd.leeEntorno()+"<br>");
%><br>

<a href="desconectarSesion.jsp">Desconectar</a><br>
<br>
<h2>Otros</h2>
TOTEM<BR>2003<br>Versión 0.0.0<br>
</body>
</html>

