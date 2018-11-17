package david.tenerife.templates.swing;

import david.tenerife.templates.*;

public class TableModelTemplate
{
  protected static String nl;
  public static synchronized TableModelTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    TableModelTemplate result = new TableModelTemplate();
    nl = null;
    return result;
  }

  protected final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "// Creado por el proyecto TENERIFE" + NL + "// Autor: David Vargas Ruiz" + NL + "// Fecha: ";
  protected final String TEXT_2 = NL + NL + "package david.";
  protected final String TEXT_3 = ".swing.TableModel;" + NL + "" + NL + "import java.util.ArrayList;" + NL + "import javax.swing.table.AbstractTableModel;" + NL + "" + NL + "import david.";
  protected final String TEXT_4 = ".entidades.";
  protected final String TEXT_5 = ";" + NL + "import david.";
  protected final String TEXT_6 = ".almacenes.IAlmacen;" + NL + "" + NL + "// Comienza la clase" + NL + "public class TableModel";
  protected final String TEXT_7 = " extends AbstractTableModel {" + NL + "   static final long serialVersionUID = 1;//para evitar warnings" + NL + "   " + NL + "   private ArrayList datos;" + NL + "   private IAlmacen almacen;" + NL + "" + NL + "   // Constructor" + NL + "   public TableModel";
  protected final String TEXT_8 = "(IAlmacen pAlmacen) {" + NL + "      setAlmacen(pAlmacen);" + NL + "   }" + NL + "   " + NL + "   // ---------------------" + NL + "   // Método getColumnCount" + NL + "   // ---------------------" + NL + "   public int getColumnCount() {" + NL + "      return (new ";
  protected final String TEXT_9 = "()).getNumeroCampos();" + NL + "   }" + NL + "   " + NL + "   // --------------------" + NL + "   // Método getColumnName" + NL + "   // --------------------" + NL + "   public String getColumnName(int index) {" + NL + "      return ";
  protected final String TEXT_10 = ".getNombreCampo(index);" + NL + "   }" + NL + "" + NL + "   // ------------------" + NL + "   // Método getRowCount" + NL + "   // ------------------" + NL + "   public int getRowCount() {" + NL + "      return datos.size();" + NL + "   }" + NL + "   " + NL + "   // -----------------" + NL + "   // Método getValueAt" + NL + "   // -----------------" + NL + "   public Object getValueAt(int fila, int col) {";
  protected final String TEXT_11 = NL + "      ";
  protected final String TEXT_12 = " reg = (";
  protected final String TEXT_13 = ") datos.get(fila);" + NL + "      return reg.getCampo(col);" + NL + "   }" + NL + "   " + NL + "   /*" + NL + "    * Este metodo sirve para determinar el editor predeterminado" + NL + "    * para cada columna de celdas" + NL + "    */" + NL + "   public Class getColumnClass(int c) {" + NL + "      return getValueAt(0, c).getClass();" + NL + "   }" + NL + "" + NL + "   /*" + NL + "    * No tienes que implementar este método a menos que" + NL + "    * las celdas de tu tabla sean Editables" + NL + "    */" + NL + "   public boolean isCellEditable(int row, int col) {" + NL + "      return false;" + NL + "   }" + NL + "   " + NL + "   /*" + NL + "    * Otros Métodos" + NL + "    */" + NL + "   public void setAlmacen(IAlmacen almacen) {" + NL + "      this.almacen = almacen;" + NL + "      reset();" + NL + "   }" + NL + "   " + NL + "   public void reset() {" + NL + "      datos = this.almacen.getAll();" + NL + "      this.fireTableDataChanged();" + NL + "   }" + NL + "}";
  protected final String TEXT_14 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
     
   ArgumentoJet arg = (ArgumentoJet) argument;

   david.tenerife.lib.BaseDatos bd = arg.getBaseDatos(); 
   david.tenerife.lib.Tabla t = arg.getTabla();

   david.tenerife.util.Notacion n = new david.tenerife.util.Notacion();;

    stringBuffer.append(TEXT_1);
    stringBuffer.append(new java.util.Date() );
    stringBuffer.append(TEXT_2);
    stringBuffer.append(bd.getPaqueteJava());
    stringBuffer.append(TEXT_3);
    stringBuffer.append(bd.getPaqueteJava());
    stringBuffer.append(TEXT_4);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_5);
    stringBuffer.append(bd.getPaqueteJava());
    stringBuffer.append(TEXT_6);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_7);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_8);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_9);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_10);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_12);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_13);
    stringBuffer.append(TEXT_14);
    return stringBuffer.toString();
  }
}
