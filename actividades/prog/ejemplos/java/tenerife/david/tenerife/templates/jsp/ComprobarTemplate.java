package david.tenerife.templates.jsp;

import david.tenerife.templates.*;

public class ComprobarTemplate
{
  protected static String nl;
  public static synchronized ComprobarTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    ComprobarTemplate result = new ComprobarTemplate();
    nl = null;
    return result;
  }

  protected final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + "<!-- Creado por el proyecto TENERIFE -->" + NL + "<!-- Autor: David Vargas Ruiz -->" + NL + "<!-- Fecha: ";
  protected final String TEXT_3 = " -->" + NL + "<!-- Nombre: jsp/login/comprobar.jsp -->" + NL + "<!-- Descripción: Comprobar usuario/password -->" + NL + "" + NL + "<%@ page import=\"david.";
  protected final String TEXT_4 = ".util.Configuracion\" %>" + NL + "<jsp:useBean id=\"vs_conf\" class=\"david.";
  protected final String TEXT_5 = ".util.Configuracion\" scope=\"session\"/>" + NL + "" + NL + "<%@ page import=\"david.";
  protected final String TEXT_6 = ".ejb.sesiones.FabricaSesiones\" %>" + NL + "" + NL + "<!-- ¡OJO! cambiar en producción-->" + NL + "<%@ page import=\"david.";
  protected final String TEXT_7 = ".ejb.sesiones.SesionPrueba\" %>" + NL + "<jsp:useBean id=\"vs_sesion\" class=\"david.";
  protected final String TEXT_8 = ".ejb.sesiones.SesionPrueba\" scope=\"session\"/>" + NL + "" + NL + "<%" + NL + "   // vs_sesion=FabricaSesiones.createSesion(FabricaSesiones.TIPO_PRUEBA);" + NL + "   vs_sesion.login(request.getParameter(\"usuario\"),request.getParameter(\"clave\"),request.getParameter(\"entorno\"));" + NL + "   " + NL + "   if (vs_sesion.isLogin()) " + NL + "   { %> <jsp:forward page=\"menu.jsp\"/> <% }" + NL + "   else " + NL + "   { %> <jsp:forward page=\"../../html/errorLogin.html\"/> <% }" + NL + "%>";
  protected final String TEXT_9 = NL;

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
    stringBuffer.append(bd.getPaqueteJava());
    stringBuffer.append(TEXT_6);
    stringBuffer.append(bd.getPaqueteJava());
    stringBuffer.append(TEXT_7);
    stringBuffer.append(bd.getPaqueteJava());
    stringBuffer.append(TEXT_8);
    stringBuffer.append(TEXT_9);
    return stringBuffer.toString();
  }
}
