package david.tenerife.templates.java;

import david.tenerife.templates.*;

public class IEntidadTemplate
{
  protected static String nl;
  public static synchronized IEntidadTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    IEntidadTemplate result = new IEntidadTemplate();
    nl = null;
    return result;
  }

  protected final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "// Creado por el proyecto TENERIFE" + NL + "// Autor: David Vargas Ruiz" + NL + "// Fecha: ";
  protected final String TEXT_2 = NL + NL + "package david.";
  protected final String TEXT_3 = ".entidades;" + NL + "" + NL + "" + NL + "public interface IEntidad {" + NL + "" + NL + "   public String toCadena();" + NL + "   public void fromCadena(String pTexto);" + NL + "" + NL + "   public String getDescripcion();" + NL + "   public String getClave();" + NL + "" + NL + "   public int getNumeroCampos();" + NL + "   public Object getCampo(int index);" + NL + "" + NL + "   public boolean equals(IEntidad registro);" + NL + "" + NL + "}" + NL;
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
