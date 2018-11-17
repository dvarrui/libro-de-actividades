package david.tenerife.templates.swing;

import david.tenerife.templates.*;

public class EditarTemplate
{
  protected static String nl;
  public static synchronized EditarTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    EditarTemplate result = new EditarTemplate();
    nl = null;
    return result;
  }

  protected final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "// Creado por el proyecto TENERIFE" + NL + "// Autor: David Vargas Ruiz" + NL + "// Fecha: ";
  protected final String TEXT_2 = NL + NL + NL + "package david.";
  protected final String TEXT_3 = ".swing.editar;" + NL + "" + NL + "// import" + NL + "import java.awt.BorderLayout;" + NL + "import java.awt.event.ActionEvent;" + NL + "import java.awt.event.ActionListener;" + NL + "" + NL + "import javax.swing.JButton;" + NL + "import javax.swing.JLabel;" + NL + "import javax.swing.JPanel;" + NL + "import javax.swing.JScrollPane;" + NL + "import javax.swing.JTextField;" + NL + "" + NL + "import david.";
  protected final String TEXT_4 = ".almacenes.FabricaAlmacenes;" + NL + "import david.";
  protected final String TEXT_5 = ".almacenes.IAlmacen;" + NL + "import david.";
  protected final String TEXT_6 = ".entidades.";
  protected final String TEXT_7 = ";";
  protected final String TEXT_8 = NL + "import david.";
  protected final String TEXT_9 = ".util.Fechas;";
  protected final String TEXT_10 = NL + "import david.";
  protected final String TEXT_11 = ".swing.IVentana;" + NL + "" + NL + "// clase" + NL + "public class FrmEditar";
  protected final String TEXT_12 = " extends javax.swing.JInternalFrame implements ActionListener, IVentana {" + NL + "   static final long serialVersionUID=1; //Evitar Warnings" + NL + "   " + NL + "   public static final String ID_VENTANA=\"";
  protected final String TEXT_13 = ".editar\";" + NL + "   public static int ANCHO = 471;" + NL + "   public static int ALTO = 296;" + NL + "   " + NL + "   // atributos" + NL + "   private JButton btnAceptar;" + NL + "   private JButton btnSalir;" + NL + "   private JScrollPane jScrollPane1;" + NL + "   private JPanel pnlBotones;" + NL + "   private JPanel pnlDatos;" + NL + "   private JPanel pnlVentana;" + NL;
  protected final String TEXT_14 = NL + "   private JLabel lbl";
  protected final String TEXT_15 = ";" + NL + "   private JTextField txt";
  protected final String TEXT_16 = ";";
  protected final String TEXT_17 = NL + "   " + NL + "   private ";
  protected final String TEXT_18 = " registro;" + NL + "" + NL + "   // constructor" + NL + "   public FrmEditar";
  protected final String TEXT_19 = "() {" + NL + "      super();" + NL + "      initGUI();" + NL + "   }" + NL + "" + NL + "   // listener" + NL + "   public void actionPerformed(ActionEvent evt) {" + NL + "      try {" + NL + "         // TODO add your code for btnAceptar.actionPerformed" + NL + "         System.out.println(evt.getActionCommand());         " + NL + "         " + NL + "         if (evt.getActionCommand().equals(btnAceptar.getActionCommand())) {" + NL + "            //Aceptar" + NL + "            this.grabar();" + NL + "         } else if (evt.getActionCommand().equals(btnSalir.getActionCommand())) {" + NL + "            //Salir" + NL + "\t\t\tthis.setClosed(true);" + NL + "         }" + NL + "      } catch(Exception e) {" + NL + "         System.err.println(e);" + NL + "      }" + NL + "   }" + NL + "" + NL + "   // initGUI" + NL + "   private void initGUI() {" + NL + "      try {" + NL + "         this.setPreferredSize(new java.awt.Dimension(ANCHO, ALTO));" + NL + "         this.setBounds(25, 25, ANCHO, ALTO);" + NL + "         setVisible(false);" + NL + "         setClosable(false);" + NL + "         {" + NL + "            pnlVentana = new JPanel();" + NL + "            BorderLayout pnlVentanaLayout = new BorderLayout();" + NL + "            pnlVentana.setLayout(pnlVentanaLayout);" + NL + "            this.getContentPane().add(pnlVentana, BorderLayout.CENTER);" + NL + "            {" + NL + "               {" + NL + "                  pnlBotones = new JPanel();" + NL + "                  pnlVentana.add(pnlBotones, BorderLayout.SOUTH);" + NL + "                  {" + NL + "                     btnAceptar = new JButton();" + NL + "                     pnlBotones.add(btnAceptar);" + NL + "                     btnAceptar.setText(\"Aceptar\");" + NL + "                     btnAceptar.setActionCommand(\"";
  protected final String TEXT_20 = ".nuevo\");" + NL + "                     btnAceptar.addActionListener(this);" + NL + "                  }" + NL + "                  {" + NL + "                     btnSalir = new JButton();" + NL + "                     pnlBotones.add(btnSalir);" + NL + "                     btnSalir.setText(\"Salir\");" + NL + "                     btnSalir.setActionCommand(\"";
  protected final String TEXT_21 = ".salir\");" + NL + "                     btnSalir.addActionListener(this);" + NL + "                  }" + NL + "               }" + NL + "               {" + NL + "                  jScrollPane1 = new JScrollPane();" + NL + "                  pnlVentana.add(jScrollPane1, BorderLayout.CENTER);" + NL + "                  {" + NL + "                     pnlDatos = new JPanel();" + NL + "                     pnlDatos.setLayout(null);" + NL + "                     jScrollPane1.setViewportView(pnlDatos);" + NL + "                     " + NL + "                     //Elementos nuevo registro";
  protected final String TEXT_22 = NL + "                     {" + NL + "                        lbl";
  protected final String TEXT_23 = " = new JLabel();" + NL + "                        pnlDatos.add(lbl";
  protected final String TEXT_24 = ");" + NL + "                        lbl";
  protected final String TEXT_25 = ".setText(\"";
  protected final String TEXT_26 = "\");" + NL + "                        lbl";
  protected final String TEXT_27 = ".setBounds(";
  protected final String TEXT_28 = ", ";
  protected final String TEXT_29 = ", 200,20);" + NL + "                     }" + NL + "                     {" + NL + "                        txt";
  protected final String TEXT_30 = " = new JTextField();" + NL + "                        pnlDatos.add(txt";
  protected final String TEXT_31 = ");" + NL + "                        txt";
  protected final String TEXT_32 = ".setBounds(";
  protected final String TEXT_33 = ", ";
  protected final String TEXT_34 = ", 200,20);" + NL + "                     }";
  protected final String TEXT_35 = NL + "                     " + NL + "                     this.setResizable(true);" + NL + "                     this.setIconifiable(true);" + NL + "                     this.setMaximizable(true);" + NL + "                     this.setSelected(true);" + NL + "                     " + NL + "                     // Título" + NL + "                     this.setTitle(\"Editar Registro - ";
  protected final String TEXT_36 = "\");" + NL + "                  }" + NL + "               }" + NL + "            }" + NL + "         }" + NL + "      } catch (Exception e) {" + NL + "         e.printStackTrace();" + NL + "      }" + NL + "   }" + NL + "" + NL + "   // grabar" + NL + "   private void grabar() {";
  protected final String TEXT_37 = NL + "      ";
  protected final String TEXT_38 = " reg = new ";
  protected final String TEXT_39 = "();" + NL + "      IAlmacen almacen;" + NL + "      try {" + NL + "         almacen = FabricaAlmacenes.createAlmacen(FabricaAlmacenes.";
  protected final String TEXT_40 = ",FabricaAlmacenes.TIPO_TXT);" + NL + "         almacen.open();" + NL + "         ";
  protected final String TEXT_41 = NL + "         reg.set";
  protected final String TEXT_42 = "(txt";
  protected final String TEXT_43 = ".getText());";
  protected final String TEXT_44 = NL + "         reg.set";
  protected final String TEXT_45 = "(Integer.parseInt(txt";
  protected final String TEXT_46 = ".getText()));";
  protected final String TEXT_47 = NL + "         reg.set";
  protected final String TEXT_48 = "(Boolean.parseBoolean(txt";
  protected final String TEXT_49 = ".getText()));";
  protected final String TEXT_50 = NL + "         reg.set";
  protected final String TEXT_51 = "(Fechas.fromCadena(txt";
  protected final String TEXT_52 = ".getText()));";
  protected final String TEXT_53 = NL + "         " + NL + "         almacen.add(reg);" + NL + "         almacen.close();" + NL + "      } catch(Exception e) {" + NL + "          e.printStackTrace();" + NL + "      }" + NL + "   }" + NL + "   " + NL + "   // setEditable" + NL + "   public void setEditable(boolean pEstado) {";
  protected final String TEXT_54 = NL + "      txt";
  protected final String TEXT_55 = ".setEnabled(pEstado);";
  protected final String TEXT_56 = NL + "      " + NL + "      btnAceptar.setVisible(pEstado);" + NL + "   }" + NL + "   " + NL + "   // setRegistro" + NL + "   public void setRegistro(";
  protected final String TEXT_57 = " pRegistro) {" + NL + "      registro = pRegistro;";
  protected final String TEXT_58 = NL + "      txt";
  protected final String TEXT_59 = ".setText(registro.get";
  protected final String TEXT_60 = "());";
  protected final String TEXT_61 = NL + "      txt";
  protected final String TEXT_62 = ".setText(Integer.toString(registro.get";
  protected final String TEXT_63 = "()));";
  protected final String TEXT_64 = NL + "      txt";
  protected final String TEXT_65 = ".setText(Boolean.toString(registro.get";
  protected final String TEXT_66 = "()));";
  protected final String TEXT_67 = NL + "      txt";
  protected final String TEXT_68 = ".setText(Fechas.toCadena(registro.get";
  protected final String TEXT_69 = "()));";
  protected final String TEXT_70 = NL + "   }" + NL + "   " + NL + "   public void reset() {" + NL + "   }" + NL + "   " + NL + "   public String getIdVentana() {" + NL + "      return ID_VENTANA;" + NL + "   }" + NL + "}";
  protected final String TEXT_71 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
     
   ArgumentoJet arg = (ArgumentoJet) argument;

   david.tenerife.lib.BaseDatos bd = arg.getBaseDatos(); 
   david.tenerife.lib.Tabla t = arg.getTabla();
   david.tenerife.lib.Campo c;

   david.tenerife.util.Notacion n = new david.tenerife.util.Notacion();

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
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_19);
    stringBuffer.append(t.getNombre().toLowerCase());
    stringBuffer.append(TEXT_20);
    stringBuffer.append(t.getNombre().toLowerCase());
    stringBuffer.append(TEXT_21);
     int x = 20;
                        int y = 20;
                        for (int j = 0; j < t.getNumeroCampos(); j++) {
                           c = t.getCampo(j);
                     
    stringBuffer.append(TEXT_22);
    stringBuffer.append(n.getPascal(c.getNombre()));
    stringBuffer.append(TEXT_23);
    stringBuffer.append(n.getPascal(c.getNombre()));
    stringBuffer.append(TEXT_24);
    stringBuffer.append(n.getPascal(c.getNombre()));
    stringBuffer.append(TEXT_25);
    stringBuffer.append(c.getEtiqueta());
    stringBuffer.append(TEXT_26);
    stringBuffer.append(n.getPascal(c.getNombre()));
    stringBuffer.append(TEXT_27);
    stringBuffer.append(x);
    stringBuffer.append(TEXT_28);
    stringBuffer.append(y);
    stringBuffer.append(TEXT_29);
    stringBuffer.append(n.getPascal(c.getNombre()));
    stringBuffer.append(TEXT_30);
    stringBuffer.append(n.getPascal(c.getNombre()));
    stringBuffer.append(TEXT_31);
    stringBuffer.append(n.getPascal(c.getNombre()));
    stringBuffer.append(TEXT_32);
    stringBuffer.append((x + 200));
    stringBuffer.append(TEXT_33);
    stringBuffer.append(y);
    stringBuffer.append(TEXT_34);
       y = y + 20;
                          }
                     
    stringBuffer.append(TEXT_35);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_36);
    stringBuffer.append(TEXT_37);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_38);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_39);
    stringBuffer.append(t.getNombre().toUpperCase());
    stringBuffer.append(TEXT_40);
     for (int j = 0; j < t.getNumeroCampos(); j++) {
               c = t.getCampo(j);
               if (n.getTipoJava(c.getTipo()).equals("String")) {
         
    stringBuffer.append(TEXT_41);
    stringBuffer.append(n.getPascal(c.getNombre()));
    stringBuffer.append(TEXT_42);
    stringBuffer.append(n.getPascal(c.getNombre()));
    stringBuffer.append(TEXT_43);
    
               } else if (n.getTipoJava(c.getTipo()).equals("int")) {
         
    stringBuffer.append(TEXT_44);
    stringBuffer.append(n.getPascal(c.getNombre()));
    stringBuffer.append(TEXT_45);
    stringBuffer.append(n.getPascal(c.getNombre()));
    stringBuffer.append(TEXT_46);
    
               } else if (n.getTipoJava(c.getTipo()).equals("boolean")) {
         
    stringBuffer.append(TEXT_47);
    stringBuffer.append(n.getPascal(c.getNombre()));
    stringBuffer.append(TEXT_48);
    stringBuffer.append(n.getPascal(c.getNombre()));
    stringBuffer.append(TEXT_49);
    
               } else if (n.getTipoJava(c.getTipo()).equals("Date")) {
         
    stringBuffer.append(TEXT_50);
    stringBuffer.append(n.getPascal(c.getNombre()));
    stringBuffer.append(TEXT_51);
    stringBuffer.append(n.getPascal(c.getNombre()));
    stringBuffer.append(TEXT_52);
        }
         	}
         
    stringBuffer.append(TEXT_53);
     for (int j = 0; j < t.getNumeroCampos(); j++) {
            c = t.getCampo(j);
      
    stringBuffer.append(TEXT_54);
    stringBuffer.append(n.getPascal(c.getNombre()));
    stringBuffer.append(TEXT_55);
     } 
    stringBuffer.append(TEXT_56);
    stringBuffer.append(n.getPascal(t.getNombre()));
    stringBuffer.append(TEXT_57);
     for (int j = 0; j < t.getNumeroCampos(); j++) {
            c = t.getCampo(j);
            
            if (n.getTipoJava(c.getTipo()).equals("String")) { 
      
    stringBuffer.append(TEXT_58);
    stringBuffer.append(n.getPascal(c.getNombre()));
    stringBuffer.append(TEXT_59);
    stringBuffer.append(n.getPascal(c.getNombre()));
    stringBuffer.append(TEXT_60);
        } else if (n.getTipoJava(c.getTipo()).equals("int")) { 
    stringBuffer.append(TEXT_61);
    stringBuffer.append(n.getPascal(c.getNombre()));
    stringBuffer.append(TEXT_62);
    stringBuffer.append(n.getPascal(c.getNombre()));
    stringBuffer.append(TEXT_63);
        } else if (n.getTipoJava(c.getTipo()).equals("boolean")) { 
    stringBuffer.append(TEXT_64);
    stringBuffer.append(n.getPascal(c.getNombre()));
    stringBuffer.append(TEXT_65);
    stringBuffer.append(n.getPascal(c.getNombre()));
    stringBuffer.append(TEXT_66);
        } else if (n.getTipoJava(c.getTipo()).equals("Date")) { 
    stringBuffer.append(TEXT_67);
    stringBuffer.append(n.getPascal(c.getNombre()));
    stringBuffer.append(TEXT_68);
    stringBuffer.append(n.getPascal(c.getNombre()));
    stringBuffer.append(TEXT_69);
        } 
         }
      
    stringBuffer.append(TEXT_70);
    stringBuffer.append(TEXT_71);
    return stringBuffer.toString();
  }
}
