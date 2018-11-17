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
import david.proyecto.entidades.Citas;
import david.proyecto.util.Fechas;
import david.proyecto.swing.IVentana;

// clase
public class FrmEditarCitas extends javax.swing.JInternalFrame implements ActionListener, IVentana {
   static final long serialVersionUID=1; //Evitar Warnings
   
   public static final String ID_VENTANA="citas.editar";
   public static int ANCHO = 471;
   public static int ALTO = 296;
   
   // atributos
   private JButton btnAceptar;
   private JButton btnSalir;
   private JScrollPane jScrollPane1;
   private JPanel pnlBotones;
   private JPanel pnlDatos;
   private JPanel pnlVentana;

   private JLabel lblCodCita;
   private JTextField txtCodCita;
   private JLabel lblCodAgenda;
   private JTextField txtCodAgenda;
   private JLabel lblCodPersona;
   private JTextField txtCodPersona;
   private JLabel lblFecha;
   private JTextField txtFecha;
   private JLabel lblCodCatalogo;
   private JTextField txtCodCatalogo;
   private JLabel lblRealizado;
   private JTextField txtRealizado;
   
   private Citas registro;

   // constructor
   public FrmEditarCitas() {
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
                     btnAceptar.setActionCommand("citas.nuevo");
                     btnAceptar.addActionListener(this);
                  }
                  {
                     btnSalir = new JButton();
                     pnlBotones.add(btnSalir);
                     btnSalir.setText("Salir");
                     btnSalir.setActionCommand("citas.salir");
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
                        lblCodCita = new JLabel();
                        pnlDatos.add(lblCodCita);
                        lblCodCita.setText("CODIGO");
                        lblCodCita.setBounds(20, 20, 200,20);
                     }
                     {
                        txtCodCita = new JTextField();
                        pnlDatos.add(txtCodCita);
                        txtCodCita.setBounds(220, 20, 200,20);
                     }
                     {
                        lblCodAgenda = new JLabel();
                        pnlDatos.add(lblCodAgenda);
                        lblCodAgenda.setText("codAgenda");
                        lblCodAgenda.setBounds(20, 40, 200,20);
                     }
                     {
                        txtCodAgenda = new JTextField();
                        pnlDatos.add(txtCodAgenda);
                        txtCodAgenda.setBounds(220, 40, 200,20);
                     }
                     {
                        lblCodPersona = new JLabel();
                        pnlDatos.add(lblCodPersona);
                        lblCodPersona.setText("codPersona");
                        lblCodPersona.setBounds(20, 60, 200,20);
                     }
                     {
                        txtCodPersona = new JTextField();
                        pnlDatos.add(txtCodPersona);
                        txtCodPersona.setBounds(220, 60, 200,20);
                     }
                     {
                        lblFecha = new JLabel();
                        pnlDatos.add(lblFecha);
                        lblFecha.setText("FECHA");
                        lblFecha.setBounds(20, 80, 200,20);
                     }
                     {
                        txtFecha = new JTextField();
                        pnlDatos.add(txtFecha);
                        txtFecha.setBounds(220, 80, 200,20);
                     }
                     {
                        lblCodCatalogo = new JLabel();
                        pnlDatos.add(lblCodCatalogo);
                        lblCodCatalogo.setText("codCatalogo");
                        lblCodCatalogo.setBounds(20, 100, 200,20);
                     }
                     {
                        txtCodCatalogo = new JTextField();
                        pnlDatos.add(txtCodCatalogo);
                        txtCodCatalogo.setBounds(220, 100, 200,20);
                     }
                     {
                        lblRealizado = new JLabel();
                        pnlDatos.add(lblRealizado);
                        lblRealizado.setText("realizado");
                        lblRealizado.setBounds(20, 120, 200,20);
                     }
                     {
                        txtRealizado = new JTextField();
                        pnlDatos.add(txtRealizado);
                        txtRealizado.setBounds(220, 120, 200,20);
                     }
                     
                     this.setResizable(true);
                     this.setIconifiable(true);
                     this.setMaximizable(true);
                     this.setSelected(true);
                     
                     // Título
                     this.setTitle("Editar Registro - Citas");
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
      Citas reg = new Citas();
      IAlmacen almacen;
      try {
         almacen = FabricaAlmacenes.createAlmacen(FabricaAlmacenes.CITAS,FabricaAlmacenes.TIPO_TXT);
         almacen.open();
         
         reg.setCodCita(Integer.parseInt(txtCodCita.getText()));
         reg.setCodAgenda(Integer.parseInt(txtCodAgenda.getText()));
         reg.setCodPersona(Integer.parseInt(txtCodPersona.getText()));
         reg.setFecha(Fechas.fromCadena(txtFecha.getText()));
         reg.setCodCatalogo(Integer.parseInt(txtCodCatalogo.getText()));
         reg.setRealizado(Boolean.parseBoolean(txtRealizado.getText()));
         
         almacen.add(reg);
         almacen.close();
      } catch(Exception e) {
          e.printStackTrace();
      }
   }
   
   // setEditable
   public void setEditable(boolean pEstado) {
      txtCodCita.setEnabled(pEstado);
      txtCodAgenda.setEnabled(pEstado);
      txtCodPersona.setEnabled(pEstado);
      txtFecha.setEnabled(pEstado);
      txtCodCatalogo.setEnabled(pEstado);
      txtRealizado.setEnabled(pEstado);
      
      btnAceptar.setVisible(pEstado);
   }
   
   // setRegistro
   public void setRegistro(Citas pRegistro) {
      registro = pRegistro;
      txtCodCita.setText(Integer.toString(registro.getCodCita()));
      txtCodAgenda.setText(Integer.toString(registro.getCodAgenda()));
      txtCodPersona.setText(Integer.toString(registro.getCodPersona()));
      txtFecha.setText(Fechas.toCadena(registro.getFecha()));
      txtCodCatalogo.setText(Integer.toString(registro.getCodCatalogo()));
      txtRealizado.setText(Boolean.toString(registro.getRealizado()));
   }
   
   public void reset() {
   }
   
   public String getIdVentana() {
      return ID_VENTANA;
   }
}

