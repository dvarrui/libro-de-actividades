package david.tenerife.templates.java;

import david.tenerife.templates.*;

public class AlmacenTXTTemplate
{
  protected static String nl;
  public static synchronized AlmacenTXTTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    AlmacenTXTTemplate result = new AlmacenTXTTemplate();
    nl = null;
    return result;
  }

  protected final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + "// Creado por el proyecto TENERIFE" + NL + "// Autor: David Vargas Ruiz" + NL + "// Fecha: ";
  protected final String TEXT_3 = NL + "// changurcio" + NL + "" + NL + "package david.";
  protected final String TEXT_4 = ".almacenes.txt;" + NL + "" + NL + "// import" + NL + "import java.util.ArrayList;" + NL + "import java.io.BufferedReader;" + NL + "import java.io.FileReader;" + NL + "import java.io.PrintWriter;" + NL + "" + NL + "import david.";
  protected final String TEXT_5 = ".entidades.IEntidad;" + NL + "import david.";
  protected final String TEXT_6 = ".entidades.";
  protected final String TEXT_7 = ";" + NL + "import david.";
  protected final String TEXT_8 = ".almacenes.IAlmacen;" + NL + "" + NL + "// Comienzo de la clase" + NL + "public class AlmacenTXT";
  protected final String TEXT_9 = " implements IAlmacen {" + NL + "   public static final String NOMBRETABLA=\"";
  protected final String TEXT_10 = "\";" + NL + "   " + NL + "   private ArrayList datos;" + NL + "   private boolean opened=false;" + NL + "" + NL + "   // --------------------------------------------------" + NL + "   // Constructor: No debe haber para EJB en páginas JSP" + NL + "" + NL + "   // ----" + NL + "   // open" + NL + "   // ----" + NL + "   public boolean open() {" + NL + "      if (opened) return true;" + NL + "" + NL + "      //Declaración de variables" + NL + "      BufferedReader entrada;" + NL + "      String str;";
  protected final String TEXT_11 = NL + "      ";
  protected final String TEXT_12 = " reg;" + NL + "      datos = new ArrayList();" + NL + "      " + NL + "      try {" + NL + "         //Abrir fichero;" + NL + "         entrada = new BufferedReader(new FileReader(\"datos/\"+NOMBRETABLA+\".txt\"));" + NL + "         //Cargar datos en ArrayList;" + NL + "         str = entrada.readLine();" + NL + "         while(str!=null) {" + NL + "            reg = new ";
  protected final String TEXT_13 = "();" + NL + "\t\t\treg.fromCadena(str);" + NL + "\t\t\tdatos.add(reg);" + NL + "\t\t\tstr = entrada.readLine();" + NL + "         }" + NL + "         entrada.close();" + NL + "         opened = true;" + NL + "         return true;" + NL + "      } catch(Exception e) {" + NL + "         System.err.println(\"Error:\"+e);" + NL + "      }" + NL + "      return false;" + NL + "   }" + NL + "" + NL + "   // -----" + NL + "   // close" + NL + "   // -----" + NL + "   public boolean close() {" + NL + "      if (!opened) return true;" + NL + "      " + NL + "      //Declaración de variables" + NL + "      PrintWriter salida;";
  protected final String TEXT_14 = NL + "      ";
  protected final String TEXT_15 = " reg;" + NL + "      " + NL + "      try {" + NL + "         //Abrir fichero" + NL + "         salida = new PrintWriter(\"datos/\"+NOMBRETABLA+\".txt\");" + NL + "         //Escribir desde el ArrayList" + NL + "         for(int i=0;i<datos.size();i++) {" + NL + "            reg = (";
  protected final String TEXT_16 = ") datos.get(i);" + NL + "            salida.println(reg.toCadena());" + NL + "         }" + NL + "         salida.close();" + NL + "         opened = false;" + NL + "         return true;" + NL + "      } catch(Exception e) {" + NL + "         System.err.println(\"Error:\"+e);" + NL + "      }" + NL + "      return false;" + NL + "   }" + NL + "   " + NL + "   // -------------" + NL + "   // Método getAll" + NL + "   // -------------" + NL + "   public ArrayList getAll() {" + NL + "      return datos;" + NL + "   }" + NL + "   " + NL + "   // ---------------" + NL + "   // Método getById" + NL + "   // ---------------" + NL + "   public IEntidad getById(int id) {";
  protected final String TEXT_17 = NL + "      ";
  protected final String TEXT_18 = " reg = new ";
  protected final String TEXT_19 = "();" + NL + "      return (IEntidad) reg;" + NL + "   }" + NL + "   " + NL + "   // ---------------" + NL + "   // Método getFind" + NL + "   // ---------------" + NL + "   public ArrayList getFind(IEntidad desde, IEntidad hasta) {" + NL + "      ArrayList lista=new ArrayList();" + NL + "      //";
  protected final String TEXT_20 = " reg;" + NL + "      //reg = new ";
  protected final String TEXT_21 = "();" + NL + "      return lista;" + NL + "   }" + NL + "" + NL + "   // ----------" + NL + "   // Método add" + NL + "   // ----------" + NL + "   public boolean add(IEntidad pRegistro) {";
  protected final String TEXT_22 = NL + "      ";
  protected final String TEXT_23 = " registro = (";
  protected final String TEXT_24 = ") pRegistro;" + NL + "      datos.add(registro);" + NL + "      return true;" + NL + "   }" + NL + "   " + NL + "   // -------------" + NL + "   // Método delete" + NL + "   // -------------" + NL + "   public boolean delete(IEntidad pRegistro) {";
  protected final String TEXT_25 = NL + "      ";
  protected final String TEXT_26 = " registro = (";
  protected final String TEXT_27 = ") pRegistro;";
  protected final String TEXT_28 = NL + "      ";
  protected final String TEXT_29 = " r;" + NL + "      " + NL + "      //Buscar elemento y remove();" + NL + "      for(int i=0;i<datos.size();i++) {" + NL + "         r = (";
  protected final String TEXT_30 = ") datos.get(i);" + NL + "         if (r.equals(registro)) datos.remove(i);" + NL + "      }" + NL + "      " + NL + "      return true;" + NL + "   }" + NL + "}";

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
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_7);
    stringBuffer.append(bd.getPaqueteJava());
    stringBuffer.append(TEXT_8);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_9);
    stringBuffer.append(t.getNombreOrigen());
    stringBuffer.append(TEXT_10);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_12);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_13);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_15);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_16);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_18);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_19);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_20);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_21);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_23);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_24);
    stringBuffer.append(TEXT_25);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_26);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_27);
    stringBuffer.append(TEXT_28);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_29);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_30);
    return stringBuffer.toString();
  }
}
