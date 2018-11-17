<!--
Nombre      : index.jsp
Descripción : Página de presentación
Fec.Ult.Mod.: 25-08-2003
-->

<%@ page import = "totem.utl.utlPropiedades" %>
<jsp:useBean id="vs_prop" class="totem.utl.utlPropiedades" scope="session"/>

<html>
<body>
<H1>Aplicación TOTEM</H1><br>

<% vs_prop.setNombreFichero("totem.rec.frmWFLogin"); %> 

   <form method=post action="/totem/jsp/login/comprobarLogin.jsp">
   Nombre  <input type=text name=nombreUsuario><br>
   Clave   <input type=password name=claveUsuario><br>
   Entorno <SELECT name="entorno" >
              <OPTION VALUE=0> <%=vs_prop.getStringElement("Entornos",0)%> <BR>
              <OPTION VALUE=1> <%=vs_prop.getStringElement("Entornos",1)%> <BR>
              <OPTION VALUE=2> <%=vs_prop.getStringElement("Entornos",2)%> <BR>
           </SELECT>
           <input type=submit value="Entrar">
   </form>
</body>
</html>

