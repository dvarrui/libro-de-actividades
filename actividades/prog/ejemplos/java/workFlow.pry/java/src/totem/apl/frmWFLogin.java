/*--------------------------------------------------------
   Fichero  :  frmWFLogin.java
   Fecha    :  22-08-2003
   Estado   :  OK-Desarrollo pendiente
   Futuro   :  permisos aplicaciones, resto aplicaciones
--------------------------------------------------------*/
package totem.apl;
import  totem.utl.*;
import  totem.lib.*;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.JOptionPane;


/**
 * Aplicación de acceso al flujo de trabajo TOTEM
 * @author David Vargas Ruiz
 * @version 0.8.1
 */
public class frmWFLogin extends JFrame 
{
   //--------------------
   //Componentes Visuales
   //--------------------
   JTextField	  edUsuario;
   JPasswordField edClave;
   JComboBox	  comEntorno;
   JButton	  btnAceptar,btnSalir,btnDesconectar,btnSalir2;
   JButton        btnBandeja,btnInformes,btnUsuarios,btnCircuitos,btnBaseDatos;
   JPanel	  panelConexion, panelVersion, panelSesion, panelAplicaciones;
   JMenuBar       menuBarra;

   //-----------------------
   //Componentes no visuales
   //-----------------------
   utlPropiedades prop;
   WFBD		  wfbd;
   WFSesion	  sesion;
   String	  url;
   
   frmWFBandejaTareas   appBandeja;
   JFrame         appInformes,appUsuarios,appCircuitos,appBaseDatos,appAyuda,appBloqueo;
   
   //=======================
   //Constructor de la clase
   //=======================
   public frmWFLogin() 
   {
      super("TOTEM");
      //setTitle("TOTEM");
      prop = new utlPropiedades();
      prop.setNombreFichero("totem.rec.frmWFLogin");
      url = new String("");

      crearPanelConexion();
      //this.setIconImage(new ImageIcon("resources.totem.gif"));
      this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
      this.getContentPane().add(panelConexion);

      /*
	buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.X_AXIS));
	buttonPane.add(Box.createHorizontalGlue());
	buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
       * */
      this.setLocation(prop.getInt("Coordenada_X1"),prop.getInt("Coordenada_Y1")); this.pack();this.setVisible(true);
      this.addWindowListener (new WindowAdapter() {public void windowClosing(WindowEvent e) {procBtnSalir();}});
   }

   
   class ClaseBotonListener implements ActionListener 
   {
      public void actionPerformed(ActionEvent e) 
      {
	 String strAccion = e.getActionCommand();
	 if (strAccion=="Salir")              { procBtnSalir();}
  	 else if (strAccion == "Aceptar")     { procBtnAceptar();}
  	 else if (strAccion == "Desconectar") { procBtnDesconectar();}
         else if (strAccion == "Bandeja")     { procBtnBandeja();}
	 else { System.out.println("<¿"+strAccion+"?>");}
      }
   }
   

