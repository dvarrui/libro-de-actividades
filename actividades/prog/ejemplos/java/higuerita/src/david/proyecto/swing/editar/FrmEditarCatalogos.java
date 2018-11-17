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
import david.proyecto.entidades.Catalogos;
import david.proyecto.swing.IVentana;

// clase
public class FrmEditarCatalogos extends javax.swing.JInternalFrame implements ActionListener, IVentana {
   static final long serialVersionUID=1; //Evitar Warnings
   
   public static final String ID_VENTANA="catalogos.editar";
   public static int ANCHO = 471;
   public static int ALTO = 296;
   
   // atributos
   private JButton btnAceptar;
   private JButton btnSalir;
   private JScrollPane jScrollPane1;
   private JPanel pnlBotones;
   private JPanel pnlDatos;
   private JPanel pnlVentana;

   private JLabel lblCodCatalogo;
   private JTextField txtCodCatalogo;
   private JLabel lblDesCatalogo;
   private JTextField txtDesCatalogo;
   private JLabel lblCodCatalogoPadre;
   private JTextField txtCodCatalogoPadre;
   private JLabel lblPeso;
   private JTextField txtPeso;
   
   private Catalogos registro;

   // constructor
   public FrmEditarCatalogos() {
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
                     btnAceptar.setActionCommand("catalogos.nuevo");
                     btnAceptar.addActionListener(this);
                  }
                  {
                     btnSalir = new JButton();
                     pnlBotones.add(btnSalir);
                     btnSalir.setText("Salir");
                     btnSalir.setActionCommand("catalogos.salir");
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
                        lblCodCatalogo = new JLabel();
                        pnlDatos.add(lblCodCatalogo);
                        lblCodCatalogo.setText("CODIGO");
                        lblCodCatalogo.setBounds(20, 20, 200,20);
                     }
                     {
                        txtCodCatalogo = new JTextField();
                        pnlDatos.add(txtCodCatalogo);
                        txtCodCatalogo.setBounds(220, 20, 200,20);
                     }
                     {
                        lblDesCatalogo = new JLabel();
                        pnlDatos.add(lblDesCatalogo);
                        lblDesCatalogo.setText("DESCRIPCION");
                        lblDesCatalogo.setBounds(20, 40, 200,20);
                     }
                     {
                        txtDesCatalogo = new JTextField();
                        pnlDatos.add(txtDesCatalogo);
                        txtDesCatalogo.setBounds(220, 40, 200,20);
                     }
                     {
                        lblCodCatalogoPadre = new JLabel();
                        pnlDatos.add(lblCodCatalogoPadre);
                        lblCodCatalogoPadre.setText("codCatalogoPadre");
                        lblCodCatalogoPadre.setBounds(20, 60, 200,20);
                     }
                     {
                        txtCodCatalogoPadre = new JTextField();
                        pnlDatos.add(txtCodCatalogoPadre);
                        txtCodCatalogoPadre.setBounds(220, 60, 200,20);
                     }
                     {
                        lblPeso = new JLabel();
                        pnlDatos.add(lblPeso);
                        lblPeso.setText("peso");
                        lblPeso.setBounds(20, 80, 200,20);
                     }
                     {
                        txtPeso = new JTextField();
                        pnlDatos.add(txtPeso);
                        txtPeso.setBounds(220, 80, 200,20);
                     }
                     
                     this.setResizable(true);
                     this.setIconifiable(true);
                     this.setMaximizable(true);
                     this.setSelected(true);
                     
                     // Título
                     this.setTitle("Editar Registro - Catalogos");
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
      Catalogos reg = new Catalogos();
      IAlmacen almacen;
      try {
         almacen = FabricaAlmacenes.createAlmacen(FabricaAlmacenes.CATALOGOS,FabricaAlmacenes.TIPO_TXT);
         almacen.open();
         
         reg.setCodCatalogo(Integer.parseInt(txtCodCatalogo.getText()));
         reg.setDesCatalogo(txtDesCatalogo.getText());
         reg.setCodCatalogoPadre(Integer.parseInt(txtCodCatalogoPadre.getText()));
         reg.setPeso(Integer.parseInt(txtPeso.getText()));
         
         almacen.add(reg);
         almacen.close();
      } catch(Exception e) {
          e.printStackTrace();
      }
   }
   
   // setEditable
   public void setEditable(boolean pEstado) {
      txtCodCatalogo.setEnabled(pEstado);
      txtDesCatalogo.setEnabled(pEstado);
      txtCodCatalogoPadre.setEnabled(pEstado);
      txtPeso.setEnabled(pEstado);
      
      btnAceptar.setVisible(pEstado);
   }
   
   // setRegistro
   public void setRegistro(Catalogos pRegistro) {
      registro = pRegistro;
      txtCodCatalogo.setText(Integer.toString(registro.getCodCatalogo()));
      txtDesCatalogo.setText(registro.getDesCatalogo());
      txtCodCatalogoPadre.setText(Integer.toString(registro.getCodCatalogoPadre()));
      txtPeso.setText(Integer.toString(registro.getPeso()));
   }
   
   public void reset() {
   }
   
   public String getIdVentana() {
      return ID_VENTANA;
   }
}

