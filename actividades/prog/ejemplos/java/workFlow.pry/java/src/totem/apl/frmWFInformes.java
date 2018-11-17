/*-----------------------------------------------
   Fichero  :  frmWFInformes.java
   Fecha    :  05-06-2003
   Estado   :  Desarrollo
-----------------------------------------------*/

package totem.apl;
import  totem.utl.*;
import  totem.lib.*;


import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;


/**
 * Aplicación de Informes
 * 
 * @author David Vargas Ruiz
 * @version 0.7.0
 */
class frmWFInformes extends JFrame 
{
   //--------------------
   //Componentes Visuales
   //--------------------
   JButton	  btnBuscar, btnPaginaAnterior, btnPaginaSiguiente, btnVerDetalle, btnSalir;
   JPanel	  panelCriterios,panelHorizontal,panelTabla;
   JTable         miTabla;
   JComboBox	  comDesTipo, comDesTarea, comPadreHijo;
   JTextField     edCodTicket, edCodTicketPadre, edFecDesde, edFecHasta;
   JMenuBar       menuBarra;
   
   //Componentes no visuales
   utlPropiedades prop;
   frmWFLogin     frmlogin;
   frmWFTicket    appTicket;
   WFSesion	  sesion;
   WFFiltro	  filtro;
   
   
   //=======================
   //Constructor de la clase
   //=======================
   frmWFInformes(frmWFLogin p_frmlogin, WFSesion p_sesion) 
   {
      setTitle("Bandeja de Tareas");
      prop = new utlPropiedades();
      prop.setNombreFichero("totem.rec.frmWFBandejaTareas");
      frmlogin = p_frmlogin; sesion=p_sesion; 
      inicializar();
      this.addWindowListener (new WindowAdapter() {public void windowClosing(WindowEvent e) {procBtnSalir();}});
   }

   public void inicializar()
   {
      filtro=sesion.crearFiltro(); appTicket=null;
      crearContentPane();
   }


   class ClaseBotonListener implements ActionListener 
   {
      public void actionPerformed(ActionEvent e) 
      {
	 String strAccion = e.getActionCommand();
	 if (strAccion=="Salir")                 { procBtnSalir();}
	 else if (strAccion == "Buscar")         { procBtnBuscar();}
  	 else if (strAccion == "VerDetalle")     { procBtnVerDetalle();}
	 else if (strAccion == "MostrarCriterios")  { procBtnMostrarCriterios();}
	 else if (strAccion == "OcultarCriterios")  { procBtnOcultarCriterios();}
	 else if (strAccion == "MostrarBotonera")   { procBtnMostrarBotonera();}
	 else if (strAccion == "OcultarBotonera")   { procBtnOcultarBotonera();}
	 else if (strAccion == "PaginaAnterior")    { procBtnPaginaAnterior();}
	 else if (strAccion == "PaginaSiguiente")   { procBtnPaginaSiguiente();}
	 else	 { System.out.println("<¿"+strAccion+"?>"); }
      }
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
      
      menu = new JMenu("Buscar");     menuBarra.add(menu);
      menuItem = new JMenuItem("Burcar registros");
      menuItem.setActionCommand("Buscar"); menuItem.addActionListener(btnListener);
      menu.add(menuItem);
      
      menu = new JMenu("Ver");     menuBarra.add(menu);
      menuItem = new JMenuItem("Página anterior");
      menuItem.setActionCommand("PaginaAnterior"); menuItem.addActionListener(btnListener);
      menu.add(menuItem);
      menuItem = new JMenuItem("Página siguiente");
      menuItem.setActionCommand("PaginaSiguiente"); menuItem.addActionListener(btnListener);
      menu.add(menuItem);
      menu.addSeparator();
      menuItem = new JMenuItem("Ocultar criterios");
      menuItem.setActionCommand("OcultarCriterios"); menuItem.addActionListener(btnListener);
      menu.add(menuItem);
      menuItem = new JMenuItem("Mostrar criterios");
      menuItem.setActionCommand("MostrarCriterios"); menuItem.addActionListener(btnListener);
      menu.add(menuItem);
      menu.addSeparator();
      menuItem = new JMenuItem("Ocultar botonera");
      menuItem.setActionCommand("OcultarBotonera"); menuItem.addActionListener(btnListener);
      menu.add(menuItem);
      menuItem = new JMenuItem("Mostrar botonera");
      menuItem.setActionCommand("MostrarBotonera"); menuItem.addActionListener(btnListener);
      menu.add(menuItem);
	    
