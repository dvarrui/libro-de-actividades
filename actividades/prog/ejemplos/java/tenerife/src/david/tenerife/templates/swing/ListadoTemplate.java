package david.tenerife.templates.swing;

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
  protected final String TEXT_1 = "// Creado por el proyecto TENERIFE" + NL + "// Autor: David Vargas Ruiz" + NL + "// Fecha: ";
  protected final String TEXT_2 = NL + NL + NL + "package david.";
  protected final String TEXT_3 = ".swing.listado;" + NL + "" + NL + "// import" + NL + "import java.awt.BorderLayout;" + NL + "import java.awt.event.ActionEvent;" + NL + "import java.awt.event.ActionListener;" + NL + "" + NL + "import javax.swing.JButton;" + NL + "import javax.swing.JPanel;" + NL + "import javax.swing.JScrollPane;" + NL + "import javax.swing.JTable;" + NL + "" + NL + "import david.";
  protected final String TEXT_4 = ".almacenes.IAlmacen;" + NL + "import david.";
  protected final String TEXT_5 = ".swing.IVentana;" + NL + "import david.";
  protected final String TEXT_6 = ".swing.TableModel.TableModel";
  protected final String TEXT_7 = ";" + NL + "" + NL + "// Comienzo de la clase" + NL + "public class FrmListado";
  protected final String TEXT_8 = " extends javax.swing.JInternalFrame implements ActionListener, IVentana {" + NL + "   static final long serialVersionUID=1; //Para evitar Warnings" + NL + "" + NL + "   // Atributos" + NL + "   public static final String ID_VENTANA=\"";
  protected final String TEXT_9 = ".listado\";" + NL + "   public static final int ANCHO = 471;" + NL + "   public static final int ALTO = 296;" + NL + "" + NL + "   private JButton btnConsultar;" + NL + "   private JButton btnEliminar;" + NL + "   private JButton btnModificar;" + NL + "   private JButton btnNuevo;" + NL + "   private JButton btnSalir;" + NL + "   private JScrollPane jScrollPane1;" + NL + "   private JPanel pnlBotones;" + NL + "   private JPanel pnlDatos;" + NL + "   private JPanel pnlVentana;" + NL + "   private JTable tablaDatos;" + NL + "" + NL + "   private TableModel";
  protected final String TEXT_10 = " modeloDeTabla;" + NL + "   private IAlmacen almacen;" + NL + "   " + NL + "   // Constructor" + NL + "   public FrmListado";
  protected final String TEXT_11 = "(IAlmacen pAlmacen) {" + NL + "      super();" + NL + "      setAlmacen(pAlmacen);" + NL + "      initGUI();" + NL + "   }" + NL + "" + NL + "   // actionPerformed" + NL + "   public void actionPerformed(ActionEvent evt) {" + NL + "      if (evt.getActionCommand().equals(\"";
  protected final String TEXT_12 = ".nuevo\")) {" + NL + "         // Abrir ventana Nuevo" + NL + "      } else if (evt.getActionCommand().equals(\"";
  protected final String TEXT_13 = ".consultar\")) {" + NL + "         this.setVisible(false);" + NL + "      } else if (evt.getActionCommand().equals(\"";
  protected final String TEXT_14 = ".modificar\")) {" + NL + "         this.setVisible(false);" + NL + "      } else if (evt.getActionCommand().equals(\"";
  protected final String TEXT_15 = ".eliminar\")) {" + NL + "         this.setVisible(false);" + NL + "      } else if (evt.getActionCommand().equals(\"salir\")) {" + NL + "         //Ejecutar Salir" + NL + "         this.setVisible(false);" + NL + "      }" + NL + "   }" + NL + "" + NL + "   // initGUI" + NL + "   private void initGUI() {" + NL + "      try {" + NL + "         this.setPreferredSize(new java.awt.Dimension(ANCHO, ALTO));" + NL + "         this.setBounds(25, 25, ANCHO, ALTO);" + NL + "         setVisible(false);" + NL + "         setClosable(false);" + NL + "         {" + NL + "            pnlVentana = new JPanel();" + NL + "            BorderLayout pnlVentanaLayout = new BorderLayout();" + NL + "            pnlVentana.setLayout(pnlVentanaLayout);" + NL + "            this.getContentPane().add(pnlVentana, BorderLayout.CENTER);" + NL + "            {" + NL + "               {" + NL + "                  pnlBotones = new JPanel();" + NL + "                  pnlVentana.add(pnlBotones, BorderLayout.SOUTH);" + NL + "                  {" + NL + "                     btnNuevo = new JButton();" + NL + "                     pnlBotones.add(btnNuevo);" + NL + "                     btnNuevo.setText(\"Nuevo\");" + NL + "                     btnNuevo.setActionCommand(\"";
  protected final String TEXT_16 = ".nuevo\");" + NL + "                     btnNuevo.addActionListener(this);" + NL + "                  }" + NL + "                  {" + NL + "                     btnConsultar = new JButton();" + NL + "                     pnlBotones.add(btnConsultar);" + NL + "                     btnConsultar.setText(\"Consultar\");" + NL + "                     btnConsultar.setActionCommand(\"";
  protected final String TEXT_17 = ".consultar\");" + NL + "                     btnConsultar.addActionListener(this);" + NL + "                  }" + NL + "                  {" + NL + "                     btnModificar = new JButton();" + NL + "                     pnlBotones.add(btnModificar);" + NL + "                     btnModificar.setText(\"Modificar\");" + NL + "                     btnModificar.setActionCommand(\"";
  protected final String TEXT_18 = ".modificar\");" + NL + "                     btnModificar.setEnabled(false);" + NL + "                     btnModificar.addActionListener(this);" + NL + "                  }" + NL + "                  {" + NL + "                     btnEliminar = new JButton();" + NL + "                     pnlBotones.add(btnEliminar);" + NL + "                     btnEliminar.setText(\"Eliminar\");" + NL + "                     btnEliminar.setActionCommand(\"";
  protected final String TEXT_19 = ".eliminar\");" + NL + "                     btnEliminar.setEnabled(false);" + NL + "                     btnEliminar.addActionListener(this);" + NL + "                  }" + NL + "                  {" + NL + "                     btnSalir = new JButton();" + NL + "                     pnlBotones.add(btnSalir);" + NL + "                     btnSalir.setText(\"Salir\");" + NL + "                     btnSalir.setActionCommand(\"salir\");                     " + NL + "                     btnSalir.addActionListener(this);" + NL + "                  }" + NL + "               }" + NL + "               {" + NL + "                  jScrollPane1 = new JScrollPane();" + NL + "                  pnlVentana.add(jScrollPane1, BorderLayout.CENTER);" + NL + "                  {" + NL + "                     pnlDatos = new JPanel();" + NL + "                     jScrollPane1.setViewportView(pnlDatos);" + NL + "" + NL + "                     modeloDeTabla = new TableModel";
  protected final String TEXT_20 = "(this.almacen);" + NL + "" + NL + "                     this.setResizable(true);" + NL + "                     this.setIconifiable(true);" + NL + "                     this.setMaximizable(true);" + NL + "                     this.setSelected(true);" + NL + "                     this.setTitle(\"Listado - ";
  protected final String TEXT_21 = "\");" + NL + "                     tablaDatos = new JTable();" + NL + "                     tablaDatos.setLayout(null);" + NL + "                     pnlDatos.add(tablaDatos);" + NL + "                     tablaDatos.setModel(modeloDeTabla);" + NL + "                     tablaDatos.getTableHeader().setBounds(0, 5, 150, 15);" + NL + "                  }" + NL + "               }" + NL + "            }" + NL + "         }" + NL + "      } catch (Exception e) {" + NL + "         e.printStackTrace();" + NL + "      }" + NL + "   }" + NL + "   " + NL + "   // Otros métodos" + NL + "   public void setAlmacen(IAlmacen almacen) {" + NL + "      this.almacen = almacen;" + NL + "      if (modeloDeTabla!=null)   modeloDeTabla.setAlmacen(this.almacen);" + NL + "   }" + NL + "   " + NL + "   public void reset() {" + NL + "      // Refrescar el TableModel y la ventana completa" + NL + "      modeloDeTabla.reset();" + NL + "      setVisible(true);" + NL + "   }" + NL + "   " + NL + "   public String getIdVentana() {" + NL + "      return ID_VENTANA;" + NL + "   }" + NL + "}";
  protected final String TEXT_22 = NL;

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
    stringBuffer.append(bd.getPaqueteJava());
    stringBuffer.append(TEXT_5);
    stringBuffer.append(bd.getPaqueteJava());
    stringBuffer.append(TEXT_6);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_7);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_8);
    stringBuffer.append(t.getNombre().toLowerCase());
    stringBuffer.append(TEXT_9);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_10);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_11);
    stringBuffer.append(t.getNombre().toLowerCase());
    stringBuffer.append(TEXT_12);
    stringBuffer.append(t.getNombre().toLowerCase());
    stringBuffer.append(TEXT_13);
    stringBuffer.append(t.getNombre().toLowerCase());
    stringBuffer.append(TEXT_14);
    stringBuffer.append(t.getNombre().toLowerCase());
    stringBuffer.append(TEXT_15);
    stringBuffer.append(t.getNombre().toLowerCase());
    stringBuffer.append(TEXT_16);
    stringBuffer.append(t.getNombre().toLowerCase());
    stringBuffer.append(TEXT_17);
    stringBuffer.append(t.getNombre().toLowerCase());
    stringBuffer.append(TEXT_18);
    stringBuffer.append(t.getNombre().toLowerCase());
    stringBuffer.append(TEXT_19);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_20);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_21);
    stringBuffer.append(TEXT_22);
    return stringBuffer.toString();
  }
}
