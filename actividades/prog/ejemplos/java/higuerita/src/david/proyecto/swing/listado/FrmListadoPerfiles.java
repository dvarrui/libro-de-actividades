// Creado por el proyecto TENERIFE
// Autor: David Vargas Ruiz
// Fecha: Tue Feb 07 22:55:25 WET 2006


package david.proyecto.swing.listado;

// import
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import david.proyecto.almacenes.IAlmacen;
import david.proyecto.swing.IVentana;
import david.proyecto.swing.TableModel.TableModelPerfiles;

// Comienzo de la clase
public class FrmListadoPerfiles extends javax.swing.JInternalFrame implements ActionListener, IVentana {
   static final long serialVersionUID=1; //Para evitar Warnings

   // Atributos
   public static final String ID_VENTANA="perfiles.listado";
   public static final int ANCHO = 471;
   public static final int ALTO = 296;

   private JButton btnConsultar;
   private JButton btnEliminar;
   private JButton btnModificar;
   private JButton btnNuevo;
   private JButton btnSalir;
   private JScrollPane jScrollPane1;
   private JPanel pnlBotones;
   private JPanel pnlDatos;
   private JPanel pnlVentana;
   private JTable tablaDatos;

   private TableModelPerfiles modeloDeTabla;
   private IAlmacen almacen;
   
   // Constructor
   public FrmListadoPerfiles(IAlmacen pAlmacen) {
      super();
      setAlmacen(pAlmacen);
      initGUI();
   }

   // actionPerformed
   public void actionPerformed(ActionEvent evt) {
      if (evt.getActionCommand().equals("perfiles.nuevo")) {
         // Abrir ventana Nuevo
      } else if (evt.getActionCommand().equals("perfiles.consultar")) {
         this.setVisible(false);
      } else if (evt.getActionCommand().equals("perfiles.modificar")) {
         this.setVisible(false);
      } else if (evt.getActionCommand().equals("perfiles.eliminar")) {
         this.setVisible(false);
      } else if (evt.getActionCommand().equals("salir")) {
         //Ejecutar Salir
         this.setVisible(false);
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
                     btnNuevo = new JButton();
                     pnlBotones.add(btnNuevo);
                     btnNuevo.setText("Nuevo");
                     btnNuevo.setActionCommand("perfiles.nuevo");
                     btnNuevo.addActionListener(this);
                  }
                  {
                     btnConsultar = new JButton();
                     pnlBotones.add(btnConsultar);
                     btnConsultar.setText("Consultar");
                     btnConsultar.setActionCommand("perfiles.consultar");
                     btnConsultar.addActionListener(this);
                  }
                  {
                     btnModificar = new JButton();
                     pnlBotones.add(btnModificar);
                     btnModificar.setText("Modificar");
                     btnModificar.setActionCommand("perfiles.modificar");
                     btnModificar.setEnabled(false);
                     btnModificar.addActionListener(this);
                  }
                  {
                     btnEliminar = new JButton();
                     pnlBotones.add(btnEliminar);
                     btnEliminar.setText("Eliminar");
                     btnEliminar.setActionCommand("perfiles.eliminar");
                     btnEliminar.setEnabled(false);
                     btnEliminar.addActionListener(this);
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
                     jScrollPane1.setViewportView(pnlDatos);

                     modeloDeTabla = new TableModelPerfiles(this.almacen);

                     this.setResizable(true);
                     this.setIconifiable(true);
                     this.setMaximizable(true);
                     this.setSelected(true);
                     this.setTitle("Listado - Perfiles");
                     tablaDatos = new JTable();
                     tablaDatos.setLayout(null);
                     pnlDatos.add(tablaDatos);
                     tablaDatos.setModel(modeloDeTabla);
                     tablaDatos.getTableHeader().setBounds(0, 5, 150, 15);
                  }
               }
            }
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
   
   // Otros métodos
   public void setAlmacen(IAlmacen almacen) {
      this.almacen = almacen;
      if (modeloDeTabla!=null)   modeloDeTabla.setAlmacen(this.almacen);
   }
   
   public void reset() {
      // Refrescar el TableModel y la ventana completa
      modeloDeTabla.reset();
      setVisible(true);
   }
   
   public String getIdVentana() {
      return ID_VENTANA;
   }
}