      menu = new JMenu("Ticket");     menuBarra.add(menu);
      menuItem = new JMenuItem("Ver detalle");
      menuItem.setActionCommand("VerDetalle"); menuItem.addActionListener(btnListener);
      menu.add(menuItem);
      /*menuItem = new JMenuItem("Imprimir");
      menuItem.setActionCommand("ImprimirTicket"); menuItem.addActionListener(btnListener);
      menu.add(menuItem);*/
      
      menu = new JMenu("Salir");      menuBarra.add(menu);
      menuItem = new JMenuItem("Salir de la aplicación");
      menuItem.setActionCommand("Salir");  menuItem.addActionListener(btnListener);
      menu.add(menuItem);
   }
   
   
   //===================
   // crearContentPane()
   //===================
   private void crearContentPane()
   {
      if (panelTabla!=null) this.remove(panelTabla);
      if (panelCriterios!=null) this.remove(panelCriterios);
      if (panelHorizontal!=null) this.remove(panelHorizontal);
      
      crearPanelTabla(); crearPanelHorizontal(); crearPanelCriterios(); crearMenu();
      
      this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
      this.getContentPane().add(panelTabla);
      this.getContentPane().add(panelCriterios);
      this.getContentPane().add(panelHorizontal);

      if (sesion.leeValorDeOpcion("APP.BANDEJA.VER-CRITERIOS").equals("S")) panelCriterios.setVisible(true);
      if (sesion.leeValorDeOpcion("APP.BANDEJA.VER-BOTONERA").equals("S")) panelHorizontal.setVisible(true);

      this.setVisible(false);
      this.setLocation(prop.getInt("Coordenada_X"),prop.getInt("Coordenada_Y")); 
      this.setSize(prop.getInt("Tamano_Largo"),prop.getInt("Tamano_Alto")); 
      this.setVisible(true);
   }

   
   //======================
   // crearPanelCriterios()
   //======================
   private void crearPanelCriterios()
   {
      JLabel      lbNumRegistros,lbNumPagina,lbBuscarPor;
   
      panelCriterios=new JPanel();
      panelCriterios.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
      panelCriterios.setBorder(BorderFactory.createTitledBorder("Criterios de selección de registros"));
      panelCriterios.setLayout(new GridLayout(4,5));

      if (filtro!=null) 
      { 
         lbNumRegistros=new JLabel("Nº registros: "+filtro.leeNumRegistros()+" / "+filtro.leeNumRegistrosTotal(),
			 JLabel.CENTER);
	 lbNumRegistros.setForeground(Color.blue);
         lbNumPagina=new JLabel("Página: "+filtro.leeNumPaginaActual()+" / "+filtro.leeNumPaginaTotal(),JLabel.CENTER);
	 lbNumPagina.setForeground(Color.blue);
      }
      else              
      {   
         lbNumRegistros=new JLabel("Nº registros: (vacío)",JLabel.CENTER);
	 lbNumRegistros.setForeground(Color.red);
         lbNumPagina=new JLabel("Página: (vacío)",JLabel.CENTER);
	 lbNumPagina.setForeground(Color.red);
      }
      
      //------------------------
      //FILTRO EDITTEXT y COMBOS
      lbBuscarPor=new JLabel("Buscar por:",JLabel.CENTER);
      lbBuscarPor.setToolTipText("Criterios para realizar una búsqueda");

      if (edCodTicket==null) //Crear estos elementos visuales cuando sea necesario
      {
         edCodTicket = new JTextField(""); edCodTicket.setToolTipText("Filtrar por el código");
         edCodTicketPadre = new JTextField(""); edCodTicketPadre.setToolTipText("Filtrar por el código del padre");
         edFecDesde = new JTextField("01/01/2000"); edFecDesde.setToolTipText("Filtrar desde esta fecha de creación");
         edFecHasta = new JTextField("01/01/3000"); edFecHasta.setToolTipText("Filtrar hasta esta fecha de creación");
         comPadreHijo=new JComboBox(); 
         comPadreHijo.addItem("(Ver todo)");comPadreHijo.addItem("Padre");comPadreHijo.addItem("Hijo");
         comPadreHijo.setToolTipText("Filtrar por tickets Padre/Hijo");
      }
      
      if (comDesTipo==null) comDesTipo=new JComboBox();
      if (comDesTipo.getItemCount()<2) 
      {
         comDesTipo=new JComboBox();
         comDesTipo.addItem("(Ver todo)");
         comDesTipo.setToolTipText("Filtrar por el tipo del ticket");
         for(int i=0;i<filtro.leeNumTipos();i++)
         {  WFTipoTicket tipo = filtro.leeTipoTicket(i); comDesTipo.addItem(tipo.leeDesTipoTicket()); }
      }

      if (comDesTarea==null) comDesTarea=new JComboBox();
      if (comDesTarea.getItemCount()<2)
      {
         comDesTarea.setToolTipText("Filtrar por tarea actual"); comDesTarea.addItem("(Ver todo)");
         for(int i=0;i<filtro.leeNumTareas();i++)
         {  WFTarea tarea = filtro.leeTarea(i); comDesTarea.addItem(tarea.leeDesTarea());}
      }
      
      panelCriterios.add(Box.createRigidArea(new Dimension(10, 0)));
      panelCriterios.add(new JLabel("Ticket ",JLabel.RIGHT));     panelCriterios.add(edCodTicket);
      panelCriterios.add(new JLabel("Padre/Hijo ",JLabel.RIGHT)); panelCriterios.add(comPadreHijo);
      panelCriterios.add(lbNumRegistros);       
      panelCriterios.add(new JLabel("TT Padre ",JLabel.RIGHT));   panelCriterios.add(edCodTicketPadre); 
      panelCriterios.add(new JLabel("Tipo ",JLabel.RIGHT));       panelCriterios.add(comDesTipo);
      panelCriterios.add(lbNumPagina); 
      panelCriterios.add(new JLabel("Fec. Desde ",JLabel.RIGHT)); panelCriterios.add(edFecDesde); 
      panelCriterios.add(new JLabel("Tarea ",JLabel.RIGHT));      panelCriterios.add(comDesTarea);
      panelCriterios.add(Box.createRigidArea(new Dimension(10, 0)));
      panelCriterios.add(new JLabel("Fec. Hasta ",JLabel.RIGHT)); panelCriterios.add(edFecHasta); 
      //dos nulos
      panelCriterios.setVisible(false);
   }

   
   //=======================
   // crearPanelHorizontal()
   //=======================
   private void crearPanelHorizontal()
   {
      if (panelHorizontal==null) //Crear el panel sólo una vez
      {
         ClaseBotonListener btnListener = new ClaseBotonListener();
         panelHorizontal=new JPanel(); panelHorizontal.setLayout(new GridLayout(1,6));
      
         btnBuscar = new JButton("Buscar"); btnBuscar.setMnemonic('b');
         btnBuscar.setActionCommand("Buscar"); btnBuscar.setToolTipText("Buscar registros seleccionados");
         btnPaginaAnterior = new JButton("Anterior"); btnPaginaAnterior.setMnemonic('a');
         btnPaginaAnterior.setActionCommand("PaginaAnterior"); btnPaginaAnterior.setToolTipText("Ir a la página anterior");
         btnPaginaSiguiente = new JButton("Siguiente"); btnPaginaSiguiente.setMnemonic('g');
         btnPaginaSiguiente.setActionCommand("PaginaSiguiente"); 
	 btnPaginaSiguiente.setToolTipText("Ir a la página siguiente");
         btnVerDetalle = new JButton("Ver detalle"); btnVerDetalle.setMnemonic('v');
         btnVerDetalle.setActionCommand("VerDetalle"); btnVerDetalle.setToolTipText("Ver datos del ticket seleccionado");
         btnSalir    = new JButton("Salir");  btnSalir.setMnemonic('s');
         btnSalir.setActionCommand("Salir");  btnSalir.setToolTipText("Salir de Bandeja de Tareas");

         btnBuscar.addActionListener(btnListener);          btnPaginaAnterior.addActionListener(btnListener);    
         btnPaginaSiguiente.addActionListener(btnListener); 
         btnVerDetalle.addActionListener(btnListener);      btnSalir.addActionListener(btnListener);
         panelHorizontal.add(btnBuscar);          panelHorizontal.add(btnPaginaAnterior); 
         panelHorizontal.add(btnPaginaSiguiente); 
         panelHorizontal.add(btnVerDetalle);      panelHorizontal.add(btnSalir);
         panelHorizontal.setVisible(false);
      }
   }

   
   //===================
   // crearPanelTabla()
   //===================
   private void crearPanelTabla()
   {
      panelTabla= new JPanel(); panelTabla.setLayout(new BoxLayout(panelTabla, BoxLayout.Y_AXIS));
      panelTabla.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
      panelTabla.setBorder(BorderFactory.createTitledBorder("Lista de registros"));

      procRefrescarTabla();
      //miTabla.setPreferredScrollableViewportSize(new Dimension(600, 300));
      JScrollPane scrollPane = new JScrollPane(miTabla);
      panelTabla.add(scrollPane, BorderLayout.CENTER);
   }

