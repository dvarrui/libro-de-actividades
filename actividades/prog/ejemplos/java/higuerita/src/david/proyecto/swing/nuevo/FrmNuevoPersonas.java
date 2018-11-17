
// Creado por el proyecto TENERIFE
// Autor: David Vargas Ruiz
// Fecha: Tue Feb 07 22:55:25 WET 2006


package david.proyecto.swing.nuevo;

// import
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import david.proyecto.almacenes.IAlmacen;
import david.proyecto.entidades.Personas;
import david.proyecto.util.Fechas;
import david.proyecto.swing.IVentana;

// clase
public class FrmNuevoPersonas extends javax.swing.JInternalFrame implements ActionListener, IVentana {
   static final long serialVersionUID=1; //Para evitar Warnings

   // atributos
   public static final String ID_VENTANA="personas.nuevo";
   public static final int ANCHO = 471; 
   public static final int ALTO = 296;

   private JButton btnAceptar;
   private JButton btnSalir;
   private JScrollPane jScrollPane1;
   private JPanel pnlBotones;
   private JPanel pnlDatos;
   private JPanel pnlVentana;

   private JLabel lblCodPersona;
   private JTextField txtCodPersona;
   private JLabel lblNombre;
   private JTextField txtNombre;
   private JLabel lblApellido1;
   private JTextField txtApellido1;
   private JLabel lblApellido2;
   private JTextField txtApellido2;
   private JLabel lblFechaNacimiento;
   private JTextField txtFechaNacimiento;
   private JLabel lblDni;
   private JTextField txtDni;
   private JLabel lblTelefono1;
   private JTextField txtTelefono1;
   private JLabel lblTelefono2;
   private JTextField txtTelefono2;
   private JLabel lblDireccion;
   private JTextField txtDireccion;

   private IAlmacen almacen;
   private IVentana frmContenedor;
   
   // constructor
   public FrmNuevoPersonas() {
      super();
      initGUI();
   }

   // listener
   public void actionPerformed(ActionEvent evt) {
      try {
         System.out.println(evt.getActionCommand());
         // TODO add your code for btnAceptar.actionPerformed
         if (evt.getActionCommand().equals(btnAceptar.getActionCommand())) {
            //Aceptar
            this.grabar();
         } else if (evt.getActionCommand().equals(btnSalir.getActionCommand())) {
            //Salir
            this.setVisible(false);
         }
      } catch(Exception e) {
         System.err.println(e);
      }
   }

