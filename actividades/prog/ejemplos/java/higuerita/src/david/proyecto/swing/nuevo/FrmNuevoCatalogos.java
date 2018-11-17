
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
import david.proyecto.entidades.Catalogos;
import david.proyecto.swing.IVentana;

// clase
public class FrmNuevoCatalogos extends javax.swing.JInternalFrame implements ActionListener, IVentana {
   static final long serialVersionUID=1; //Para evitar Warnings

   // atributos
   public static final String ID_VENTANA="catalogos.nuevo";
   public static final int ANCHO = 471; 
   public static final int ALTO = 296;

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

   private IAlmacen almacen;
   private IVentana frmContenedor;
   
   // constructor
   public FrmNuevoCatalogos() {
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
                     this.setTitle("Nuevo Registro - Catalogos");
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
      try {
         reg.setCodCatalogo(Integer.parseInt(txtCodCatalogo.getText()));
         reg.setDesCatalogo(txtDesCatalogo.getText());
         reg.setCodCatalogoPadre(Integer.parseInt(txtCodCatalogoPadre.getText()));
         reg.setPeso(Integer.parseInt(txtPeso.getText()));

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
      txtCodCatalogo.setText(null);
      txtDesCatalogo.setText(null);
      txtCodCatalogoPadre.setText(null);
      txtPeso.setText(null);
   }
   
   public String getIdVentana() {
      return ID_VENTANA;
   }
}