   private void procRefrescarTabla()
   {     //No funciona bien*****
         MiModeloDeTabla miModelo = new MiModeloDeTabla();
         TablaOrdenada   miTablaOrdenada = new TablaOrdenada(miModelo);
         miTabla = new JTable(miTablaOrdenada);
         miTablaOrdenada.addMouseListenerToHeaderInTable(miTabla);
   }
   

   
   //================
   // procBtnBuscar()
   //================ 
   private boolean procBtnBuscar()
   {
      try
      {
         filtro.desactivar();
         //Establecer los criterios de búsqueda en el filtro
         if (edCodTicket!=null)      { filtro.ponBuscar("COD_TICKET", edCodTicket.getText());}
         if (edCodTicketPadre!=null) { filtro.ponBuscar("COD_TICKET_PADRE",edCodTicketPadre.getText());}
         if (comDesTipo!=null)       { filtro.ponBuscar("DES_TIPO",(String)comDesTipo.getSelectedItem());}
         if (comDesTarea!=null)      { filtro.ponBuscar("DES_TAREA",(String)comDesTarea.getSelectedItem());}
         filtro.activar();
         crearContentPane(); //*****
         return true;
      }
      catch(Exception e) {System.err.println("frmBandeja.procBuscar:"+e);return false;}
   }
   

