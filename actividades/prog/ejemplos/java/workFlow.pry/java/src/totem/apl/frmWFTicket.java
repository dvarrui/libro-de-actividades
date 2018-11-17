/*-----------------------------------------------
   Fichero     :  frmWFTicket.java
   Fecha       :  10-06-2003
   Estado      :  Desarrollo
   Descripción :  Consulta y modificación de la información asociada al Ticket
-----------------------------------------------*/
package totem.apl;
import  totem.utl.*;
import  totem.lib.*;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.JOptionPane;

import javax.swing.JTree;
import javax.swing.table.TableColumn;
import javax.swing.table.TableCellRenderer;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import javax.swing.tree.DefaultTreeCellRenderer;



/**
 * Aplicación para la gestión de los tiquets
 *
 * @author David Vargas Ruiz
 * @version 0.8.0
 */
class frmWFTicket extends JFrame
{
   //--------------------
   //Componentes Visuales
   //--------------------
   JPanel	  panelPrincipal, panelBotones;
   JTabbedPane    panelPestanas;
   JMenuBar       menuBarra;
      
   //Componentes no visuales
   utlPropiedades     prop;
   frmWFBandejaTareas frmbandeja;
   WFTicket	      ticket;
   WFSesion           sesion;
   WFRegistrarSesion  logger;
   
   
   //=======================
   //Constructor de la clase
   //=======================
   frmWFTicket(frmWFBandejaTareas p_frmbandeja, WFTicket p_ticket) 
   {
      prop = new utlPropiedades();
      prop.setNombreFichero("totem.rec.frmWFTicket");
      frmbandeja=p_frmbandeja; ticket=p_ticket; sesion=frmbandeja.leeSesion();
      crearContentPane();
      this.addWindowListener (new WindowAdapter() {public void windowClosing(WindowEvent e) {procBtnSalir();}});
      logger=new WFRegistrarSesion(sesion);logger.ponModulo("frmWFTicket");
   }

  
   class ClaseBotonListener implements ActionListener 
   {
      public void actionPerformed(ActionEvent e) 
      {
	 String strAccion = e.getActionCommand();
	 if (strAccion=="Salir")                   { procBtnSalir();}
  	 else if (strAccion=="Aceptar")            { procBtnAceptar();}
	 else if (strAccion=="Cancelar")           { procBtnSalir();}
	 else if (strAccion=="Grabar")             { procBtnAceptar();}
	 else if (strAccion=="MostrarPanelBasico") { procBtnMostrarPanelBasico();}
	 else if (strAccion=="OcultarPanelBasico") { procBtnOcultarPanelBasico();}
	 else if (strAccion=="MostrarBotonera")    { procBtnMostrarBotonera();}
	 else if (strAccion=="OcultarBotonera")    { procBtnOcultarBotonera();}
	 
	 else	 { System.out.println("<¿"+strAccion+"?>");}
      };
   }

   
   //===================
   // crearContentPane()
   //===================
   private void crearContentPane()
   {
      String str;
      if (ticket.leeCodTicketPadre()==0) {str= new String("(P)");}
      else {str= new String("(H)");}
      setTitle("Tiquet - "+ticket.leeCodTicket()+"   "+str+",   "
         +ticket.leeFechaCreacion()+",   "+ticket.leeDesTarea()+"   ["+ticket.leeDesTipoTicket()+"]");

      if(panelPrincipal!=null)
      { this.remove(panelPrincipal); this.remove(panelPestanas); this.remove(panelBotones);}

      crearMenu();crearPanelPrincipal();crearPanelPestanas();crearPanelBotones();
      //this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
      this.getContentPane().add(panelPrincipal, BorderLayout.WEST);
      this.getContentPane().add(panelPestanas, BorderLayout.CENTER);
      this.getContentPane().add(panelBotones, BorderLayout.SOUTH);
      
      if (sesion.leeValorDeOpcion("APL.TIQUET.VER-PANEL-BOTONES").equals("S")) panelBotones.setVisible(true);
      if (sesion.leeValorDeOpcion("APL.TIQUET.VER-PANEL-BASICO").equals("S")) panelPrincipal.setVisible(true);
      
      this.setVisible(false);
      this.setLocation(prop.getInt("Coordenada_X"),prop.getInt("Coordenada_Y"));
      this.setSize(prop.getInt("Tamano_Largo"),prop.getInt("Tamano_Alto")); 
      this.setVisible(true);
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
      menuBarra = new JMenuBar(); setJMenuBar(menuBarra);
      menu = new JMenu("Acciones");              menuBarra.add(menu);
      menuItem = new JMenuItem("Grabar");       menuItem.setActionCommand("Grabar"); 
      menuItem.addActionListener(btnListener);  menu.add(menuItem);
      menuItem = new JMenuItem("Cancelar");     menuItem.setActionCommand("Cancelar"); 
      menuItem.addActionListener(btnListener);  menu.add(menuItem);
      
      menu = new JMenu("Ver");                             menuBarra.add(menu);
      menuItem = new JMenuItem("Mostrar panel de datos básicos"); menuItem.setActionCommand("MostrarPanelBasico"); 
      menuItem.addActionListener(btnListener);                    menu.add(menuItem);
      menuItem = new JMenuItem("Ocultar panel de datos básicos"); menuItem.setActionCommand("OcultarPanelBasico");
      menuItem.addActionListener(btnListener);              menu.add(menuItem);
      menu.addSeparator();
      menuItem = new JMenuItem("Mostrar botonera"); menuItem.setActionCommand("MostrarBotonera"); 
      menuItem.addActionListener(btnListener);      menu.add(menuItem);
      menuItem = new JMenuItem("Ocultar botonera"); menuItem.setActionCommand("OcultarBotonera");
      menuItem.addActionListener(btnListener);      menu.add(menuItem);

      menu = new JMenu("Salir");                            menuBarra.add(menu);
      menuItem = new JMenuItem("Salir de la ventana");   menuItem.setActionCommand("Salir");  
      menuItem.addActionListener(btnListener);              menu.add(menuItem);
      //menuItem = new JMenuItem("Cancelar cambios y salir"); menuItem.setActionCommand("Salir");  
      //menuItem.addActionListener(btnListener);              menu.add(menuItem);
   }

   
   //======================
   // crearPanelPrincipal()
   //======================
   private void crearPanelPrincipal()
   {
      JLabel lb1;
      panelPrincipal	= new JPanel();
      panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
      panelPrincipal.setBorder(BorderFactory.createTitledBorder("Datos básicos"));
      panelPrincipal.setLayout(new GridLayout(16,1));

      panelPrincipal.add(new JLabel("Código",JLabel.CENTER)); 
      lb1=new JLabel(" "+ticket.leeCodTicket(),JLabel.CENTER);lb1.setForeground(Color.blue); panelPrincipal.add(lb1);
      panelPrincipal.add(new JLabel("Código Padre",JLabel.CENTER)); 
      lb1=new JLabel(" "+ticket.leeCodTicketPadre(),JLabel.CENTER);lb1.setForeground(Color.blue); panelPrincipal.add(lb1);
      panelPrincipal.add(new JLabel("Fecha creación",JLabel.CENTER)); 
      lb1=new JLabel(" "+ticket.leeFechaCreacion(),JLabel.CENTER);lb1.setForeground(Color.blue); panelPrincipal.add(lb1);
      
      panelPrincipal.add(new JLabel("Tipo",JLabel.CENTER)); 
      lb1=new JLabel(
         (ticket.leeDesTipoTicket().length()>18?ticket.leeDesTipoTicket().substring(0,18):ticket.leeDesTipoTicket())
	  ,JLabel.CENTER);
      lb1.setForeground(Color.blue);
      lb1.setToolTipText(ticket.leeDesTipoTicket());  panelPrincipal.add(lb1);
      panelPrincipal.add(new JLabel("Tarea",JLabel.CENTER)); 
      lb1=new JLabel((ticket.leeDesTarea().length()>18?ticket.leeDesTarea().substring(0,18):ticket.leeDesTarea()),
			      JLabel.CENTER);
      lb1.setForeground(Color.blue);
      lb1.setToolTipText(ticket.leeDesTarea()); panelPrincipal.add(lb1);
      
      panelPrincipal.add(new JLabel("Fecha UM",JLabel.CENTER)); 
      lb1=new JLabel(" "+ticket.leeFecUltMod(),JLabel.CENTER);lb1.setForeground(Color.blue);
      panelPrincipal.add(lb1);
      panelPrincipal.add(new JLabel("Usuario UM",JLabel.CENTER)); 
      lb1=new JLabel(ticket.leeCodUsuario(),JLabel.CENTER);lb1.setForeground(Color.blue);
      panelPrincipal.add(lb1);
      
      panelPrincipal.setVisible(false);
   }


