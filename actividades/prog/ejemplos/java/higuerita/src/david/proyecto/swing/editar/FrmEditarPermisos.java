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
import david.proyecto.entidades.Permisos;
import david.proyecto.swing.IVentana;

// clase
public class FrmEditarPermisos extends javax.swing.JInternalFrame implements ActionListener, IVentana {
   static final long serialVersionUID=1; //Evitar Warnings
   
   public static final String ID_VENTANA="permisos.editar";
   public static int ANCHO = 471;
   public static int ALTO = 296;
   
   // atributos
   private JButton btnAceptar;
   private JButton btnSalir;
   private JScrollPane jScrollPane1;
   private JPanel pnlBotones;
   private JPanel pnlDatos;
   private JPanel pnlVentana;

   private JLabel lblCodPermiso;
   private JTextField txtCodPermiso;
   private JLabel lblDesPermiso;
   private JTextField txtDesPermiso;
   
   private Permisos registro;

   // constructor
   public FrmEditarPermisos() {
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
                     btnAceptar.setActionCommand("permisos.nuevo");
                     btnAceptar.addActionListener(this);
                  }
                  {
                     btnSalir = new JButton();
                     pnlBotones.add(btnSalir);
                     btnSalir.setText("Salir");
                     btnSalir.setActionCommand("permisos.salir");
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
                        lblCodPermiso = new JLabel();
                        pnlDatos.add(lblCodPermiso);
                        lblCodPermiso.setText("CODIGO");
                        lblCodPermiso.setBounds(20, 20, 200,20);
                     }
                     {
                        txtCodPermiso = new JTextField();
                        pnlDatos.add(txtCodPermiso);
                        txtCodPermiso.setBounds(220, 20, 200,20);
                     }
                     {
                        lblDesPermiso = new JLabel();
                        pnlDatos.add(lblDesPermiso);
                        lblDesPermiso.setText("DESCRIPCION");
                        lblDesPermiso.setBounds(20, 40, 200,20);
                     }
                     {
                        txtDesPermiso = new JTextField();
                        pnlDatos.add(txtDesPermiso);
                        txtDesPermiso.setBounds(220, 40, 200,20);
                     }
                     
                     this.setResizable(true);
                     this.setIconifiable(true);
                     this.setMaximizable(true);
                     this.setSelected(true);
                     
                     // Título
                     this.setTitle("Editar Registro - Permisos");
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
      Permisos reg = new Permisos();
      IAlmacen almacen;
      try {
         almacen = FabricaAlmacenes.createAlmacen(FabricaAlmacenes.PERMISOS,FabricaAlmacenes.TIPO_TXT);
         almacen.open();
         
         reg.setCodPermiso(Integer.parseInt(txtCodPermiso.getText()));
         reg.setDesPermiso(txtDesPermiso.getText());
         
         almacen.add(reg);
         almacen.close();
      } catch(Exception e) {
          e.printStackTrace();
      }
   }
   
   // setEditable
   public void setEditable(boolean pEstado) {
      txtCodPermiso.setEnabled(pEstado);
      txtDesPermiso.setEnabled(pEstado);
      
      btnAceptar.setVisible(pEstado);
   }
   
   // setRegistro
   public void setRegistro(Permisos pRegistro) {
      registro = pRegistro;
      txtCodPermiso.setText(Integer.toString(registro.getCodPermiso()));
      txtDesPermiso.setText(registro.getDesPermiso());
   }
   
   public void reset() {
   }
   
   public String getIdVentana() {
      return ID_VENTANA;
   }
}

