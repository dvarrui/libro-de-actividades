package david.tenerife.templates.swing;

import david.tenerife.lib.*;

public class MenuPrincipalTemplate
{
  protected static String nl;
  public static synchronized MenuPrincipalTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    MenuPrincipalTemplate result = new MenuPrincipalTemplate();
    nl = null;
    return result;
  }

  protected final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "// Creado por el proyecto TENERIFE" + NL + "// Autor: David Vargas Ruiz" + NL + "// Fecha: ";
  protected final String TEXT_2 = NL + NL + "package david.";
  protected final String TEXT_3 = ".swing.menu;" + NL + "" + NL + "import java.util.ArrayList;" + NL + "" + NL + "import java.awt.event.KeyEvent;" + NL + "import java.awt.event.ActionListener;" + NL + "import java.awt.event.ActionEvent;" + NL + "import java.awt.Event;" + NL + "import java.awt.BorderLayout;" + NL + "" + NL + "import javax.swing.KeyStroke;" + NL + "import javax.swing.JPanel;" + NL + "import javax.swing.JMenuItem;" + NL + "import javax.swing.JMenuBar;" + NL + "import javax.swing.JMenu;" + NL + "import javax.swing.JDialog;" + NL + "import javax.swing.JFrame;" + NL + "import javax.swing.JDesktopPane;" + NL + "" + NL + "import david.";
  protected final String TEXT_4 = ".almacenes.IAlmacen;" + NL + "import david.";
  protected final String TEXT_5 = ".almacenes.FabricaAlmacenes;" + NL + "import david.";
  protected final String TEXT_6 = ".swing.listado.*;" + NL + "import david.";
  protected final String TEXT_7 = ".swing.nuevo.*;" + NL + "import david.";
  protected final String TEXT_8 = ".swing.IVentana;" + NL + "" + NL + "public class FrmMenuPrincipal extends JFrame implements ActionListener, IVentana {" + NL + "    static final long serialVersionUID=1; //Para evitar Warnings" + NL + "    " + NL + "    public static final String ID_VENTANA=\"menu.principal\";" + NL + "\tpublic static final int ANCHO = 900;" + NL + "\tpublic static final int ALTO = 700;" + NL + "" + NL + "\t//Elementos visuales" + NL + "\t" + NL + "\tprivate JPanel jContentPane = null;" + NL + "\tprivate JMenuBar jJMenuBar = null;" + NL + "" + NL + "\tprivate JMenu menuArchivo = null;" + NL + "\tprivate JMenu menuListado = null;" + NL + "\tprivate JMenu menuNuevo = null;" + NL + "\tprivate JMenu menuVentanas = null;" + NL + "\tprivate JMenu menuAyuda = null;" + NL + "" + NL + "\tprivate JMenuItem menuItemGuardar = null;" + NL + "\tprivate JMenuItem menuItemSalir = null;" + NL + "\t";
  protected final String TEXT_9 = NL + "    private JMenuItem menuItemListado";
  protected final String TEXT_10 = " = null;";
  protected final String TEXT_11 = NL + "    private JMenuItem menuItemNuevo";
  protected final String TEXT_12 = " = null;";
  protected final String TEXT_13 = NL + "\tprivate JMenuItem menuItemAcercaDe = null;" + NL + "\tprivate JMenuItem menuItemVentanasCerrar = null;" + NL + "\tprivate JMenuItem menuItemVentanasAbrir = null;" + NL + "\t" + NL + "\tprivate JDesktopPane jDesktopPane = null;" + NL + "\t" + NL + "\tprivate ArrayList ventanas = null;" + NL + "" + NL + "    //Componentes No visuales" + NL + "    ";
  protected final String TEXT_14 = NL + "    private IAlmacen almacen";
  protected final String TEXT_15 = " = null;";
  protected final String TEXT_16 = NL + "    " + NL + "\t";
  protected final String TEXT_17 = NL + "    private FrmListado";
  protected final String TEXT_18 = " frmListado";
  protected final String TEXT_19 = " = null;";
  protected final String TEXT_20 = NL + "    " + NL + "\t";
  protected final String TEXT_21 = NL + "    private FrmNuevo";
  protected final String TEXT_22 = " frmNuevo";
  protected final String TEXT_23 = " = null;";
  protected final String TEXT_24 = NL + NL + NL + NL + "\t/**" + NL + "\t * This is the default constructor" + NL + "\t */" + NL + "\tpublic FrmMenuPrincipal() {" + NL + "\t\tsuper();" + NL + "\t\tinitialize();" + NL + "\t\tinicializar();" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * This method initializes this" + NL + "\t * " + NL + "\t * @return void" + NL + "\t */" + NL + "\tprivate void initialize() {" + NL + "\t\tthis.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);" + NL + "\t\tthis.setPreferredSize(new java.awt.Dimension(ANCHO,ALTO));" + NL + "\t\tthis.setJMenuBar(getJJMenuBar());" + NL + "\t\tthis.setSize(ANCHO, ALTO);" + NL + "\t\tthis.setContentPane(getJContentPane());" + NL + "\t\tthis.setTitle(\"";
  protected final String TEXT_25 = "\");" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * This method initializes jContentPane" + NL + "\t * " + NL + "\t * @return javax.swing.JPanel" + NL + "\t */" + NL + "\tprivate JPanel getJContentPane() {" + NL + "\t\tif (jContentPane == null) {" + NL + "\t\t\tjContentPane = new JPanel();" + NL + "\t\t\tjContentPane.setLayout(new BorderLayout());" + NL + "\t\t\tjContentPane.add(getJDesktopPane(), java.awt.BorderLayout.CENTER);" + NL + "\t\t}" + NL + "\t\treturn jContentPane;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * This method initializes jDesktopPane\t" + NL + "\t * \t" + NL + "\t * @return javax.swing.JDesktopPane\t" + NL + "\t */" + NL + "\tprivate JDesktopPane getJDesktopPane() {" + NL + "\t\tif (jDesktopPane == null) {" + NL + "\t\t\tjDesktopPane = new JDesktopPane();" + NL + "\t\t}" + NL + "\t\treturn jDesktopPane;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * This method initializes jJMenuBar\t" + NL + "\t * \t" + NL + "\t * @return javax.swing.JMenuBar\t" + NL + "\t */" + NL + "\tprivate JMenuBar getJJMenuBar() {" + NL + "\t\tif (jJMenuBar == null) {" + NL + "\t\t\tjJMenuBar = new JMenuBar();" + NL + "\t\t\tjJMenuBar.add(getMenuArchivo());" + NL + "\t\t\tjJMenuBar.add(getMenuListado());" + NL + "\t\t\tjJMenuBar.add(getMenuNuevo());\t\t\t" + NL + "\t\t\tjJMenuBar.add(getMenuVentanas());\t\t\t\t\t\t" + NL + "\t\t\tjJMenuBar.add(getMenuAyuda());" + NL + "\t\t}" + NL + "\t\treturn jJMenuBar;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * This method initializes jMenu\t" + NL + "\t * \t" + NL + "\t * @return javax.swing.JMenu\t" + NL + "\t */" + NL + "\tprivate JMenu getMenuArchivo() {" + NL + "\t\tif (menuArchivo == null) {" + NL + "\t\t\tmenuArchivo = new JMenu();" + NL + "\t\t\tmenuArchivo.setText(\"Archivo\");" + NL + "\t\t\tmenuArchivo.add(getMenuItemGuardar());" + NL + "\t\t\tmenuArchivo.add(getMenuItemSalir());" + NL + "\t\t}" + NL + "\t\treturn menuArchivo;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * This method initializes jMenuItem\t" + NL + "\t * \t" + NL + "\t * @return javax.swing.JMenuItem\t" + NL + "\t */" + NL + "\tprivate JMenuItem getMenuItemGuardar() {" + NL + "\t\tif (menuItemGuardar == null) {" + NL + "\t\t\tmenuItemGuardar = new JMenuItem();" + NL + "\t\t\tmenuItemGuardar.setText(\"Guardar\");" + NL + "\t\t\tmenuItemGuardar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G," + NL + "\t\t\t\t\tEvent.CTRL_MASK, true));" + NL + "\t\t\tmenuItemGuardar.addActionListener(this);\t" + NL + "\t\t\tmenuItemGuardar.setActionCommand(\"guardar\");\t\t\t\t" + NL + "\t\t}" + NL + "\t\treturn menuItemGuardar;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * This method initializes jMenuItem\t" + NL + "\t * \t" + NL + "\t * @return javax.swing.JMenuItem\t" + NL + "\t */" + NL + "\tprivate JMenuItem getMenuItemSalir() {" + NL + "\t\tif (menuItemSalir == null) {" + NL + "\t\t\tmenuItemSalir = new JMenuItem();" + NL + "\t\t\tmenuItemSalir.setText(\"Salir\");" + NL + "\t\t\tmenuItemSalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S," + NL + "\t\t\t\t\tEvent.CTRL_MASK, true));" + NL + "\t\t\tmenuItemSalir.addActionListener(this);" + NL + "\t\t\tmenuItemSalir.setActionCommand(\"salir\");\t\t\t\t" + NL + "\t\t}" + NL + "\t\treturn menuItemSalir;" + NL + "\t}" + NL + "\t" + NL + "\t/**" + NL + "\t * This method initializes jMenu\t" + NL + "\t * \t" + NL + "\t * @return javax.swing.JMenu\t" + NL + "\t */" + NL + "\tprivate JMenu getMenuVentanas() {" + NL + "\t\tif (menuVentanas == null) {" + NL + "\t\t\tmenuVentanas = new JMenu();" + NL + "\t\t\tmenuVentanas.setText(\"Ventanas\");" + NL + "\t\t\tmenuVentanas.add(getMenuItemVentanasCerrar());" + NL + "\t\t\tmenuVentanas.add(getMenuItemVentanasAbrir());" + NL + "\t\t}" + NL + "\t\treturn menuVentanas;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * This method initializes jMenuItem\t" + NL + "\t * \t" + NL + "\t * @return javax.swing.JMenuItem\t" + NL + "\t */" + NL + "\tprivate JMenuItem getMenuItemVentanasCerrar() {" + NL + "\t\tif (menuItemVentanasCerrar == null) {" + NL + "\t\t\tmenuItemVentanasCerrar = new JMenuItem();" + NL + "\t\t\tmenuItemVentanasCerrar.setText(\"Cerrar ventanas\");" + NL + "\t\t\tmenuItemVentanasCerrar.addActionListener(this);" + NL + "\t\t\tmenuItemVentanasCerrar.setActionCommand(\"ventanas.cerrar\");\t\t\t\t" + NL + "\t\t}" + NL + "\t\treturn menuItemVentanasCerrar;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * This method initializes jMenuItem\t" + NL + "\t * \t" + NL + "\t * @return javax.swing.JMenuItem\t" + NL + "\t */" + NL + "\tprivate JMenuItem getMenuItemVentanasAbrir() {" + NL + "\t\tif (menuItemVentanasAbrir == null) {" + NL + "\t\t\tmenuItemVentanasAbrir = new JMenuItem();" + NL + "\t\t\tmenuItemVentanasAbrir.setText(\"Abrir ventanas\");" + NL + "\t\t\tmenuItemVentanasAbrir.addActionListener(this);" + NL + "\t\t\tmenuItemVentanasAbrir.setActionCommand(\"ventanas.abrir\");\t\t\t\t" + NL + "\t\t}" + NL + "\t\treturn menuItemVentanasAbrir;" + NL + "\t}" + NL + "\t" + NL + "\t/**" + NL + "\t * This method initializes jMenu\t" + NL + "\t * \t" + NL + "\t * @return javax.swing.JMenu\t" + NL + "\t */" + NL + "\tprivate JMenu getMenuAyuda() {" + NL + "\t\tif (menuAyuda == null) {" + NL + "\t\t\tmenuAyuda = new JMenu();" + NL + "\t\t\tmenuAyuda.setText(\"Ayuda\");" + NL + "\t\t\tmenuAyuda.add(getMenuItemAcercaDe());" + NL + "\t\t}" + NL + "\t\treturn menuAyuda;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * This method initializes jMenuItem\t" + NL + "\t * \t" + NL + "\t * @return javax.swing.JMenuItem\t" + NL + "\t */" + NL + "\tprivate JMenuItem getMenuItemAcercaDe() {" + NL + "\t\tif (menuItemAcercaDe == null) {" + NL + "\t\t\tmenuItemAcercaDe = new JMenuItem();" + NL + "\t\t\tmenuItemAcercaDe.setText(\"Acerca de\");" + NL + "\t\t\tmenuItemAcercaDe.addActionListener(this);" + NL + "\t\t\tmenuItemAcercaDe.setActionCommand(\"acercade\");\t\t\t\t" + NL + "\t\t}" + NL + "\t\treturn menuItemAcercaDe;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * This method initializes jMenu\t" + NL + "\t * \t" + NL + "\t * @return javax.swing.JMenu\t" + NL + "\t */" + NL + "\tprivate JMenu getMenuListado() {" + NL + "\t\tif (menuListado == null) {" + NL + "\t\t\tmenuListado = new JMenu();" + NL + "\t\t\tmenuListado.setText(\"Listado\");" + NL + "" + NL + "\t\t\t";
  protected final String TEXT_26 = NL + "\t\t\tmenuListado.add(getMenuItemListado";
  protected final String TEXT_27 = "());";
  protected final String TEXT_28 = NL + "\t\t}" + NL + "\t\treturn menuListado;" + NL + "\t}" + NL;
  protected final String TEXT_29 = NL + NL + "\t/**" + NL + "\t * This method initializes jMenuItem\t" + NL + "\t * \t" + NL + "\t * @return javax.swing.JMenuItem\t" + NL + "\t */" + NL + "\tprivate JMenuItem getMenuItemListado";
  protected final String TEXT_30 = "() {" + NL + "\t\tif (menuItemListado";
  protected final String TEXT_31 = " == null) {" + NL + "\t\t\tmenuItemListado";
  protected final String TEXT_32 = " = new JMenuItem();" + NL + "\t\t\tmenuItemListado";
  protected final String TEXT_33 = ".setText(\"";
  protected final String TEXT_34 = "\");" + NL + "\t\t\tmenuItemListado";
  protected final String TEXT_35 = ".addActionListener(this);" + NL + "\t\t\tmenuItemListado";
  protected final String TEXT_36 = ".setActionCommand(\"";
  protected final String TEXT_37 = ".listado\");" + NL + "\t\t}" + NL + "\t\treturn menuItemListado";
  protected final String TEXT_38 = ";" + NL + "\t}";
  protected final String TEXT_39 = NL + NL + "\t/**" + NL + "\t * This method initializes jMenu\t" + NL + "\t * \t" + NL + "\t * @return javax.swing.JMenu\t" + NL + "\t */" + NL + "\tprivate JMenu getMenuNuevo() {" + NL + "\t\tif (menuNuevo == null) {" + NL + "\t\t\tmenuNuevo = new JMenu();" + NL + "\t\t\tmenuNuevo.setText(\"Nuevo\");" + NL + "" + NL + "\t\t\t";
  protected final String TEXT_40 = NL + "\t\t\tmenuNuevo.add(getMenuItemNuevo";
  protected final String TEXT_41 = "());";
  protected final String TEXT_42 = NL + "\t\t}" + NL + "\t\treturn menuNuevo;" + NL + "\t}" + NL;
  protected final String TEXT_43 = NL + "\t/**" + NL + "\t * This method initializes jMenuItem\t" + NL + "\t * \t" + NL + "\t * @return javax.swing.JMenuItem\t" + NL + "\t */" + NL + "\tprivate JMenuItem getMenuItemNuevo";
  protected final String TEXT_44 = "() {" + NL + "\t\tif (menuItemNuevo";
  protected final String TEXT_45 = " == null) {" + NL + "\t\t\tmenuItemNuevo";
  protected final String TEXT_46 = " = new JMenuItem();" + NL + "\t\t\tmenuItemNuevo";
  protected final String TEXT_47 = ".setText(\"";
  protected final String TEXT_48 = "\");" + NL + "\t\t\tmenuItemNuevo";
  protected final String TEXT_49 = ".addActionListener(this);" + NL + "\t\t\tmenuItemNuevo";
  protected final String TEXT_50 = ".setActionCommand(\"";
  protected final String TEXT_51 = ".nuevo\");" + NL + "\t\t}" + NL + "\t\treturn menuItemNuevo";
  protected final String TEXT_52 = ";" + NL + "\t}" + NL + "\t";
  protected final String TEXT_53 = NL + NL + "\t/**" + NL + "\t * Este método inicializa otros componentes" + NL + "\t * " + NL + "\t * @return void" + NL + "\t */" + NL + "\tprivate void inicializar() {" + NL + "\t   \t   " + NL + "\t   ";
  protected final String TEXT_54 = NL + "\t   almacen";
  protected final String TEXT_55 = " = FabricaAlmacenes.createAlmacen(FabricaAlmacenes.";
  protected final String TEXT_56 = ",FabricaAlmacenes.TIPO_TXT);" + NL + "       almacen";
  protected final String TEXT_57 = ".open(); //Compatibilidad Almacenes TXT y SQL" + NL + "\t   ";
  protected final String TEXT_58 = NL + "\t   " + NL + "\t   ventanas = new ArrayList();" + NL + "\t   " + NL + "\t   ";
  protected final String TEXT_59 = NL + "\t   frmListado";
  protected final String TEXT_60 = " = new FrmListado";
  protected final String TEXT_61 = "(almacen";
  protected final String TEXT_62 = ");" + NL + "\t   this.getJDesktopPane().add(frmListado";
  protected final String TEXT_63 = ");" + NL + "\t   ventanas.add(frmListado";
  protected final String TEXT_64 = ");" + NL + "\t   ";
  protected final String TEXT_65 = NL + "\t   " + NL + "\t   ";
  protected final String TEXT_66 = NL + "\t   frmNuevo";
  protected final String TEXT_67 = " = new FrmNuevo";
  protected final String TEXT_68 = "();" + NL + "\t   frmNuevo";
  protected final String TEXT_69 = ".setAlmacen(almacen";
  protected final String TEXT_70 = ");" + NL + "       frmNuevo";
  protected final String TEXT_71 = ".setContenedor(this);" + NL + "   \t   this.getJDesktopPane().add(frmNuevo";
  protected final String TEXT_72 = ");" + NL + "\t   ";
  protected final String TEXT_73 = NL + "\t}" + NL + "" + NL + "   /**" + NL + "    * Este método actualiza todos los componentes" + NL + "    */" + NL + "   public void reset() {" + NL + "   \t   ";
  protected final String TEXT_74 = NL + "\t   if (frmListado";
  protected final String TEXT_75 = ".isVisible()) frmListado";
  protected final String TEXT_76 = ".reset();" + NL + "\t   ";
  protected final String TEXT_77 = " " + NL + "   }" + NL + "" + NL + "   public String getIdVentana() {" + NL + "      return ID_VENTANA;" + NL + "   }" + NL + "" + NL + "   public void actionPerformed(ActionEvent evt) {" + NL + "      System.out.println(evt.getActionCommand());" + NL + "      " + NL + "      if (evt.getActionCommand().equals(\"guardar\")) {" + NL + "    \t  //TODO: Guardar" + NL + "\t      ";
  protected final String TEXT_78 = NL + "          almacen";
  protected final String TEXT_79 = ".close(); //Compatibilidad Almacenes TXT y SQL" + NL + "\t   ";
  protected final String TEXT_80 = NL + "      } else if (evt.getActionCommand().equals(\"salir\")) {" + NL + "    \t  System.exit(0);" + NL + "      } else if (evt.getActionCommand().equals(\"acercade\")) {" + NL + "           new JDialog(FrmMenuPrincipal.this, \"Acerca de\", true).setVisible(true);";
  protected final String TEXT_81 = NL + "      } else if (evt.getActionCommand().equals(\"";
  protected final String TEXT_82 = ".listado\")) {" + NL + "          frmListado";
  protected final String TEXT_83 = ".reset();" + NL + "          frmListado";
  protected final String TEXT_84 = ".moveToFront();";
  protected final String TEXT_85 = NL + "      } else if (evt.getActionCommand().equals(\"";
  protected final String TEXT_86 = ".nuevo\")) {" + NL + "          frmNuevo";
  protected final String TEXT_87 = ".reset();" + NL + "          frmNuevo";
  protected final String TEXT_88 = ".moveToFront();";
  protected final String TEXT_89 = NL + "      }" + NL + "   }" + NL + "" + NL + "\t/**" + NL + "\t * @param args" + NL + "\t */" + NL + "\tpublic static void main(String[] args) {" + NL + "\t\tFrmMenuPrincipal application = new FrmMenuPrincipal();" + NL + "\t\tapplication.setVisible(true);" + NL + "\t}" + NL + "" + NL + "" + NL + "}";
  protected final String TEXT_90 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
     
   BaseDatos bd = (BaseDatos) argument; 
   Tabla t;
   Campo c;
   david.tenerife.util.Notacion n = new david.tenerife.util.Notacion();;

    stringBuffer.append(TEXT_1);
    stringBuffer.append(new java.util.Date() );
    stringBuffer.append(TEXT_2);
    stringBuffer.append( bd.getPaqueteJava() );
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
     for(int i=0;i<bd.getNumeroTablas();i++) { 
	      t = bd.getTabla(i);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_10);
     } 
	   for(int i=0;i<bd.getNumeroTablas();i++) { 
	      t = bd.getTabla(i);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_12);
     } 
    stringBuffer.append(TEXT_13);
     for(int i=0;i<bd.getNumeroTablas();i++) { 
	      t = bd.getTabla(i);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_15);
     } 
    stringBuffer.append(TEXT_16);
     for(int i=0;i<bd.getNumeroTablas();i++) { 
	      t = bd.getTabla(i);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_18);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_19);
     } 
    stringBuffer.append(TEXT_20);
      for(int i=0;i<bd.getNumeroTablas();i++) { 
	      t = bd.getTabla(i);
    stringBuffer.append(TEXT_21);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_22);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_23);
     } 
    stringBuffer.append(TEXT_24);
    stringBuffer.append(bd.getNombreProyecto() );
    stringBuffer.append(TEXT_25);
     for(int i=0;i<bd.getNumeroTablas();i++) {
			   t = bd.getTabla(i);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_27);
     
			} 
    stringBuffer.append(TEXT_28);
     for(int i=0;i<bd.getNumeroTablas();i++) { 
      t = bd.getTabla(i);

    stringBuffer.append(TEXT_29);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_30);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_31);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_32);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_33);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_34);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_35);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_36);
    stringBuffer.append(t.getNombre().toLowerCase());
    stringBuffer.append(TEXT_37);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_38);
     } 
    stringBuffer.append(TEXT_39);
     for(int i=0;i<bd.getNumeroTablas();i++) {
			   t = bd.getTabla(i);
    stringBuffer.append(TEXT_40);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_41);
     
			} 
    stringBuffer.append(TEXT_42);
     for(int i=0;i<bd.getNumeroTablas();i++) { 
      t = bd.getTabla(i);

    stringBuffer.append(TEXT_43);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_44);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_45);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_46);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_47);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_48);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_49);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_50);
    stringBuffer.append(t.getNombre().toLowerCase());
    stringBuffer.append(TEXT_51);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_52);
     } 
    stringBuffer.append(TEXT_53);
     for(int i=0;i<bd.getNumeroTablas();i++) { 
	         t = bd.getTabla(i);
	   
    stringBuffer.append(TEXT_54);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_55);
    stringBuffer.append(t.getNombre().toUpperCase());
    stringBuffer.append(TEXT_56);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_57);
     } 
    stringBuffer.append(TEXT_58);
     for(int i=0;i<bd.getNumeroTablas();i++) { 
	         t = bd.getTabla(i);
	   
    stringBuffer.append(TEXT_59);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_60);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_61);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_62);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_63);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_64);
     } 
    stringBuffer.append(TEXT_65);
     for(int i=0;i<bd.getNumeroTablas();i++) { 
	         t = bd.getTabla(i);
	   
    stringBuffer.append(TEXT_66);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_67);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_68);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_69);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_70);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_71);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_72);
     } 
    stringBuffer.append(TEXT_73);
     for(int i=0;i<bd.getNumeroTablas();i++) { 
	         t = bd.getTabla(i);
	   
    stringBuffer.append(TEXT_74);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_75);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_76);
     } 
    stringBuffer.append(TEXT_77);
     for(int i=0;i<bd.getNumeroTablas();i++) { 
	         t = bd.getTabla(i);
	      
    stringBuffer.append(TEXT_78);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_79);
     } 
    stringBuffer.append(TEXT_80);
     for(int i=0; i<bd.getNumeroTablas();i++) {
            t = bd.getTabla(i); 
    stringBuffer.append(TEXT_81);
    stringBuffer.append(t.getNombre().toLowerCase());
    stringBuffer.append(TEXT_82);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_83);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_84);
     } 
         for(int i=0; i<bd.getNumeroTablas();i++) {
            t = bd.getTabla(i); 
    stringBuffer.append(TEXT_85);
    stringBuffer.append(t.getNombre().toLowerCase());
    stringBuffer.append(TEXT_86);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_87);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_88);
     } 
    stringBuffer.append(TEXT_89);
    stringBuffer.append(TEXT_90);
    return stringBuffer.toString();
  }
}
