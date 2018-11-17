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
import david.proyecto.entidades.Usuarios;
import david.proyecto.util.Fechas;
import david.proyecto.swing.IVentana;

// clase
public class FrmEditarUsuarios extends javax.swing.JInternalFrame implements ActionListener, IVentana {
   static final long serialVersionUID=1; //Evitar Warnings
   
   public static final String ID_VENTANA="usuarios.editar";
   public static int ANCHO = 471;
   public static int ALTO = 296;
   
   // atributos
   private JButton btnAceptar;
   private JButton btnSalir;
   private JScrollPane jScrollPane1;
   private JPanel pnlBotones;
   private JPanel pnlDatos;
   private JPanel pnlVentana;

   private JLabel lblCodUsuario;
   private JTextField txtCodUsuario;
   private JLabel lblDesUsuario;
   private JTextField txtDesUsuario;
   private JLabel lblPassword;
   private JTextField txtPassword;
   private JLabel lblCodPerfil;
   private JTextField txtCodPerfil;
   private JLabel lblFecDesde;
   private JTextField txtFecDesde;
   private JLabel lblFecHasta;
   private JTextField txtFecHasta;
   private JLabel lblActivo;
   private JTextField txtActivo;
   
   private Usuarios registro;

   // constructor
   public FrmEditarUsuarios() {
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
                     btnAceptar.setActionCommand("usuarios.nuevo");
                     btnAceptar.addActionListener(this);
                  }
                  {
                     btnSalir = new JButton();
                     pnlBotones.add(btnSalir);
                     btnSalir.setText("Salir");
                     btnSalir.setActionCommand("usuarios.salir");
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
                        lblCodUsuario = new JLabel();
                        pnlDatos.add(lblCodUsuario);
                        lblCodUsuario.setText("CODIGO");
                        lblCodUsuario.setBounds(20, 20, 200,20);
                     }
                     {
                        txtCodUsuario = new JTextField();
                        pnlDatos.add(txtCodUsuario);
                        txtCodUsuario.setBounds(220, 20, 200,20);
                     }
                     {
                        lblDesUsuario = new JLabel();
                        pnlDatos.add(lblDesUsuario);
                        lblDesUsuario.setText("DESCRIPCION");
                        lblDesUsuario.setBounds(20, 40, 200,20);
                     }
                     {
                        txtDesUsuario = new JTextField();
                        pnlDatos.add(txtDesUsuario);
                        txtDesUsuario.setBounds(220, 40, 200,20);
                     }
                     {
                        lblPassword = new JLabel();
                        pnlDatos.add(lblPassword);
                        lblPassword.setText("CLAVE");
                        lblPassword.setBounds(20, 60, 200,20);
                     }
                     {
                        txtPassword = new JTextField();
                        pnlDatos.add(txtPassword);
                        txtPassword.setBounds(220, 60, 200,20);
                     }
                     {
                        lblCodPerfil = new JLabel();
                        pnlDatos.add(lblCodPerfil);
                        lblCodPerfil.setText("codPerfil");
                        lblCodPerfil.setBounds(20, 80, 200,20);
                     }
                     {
                        txtCodPerfil = new JTextField();
                        pnlDatos.add(txtCodPerfil);
                        txtCodPerfil.setBounds(220, 80, 200,20);
                     }
                     {
                        lblFecDesde = new JLabel();
                        pnlDatos.add(lblFecDesde);
                        lblFecDesde.setText("DESDE");
                        lblFecDesde.setBounds(20, 100, 200,20);
                     }
                     {
                        txtFecDesde = new JTextField();
                        pnlDatos.add(txtFecDesde);
                        txtFecDesde.setBounds(220, 100, 200,20);
                     }
                     {
                        lblFecHasta = new JLabel();
                        pnlDatos.add(lblFecHasta);
                        lblFecHasta.setText("HASTA");
                        lblFecHasta.setBounds(20, 120, 200,20);
                     }
                     {
                        txtFecHasta = new JTextField();
                        pnlDatos.add(txtFecHasta);
                        txtFecHasta.setBounds(220, 120, 200,20);
                     }
                     {
                        lblActivo = new JLabel();
                        pnlDatos.add(lblActivo);
                        lblActivo.setText("activo");
                        lblActivo.setBounds(20, 140, 200,20);
                     }
                     {
                        txtActivo = new JTextField();
                        pnlDatos.add(txtActivo);
                        txtActivo.setBounds(220, 140, 200,20);
                     }
                     
                     this.setResizable(true);
                     this.setIconifiable(true);
                     this.setMaximizable(true);
                     this.setSelected(true);
                     
                     // Título
                     this.setTitle("Editar Registro - Usuarios");
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
      Usuarios reg = new Usuarios();
      IAlmacen almacen;
      try {
         almacen = FabricaAlmacenes.createAlmacen(FabricaAlmacenes.USUARIOS,FabricaAlmacenes.TIPO_TXT);
         almacen.open();
         
         reg.setCodUsuario(Integer.parseInt(txtCodUsuario.getText()));
         reg.setDesUsuario(txtDesUsuario.getText());
         reg.setPassword(txtPassword.getText());
         reg.setCodPerfil(Integer.parseInt(txtCodPerfil.getText()));
         reg.setFecDesde(Fechas.fromCadena(txtFecDesde.getText()));
         reg.setFecHasta(Fechas.fromCadena(txtFecHasta.getText()));
         reg.setActivo(Boolean.parseBoolean(txtActivo.getText()));
         
         almacen.add(reg);
         almacen.close();
      } catch(Exception e) {
          e.printStackTrace();
      }
   }
   
   // setEditable
   public void setEditable(boolean pEstado) {
      txtCodUsuario.setEnabled(pEstado);
      txtDesUsuario.setEnabled(pEstado);
      txtPassword.setEnabled(pEstado);
      txtCodPerfil.setEnabled(pEstado);
      txtFecDesde.setEnabled(pEstado);
      txtFecHasta.setEnabled(pEstado);
      txtActivo.setEnabled(pEstado);
      
      btnAceptar.setVisible(pEstado);
   }
   
   // setRegistro
   public void setRegistro(Usuarios pRegistro) {
      registro = pRegistro;
      txtCodUsuario.setText(Integer.toString(registro.getCodUsuario()));
      txtDesUsuario.setText(registro.getDesUsuario());
      txtPassword.setText(registro.getPassword());
      txtCodPerfil.setText(Integer.toString(registro.getCodPerfil()));
      txtFecDesde.setText(Fechas.toCadena(registro.getFecDesde()));
      txtFecHasta.setText(Fechas.toCadena(registro.getFecHasta()));
      txtActivo.setText(Boolean.toString(registro.getActivo()));
   }
   
   public void reset() {
   }
   
   public String getIdVentana() {
      return ID_VENTANA;
   }
}