   //============================================
   //crearPanelConexion() (Usuario,clave,entorno)
   //============================================
   private void crearPanelConexion()
   {
      JLabel lb1;
      
      panelConexion = new JPanel(); panelConexion.setLayout(new GridLayout(4,2));
      panelConexion.setBorder(BorderFactory.createEmptyBorder(40,30,40,30));
      panelConexion.setBorder(BorderFactory.createTitledBorder("Establecer conexión"));

      lb1=new JLabel("Usuario:");     panelConexion.add(lb1); 
      edUsuario=new JTextField("");   panelConexion.add(edUsuario); 
      lb1=new JLabel("Clave:");       panelConexion.add(lb1);
      edClave=new JPasswordField(""); panelConexion.add(edClave);
      lb1=new JLabel("Entorno:");     panelConexion.add(lb1);
      comEntorno = new JComboBox(); comEntorno.addItem(prop.getStringElement("Entornos",0));
      comEntorno.addItem(prop.getStringElement("Entornos",1)); comEntorno.addItem(prop.getStringElement("Entornos",2));
      panelConexion.add(comEntorno);
      
      btnAceptar = new JButton("Aceptar"); btnAceptar.setMnemonic('a');
      btnAceptar.setActionCommand("Aceptar"); btnAceptar.setToolTipText("Entrar en una nueva sesión");
      panelConexion.add(btnAceptar);
      btnSalir = new JButton("Salir");  btnSalir.setMnemonic('s');
      btnSalir.setActionCommand("Salir");  btnSalir.setToolTipText("Salir de la aplicación");
      panelConexion.add(btnSalir);
      
      ClaseBotonListener btnListener = new ClaseBotonListener();
      btnAceptar.addActionListener(btnListener);
      btnSalir.addActionListener(btnListener);
   }

   
   //===================
   //crearPanelVersion()
   //===================
   private void crearPanelVersion()
   {
      panelVersion = new JPanel(); panelVersion.setLayout(new GridLayout(4,1));
      panelVersion.setBorder(BorderFactory.createEmptyBorder(20,5,20,5));
      panelVersion.add(new JLabel("TOTEM",JLabel.CENTER));
      panelVersion.add(new JLabel("2003",JLabel.CENTER));
      //panelVersion.add(Box.createHorizontalGlue());
      panelVersion.add(new JLabel("Ver 0.0.0",JLabel.CENTER));
   }

   
   //==================
   //crearPanelSesion()
   //==================
   private void crearPanelSesion()
   {
      JLabel e1;
      ClaseBotonListener btnListener = new ClaseBotonListener();
      panelSesion	= new JPanel(); panelSesion.setLayout(new GridLayout(8,1));
      panelSesion.setBorder(BorderFactory.createTitledBorder("Sesión"));

      btnDesconectar= new JButton("Desconectar"); btnDesconectar.setMnemonic('d');
      btnDesconectar.setActionCommand("Desconectar"); btnDesconectar.addActionListener(btnListener);
      btnDesconectar.setToolTipText("Deconectar la sesión");
      btnSalir2    = new JButton("Salir");  btnSalir2.setMnemonic('s');
      btnSalir2.setActionCommand("Salir");  btnSalir2.addActionListener(btnListener);
      btnSalir2.setToolTipText("Salir de la aplicación");

      panelSesion.add(new JLabel("Usuario",JLabel.CENTER));
      e1=new JLabel(edUsuario.getText(),JLabel.CENTER); e1.setForeground(Color.blue);
      //lbCodUsuario.setBorder(BorderFactory.createLineBorder(Color.black));
      e1.setToolTipText(sesion.leeNombre()+" "+sesion.leeApellido1()+" "+sesion.leeApellido2());
      panelSesion.add(e1); 
      panelSesion.add(new JLabel("Sesión anterior",JLabel.CENTER));
      if (sesion.leeFechaLastLogin()!=null)
      { 
         //java.util.Date f = sesion.leeFechaLastLogin();
         //long h = f.getTime();
	 //e1=new JLabel(f+" "+h,JLabel.CENTER);e1.setForeground(Color.blue);
	 e1=new JLabel(sesion.leeFechaLastLogin().toString(),JLabel.CENTER);e1.setForeground(Color.blue);
	 panelSesion.add(e1); 
      }
      else
      { 
	 e1=new JLabel("[Primer acceso]",JLabel.CENTER);e1.setForeground(Color.blue);
	 panelSesion.add(e1); 
      }
      panelSesion.add(new JLabel("Entorno",JLabel.CENTER));
      e1=new JLabel((String) comEntorno.getSelectedItem(),JLabel.CENTER);e1.setForeground(Color.blue);
      panelSesion.add(e1);
      panelSesion.add(btnDesconectar); panelSesion.add(btnSalir2);
   }

   
   //========================
   //crearPanelAplicaciones()
   //========================
   private void crearPanelAplicaciones()
   {
      ClaseBotonListener btnListener = new ClaseBotonListener();
      panelAplicaciones	= new JPanel();
      panelAplicaciones.setBorder(BorderFactory.createTitledBorder("Aplicaciones"));
      panelAplicaciones.setLayout(new GridLayout(5,1));

      btnBandeja = new JButton("Bandeja Tareas"); btnBandeja.setMnemonic('t');
      btnBandeja.setActionCommand("Bandeja"); btnBandeja.addActionListener(btnListener);
      btnBandeja.setToolTipText("Entrar en la Bandeja de Tareas");
      btnInformes = new JButton("Informes"); btnInformes.setMnemonic('i');
      btnInformes.setActionCommand("Informes"); btnInformes.addActionListener(btnListener);
      btnInformes.setToolTipText("Entrar en la opción de Informes");
      btnUsuarios = new JButton("Usuarios"); btnUsuarios.setMnemonic('u');
      btnUsuarios.setActionCommand("Usuarios"); btnUsuarios.addActionListener(btnListener);
      btnUsuarios.setToolTipText("Gestión de los Usuarios y sus permisos");
      btnCircuitos = new JButton("Circuitos"); btnCircuitos.setMnemonic('c');
      btnCircuitos.setActionCommand("Circuitos"); btnCircuitos.addActionListener(btnListener);
      btnCircuitos.setToolTipText("Definir los Circuitos y las tareas");
      btnBaseDatos = new JButton("Base Datos"); btnBaseDatos.setMnemonic('b');
      btnBaseDatos.setActionCommand("BaseDatos"); btnBaseDatos.addActionListener(btnListener);
      btnBaseDatos.setToolTipText("Gestión de la Base de Datos");
	 
      if (sesion.tienePermiso("APP.BANDEJA")) panelAplicaciones.add(btnBandeja); 
      if (sesion.tienePermiso("APP.INFORMES")) panelAplicaciones.add(btnInformes);  
      //panelAplicaciones.add(Box.createRigidArea(new Dimension(10, 0)));
      if (sesion.tienePermiso("APP.USUARIOS")) panelAplicaciones.add(btnUsuarios); 
      if (sesion.tienePermiso("APP.CIRCUITOS")) panelAplicaciones.add(btnCircuitos); 
      if (sesion.tienePermiso("APP.BASEDATOS")) panelAplicaciones.add(btnBaseDatos);
   }
   

