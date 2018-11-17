package david.tenerife.templates.sql;

import david.tenerife.lib.*;

public class CreateTableTemplate
{
  protected static String nl;
  public static synchronized CreateTableTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    CreateTableTemplate result = new CreateTableTemplate();
    nl = null;
    return result;
  }

  protected final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL;
  protected final String TEXT_3 = NL + NL + "CREATE TABLE ";
  protected final String TEXT_4 = "(";
  protected final String TEXT_5 = NL + "    ";
  protected final String TEXT_6 = "\t\t";
  protected final String TEXT_7 = ",\t-- ";
  protected final String TEXT_8 = NL + ");";
  protected final String TEXT_9 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    
   BaseDatos bd = (BaseDatos) argument;
   Tabla t;
   Campo c;

    stringBuffer.append(TEXT_2);
    
   for(int i=0;i<bd.getNumeroTablas();i++) {
      t = bd.getTabla(i);

    stringBuffer.append(TEXT_3);
    stringBuffer.append(t.getNombre());
    stringBuffer.append(TEXT_4);
    
      for(int j=0;j<t.getNumeroCampos();j++) {
         c = t.getCampo(j);

    stringBuffer.append(TEXT_5);
    stringBuffer.append(c.getNombre());
    stringBuffer.append(TEXT_6);
    stringBuffer.append(c.getTipo());
    stringBuffer.append(TEXT_7);
    stringBuffer.append(c.getEtiqueta());
        } 
    stringBuffer.append(TEXT_8);
     } 
    stringBuffer.append(TEXT_9);
    return stringBuffer.toString();
  }
}