   //========================
   // procBtnPaginaAnterior()
   //========================
   private boolean procBtnPaginaAnterior()
   {
      int i = filtro.leeNumPaginaActual();
      if (i>1) {i--;filtro.ponNumPagina(i); crearContentPane(); return true;}
      return false;	   
   }


   //=========================
   // procBtnPaginaSiguiente()
   //=========================
   private boolean procBtnPaginaSiguiente()
   {
      int i = filtro.leeNumPaginaActual();
      if (i<filtro.leeNumPaginaTotal()) {i++;filtro.ponNumPagina(i); crearContentPane(); return true;}
      return false;	   
   }

   
   //==============================
   // procBtnOcultarCriterios()
   //==============================
   private boolean procBtnOcultarCriterios()
   {   panelCriterios.setVisible(false);//this.pack(); 
      this.setSize(prop.getInt("Tamano_Largo"),prop.getInt("Tamano_Alto")); 
       sesion.ponValorDeOpcion("APP.BANDEJA.VER-CRITERIOS","N");
       return true;
   }

   
   //==========================
   // procBtnMostrarCriterios()
   //==========================
   private boolean procBtnMostrarCriterios()
   {  panelCriterios.setVisible(true);//this.pack();    
      this.setSize(prop.getInt("Tamano_Largo"),prop.getInt("Tamano_Alto")); 
      sesion.ponValorDeOpcion("APP.BANDEJA.VER-CRITERIOS","S");
      return true;	     
   }

   
   //==============================
   // procBtnOcultarBotonera()
   //==============================
   private boolean procBtnOcultarBotonera()
   {  
      panelHorizontal.setVisible(false); this.setSize(prop.getInt("Tamano_Largo"),prop.getInt("Tamano_Alto")); 
      sesion.ponValorDeOpcion("APP.BANDEJA.VER-BOTONERA","N");return true;
   }
   

