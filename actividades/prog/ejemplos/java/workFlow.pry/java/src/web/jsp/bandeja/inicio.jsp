<!--
Nombre      : bandeja/inicio.jsp
Descripción : Página principal de bandeja de tareas
Fec.Ult.Mod.: 25-08-2003 DVR
Versión     : 0.8.0
-->

<%@ page import = "totem.lib.WFSesion" %>
<jsp:useBean id="vs_sesion" class="totem.lib.WFSesion" scope="session"/>

<%@ page import = "totem.lib.WFFiltro" %>
<jsp:useBean id="vs_filtro" class="totem.lib.WFFiltro" scope="session"/>

<%@ page import = "totem.lib.WFTicket" %>

<html>
<body>
<H1>Bandeja de tareas</H1><br>

<h2>Acciones disponibles</h2>
<a href="buscarTiquets.jsp">Buscar tiquets</a>,
<a href="buscar.jsp">Acción 2</a>,
<a href="../login/accesoAplicaciones.jsp">Volver</a>
<br>

<h2>Resumen</h2>
<%
   vs_filtro.inicializar(vs_sesion);
   vs_filtro.activar();

   out.println("<b>Número de página :</b> "+vs_filtro.leeNumPaginaActual()+"/"+vs_filtro.leeNumPaginaTotal()+"<br>");
   out.println("<b>Número de registros :</b> "+vs_filtro.leeNumRegistros()+"/"+vs_filtro.leeNumRegistrosTotal()+"<br>");
%><br>

<h2>Detalle</h2>
<%
   out.println("<TABLE>");
   out.println("<TR><td>Código</td><td>Padre</td><td>Fecha Creacion</td><td>Tipo</td><td>Tarea</td></TR>");	    
   for(int i=0;i<vs_filtro.leeNumRegistros();i++)
   {
      WFTicket t = vs_filtro.leeTicket(i);
      out.println("<TR>");
      out.println("<td><a href='../tiquet/verTiquet.jsp?'"+t.leeCodTicket()+">"+t.leeCodTicket()+"<a></td>");
      out.println("<td>"+t.leeCodTicketPadre()+"</td>");
      out.println("<td>"+t.leeFechaCreacion()+"</td>");
      out.println("<td>"+t.leeDesTipoTicket()+"</td>");
      out.println("<td>"+t.leeDesTarea()+"</td>");
      out.println("</TR>");	    
   }
   out.println("</TABLE>");
%>
</body>
</html>