   //=====================
   // crearPanelPestanas()
   //=====================
   private void crearPanelPestanas()
   {
      panelPestanas = new JTabbedPane();
      panelPestanas.addTab("Datos básicos",null,crearPanelDatosBasicos(),"Datos básicos del tiquet");
      panelPestanas.addTab("Valores actuales",null,crearPanelValoresActuales(),"Valores actuales del tiquet");
      panelPestanas.addTab("Hist. Transiciones",null,crearPanelHistTransiciones(),"Ver histórico de transiciones");
      panelPestanas.addTab("Explorar",null,crearPanelExplorar(),"Ver información completa");
   }
   

   private JPanel crearPanelExplorar()
   {
      JPanel panelArbol = new JPanel();panelArbol.setLayout(new BoxLayout(panelArbol, BoxLayout.Y_AXIS));
      panelArbol.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

      DefaultMutableTreeNode nodo_raiz = new DefaultMutableTreeNode("Información completa");
      crearArbol(nodo_raiz);

      JTree MiArbol = new JTree(nodo_raiz);
      MiArbol.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
      JScrollPane MiScrollPane = new JScrollPane(MiArbol);
      panelArbol.add(MiScrollPane,BorderLayout.CENTER);
      return panelArbol;
   }

   
   private void crearArbol(DefaultMutableTreeNode nodo_raiz) 
   {
      try
      {
         DefaultMutableTreeNode nivel2 = null;
         DefaultMutableTreeNode nivel3 = null;
         DefaultMutableTreeNode nivel4 = null;
         DefaultMutableTreeNode nivel5 = null;

         nivel2 = new DefaultMutableTreeNode("Datos Básicos"); 	 nodo_raiz.add(nivel2);
         nivel3 = new DefaultMutableTreeNode("Código = "+ticket.leeCodTicket());            nivel2.add(nivel3);
         nivel3 = new DefaultMutableTreeNode("Cód. Padre = "+ticket.leeCodTicketPadre()); nivel2.add(nivel3);
         nivel3 = new DefaultMutableTreeNode(("Fecha_Creación = "+ticket.leeFechaCreacion()));  nivel2.add(nivel3);
         nivel3 = new DefaultMutableTreeNode("Tipo = "+ticket.leeDesTipoTicket() );             nivel2.add(nivel3);
         nivel3 = new DefaultMutableTreeNode("Tarea = "+ticket.leeDesTarea());             nivel2.add(nivel3);
         nivel3 = new DefaultMutableTreeNode("Fec. Ult. Mod. = "+ticket.leeFecUltMod());   nivel2.add(nivel3);
         nivel3 = new DefaultMutableTreeNode("Usuario = "+ticket.leeCodUsuario());     nivel2.add(nivel3);
      
         nivel2 = new DefaultMutableTreeNode("Líneas de información ("+ticket.leeNumLineas()+")");
	 nodo_raiz.add(nivel2);
         for(int i=0; i<ticket.leeNumLineas();i++)
         {
	    WFLinea linea = ticket.leeLinea(i);
            nivel3 = new DefaultMutableTreeNode
		    ("[C."+linea.leeCodLinea()+"] "+linea.leeDesConcepto()+" ("+linea.leeNumValores()+")");  
	    nivel2.add(nivel3);
            //nivel4 = new DefaultMutableTreeNode("Código = "+linea.leeCodLinea());        nivel3.add(nivel4);
            //nivel4 = new DefaultMutableTreeNode("Valores ("+linea.leeNumValores()+")");  nivel3.add(nivel4);
            for(int j=0; j<linea.leeNumValores();j++)
            {
	       WFValor valor = linea.leeValor(j);
               nivel4 = new DefaultMutableTreeNode(valor.leeDesCampo()+" = "+valor.leeValor()); nivel3.add(nivel4);
	    }
         }
         nivel2 = new DefaultMutableTreeNode("Transiciones posibles ("+ticket.leeNumTransiciones()+")");
	 nodo_raiz.add(nivel2);
         for(int i=0; i<ticket.leeNumTransiciones();i++)
         {
	    WFTransicion t=ticket.leeTransicion(i);
	    nivel3 = new DefaultMutableTreeNode("[C."+t.leeCodTransicion()+"] "+t.leeDesTarea2());  nivel2.add(nivel3);
	    //nivel4 = new DefaultMutableTreeNode("Código = "+t.leeCodTransicion());        nivel3.add(nivel4);
	    nivel4 = new DefaultMutableTreeNode("Tipo   = "+t.leeDesTipoTransicion());  nivel3.add(nivel4);
         }

         nivel2 = new DefaultMutableTreeNode("Histórico de transiciones ("+ticket.leeNumHistTransiciones()+")"); 
	 nodo_raiz.add(nivel2);
         for(int i=0; i<ticket.leeNumHistTransiciones();i++)
         {
	    WFHistTransicion h=ticket.leeHistTransicion(i);
	    nivel3 = new DefaultMutableTreeNode("Fecha = "+h.leeFecha());  nivel2.add(nivel3);
	    nivel4 = new DefaultMutableTreeNode("Tarea = "+h.leeDesTarea());        nivel3.add(nivel4);
	    WFTransicion t=h.leeTransicion();
	    nivel4 = new DefaultMutableTreeNode("[C."+t.leeCodTransicion()+"] "+t.leeDesTarea1()+"->"+t.leeDesTarea2());  
	                nivel3.add(nivel4);
	    //nivel5 = new DefaultMutableTreeNode("Código = "+t.leeCodTransicion());        nivel4.add(nivel5);
	    nivel5 = new DefaultMutableTreeNode("Tipo   = "+t.leeDesTipoTransicion());  nivel4.add(nivel5);
	    nivel4 = new DefaultMutableTreeNode("Apunte = "+h.leeApunte());  nivel3.add(nivel4);
	    nivel4 = new DefaultMutableTreeNode("Usuario = "+h.leeCodUsuario());  nivel3.add(nivel4);
         }

         nivel2 = new DefaultMutableTreeNode("Histórico de líneas("+ticket.leeNumLineas()+")"); nodo_raiz.add(nivel2);
         for(int i=0;i<ticket.leeNumLineas();i++)
         {
            WFLinea l= ticket.leeLinea(i);
            nivel3 = new DefaultMutableTreeNode
		    ("[C."+l.leeCodLinea()+"] "+l.leeDesConcepto()+" ("+l.leeNumValores()+")");  nivel2.add(nivel3);
            for(int j=0;j<l.leeNumValores();j++)
            {
	       WFValor v=l.leeValor(j);
    	       nivel4 = new DefaultMutableTreeNode(v.leeDesCampo()+" ("+v.leeNumHistValores()+")"); nivel3.add(nivel4);
	       for(int k=0; k<v.leeNumHistValores();k++)
	       {
		  WFHistValor hv=v.leeHistValor(k);
		  nivel5 = new DefaultMutableTreeNode
			  ("Valor = "+hv.leeValor()+" , Fecha = "+hv.leeFecha()+" , Usuario = "+hv.leeCodUsuario()); 
		  nivel4.add(nivel5);
	       }
            }
         }
      }
      catch(Exception e)
      { System.err.println("frmWFTiquet.crearArbol():"+e);}
   }


