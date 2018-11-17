package david.tenerife.templates.jsp;

import david.tenerife.templates.*;

public class DesconectarTemplate
{
  protected static String nl;
  public static synchronized DesconectarTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    DesconectarTemplate result = new DesconectarTemplate();
    nl = null;
    return result;
  }

  protected final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + "<!-- Creado por el proyecto TENERIFE -->" + NL + "<!-- Autor: David Vargas Ruiz -->" + NL + "<!-- Fecha: <%=new java.util.Date() %> -->" + NL + "<!-- Nombre: jsp/login/desconectar.jsp -->" + NL + "<!-- Descripción: Realizar la desconexi&oacute;n -->" + NL + "" + NL + "<%@ page import=\"david.";
  protected final String TEXT_3 = ".util.Configuracion\" %>" + NL + "<jsp:useBean id=\"vs_conf\" class=\"david.";
  protected final String TEXT_4 = ".util.Configuracion\" scope=\"session\"/>" + NL + "" + NL + "<%@ page import=\"david.";
  protected final String TEXT_5 = ".ejb.sesiones.SesionPrueba\" %>" + NL + "<jsp:useBean id=\"vs_sesion\" class=\"david.";
  protected final String TEXT_6 = ".ejb.sesiones.SesionPrueba\" scope=\"session\"/>" + NL + "" + NL + "<%" + NL + "   vs_sesion.logout();" + NL + "   vs_sesion = null;" + NL + "   vs_conf = null;" + NL + "%>" + NL + "" + NL + "<jsp:forward page=\"../../html/desconectado.html\"/>";
  protected final String TEXT_7 = NL;

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
    stringBuffer.append(bd.getPaqueteJava());
    stringBuffer.append(TEXT_3);
    stringBuffer.append(bd.getPaqueteJava());
    stringBuffer.append(TEXT_4);
    stringBuffer.append(bd.getPaqueteJava());
    stringBuffer.append(TEXT_5);
    stringBuffer.append(bd.getPaqueteJava());
    stringBuffer.append(TEXT_6);
    stringBuffer.append(TEXT_7);
    return stringBuffer.toString();
  }
}
