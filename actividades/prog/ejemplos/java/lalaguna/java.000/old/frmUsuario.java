/*-----------------------------------------------
		Programa	:	AppsReportSIR.java
    Versión		:	0.00.01
    Fecha			:	26-03-2002
-----------------------------------------------*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.io.*;

//----------------------------
//Clase principal del proyecto
//----------------------------
public class frmUsuario extends JPanel 
{
	//--------------------
	//Componentes Visuales
	//--------------------
	static JFrame frame;
  static String windows = "Windows";
  static String motif = "Motif";
	static String metal= "Metal";

  JLabel				lbUsuario, lbPassword, lbBaseDatos, lbFichSalida, lbFichLog;
  JLabel				lbNodos, lbStatusBar;
  JTextField		edUsuario, edFichSalida, edFichLog;
  //JPasswordField	edPassword;
  JTextField		edPassword;

  JRadioButton	rbtnWindows, rbtnMotif, rbtnMetal;
 	JButton				btnConectar, btnInicio, btnSalir;
  JComboBox			comBaseDatos, comNodos;
  //JList					lstNodos;
  JPanel				panel1, panel2, panel3, panel4;
  
  //-----------------------
  //Componentes NO visuales
  //-----------------------
  Connection conSIR, conSACF;
		
   	
	//=======================
  //Constructor de la clase
  //=======================
  public frmUsuario() 
  {
  	panel1 		= new JPanel();
  	panel2 		= new JPanel();
  	panel3 		= new JPanel();
	  panel4 		= new JPanel();
 
		
		panel1.setBorder(BorderFactory.createTitledBorder("Botones"));

		panel2.setBorder(BorderFactory.createTitledBorder("Apariencia"));
		panel2.setLayout(new GridLayout(1,3));

		panel3.setBorder(BorderFactory.createTitledBorder("Parámetros"));
		panel3.setLayout(new GridLayout(6,2));

    //-------
		//Panel 1
		btnConectar = new JButton("Conectar con la BBDD");
		btnConectar.setMnemonic('c');
		btnConectar.setActionCommand("ConectarBBDD");

		btnInicio   = new JButton("Inicio");
    btnInicio.setMnemonic('i');
  	btnInicio.setActionCommand("GenerarSalida");

		btnSalir    = new JButton("Salir");
    btnSalir.setMnemonic('s');
  	btnSalir.setActionCommand("Salir");

		panel1.add(btnConectar);
		panel1.add(btnInicio);
		panel1.add(btnSalir);
 			
		ClaseBotonListener btnListener = new ClaseBotonListener();

		btnConectar.addActionListener(btnListener);
		btnInicio.addActionListener(btnListener);
		btnSalir.addActionListener(btnListener);

    //-------
		//Panel 2
  	rbtnWindows = new JRadioButton(windows);
    rbtnWindows.setMnemonic('w'); 
		rbtnWindows.setActionCommand("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

		rbtnMotif = new JRadioButton(motif);
    rbtnMotif.setMnemonic('o'); 
		rbtnMotif.setActionCommand("com.sun.java.swing.plaf.motif.MotifLookAndFeel");

		rbtnMetal = new JRadioButton(metal);
    rbtnMetal.setMnemonic('m'); 
		rbtnMetal.setActionCommand("javax.swing.plaf.metal.MetalLookAndFeel");

			
		// Group the radio buttons.
		ButtonGroup group = new ButtonGroup();
		group.add(rbtnWindows);
		group.add(rbtnMotif);
		group.add(rbtnMetal);

    // Register a listener for the radio buttons.
		ClaseRadioListener radListener = new ClaseRadioListener();
		rbtnWindows.addActionListener(radListener);
		rbtnMotif.addActionListener(radListener);
		rbtnMetal.addActionListener(radListener);

		panel2.add(rbtnWindows);
		panel2.add(rbtnMetal);
		panel2.add(rbtnMotif);
			
		//-------
		//Panel 3
		lbUsuario    = new JLabel("Usuario");
		lbPassword   = new JLabel("Clave");
		lbBaseDatos  = new JLabel("Base de Datos");
  	lbFichSalida = new JLabel("Fichero de Salida");
  	lbFichLog    = new JLabel("Fichero de LOG");
  	lbNodos      = new JLabel("Nodos");


		edUsuario    = new JTextField("Usuario",10);
		//edPassword   = new JPasswordField();
		edPassword   = new JTextField();


		//edPassword   = new JTextField("Password",10);
		//edPassword.setEchoChar('*');
		comBaseDatos = new JComboBox();
		comBaseDatos.addItem("Explotación");
		comBaseDatos.addItem("Pruebas");
		edFichSalida = new JTextField("Salida.txt",10);
  	edFichLog    = new JTextField("Salida.log",10);
		//lstNodos		 = new JList();
		comNodos     = new JComboBox();


		panel3.add(lbUsuario);   panel3.add(edUsuario);
		panel3.add(lbPassword);	 panel3.add(edPassword);
		panel3.add(lbBaseDatos); panel3.add(comBaseDatos);
		panel3.add(lbFichSalida); panel3.add(edFichSalida);
		panel3.add(lbFichLog); panel3.add(edFichLog);
		panel3.add(lbNodos); panel3.add(comNodos);

			
		//Panel 4
		lbStatusBar = new JLabel("Aplicación REPORT SIR iniciada");
		panel4.add(lbStatusBar);

		//lstSalida = new JList();
		//lstSalida.addItem("1");
		//panel4.add(lstSalida);
		
		//Añadir al panel principal
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		add(panel1);
		add(panel2);
		add(panel3);
		add(panel4);
		
	}


	//----------------------------------------------------
  //An ActionListener that listens to the radio buttons.
	//Clase privada al objeto AppsColiseo
  class ClaseRadioListener implements ActionListener 
  {
		public void actionPerformed(ActionEvent e) 
		{
	   	String lnfName = e.getActionCommand();

      try
      {
				UIManager.setLookAndFeel(lnfName);
				SwingUtilities.updateComponentTreeUI(frame);
				frame.pack();
      } 
	   	catch (Exception exc) 
	    {
				JRadioButton button = (JRadioButton)e.getSource();
				button.setEnabled(false);
				procUpdateState();
        System.err.println("No se puede cargar LookAndFeel {" + lnfName + "} !!!");
      }
	  }
  }


  class ClaseBotonListener implements ActionListener 
  {
		public void actionPerformed(ActionEvent e) 
		{
     	String strAccion = e.getActionCommand();
     	if (strAccion == "Salir")
  	  {	System.exit(0); 	}
  	  else if (strAccion == "ConectarBBDD")
  	  {
  	  	//Conectar con la BBDD
  	  	lbStatusBar.setText("Conectando con la BBDD...");
  	  	procConectarBBDD();
  			lbStatusBar.setText("Se han leido <"+ comNodos.getItemCount()+"> nodos.");
  	  }
  	  else if (strAccion == "GenerarSalida")
  	  {
  	  	//Generar fihceros de salida
  	  	lbStatusBar.setText("Generando fichero de salida...");
  	  	procGenerarSalida();
  			lbStatusBar.setText("Fichero <"+ edFichSalida.getText()+"> generado");
  	  }
      else
      {
      	//Se ejecuta al pulsar un botón.
      	lbStatusBar.setText(strAccion);
      }
		}
  }
    
  //==========================================================
  //Definición de los procedimientos de la clase AppsReportSIR
  //==========================================================

  //===============================
  //AppsReportSIR.procUpdateState()
  //===============================
    public void procUpdateState()
  {
  	//Actualiza el grupo de radio botones
	 	String lnfName = UIManager.getLookAndFeel().getClass().getName();
	 	if (lnfName.indexOf(metal) >= 0) 
	 	{ rbtnMetal.setSelected(true);} 
	 	else if (lnfName.indexOf(windows) >= 0) 
	 	{ rbtnWindows.setSelected(true);} 
	 	else if (lnfName.indexOf(motif) >= 0) 
	 	{ rbtnMotif.setSelected(true); } 
	 	else 
	 	{ System.err.println("ERROR: L&F desconocido {" + lnfName+"}"); }
  }
  
	//================================
  //AppsReportSIR.procConectarBBDD()
  //================================
  public void procConectarBBDD()
  {
  	String urlSIR, urlSACF ;
		Statement stmt;
  	
  	if ((String) comBaseDatos.getSelectedItem()=="Explotación")
  	{
  		urlSIR = new String("jdbc:odbc:MS Inventario de red de SC");
  		urlSACF = new String("jdbc:odbc:SGAF");
  	}
  	else
  	{
  		urlSIR = new String("jdbc:odbc:CPIR");
  		urlSACF = new String("jdbc:odbc:PGAF");
  	}
  	
	
		try
		{
			conSIR = DriverManager.getConnection(urlSIR, edUsuario.getText(), edPassword.getText());
	
			stmt = conSIR.createStatement();							
			ResultSet rs = stmt.executeQuery("SELECT cod_nodo FROM irnodos WHERE tmod_tipo='NA' ORDER BY cod_nodo");
	
			while (rs.next()) 
			{	comNodos.addItem(rs.getString("COD_NODO"));	}
			rs.close();
			stmt.close();
			conSIR.close();
			} 
		catch(SQLException ex) 
		{	System.err.println("SQLException: " + ex.getMessage());	}
	}
	
  //=================================
	//AppsReportSIR.procGenerarSalida()
	//=================================
  public void procGenerarSalida()
  {
  	
  	String urlSIR, urlSACF ;
  	StringBuffer v_nodo_cod_nodo 	= new StringBuffer(20);
  	StringBuffer v_ordenbocae			= new StringBuffer(20);
  	StringBuffer v_telefono				= new StringBuffer(20);

		Statement stmt1, stmt2, stmt3;
		int v_nreg_ok, v_nreg_err, v_nreg_tot;
  	
  	try
  	{
			//-------------------------------------------------------------------
  		//Paso 01: Abrir fichero de salida, el de log e inicializar variables
  		v_nreg_ok=v_nreg_err=v_nreg_tot=0;
  		
  	  PrintStream fichSalida = new PrintStream(new FileOutputStream(edFichSalida.getText()));
	    PrintStream fichLog    = new PrintStream(new FileOutputStream(edFichLog.getText()));

			fichSalida.println("COD_NODO@ORDENBOCAE");
			fichLog.println("Fichero de LOG\t\t:\t"+edFichLog.getText());
			fichLog.println("Nodo seleccionado\t:\t"+(String)comNodos.getSelectedItem());
  	
	  	if ((String) comBaseDatos.getSelectedItem()=="Explotación")
  		{
  			urlSIR = new String("jdbc:odbc:MS Inventario de red de SC");
  			urlSACF = new String("jdbc:odbc:SGAF");
	  	}
  		else
  		{
  			urlSIR = new String("jdbc:odbc:CPIR");
  			urlSACF = new String("jdbc:odbc:PGAF");
	  	}
	
			//--------------------------------------
	  	//Paso 02: Conectar con la base de datos
			conSIR = DriverManager.getConnection(urlSIR, edUsuario.getText(), edPassword.getText());
			stmt1 = conSIR.createStatement();							
			//stmt2 = conSIR.createStatement();							
			ResultSet rs1 = stmt1.executeQuery("SELECT * FROM irbocas_entrada WHERE nodo_cod_nodo='"+(String)comNodos.getSelectedItem()+"' AND ordenbocae<20 ORDER BY ordenbocae");
			
			
			while (rs1.next()) 
			{	
				//---------------------------------------------------------------
				//Paso 03: Para cada recursos internos de entrada del nodo hacer:
				v_nodo_cod_nodo.delete(0,20);
				v_nodo_cod_nodo.append(rs1.getString("nodo_cod_nodo"));
				v_ordenbocae.delete(0,20);
				v_ordenbocae.append(rs1.getString("ordenbocae"));

				//--------------------------------------------------------
				//Paso 04: Identificar el teléfono en la tabla IRTELEFONOS
				
				stmt2 = conSIR.createStatement();	
				ResultSet rs2 = stmt2.executeQuery("SELECT * FROM irrinternos_entrada WHERE bent_nodo_cod_nodo='"+v_nodo_cod_nodo+"' AND bent_ordenbocae='"+v_ordenbocae+"'");
				rs2.next();
				v_telefono.delete(0,20);
				v_telefono.append(rs2.getString("TELEFONO"));

				if (v_telefono.toString()!="null")
				{
					if (v_telefono.substring(1,3)=="CAD")
					{
						stmt3 = conSIR.createStatement();
						ResultSet rs3 = stmt3.executeQuery("select * from irtelefonos where id_red='"+v_telefono+"'");
						rs3.next();
						v_telefono.delete(0,20);
						v_telefono.append(rs3.getString("COD_TELEFONO"));
						rs3.close();
						stmt3.close();
					}
				}
				else
				{	v_telefono.append("NULL");}
				rs2.close();
				stmt2.close();
				
				
				//----------------------------------------------------------
				//Paso 05:Localizar producto de SACF activo con dicho número
				//Localizar el contrato/cliente con dicho producto
				//Localizar la cadena hasta llegar al PS
				//Identificar el PS y comparar con el del contrato
				//Registrar resultados en fichero de salida
				

				//-------------------------------
				//Paso 10:Contabilizar contadores
				fichSalida.println(v_nodo_cod_nodo+"@"+v_ordenbocae+"@"+v_telefono);
				v_nreg_tot++;
			}
			//Paso 12: Cerrar ficheros de salida y de log
			rs1.close();stmt1.close();
			conSIR.close();

			fichSalida.close();
			fichLog.println("Número registros OK\t:\t"+v_nreg_ok);
			fichLog.println("Número registros ERR\t:\t"+v_nreg_err);
			fichLog.println("Número registros Total\t:\t"+v_nreg_tot);
			fichLog.close();
		} 
		catch(Exception e) 
		{	System.err.println("Exception en procGenerarSalida: " + e.getMessage());}
		
	}
	
  //===================
  //Main de AppsColiseo
  //===================
  public static void main(String s[]) 
  {

		frmUsuario apps = new frmUsuario();
	
		frame = new JFrame("Ventana Principal");
    frame.setLocation(250 , 250 );
 	  frame.getContentPane().add("Center", apps);
 		//frame.setIcon(new ImageIcon("application.gif"));
 
		frame.addWindowListener
		(
			new WindowAdapter() 
			{public void windowClosing(WindowEvent e) {System.exit(0);}				}
		);
	
			
    //---------------------------------
		//Poner L&F del sistema por defecto
    
		try
    {
	   	UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	    SwingUtilities.updateComponentTreeUI(frame);
			frame.pack();
			frame.setVisible(true);

		} 
	  catch (Exception exc) 
	  {
	    System.err.println("Error loading L&F: " + exc);
	  }
	  
		//Actulizar el grupo de radioBotones
		apps.procUpdateState();
		
		try //Cargar el driver JDBC
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		}
		catch(java.lang.ClassNotFoundException e) 
		{
			System.err.print("ClassNotFoundException: ");
			System.err.println(e.getMessage());
		}

	}
}