   //=========================
   // crearPanelDatosBasicos()
   //=========================
   private JPanel crearPanelDatosBasicos()
   {
      JPanel panelTabla= new JPanel(); panelTabla.setLayout(new BoxLayout(panelTabla, BoxLayout.Y_AXIS));
      panelTabla.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

      Object[][] misDatos = new Object[10][2];
      misDatos[0][0]=new String("Cod. tiquet");        misDatos[0][1]=new Integer(ticket.leeCodTicket());  
      misDatos[1][0]=new String("Cod. Padre");         misDatos[1][1]=new Integer(ticket.leeCodTicketPadre()); 
      misDatos[2][0]=new String("Fecha de creación");  misDatos[2][1]=ticket.leeFechaCreacion();
      misDatos[3][0]=new String("Tipo");               misDatos[3][1]=ticket.leeDesTipoTicket();
      misDatos[4][0]=new String("Tarea");              misDatos[4][1]=ticket.leeDesTarea();
      misDatos[5][0]=new String("Apunte");             misDatos[5][1]=ticket.leeApunte();
      misDatos[6][0]=new String("Fec. Ult. Mod.");     misDatos[6][1]=ticket.leeFecUltMod();
      misDatos[7][0]=new String("Usuario");            misDatos[7][1]=ticket.leeCodUsuario();
      misDatos[8][0]=new String("(Número líneas información)");  misDatos[8][1]=new Integer(ticket.leeNumLineas());
      misDatos[9][0]=new String("(Número transiciones posibles)");  misDatos[9][1]=new Integer(ticket.leeNumTransiciones());
      String[] misColumnas = {"Campo","Valor"};
      JTable miTabla = new JTable(misDatos, misColumnas);
      
      /*      
      class ClaseTablaListener implements ActionListener 
      {
         public void actionPerformed(ActionEvent e) 
         {
	    String strAccion = e.getActionCommand();
	    System.out.println("DVR"+strAccion);
         };
      }
      ClaseTablaListener tablaListener = new ClaseTablaListener();
      
      class JTextFieldEditor extends JTextField
      {
         JTextFieldEditor(JTable miTabla){ super(); }
      }
      TableColumn columna = miTabla.getColumnModel().getColumn(2);
      //JComboBox j = new JComboBox();      j.addItem("¿?");
      JTextField j = new JTextField();
      j.addActionListener(tablaListener);
      //JTextField j=new JTextField("Ok");
      columna.setCellEditor(new DefaultCellEditor(j));
      */
      JScrollPane miScrollPane = new JScrollPane(miTabla);
      panelTabla.add(miScrollPane, BorderLayout.CENTER);
      return panelTabla;
   }


