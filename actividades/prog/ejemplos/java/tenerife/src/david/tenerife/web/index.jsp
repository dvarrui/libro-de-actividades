<!--
Nombre      : index.jsp
Descripciï¿½n : Pï¿½gina de presentaciï¿½n
Fec.Ult.Mod.: 25-08-2003
-->

<%@ page import = "david.proyecto.util.Configuracion" %>
<jsp:useBean id="vs_prop" class="david.proyectos.util.Configuracion" scope="session"/>

<html>
<body>
<H1>Aplicación PROYECTO</H1><br>

<% vs_prop.setFichero("david.proyecto.rec.inicio"); %> 

   <form method=post action="/jsp/login/comprobarLogin.jsp">
   Nombre  <input type=text name=nombreUsuario><br>
   Clave   <input type=password name=claveUsuario><br>
   Entorno <SELECT name="entorno" >
              <OPTION VALUE=0> <%=vs_prop.getStringAt("Entornos",0)%> <BR>
              <OPTION VALUE=1> <%=vs_prop.getStringAt("Entornos",1)%> <BR>
              <OPTION VALUE=2> <%=vs_prop.getStringAt("Entornos",2)%> <BR>
           </SELECT>
           <input type=submit value="Entrar">
   </form>
</body>
</html>