   // initGUI
   private void initGUI() {
      try {
         this.setPreferredSize(new java.awt.Dimension(ANCHO, ALTO));
         this.setBounds(25, 25, ANCHO, ALTO);
         setVisible(false);
         setClosable(false);
         {
            pnlVentana = new JPanel();
            BorderLayout pnlVentanaLayout = new BorderLayout();
            pnlVentana.setLayout(pnlVentanaLayout);
            this.getContentPane().add(pnlVentana, BorderLayout.CENTER);
            {
               {
                  pnlBotones = new JPanel();
                  pnlVentana.add(pnlBotones, BorderLayout.SOUTH);
                  {
                     btnAceptar = new JButton();
                     pnlBotones.add(btnAceptar);
                     btnAceptar.setText("Aceptar");
                     btnAceptar.setActionCommand("aceptar");
                     btnAceptar.addActionListener(this);
                  }
                  {
                     btnSalir = new JButton();
                     pnlBotones.add(btnSalir);
                     btnSalir.setText("Salir");
                     btnSalir.setActionCommand("salir");
                     btnSalir.addActionListener(this);
                  }
               }
               {
                  jScrollPane1 = new JScrollPane();
                  pnlVentana.add(jScrollPane1, BorderLayout.CENTER);
                  {
                     pnlDatos = new JPanel();
                     pnlDatos.setLayout(null);
                     jScrollPane1.setViewportView(pnlDatos);
                     
                     //Elementos nuevo registro
                     
					 {
					    lblCodPersona = new JLabel();
					    pnlDatos.add(lblCodPersona);
					    lblCodPersona.setText("CODIGO");
					    lblCodPersona.setBounds(20, 20, 200,20);
					 }
					 {
					    txtCodPersona = new JTextField();
					    pnlDatos.add(txtCodPersona);
					    txtCodPersona.setBounds(220, 20, 200,20);
					 }
					 
					 {
					    lblNombre = new JLabel();
					    pnlDatos.add(lblNombre);
					    lblNombre.setText("nombre");
					    lblNombre.setBounds(20, 40, 200,20);
					 }
					 {
					    txtNombre = new JTextField();
					    pnlDatos.add(txtNombre);
					    txtNombre.setBounds(220, 40, 200,20);
					 }
					 
					 {
					    lblApellido1 = new JLabel();
					    pnlDatos.add(lblApellido1);
					    lblApellido1.setText("apellido1");
					    lblApellido1.setBounds(20, 60, 200,20);
					 }
					 {
					    txtApellido1 = new JTextField();
					    pnlDatos.add(txtApellido1);
					    txtApellido1.setBounds(220, 60, 200,20);
					 }
					 
					 {
					    lblApellido2 = new JLabel();
					    pnlDatos.add(lblApellido2);
					    lblApellido2.setText("apellido2");
					    lblApellido2.setBounds(20, 80, 200,20);
					 }
					 {
					    txtApellido2 = new JTextField();
					    pnlDatos.add(txtApellido2);
					    txtApellido2.setBounds(220, 80, 200,20);
					 }
					 
					 {
					    lblFechaNacimiento = new JLabel();
					    pnlDatos.add(lblFechaNacimiento);
					    lblFechaNacimiento.setText("fechaNacimiento");
					    lblFechaNacimiento.setBounds(20, 100, 200,20);
					 }
					 {
					    txtFechaNacimiento = new JTextField();
					    pnlDatos.add(txtFechaNacimiento);
					    txtFechaNacimiento.setBounds(220, 100, 200,20);
					 }
					 
					 {
					    lblDni = new JLabel();
					    pnlDatos.add(lblDni);
					    lblDni.setText("dni");
					    lblDni.setBounds(20, 120, 200,20);
					 }
					 {
					    txtDni = new JTextField();
					    pnlDatos.add(txtDni);
					    txtDni.setBounds(220, 120, 200,20);
					 }
					 
					 {
					    lblTelefono1 = new JLabel();
					    pnlDatos.add(lblTelefono1);
					    lblTelefono1.setText("telefono1");
					    lblTelefono1.setBounds(20, 140, 200,20);
					 }
					 {
					    txtTelefono1 = new JTextField();
					    pnlDatos.add(txtTelefono1);
					    txtTelefono1.setBounds(220, 140, 200,20);
					 }
					 
					 {
					    lblTelefono2 = new JLabel();
					    pnlDatos.add(lblTelefono2);
					    lblTelefono2.setText("telefono2");
					    lblTelefono2.setBounds(20, 160, 200,20);
					 }
					 {
					    txtTelefono2 = new JTextField();
					    pnlDatos.add(txtTelefono2);
					    txtTelefono2.setBounds(220, 160, 200,20);
					 }
					 
					 {
					    lblDireccion = new JLabel();
					    pnlDatos.add(lblDireccion);
					    lblDireccion.setText("direccion");
					    lblDireccion.setBounds(20, 180, 200,20);
					 }
					 {
					    txtDireccion = new JTextField();
					    pnlDatos.add(txtDireccion);
					    txtDireccion.setBounds(220, 180, 200,20);
					 }
					 
                  
                     this.setResizable(true);
                     this.setIconifiable(true);
                     this.setMaximizable(true);
                     this.setSelected(true);
                     this.setTitle("Nuevo Registro - Personas");
                  }
               }
            }
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   // grabar
   private void grabar() {
      Personas reg = new Personas();
      try {
         reg.setCodPersona(Integer.parseInt(txtCodPersona.getText()));
         reg.setNombre(txtNombre.getText());
         reg.setApellido1(txtApellido1.getText());
         reg.setApellido2(txtApellido2.getText());
         reg.setFechaNacimiento(Fechas.fromCadena(txtFechaNacimiento.getText()));
         reg.setDni(txtDni.getText());
         reg.setTelefono1(txtTelefono1.getText());
         reg.setTelefono2(txtTelefono2.getText());
         reg.setDireccion(txtDireccion.getText());

         almacen.add(reg);
         if (frmContenedor!=null) frmContenedor.reset();
      } catch(Exception e) {
         System.err.println(e);
      }
   }
   
   // Otros métodos
   public void setAlmacen(IAlmacen almacen) {
      this.almacen = almacen;
   }
   
   public void setContenedor(IVentana f) {
      frmContenedor = f;
   }

   public void reset() {
      // Visualizar y refrescar la ventana completa
      setVisible(true);
      txtCodPersona.setText(null);
      txtNombre.setText(null);
      txtApellido1.setText(null);
      txtApellido2.setText(null);
      txtFechaNacimiento.setText(null);
      txtDni.setText(null);
      txtTelefono1.setText(null);
      txtTelefono2.setText(null);
      txtDireccion.setText(null);
   }
   
   public String getIdVentana() {
      return ID_VENTANA;
   }
}


