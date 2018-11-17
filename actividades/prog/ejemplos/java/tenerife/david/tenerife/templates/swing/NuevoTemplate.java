package david.tenerife.templates.swing;

import david.tenerife.templates.*;

public class NuevoTemplate
{
  protected static String nl;
  public static synchronized NuevoTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    NuevoTemplate result = new NuevoTemplate();
    nl = null;
    return result;
  }

  protected final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + "// Creado por el proyecto TENERIFE" + NL + "// Autor: David Vargas Ruiz" + NL + "// Fecha: ";
  protected final String TEXT_3 = NL + NL + NL + "package david.";
  protected final String TEXT_4 = ".swing.nuevo;" + NL + "" + NL + "// import" + NL + "import java.awt.BorderLayout;" + NL + "import java.awt.event.ActionEvent;" + NL + "import java.awt.event.ActionListener;" + NL + "" + NL + "import javax.swing.JButton;" + NL + "import javax.swing.JLabel;" + NL + "import javax.swing.JPanel;" + NL + "import javax.swing.JScrollPane;" + NL + "import javax.swing.JTextField;" + NL + "" + NL + "import david.";
  protected final String TEXT_5 = ".almacenes.IAlmacen;" + NL + "import david.";
  protected final String TEXT_6 = ".entidades.";
  protected final String TEXT_7 = ";";
  protected final String TEXT_8 = NL + "import david.";
  protected final String TEXT_9 = ".util.Fechas;";
  protected final String TEXT_10 = NL + "import david.";
  protected final String TEXT_11 = ".swing.IVentana;" + NL + "" + NL + "// clase" + NL + "public class FrmNuevo";
  protected final String TEXT_12 = " extends javax.swing.JInternalFrame implements ActionListener, IVentana {" + NL + "   static final long serialVersionUID=1; //Para evitar Warnings" + NL + "" + NL + "   // atributos" + NL + "   public static final String ID_VENTANA=\"";
  protected final String TEXT_13 = ".nuevo\";" + NL + "   public static final int ANCHO = 471; " + NL + "   public static final int ALTO = 296;" + NL + "" + NL + "   private JButton btnAceptar;" + NL + "   private JButton btnSalir;" + NL + "   private JScrollPane jScrollPane1;" + NL + "   private JPanel pnlBotones;" + NL + "   private JPanel pnlDatos;" + NL + "   private JPanel pnlVentana;" + NL;
  protected final String TEXT_14 = NL + "   private JLabel lbl";
  protected final String TEXT_15 = ";" + NL + "   private JTextField txt";
  protected final String TEXT_16 = ";";
  protected final String TEXT_17 = NL + NL + "   private IAlmacen almacen;" + NL + "   private IVentana frmContenedor;" + NL + "   " + NL + "   // constructor" + NL + "   public FrmNuevo";
  protected final String TEXT_18 = "() {" + NL + "      super();" + NL + "      initGUI();" + NL + "   }" + NL + "" + NL + "   // listener" + NL + "   public void actionPerformed(ActionEvent evt) {" + NL + "      try {" + NL + "         System.out.println(evt.getActionCommand());" + NL + "         // TODO add your code for btnAceptar.actionPerformed" + NL + "         if (evt.getActionCommand().equals(btnAceptar.getActionCommand())) {" + NL + "            //Aceptar" + NL + "            this.grabar();" + NL + "         } else if (evt.getActionCommand().equals(btnSalir.getActionCommand())) {" + NL + "            //Salir" + NL + "            this.setVisible(false);" + NL + "         }" + NL + "      } catch(Exception e) {" + NL + "         System.err.println(e);" + NL + "      }" + NL + "   }" + NL + "" + NL + "   // initGUI" + NL + "   private void initGUI() {" + NL + "      try {" + NL + "         this.setPreferredSize(new java.awt.Dimension(ANCHO, ALTO));" + NL + "         this.setBounds(25, 25, ANCHO, ALTO);" + NL + "         setVisible(false);" + NL + "         setClosable(false);" + NL + "         {" + NL + "            pnlVentana = new JPanel();" + NL + "            BorderLayout pnlVentanaLayout = new BorderLayout();" + NL + "            pnlVentana.setLayout(pnlVentanaLayout);" + NL + "            this.getContentPane().add(pnlVentana, BorderLayout.CENTER);" + NL + "            {" + NL + "               {" + NL + "                  pnlBotones = new JPanel();" + NL + "                  pnlVentana.add(pnlBotones, BorderLayout.SOUTH);" + NL + "                  {" + NL + "                     btnAceptar = new JButton();" + NL + "                     pnlBotones.add(btnAceptar);" + NL + "                     btnAceptar.setText(\"Aceptar\");" + NL + "                     btnAceptar.setActionCommand(\"aceptar\");" + NL + "                     btnAceptar.addActionListener(this);" + NL + "                  }" + NL + "                  {" + NL + "                     btnSalir = new JButton();" + NL + "                     pnlBotones.add(btnSalir);" + NL + "                     btnSalir.setText(\"Salir\");" + NL + "                     btnSalir.setActionCommand(\"salir\");" + NL + "                     btnSalir.addActionListener(this);" + NL + "                  }" + NL + "               }" + NL + "               {" + NL + "                  jScrollPane1 = new JScrollPane();" + NL + "                  pnlVentana.add(jScrollPane1, BorderLayout.CENTER);" + NL + "                  {" + NL + "                     pnlDatos = new JPanel();" + NL + "                     pnlDatos.setLayout(null);" + NL + "                     jScrollPane1.setViewportView(pnlDatos);" + NL + "                     " + NL + "                     //Elementos nuevo registro" + NL + "                     ";
  protected final String TEXT_19 = NL + "\t\t\t\t\t {" + NL + "\t\t\t\t\t    lbl";
  protected final String TEXT_20 = " = new JLabel();" + NL + "\t\t\t\t\t    pnlDatos.add(lbl";
  protected final String TEXT_21 = ");" + NL + "\t\t\t\t\t    lbl";
  protected final String TEXT_22 = ".setText(\"";
  protected final String TEXT_23 = "\");" + NL + "\t\t\t\t\t    lbl";
  protected final String TEXT_24 = ".setBounds(";
  protected final String TEXT_25 = ", ";
  protected final String TEXT_26 = ", 200,20);" + NL + "\t\t\t\t\t }" + NL + "\t\t\t\t\t {" + NL + "\t\t\t\t\t    txt";
  protected final String TEXT_27 = " = new JTextField();" + NL + "\t\t\t\t\t    pnlDatos.add(txt";
  protected final String TEXT_28 = ");" + NL + "\t\t\t\t\t    txt";
  protected final String TEXT_29 = ".setBounds(";
  protected final String TEXT_30 = ", ";
  protected final String TEXT_31 = ", 200,20);" + NL + "\t\t\t\t\t }" + NL + "\t\t\t\t\t ";
  protected final String TEXT_32 = NL + "                  " + NL + "                     this.setResizable(true);" + NL + "                     this.setIconifiable(true);" + NL + "                     this.setMaximizable(true);" + NL + "                     this.setSelected(true);" + NL + "                     this.setTitle(\"Nuevo Registro - ";
  protected final String TEXT_33 = "\");" + NL + "                  }" + NL + "               }" + NL + "            }" + NL + "         }" + NL + "      } catch (Exception e) {" + NL + "         e.printStackTrace();" + NL + "      }" + NL + "   }" + NL + "" + NL + "   // grabar" + NL + "   private void grabar() {";
  protected final String TEXT_34 = NL + "      ";
  protected final String TEXT_35 = " reg = new ";
  protected final String TEXT_36 = "();" + NL + "      try {";
  protected final String TEXT_37 = NL + "         reg.set";
  protected final String TEXT_38 = "(txt";
  protected final String TEXT_39 = ".getText());";
  protected final String TEXT_40 = NL + "         reg.set";
  protected final String TEXT_41 = "(Integer.parseInt(txt";
  protected final String TEXT_42 = ".getText()));";
  protected final String TEXT_43 = NL + "         reg.set";
  protected final String TEXT_44 = "(Boolean.parseBoolean(txt";
  protected final String TEXT_45 = ".getText()));";
  protected final String TEXT_46 = NL + "         reg.set";
  protected final String TEXT_47 = "(Fechas.fromCadena(txt";
  protected final String TEXT_48 = ".getText()));";
  protected final String TEXT_49 = NL + NL + "         almacen.add(reg);" + NL + "         if (frmContenedor!=null) frmContenedor.reset();" + NL + "      } catch(Exception e) {" + NL + "         System.err.println(e);" + NL + "      }" + NL + "   }" + NL + "   " + NL + "   // Otros métodos" + NL + "   public void setAlmacen(IAlmacen almacen) {" + NL + "      this.almacen = almacen;" + NL + "   }" + NL + "   " + NL + "   public void setContenedor(IVentana f) {" + NL + "      frmContenedor = f;" + NL + "   }" + NL + "" + NL + "   public void reset() {" + NL + "      // Visualizar y refrescar la ventana completa" + NL + "      setVisible(true);";
  protected final String TEXT_50 = NL + "      txt";
  protected final String TEXT_51 = ".setText(null);";
  protected final String TEXT_52 = NL + "   }" + NL + "   " + NL + "   public String getIdVentana() {" + NL + "      return ID_VENTANA;" + NL + "   }" + NL + "}" + NL;
  protected final String TEXT_53 = NL;

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
     if (t.hayFecha()) {
    stringBuffer.append(TEXT_8);
    stringBuffer.append(bd.getPaqueteJava());
    stringBuffer.append(TEXT_9);
     } 
    stringBuffer.append(TEXT_10);
    stringBuffer.append(bd.getPaqueteJava());
    stringBuffer.append(TEXT_11);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_12);
    stringBuffer.append(t.getNombre().toLowerCase());
    stringBuffer.append(TEXT_13);
     for (int j = 0; j < t.getNumeroCampos(); j++) {
		c = t.getCampo(j); 
    stringBuffer.append(TEXT_14);
    stringBuffer.append(n.getPascal(c.getNombre()));
    stringBuffer.append(TEXT_15);
    stringBuffer.append(n.getPascal(c.getNombre()));
    stringBuffer.append(TEXT_16);
     } 
    stringBuffer.append(TEXT_17);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_18);
     int x = 20;
                        int y = 20;
                        for (int j = 0; j < t.getNumeroCampos(); j++) {
					       c = t.getCampo(j); 
    stringBuffer.append(TEXT_19);
    stringBuffer.append(n.getPascal(c.getNombre()));
    stringBuffer.append(TEXT_20);
    stringBuffer.append(n.getPascal(c.getNombre()));
    stringBuffer.append(TEXT_21);
    stringBuffer.append(n.getPascal(c.getNombre()));
    stringBuffer.append(TEXT_22);
    stringBuffer.append(c.getEtiqueta());
    stringBuffer.append(TEXT_23);
    stringBuffer.append(n.getPascal(c.getNombre()));
    stringBuffer.append(TEXT_24);
    stringBuffer.append(x);
    stringBuffer.append(TEXT_25);
    stringBuffer.append(y);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(n.getPascal(c.getNombre()));
    stringBuffer.append(TEXT_27);
    stringBuffer.append(n.getPascal(c.getNombre()));
    stringBuffer.append(TEXT_28);
    stringBuffer.append(n.getPascal(c.getNombre()));
    stringBuffer.append(TEXT_29);
    stringBuffer.append((x + 200));
    stringBuffer.append(TEXT_30);
    stringBuffer.append(y);
    stringBuffer.append(TEXT_31);
     y = y + 20;
					    } 
    stringBuffer.append(TEXT_32);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_33);
    stringBuffer.append(TEXT_34);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_35);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_36);
     for (int j = 0; j < t.getNumeroCampos(); j++) {
            c = t.getCampo(j);
			if (n.getTipoJava(c.getTipo()).equals("String")) {
			
    stringBuffer.append(TEXT_37);
    stringBuffer.append(n.getPascal(c.getNombre()));
    stringBuffer.append(TEXT_38);
    stringBuffer.append(n.getPascal(c.getNombre()));
    stringBuffer.append(TEXT_39);
    	} else if (n.getTipoJava(c.getTipo()).equals("int")) { 
    stringBuffer.append(TEXT_40);
    stringBuffer.append(n.getPascal(c.getNombre()));
    stringBuffer.append(TEXT_41);
    stringBuffer.append(n.getPascal(c.getNombre()));
    stringBuffer.append(TEXT_42);
     } else if (n.getTipoJava(c.getTipo()).equals("boolean")) { 
    stringBuffer.append(TEXT_43);
    stringBuffer.append(n.getPascal(c.getNombre()));
    stringBuffer.append(TEXT_44);
    stringBuffer.append(n.getPascal(c.getNombre()));
    stringBuffer.append(TEXT_45);
     } else if (n.getTipoJava(c.getTipo()).equals("Date")) { 
    stringBuffer.append(TEXT_46);
    stringBuffer.append(n.getPascal(c.getNombre()));
    stringBuffer.append(TEXT_47);
    stringBuffer.append(n.getPascal(c.getNombre()));
    stringBuffer.append(TEXT_48);
     }
			}
    stringBuffer.append(TEXT_49);
     for (int j = 0; j < t.getNumeroCampos(); j++) {
		c = t.getCampo(j); 
    stringBuffer.append(TEXT_50);
    stringBuffer.append(n.getPascal(c.getNombre()));
    stringBuffer.append(TEXT_51);
     } 
    stringBuffer.append(TEXT_52);
    stringBuffer.append(TEXT_53);
    return stringBuffer.toString();
  }
}