   //=============================
   // crearPanelHistTransiciones()
   //=============================
   private JPanel crearPanelHistTransiciones()
   {
      JPanel panelTabla= new JPanel(); panelTabla.setLayout(new BoxLayout(panelTabla, BoxLayout.Y_AXIS));
      panelTabla.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

      Object[][] misDatos = new Object[ticket.leeNumHistTransiciones()][4];
      for(int i=0;i<ticket.leeNumHistTransiciones();i++)
      {
         WFHistTransicion ht= ticket.leeHistTransicion(i);
	 WFTransicion      t=ht.leeTransicion();
         misDatos[i][0]=t.leeDesTarea2();	 misDatos[i][1]=ht.leeApunte();
	 misDatos[i][2]=ht.leeFecha();           misDatos[i][3]=ht.leeCodUsuario();
      }
      String[] misColumnas = {"Tarea","Apunte","Fecha","Usuario"};
      JTable miTabla = new JTable(misDatos, misColumnas);

      TableColumn columna = null;
      for (int i = 0; i < 4; i++) 
      {
         columna = miTabla.getColumnModel().getColumn(i);
         if (i<2) {  columna.setPreferredWidth(120); }
	 else  {  columna.setPreferredWidth(10); }
      }

      JScrollPane miScrollPane = new JScrollPane(miTabla);
      panelTabla.add(miScrollPane, BorderLayout.CENTER);
      return panelTabla;
   }


