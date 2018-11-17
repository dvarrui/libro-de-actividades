
// Creado por el proyecto TENERIFE
// Autor: David Vargas Ruiz
// Fecha: Tue Feb 07 22:55:26 WET 2006


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
import david.proyecto.entidades.Perfiles;
import david.proyecto.swing.IVentana;

// clase
public class FrmNuevoPerfiles extends javax.swing.JInternalFrame implements ActionListener, IVentana {
   static final long serialVersionUID=1; //Para evitar Warnings

   // atributos
   public static final String ID_VENTANA="perfiles.nuevo";
   public static final int ANCHO = 471; 
   public static final int ALTO = 296;

   private JButton btnAceptar;
   private JButton btnSalir;
   private JScrollPane jScrollPane1;
   private JPanel pnlBotones;
   private JPanel pnlDatos;
   private JPanel pnlVentana;

   private JLabel lblCodPerfil;
   private JTextField txtCodPerfil;
   private JLabel lblDesPerfil;
   private JTextField txtDesPerfil;

   private IAlmacen almacen;
   private IVentana frmContenedor;
   
   // constructor
   public FrmNuevoPerfiles() {
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
					    lblCodPerfil = new JLabel();
					    pnlDatos.add(lblCodPerfil);
					    lblCodPerfil.setText("CODIGO");
					    lblCodPerfil.setBounds(20, 20, 200,20);
					 }
					 {
					    txtCodPerfil = new JTextField();
					    pnlDatos.add(txtCodPerfil);
					    txtCodPerfil.setBounds(220, 20, 200,20);
					 }
					 
					 {
					    lblDesPerfil = new JLabel();
					    pnlDatos.add(lblDesPerfil);
					    lblDesPerfil.setText("DESCRIPCION");
					    lblDesPerfil.setBounds(20, 40, 200,20);
					 }
					 {
					    txtDesPerfil = new JTextField();
					    pnlDatos.add(txtDesPerfil);
					    txtDesPerfil.setBounds(220, 40, 200,20);
					 }
					 
                  
                     this.setResizable(true);
                     this.setIconifiable(true);
                     this.setMaximizable(true);
                     this.setSelected(true);
                     this.setTitle("Nuevo Registro - Perfiles");
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
      Perfiles reg = new Perfiles();
      try {
         reg.setCodPerfil(Integer.parseInt(txtCodPerfil.getText()));
         reg.setDesPerfil(txtDesPerfil.getText());

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
      txtCodPerfil.setText(null);
      txtDesPerfil.setText(null);
   }
   
   public String getIdVentana() {
      return ID_VENTANA;
   }
}