   //==============================
   // procBtnMostrarBotonera()
   //==============================
   private boolean procBtnMostrarBotonera()
   { 
      panelHorizontal.setVisible(true); this.setSize(prop.getInt("Tamano_Largo"),prop.getInt("Tamano_Alto")); 
      sesion.ponValorDeOpcion("APP.BANDEJA.VER-BOTONERA","S");return true;
   }
   
   
   //====================
   // procBtnVerDetalle()
   //====================
   private boolean procBtnVerDetalle()
   {
      try
      {
         //Si hay una fila seleccionada entonces entrar en el ticket
         if(miTabla.getSelectedRow()<0) return true;
	 int i= ((Integer)miTabla.getValueAt(miTabla.getSelectedRow(),0)).intValue();
	 WFTicket ticket=filtro.leeTicketConCodigo(i);
	 //if (appTicket==null)  {appTicket = new frmWFTicket(this,ticket);}
	 //else                  { appTicket.ponTicket(ticket);}
	 //¿Actualizar el JTable miTabla?
	 return true;
      }
      catch(Exception e)
      { 
         // JOptionPane.showMessageDialog(frame,"Callejero PLC preprocesado."); 
         //  JOptionPane.showMessageDialog(frame,"Se ha producido un error!","ERROR",JOptionPane.ERROR_MESSAGE);
	 System.err.println(e);
      }
      return false;	   
   }

   
   //===============
   // procBtnSalir()
   //===============
   private boolean procBtnSalir() 
   {  
      if (appTicket!=null) appTicket.setVisible(false); 
      appTicket=null;
      this.setVisible(false); return true; 
   }

   //=====================
   // Métodos LEE públicos
   //=====================
   public WFSesion leeSesion()  {return sesion;}

   
   //================================
   //Definición de mi modelo de tabla
   //================================
   class MiModeloDeTabla extends AbstractTableModel 
   {
      //Variables
      //final String[] columnNames = {"CODIGO","PADRE","FECHA CREACION","TIPO","TAREA","SELECCIONADO"};
      final String[] columnNames = {"CODIGO","PADRE","FECHA CREACION","TIPO","TAREA"};
      private Object data[][];

      MiModeloDeTabla()      { refrescarTabla(); }
      
      //-----------
      //Métodos GET
      //-----------
      public int     getColumnCount()                 {return columnNames.length;}
      public int     getRowCount()                    {return data.length; }
      public String  getColumnName(int col)           {return columnNames[col]; }
      public Object  getValueAt(int row, int col)     {return data[row][col]; }
      public Class   getColumnClass(int c)            {return getValueAt(0, c).getClass();}
      public boolean isCellEditable(int row, int col) {if(col<5) {return false;} else {return true;}}

      
      //-----------
      //Métodos SET
      //-----------
      public void    refrescarTabla()
      {
         data = new Object[filtro.leeNumRegistros()][6];
         for(int i=0;i<filtro.leeNumRegistros();i++)
         {
	    WFTicket t = filtro.leeTicket(i);
            data[i][0]=new Integer(t.leeCodTicket()); data[i][1]=new Integer(t.leeCodTicketPadre());
	    data[i][2]=(Object)t.leeFechaCreacion();  data[i][3]=t.leeDesTipoTicket();
	    data[i][4]=t.leeDesTarea();	              //data[i][5]=new Boolean(false);
         }
      }
      
      public void setValueAt(Object value, int row, int col) 
      {
         System.out.println("pon valor (" + row + "," + col+ ")=" +value+" tipo="+value.getClass());

         if (data[0][col] instanceof Integer && !(value instanceof Integer)) 
	 {
            try  {data[row][col] = new Integer(value.toString()); fireTableCellUpdated(row, col);} 
	    catch (NumberFormatException e) 
	    {JOptionPane.showMessageDialog(panelTabla,"The\""+getColumnName(col)+"\"col acc only int val.");}
         } 
	 else 
	 {  data[row][col] = value;  fireTableCellUpdated(row, col); }
	 System.out.println("New value of data:"); printDebugData();
      }
      
      private void printDebugData() 
      {
         int numRows = getRowCount(); int numCols = getColumnCount();
         for (int i=0; i<numRows; i++) 
         { 
            System.out.print(" Fila[" + i + "]");
            for (int j=0; j<numCols; j++) { System.out.print("  " + data[i][j]); } 
	    System.out.println();
         }
         System.out.println("--------------------------");
      }
   } //Fin de la definición de clase miModeloDeTabla
}  //Fin de la definición de la clase frmWFBandejaTareas
