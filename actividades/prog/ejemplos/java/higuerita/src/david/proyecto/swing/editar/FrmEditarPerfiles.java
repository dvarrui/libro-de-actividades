// Creado por el proyecto TENERIFE
// Autor: David Vargas Ruiz
// Fecha: Tue Feb 07 22:55:26 WET 2006


package david.proyecto.swing.editar;

// import
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import david.proyecto.almacenes.FabricaAlmacenes;
import david.proyecto.almacenes.IAlmacen;
import david.proyecto.entidades.Perfiles;
import david.proyecto.swing.IVentana;

// clase
public class FrmEditarPerfiles extends javax.swing.JInternalFrame implements ActionListener, IVentana {
   static final long serialVersionUID=1; //Evitar Warnings
   
   public static final String ID_VENTANA="perfiles.editar";
   public static int ANCHO = 471;
   public static int ALTO = 296;
   
   // atributos
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
   
   private Perfiles registro;

   // constructor
   public FrmEditarPerfiles() {
      super();
      initGUI();
   }

   // listener
   public void actionPerformed(ActionEvent evt) {
      try {
         // TODO add your code for btnAceptar.actionPerformed
         System.out.println(evt.getActionCommand());         
         
         if (evt.getActionCommand().equals(btnAceptar.getActionCommand())) {
            //Aceptar
            this.grabar();
         } else if (evt.getActionCommand().equals(btnSalir.getActionCommand())) {
            //Salir
			this.setClosed(true);
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
                     btnAceptar.setActionCommand("perfiles.nuevo");
                     btnAceptar.addActionListener(this);
                  }
                  {
                     btnSalir = new JButton();
                     pnlBotones.add(btnSalir);
                     btnSalir.setText("Salir");
                     btnSalir.setActionCommand("perfiles.salir");
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
                     
                     // Título
                     this.setTitle("Editar Registro - Perfiles");
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
      IAlmacen almacen;
      try {
         almacen = FabricaAlmacenes.createAlmacen(FabricaAlmacenes.PERFILES,FabricaAlmacenes.TIPO_TXT);
         almacen.open();
         
         reg.setCodPerfil(Integer.parseInt(txtCodPerfil.getText()));
         reg.setDesPerfil(txtDesPerfil.getText());
         
         almacen.add(reg);
         almacen.close();
      } catch(Exception e) {
          e.printStackTrace();
      }
   }
   
   // setEditable
   public void setEditable(boolean pEstado) {
      txtCodPerfil.setEnabled(pEstado);
      txtDesPerfil.setEnabled(pEstado);
      
      btnAceptar.setVisible(pEstado);
   }
   
   // setRegistro
   public void setRegistro(Perfiles pRegistro) {
      registro = pRegistro;
      txtCodPerfil.setText(Integer.toString(registro.getCodPerfil()));
      txtDesPerfil.setText(registro.getDesPerfil());
   }
   
   public void reset() {
   }
   
   public String getIdVentana() {
      return ID_VENTANA;
   }
}