   //============================
   // crearPanelValoresActuales()
   //============================
   private JPanel crearPanelValoresActuales()
   {
      JPanel panelTabla= new JPanel(); panelTabla.setLayout(new BoxLayout(panelTabla, BoxLayout.Y_AXIS));
      panelTabla.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

      int nv=0;
      for(int i=0;i<ticket.leeNumLineas();i++) { WFLinea l = ticket.leeLinea(i); nv=nv+l.leeNumValores();}
      
      Object[][] misDatos = new Object[nv][5];
      for(int i=0;i<ticket.leeNumLineas();i++)
      {
         WFLinea l= ticket.leeLinea(i);
	 for(int j=0;j<l.leeNumValores();j++)
         {
	     WFValor v=l.leeValor(j);
             misDatos[j][0]=new String(l.leeDesConcepto()+" [C."+l.leeCodLinea()+"]");
	     misDatos[j][1]=v.leeDesCampo();                misDatos[j][2]=v.leeValor();
	     misDatos[j][3]=v.leeFecUltMod();               misDatos[j][4]=v.leeCodUsuario();
	 }
      }
      String[] misColumnas = {"Concepto","Campo","Valor","Fecha","Usuario"};
      JTable miTabla = new JTable(misDatos, misColumnas);
      TableColumn columna = null;
      for (int i=0; i<5; i++) 
      {
         columna = miTabla.getColumnModel().getColumn(i);
	 if (i==0)           { columna.setPreferredWidth(170); }
         else if (i>0&&i<=2) { columna.setPreferredWidth(20); }
	 else if (i>2)       { columna.setPreferredWidth(10); }
      }
      JScrollPane miScrollPane = new JScrollPane(miTabla);
      panelTabla.add(miScrollPane, BorderLayout.CENTER);
      return panelTabla;
   }

   
   //====================
   // crearPanelBotones()
   //===================
   private void crearPanelBotones()
   {
      ClaseBotonListener btnListener = new ClaseBotonListener();
      JButton	  btn;
      panelBotones = new JPanel();      panelBotones.setLayout(new GridLayout(1,3));
      panelBotones.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

      btn = new JButton("Aceptar"); btn.setMnemonic('a');
      btn.setActionCommand("Aceptar"); btn.setToolTipText("Aceptar los cambios");
      btn.addActionListener(btnListener); panelBotones.add(btn);
      btn = new JButton("Cancelar"); btn.setMnemonic('c');
      btn.setActionCommand("Cancelar"); btn.setToolTipText("Cancelar modificaciones");
      btn.addActionListener(btnListener); panelBotones.add(btn);
      btn  = new JButton("Salir");  btn.setMnemonic('s');
      btn.setActionCommand("Salir");  btn.setToolTipText("Salir sin modificar");
      btn.addActionListener(btnListener); panelBotones.add(btn);
      panelBotones.add(btn);
      panelBotones.setVisible(false);
   }

   
   //==================
   // procBtnAcceptar()
   //==================
   private boolean procBtnAceptar()
   {
      //Guardar los cambios del ticket y salir
      logger.registrarLOG("Se pulsa botón aceptar");
      return true;
   }

   
   //============================
   // procBtnMostrarPanelBasico()
   //============================
   private boolean procBtnMostrarPanelBasico()
   { 
      if (sesion.leeValorDeParametro("BD.MONITORIZAR").equals("S")) logger.registrarLOG("procBtnMostrarPanelBasico()");
      panelPrincipal.setVisible(true); this.setSize(prop.getInt("Tamano_Largo"),prop.getInt("Tamano_Alto")); 
      sesion.ponValorDeOpcion("APL.TIQUET.VER-PANEL-BASICO","S");return true;
   }


