<!--
Nombre      : desconectarSesion.jsp
Descripción : 
Fecha       : 28-08-2003
Estado      : OK
JSP visual  : No
Enlaces con : index.jsp
-->

<%@ page import = "totem.lib.WFBD" %>
<jsp:useBean id="vs_wfbd" class="totem.lib.WFBD" scope="session"/>

<%@ page import = "totem.lib.WFSesion" %>
<jsp:useBean id="vs_sesion" class="totem.lib.WFSesion" scope="session"/>

<%@ page import = "totem.lib.WFRegistrarConexion" %>

<%
   //Registrar la conexión
   WFRegistrarConexion rc = new WFRegistrarConexion(vs_wfbd.leeConnection()); rc.ponModulo("ConexiónWEB");
   rc.registrarLOG("FinConexiónWEB");

   vs_sesion.logout();
   vs_wfbd.desconectar();
%>
<jsp:forward page="/index.jsp"/>

