package david.tenerife.templates.swing;

import david.tenerife.templates.*;

public class IVentanaTemplate
{
  protected static String nl;
  public static synchronized IVentanaTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    IVentanaTemplate result = new IVentanaTemplate();
    nl = null;
    return result;
  }

  protected final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "// Creado por el proyecto TENERIFE" + NL + "// Autor: David Vargas Ruiz" + NL + "// Fecha: ";
  protected final String TEXT_2 = NL + NL + "package david.";
  protected final String TEXT_3 = ".swing;" + NL + "" + NL + "" + NL + "public interface IVentana {" + NL + "" + NL + "   public void reset();" + NL + "   " + NL + "   public String getIdVentana();" + NL + "" + NL + "}" + NL;
  protected final String TEXT_4 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
     
   ArgumentoJet arg = (ArgumentoJet) argument;

   david.tenerife.lib.BaseDatos bd = arg.getBaseDatos(); 

    stringBuffer.append(TEXT_1);
    stringBuffer.append(new java.util.Date() );
    stringBuffer.append(TEXT_2);
    stringBuffer.append(bd.getPaqueteJava());
    stringBuffer.append(TEXT_3);
    stringBuffer.append(TEXT_4);
    return stringBuffer.toString();
  }
}
