package david.tenerife.templates.jsp;

import david.tenerife.templates.*;

public class IndexTemplate
{
  protected static String nl;
  public static synchronized IndexTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    IndexTemplate result = new IndexTemplate();
    nl = null;
    return result;
  }

  protected final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + "<!-- Creado por el proyecto TENERIFE -->" + NL + "<!-- Autor: David Vargas Ruiz -->" + NL + "<!-- Fecha: <%=new java.util.Date() %> -->" + NL + "<!-- Nombre: index.jsp -->" + NL + "<!-- Descripción: Página de presentación -->" + NL + "" + NL + "<%@ page import = \"david.";
  protected final String TEXT_3 = ".util.Configuracion\" %>" + NL + "<jsp:useBean id=\"vs_conf\" class=\"david.";
  protected final String TEXT_4 = ".util.Configuracion\" scope=\"session\"/>" + NL + "" + NL + "<html>" + NL + "<body>" + NL + "<H1>Aplicaci&oacute;n ";
  protected final String TEXT_5 = "</H1>" + NL + "<br>" + NL + "<% vs_conf.setFichero(\"david.";
  protected final String TEXT_6 = ".rec.inicio\"); %>" + NL + "<form method=post action=\"jsp/login/comprobar.jsp\">" + NL + "   <table>" + NL + "      <tr><td>Nombre</td><td><input type=text name=usuario></td></tr>" + NL + "      <tr><td>Clave</td><td><input type=password name=clave></td></tr>" + NL + "      <tr><td>Entorno</td><td><SELECT name=entorno >" + NL + "         <%" + NL + "            for(int i=0;i<vs_conf.getLength(\"entornos\");i++) {" + NL + "               out.print(\"<OPTION VALUE=\"+i+\">\");" + NL + "               out.println(vs_conf.getStringAt(\"entornos\",i)+\"</OPTION>\");" + NL + "            }" + NL + "         %>" + NL + "         </SELECT></td></tr>" + NL + "      <tr><td></td><td><input type=submit value=\"Entrar\"></td></tr>" + NL + "   </table>" + NL + "</form>" + NL + "<hr>" + NL + "webmaster@dominio.es<br>" + NL + "</body>" + NL + "</html>";
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
    stringBuffer.append(bd.getNombreProyecto().toUpperCase());
    stringBuffer.append(TEXT_5);
    stringBuffer.append(bd.getPaqueteJava());
    stringBuffer.append(TEXT_6);
    stringBuffer.append(TEXT_7);
    return stringBuffer.toString();
  }
}
