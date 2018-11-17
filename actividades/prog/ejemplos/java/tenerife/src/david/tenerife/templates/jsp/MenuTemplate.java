package david.tenerife.templates.jsp;

import david.tenerife.templates.*;

public class MenuTemplate
{
  protected static String nl;
  public static synchronized MenuTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    MenuTemplate result = new MenuTemplate();
    nl = null;
    return result;
  }

  protected final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + "<!-- Creado por el proyecto TENERIFE -->" + NL + "<!-- Autor: David Vargas Ruiz -->" + NL + "<!-- Fecha: ";
  protected final String TEXT_3 = " -->" + NL + "<!-- Nombre: jsp/login/comprobar.jsp -->" + NL + "<!-- Descripción: Comprobar usuario/password -->" + NL + "" + NL + "<%@ page import=\"david.";
  protected final String TEXT_4 = ".util.Configuracion\" %>" + NL + "<jsp:useBean id=\"vs_conf\" class=\"david.";
  protected final String TEXT_5 = ".util.Configuracion\" scope=\"session\"/>" + NL + "" + NL + "" + NL + "\t\t\tfich = new PrintWriter(ruta + \"/menu.jsp\");" + NL + "\t\t\tfich.println(\"<!--Proyecto TENERIFE-->\");" + NL + "\t\t\tfich.println(\"<!--Fecha:\" + fecha.toString() + \"-->\");" + NL + "\t\t\tfich.println(\"<!--Nombre      : jsp/login/menu.jsp-->\");" + NL + "\t\t\tfich.println(\"<!--Descripción : Menú principal-->\");" + NL + "" + NL + "\t\t\tfich.println(\"<%@ page import = \\\"david.\"" + NL + "\t\t\t\t\t+ param.getValor(Parametros.PROYECTO)" + NL + "\t\t\t\t\t+ \".util.Configuracion\\\" %>\");" + NL + "\t\t\tfich.println(\"<jsp:useBean id=\\\"vs_conf\\\" class=\\\"david.\"" + NL + "\t\t\t\t\t+ param.getValor(Parametros.PROYECTO)" + NL + "\t\t\t\t\t+ \".util.Configuracion\\\" scope=\\\"session\\\"/>\");" + NL + "" + NL + "\t\t\tfich.println(\"<%@ page import = \\\"david.\"" + NL + "\t\t\t\t\t+ param.getValor(Parametros.PROYECTO)" + NL + "\t\t\t\t\t+ \".ejb.sesiones.SesionPrueba\\\" %>\");" + NL + "\t\t\tfich.println(\"<jsp:useBean id=\\\"vs_sesion\\\" class=\\\"david.\"" + NL + "\t\t\t\t\t+ param.getValor(Parametros.PROYECTO)" + NL + "\t\t\t\t\t+ \".ejb.sesiones.SesionPrueba\\\" scope=\\\"session\\\"/>\");" + NL + "" + NL + "\t\t\tfich.println(\"<% if (!vs_sesion.isLogin()||vs_sesion==null) {\");" + NL + "\t\t\tfich" + NL + "\t\t\t\t\t.println(\"%> <jsp:forward page=\\\"../../html/errorLogin.html\\\"/> <%\");" + NL + "\t\t\tfich.println(\"} %>\");" + NL + "" + NL + "<html>" + NL + "<body>" + NL + "<h1>Men&uacute; principal</h1>" + NL + "<h2>";
  protected final String TEXT_6 = "</h2>" + NL + "<a href=\"desconectar.jsp\">Desconectar</a><br>" + NL + "" + NL + "<h3>Aplicaciones disponibles</h3>" + NL + "<table border=1>";
  protected final String TEXT_7 = NL + "   <% " + NL + "      if (vs_sesion.tienePermiso(\"";
  protected final String TEXT_8 = "\")) {" + NL + "         out.println(\"<tr>\");" + NL + "         out.print(\"<td>";
  protected final String TEXT_9 = "</td>\");" + NL + "         out.print(\"<td>\");" + NL + "         out.print(\"<td><a href=\\\"../";
  protected final String TEXT_10 = "/index.jsp\\\">Indice</a></td>\");" + NL + "         out.print(\"<td>\");" + NL + "         " + NL + "         if (vs_sesion.tienePermiso(\"";
  protected final String TEXT_11 = ".listado\")) {" + NL + "            out.print(\"<a href=\\\"../";
  protected final String TEXT_12 = "/listado.jsp\\\">Listado</a>\");" + NL + "         }" + NL + "         out.println(\"</td>\");" + NL + "         " + NL + "         out.print(\"<td>\");" + NL + "         if (vs_sesion.tienePermiso(\"";
  protected final String TEXT_13 = ".nuevo\")) {" + NL + "            out.print(\"<a href=\\\"../";
  protected final String TEXT_14 = "/nuevo.jsp\\\">Nuevo</a>\");" + NL + "         }" + NL + "         out.println(\"</td>\");" + NL + "         " + NL + "         out.println(\"</tr>\");" + NL + "      }" + NL + "   %>";
  protected final String TEXT_15 = NL + "</table>" + NL + "" + NL + "<h3>Datos de la sesi&oacute;n</h3>" + NL + "<b>Usuario:</b><%=vs_sesion.getUsuario()%><br>" + NL + "<b>Fecha login:</b><%=vs_sesion.getFechaLogin()%><br>" + NL + "<b>Entorno:</b><%=+vs_sesion.getEntorno()%><br>" + NL + "</body>" + NL + "</html>";
  protected final String TEXT_16 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    
   ArgumentoJet arg = (ArgumentoJet) argument;

   david.tenerife.lib.BaseDatos bd = arg.getBaseDatos(); 
   david.tenerife.lib.Tabla t = arg.getTabla();
   david.tenerife.lib.Campo c;

   david.tenerife.util.Notacion n = new david.tenerife.util.Notacion();;

    stringBuffer.append(TEXT_2);
    stringBuffer.append(new java.util.Date() );
    stringBuffer.append(TEXT_3);
    stringBuffer.append(bd.getPaqueteJava());
    stringBuffer.append(TEXT_4);
    stringBuffer.append(bd.getPaqueteJava());
    stringBuffer.append(TEXT_5);
    stringBuffer.append(bd.getPaqueteJava().toUpperCase());
    stringBuffer.append(TEXT_6);
    
   for (int i = 0; i < bd.getNumeroTablas(); i++) {
      t = bd.getTabla(i);

    stringBuffer.append(TEXT_7);
    stringBuffer.append(t.getNombre());
    stringBuffer.append(TEXT_8);
    stringBuffer.append(t.getNombre());
    stringBuffer.append(TEXT_9);
    stringBuffer.append(t.getNombre());
    stringBuffer.append(TEXT_10);
    stringBuffer.append(t.getNombre());
    stringBuffer.append(TEXT_11);
    stringBuffer.append(t.getNombre());
    stringBuffer.append(TEXT_12);
    stringBuffer.append(t.getNombre());
    stringBuffer.append(TEXT_13);
    stringBuffer.append(t.getNombre());
    stringBuffer.append(TEXT_14);
    
   }

    stringBuffer.append(TEXT_15);
    stringBuffer.append(TEXT_16);
    return stringBuffer.toString();
  }
}
