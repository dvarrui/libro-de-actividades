package david.tenerife.templates.jsp;

import david.tenerife.templates.*;

public class ListadoTemplate
{
  protected static String nl;
  public static synchronized ListadoTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    ListadoTemplate result = new ListadoTemplate();
    nl = null;
    return result;
  }

  protected final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + "<!-- Creado por el proyecto TENERIFE -->" + NL + "<!-- Autor: David Vargas Ruiz -->" + NL + "<!-- Fecha: ";
  protected final String TEXT_3 = " -->" + NL + "<!-- Nombre: jsp/";
  protected final String TEXT_4 = "/listado.jsp-->" + NL + "<!-- Descripción: Menú Tabla listado-->" + NL + "" + NL + "<%@ page import=\"david.";
  protected final String TEXT_5 = ".util.Configuracion\" %>" + NL + "<jsp:useBean id=\"vs_conf\" class=\"david.";
  protected final String TEXT_6 = ".util.Configuracion\" scope=\"session\"/>" + NL + "" + NL + "<%@ page import=\"david.";
  protected final String TEXT_7 = ".ejb.sesiones.SesionPrueba\" %>" + NL + "<jsp:useBean id=\"vs_sesion\" class=\"david.";
  protected final String TEXT_8 = ".ejb.sesiones.SesionPrueba\" scope=\"session\"/>" + NL + "" + NL + "<%@ page import=\"david.";
  protected final String TEXT_9 = ".almacenes.txt.AlmacenTXT";
  protected final String TEXT_10 = "\" %>" + NL + "<jsp:useBean id=\"vs_almacen";
  protected final String TEXT_11 = "\" class=\"david.";
  protected final String TEXT_12 = ".almacenes.txt.AlmacenTXT";
  protected final String TEXT_13 = "\" scope=\"session\"/>" + NL + "" + NL + "<%@ page import=\"david.";
  protected final String TEXT_14 = ".entidades.";
  protected final String TEXT_15 = "\" %>" + NL + "<%@ page import=\"java.util.ArrayList\" %>" + NL + "" + NL + "<% " + NL + "   if (!vs_sesion.isLogin()||vs_sesion==null) {" + NL + "      %> <jsp:forward page=\"../../html/errorLogin.html\"/> <%" + NL + "   } " + NL + "%>" + NL + "" + NL + "<html>" + NL + "<body>" + NL + "<a href=\"../login/menu.jsp\">Principal</a> &gt" + NL + "<a href=\"../";
  protected final String TEXT_16 = "/index.jsp\">";
  protected final String TEXT_17 = "</a> |" + NL + "<a href=\"../login/desconectar.jsp\">Desconectar</a><br>" + NL + "" + NL + "<h3>Listado de ";
  protected final String TEXT_18 = "</h3>" + NL + "<% " + NL + "   if (!vs_sesion.tienePermiso(\"";
  protected final String TEXT_19 = ".listado\")) {" + NL + "      vs_sesion.logout();" + NL + "      %> <jsp:forward page=\"../../html/errorAcceso.html\"/> <%" + NL + "   }" + NL + "   " + NL + "   vs_almacen";
  protected final String TEXT_20 = ".open();" + NL + "   ArrayList lista = new ArrayList();" + NL + "   lista = vs_almacen";
  protected final String TEXT_21 = ".getAll();" + NL + "   boolean par=false; //Nos indica si nos encontramos en una línea par o impar";
  protected final String TEXT_22 = NL + "   ";
  protected final String TEXT_23 = " reg;" + NL + "%>" + NL + "<hr>" + NL + "<table>" + NL + "<tr>";
  protected final String TEXT_24 = "<td>";
  protected final String TEXT_25 = "</td>";
  protected final String TEXT_26 = NL + "</tr>" + NL + "<%" + NL + "   for(int i=0;i<lista.size();i++) {" + NL + "      //Mostrar registro" + NL + "      reg = (";
  protected final String TEXT_27 = ") lista.get(i);" + NL + "      " + NL + "      //Pinta las líneas par-impar de distinto color" + NL + "      if (par) {" + NL + "         out.println(\"<tr bgcolor=\\\"#aaaaaa\\\">\");" + NL + "         par=false;" + NL + "      } else {" + NL + "         out.println(\"<tr bgcolor=\\\"#dddddd\\\">\");" + NL + "         par=true;" + NL + "      }" + NL + "\t\t\t\t      ";
  protected final String TEXT_28 = " out.println(\"<td>\"+reg.get";
  protected final String TEXT_29 = "()+\"</td>\"); ";
  protected final String TEXT_30 = NL + "      out.println(\"</tr>\");" + NL + "   }" + NL + "%>" + NL + "</table>" + NL + "<hr>" + NL + "N&uacute;mero de registros = <%=lista.size()%>" + NL + "</body>" + NL + "</html>";

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
    stringBuffer.append(t.getNombre());
    stringBuffer.append(TEXT_4);
    stringBuffer.append(bd.getPaqueteJava());
    stringBuffer.append(TEXT_5);
    stringBuffer.append(bd.getPaqueteJava());
    stringBuffer.append(TEXT_6);
    stringBuffer.append(bd.getPaqueteJava());
    stringBuffer.append(TEXT_7);
    stringBuffer.append(bd.getPaqueteJava());
    stringBuffer.append(TEXT_8);
    stringBuffer.append(bd.getPaqueteJava());
    stringBuffer.append(TEXT_9);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_10);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_11);
    stringBuffer.append(bd.getPaqueteJava());
    stringBuffer.append(TEXT_12);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_13);
    stringBuffer.append(bd.getPaqueteJava());
    stringBuffer.append(TEXT_14);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_15);
    stringBuffer.append(t.getNombre());
    stringBuffer.append(TEXT_16);
    stringBuffer.append(t.getNombre());
    stringBuffer.append(TEXT_17);
    stringBuffer.append(t.getNombre());
    stringBuffer.append(TEXT_18);
    stringBuffer.append(t.getNombre());
    stringBuffer.append(TEXT_19);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_20);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_21);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_23);
    
   // Cabecera del listado
   for (int j=0; j<t.getNumeroCampos(); j++) {
      c = t.getCampo(j);
      
    stringBuffer.append(TEXT_24);
    stringBuffer.append(c.getEtiqueta());
    stringBuffer.append(TEXT_25);
    
   }

    stringBuffer.append(TEXT_26);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_27);
    
      for (int j=0; j<t.getNumeroCampos(); j++) {
         c = t.getCampo(j);
         
    stringBuffer.append(TEXT_28);
    stringBuffer.append(n.getPascal(c.getNombre()));
    stringBuffer.append(TEXT_29);
    
      }
      
    stringBuffer.append(TEXT_30);
    return stringBuffer.toString();
  }
}