   //============
   // crearMenu()
   //============
   private void crearMenu()
   {
      ClaseBotonListener btnListener = new ClaseBotonListener();
      JMenu          menu;
      JMenuItem      menuItem;

      //Crear los menús
      menuBarra = new JMenuBar(); 
      menu = new JMenu("Opciones");            menuBarra.add(menu);
      
      /*menuItem = new JMenuItem("Ayuda");       menuItem.setActionCommand("Ayuda"); 
      menuItem.addActionListener(btnListener); menu.add(menuItem);      
      menu.addSeparator();
      menuItem = new JMenuItem("Bloquear");    menuItem.setActionCommand("Bloquear"); 
      menuItem.addActionListener(btnListener); menu.add(menuItem);
      menuItem = new JMenuItem("Desbloquear"); menuItem.setActionCommand("Desbloquear"); 
      menuItem.addActionListener(btnListener); menu.add(menuItem);
      menu.addSeparator();
      menuItem = new JMenuItem("Minimizar");   menuItem.setActionCommand("Minimizar");
      menuItem.addActionListener(btnListener); menu.add(menuItem);
      menuItem = new JMenuItem("Maximizar");   menuItem.setActionCommand("Maximizar"); 
      menuItem.addActionListener(btnListener); menu.add(menuItem);
      menu.addSeparator();*/
      menuItem = new JMenuItem("Desconectar"); menuItem.setActionCommand("Desconectar"); 
      menuItem.addActionListener(btnListener); menu.add(menuItem);
      menuItem = new JMenuItem("Salir");       menuItem.setActionCommand("Salir");  
      menuItem.addActionListener(btnListener); menu.add(menuItem);
   }
   
   
   //=================
   // procBtnAceptar()
   //================= 
   private boolean procBtnAceptar()
   {
      String driver,c1,c2;
      try
      {
	 this.setCursor(new Cursor(java.awt.Cursor.WAIT_CURSOR));
         driver = prop.getString("driver");
	 c1 = prop.getString("c1");
	 c2 = prop.getStringElement("c2",comEntorno.getSelectedIndex());
	 
	 if (!url.equals(driver+c1+c2))
	 {
	    url = new String(driver+c1+c2); 
	    wfbd = new WFBD(); wfbd.inicializar(url,"","","entorno"); 
	    //wfbd = new WFBD(url,"",""); 
	    sesion = wfbd.crearSesion(); 
	 }
	 else if(wfbd==null||sesion==null) { System.exit(0); }

	 sesion.login(edUsuario.getText(),new String(edClave.getPassword()));
	 if (sesion.estaLogin())
         {
	    crearPanelSesion();crearPanelAplicaciones(); crearPanelVersion();crearMenu();
	    
            this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));	 
	    this.getContentPane().add(panelAplicaciones);
            this.getContentPane().add(panelSesion);
	    this.getContentPane().add(panelVersion);
            setJMenuBar(menuBarra);
	    this.remove(panelConexion);
            this.setVisible(false); this.setLocation(prop.getInt("Coordenada_X2"),prop.getInt("Coordenada_Y2")); 
	    this.pack(); this.setVisible(true);
	 }
	 this.setCursor(new Cursor(java.awt.Cursor.DEFAULT_CURSOR));
	 return true;
      }
      catch(Exception exc)
      { 
         // JOptionPane.showMessageDialog(frame,"Callejero PLC preprocesado."); 
         //  JOptionPane.showMessageDialog(frame,"Se ha producido un error!","ERROR",JOptionPane.ERROR_MESSAGE);
	 System.err.println("frmWFLogin.BtnAceptar():"+exc);
      }
      return false;	   
   }

   
   //=====================
   // procBtnDesconectar()
   //=====================
   private boolean procBtnDesconectar()
   {
      try
      {
	 if (appBandeja!=null) appBandeja.procBtnSalir(); //setVisible(false);
	 appBandeja=null; sesion.logout();url=new String("");
	 this.remove(panelAplicaciones);this.remove(panelSesion);this.remove(panelVersion);setJMenuBar(null);
         this.getContentPane().setLayout(new BoxLayout(this.getContentPane(),BoxLayout.Y_AXIS));
	 crearPanelConexion();this.getContentPane().add(panelConexion);
         this.setVisible(false); this.setLocation(prop.getInt("Coordenada_X1"),prop.getInt("Coordenada_Y1"));
	 this.pack(); this.setVisible(true);
	 return true;
      }
      catch(Exception e)
      { 
	 System.err.println("ERROR procBtnDesconectar():"+e);
      }
      return false;	   
   }


   //===============
   // procBtnSalir()
   //===============
   private boolean procBtnSalir()
   {
      try
      {
         this.setCursor(new Cursor(java.awt.Cursor.WAIT_CURSOR));
	 if (appBandeja!=null) {appBandeja.procBtnSalir(); appBandeja=null;}
	 if (appInformes!=null) appInformes=null;
	 if (appUsuarios!=null) appUsuarios=null;
	 if (appCircuitos!=null) appCircuitos=null;
	 if (appBaseDatos!=null) appBaseDatos=null;
	 if (appAyuda!=null) appAyuda=null;
         if (appBloqueo!=null) appBloqueo=null;
   	 if (sesion!=null)  {   if(sesion.estaLogin()) sesion.logout();  sesion=null;  }
	 if (wfbd!=null) {wfbd.desconectar();wfbd=null;url=null; }
         this.setCursor(new Cursor(java.awt.Cursor.DEFAULT_CURSOR));
         System.exit(0);
	 return true;
      }
      catch(Exception e) {System.err.println("ERROR procBtnSalir():"+e);}
      return false;	   
   }


   //=================
   // procBtnBandeja()
   //=================
   private boolean procBtnBandeja()
   {
      this.setCursor(new Cursor(java.awt.Cursor.WAIT_CURSOR));
      if (appBandeja==null) { appBandeja = new frmWFBandejaTareas(this,sesion);}
      else {appBandeja.setVisible(true);}
      this.setCursor(new Cursor(java.awt.Cursor.DEFAULT_CURSOR));
      return true;
   }


   //================
   //Main de frmLogin
   //================
   public static void main(String s[])   { frmWFLogin frmlogin = new frmWFLogin();  }
}  //Fin de la definición de la clase frmWFLogin