   //============================
   // procBtnOcultarPanelBasico()
   //============================
   private boolean procBtnOcultarPanelBasico()
   {  
      if (sesion.leeValorDeParametro("BD.MONITORIZAR").equals("S")) logger.registrarLOG("procBtnOcultarPanelBasico()");
      panelPrincipal.setVisible(false); this.setSize(prop.getInt("Tamano_Largo"),prop.getInt("Tamano_Alto")); 
      sesion.ponValorDeOpcion("APL.TIQUET.VER-PANEL-BASICO","N");return true;
   }
   
   
   //=========================
   // procBtnMostrarBotonera()
   //=========================
   private boolean procBtnMostrarBotonera()
   { 
      if (sesion.leeValorDeParametro("BD.MONITORIZAR").equals("S")) logger.registrarLOG("procBtnMostrarBotonera()");
      panelBotones.setVisible(true); this.setSize(prop.getInt("Tamano_Largo"),prop.getInt("Tamano_Alto")); 
      sesion.ponValorDeOpcion("APL.TIQUET.VER-PANEL-BOTONES","S");return true;
   }
   

   //=========================
   // procBtnOcultarBotonera()
   //=========================
   private boolean procBtnOcultarBotonera()
   { 
      if (sesion.leeValorDeParametro("BD.MONITORIZAR").equals("S")) logger.registrarLOG("procBtnMostrarBotonera()");
      panelBotones.setVisible(false); this.setSize(prop.getInt("Tamano_Largo"),prop.getInt("Tamano_Alto")); 
      sesion.ponValorDeOpcion("APL.TIQUET.VER-PANEL-BOTONES","N");return true;
   }

   
   //===============
   // procBtnSalir()
   //===============
   private boolean procBtnSalir()
   {  
      if (sesion.leeValorDeParametro("BD.MONITORIZAR").equals("S")) logger.registrarLOG("procBtnSalir()");	   
      this.setVisible(false);   return true;  
   }

   
   //===============
   // ponTiquet()
   //===============
   public boolean ponTiquet(WFTicket p_tiquet)
   {
      this.ticket=p_tiquet; this.crearContentPane();
      if (sesion.leeValorDeParametro("BD.MONITORIZAR").equals("S")) 
	      logger.registrarLOG("ponTicket("+ticket.leeCodTicket()+")");	   	         
      return true;
   }
}  //Fin de la definición de la clase frmWFTicket
