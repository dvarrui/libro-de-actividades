// Creado por el proyecto TENERIFE
// Autor: David Vargas Ruiz
// Fecha: Tue Feb 07 22:55:25 WET 2006

package david.proyecto.swing.menu;

import java.util.ArrayList;

import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Event;
import java.awt.BorderLayout;

import javax.swing.KeyStroke;
import javax.swing.JPanel;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JDesktopPane;

import david.proyecto.almacenes.IAlmacen;
import david.proyecto.almacenes.FabricaAlmacenes;
import david.proyecto.swing.listado.*;
import david.proyecto.swing.nuevo.*;
import david.proyecto.swing.IVentana;

public class FrmMenuPrincipal extends JFrame implements ActionListener, IVentana {
    static final long serialVersionUID=1; //Para evitar Warnings
    
    public static final String ID_VENTANA="menu.principal";
	public static final int ANCHO = 900;
	public static final int ALTO = 700;

	//Elementos visuales
	
	private JPanel jContentPane = null;
	private JMenuBar jJMenuBar = null;

	private JMenu menuArchivo = null;
	private JMenu menuListado = null;
	private JMenu menuNuevo = null;
	private JMenu menuVentanas = null;
	private JMenu menuAyuda = null;

	private JMenuItem menuItemGuardar = null;
	private JMenuItem menuItemSalir = null;
	
    private JMenuItem menuItemListadoPersonas = null;
    private JMenuItem menuItemListadoAgendas = null;
    private JMenuItem menuItemListadoCatalogos = null;
    private JMenuItem menuItemListadoCitas = null;
    private JMenuItem menuItemListadoUsuarios = null;
    private JMenuItem menuItemListadoPerfiles = null;
    private JMenuItem menuItemListadoPermisos = null;
    private JMenuItem menuItemNuevoPersonas = null;
    private JMenuItem menuItemNuevoAgendas = null;
    private JMenuItem menuItemNuevoCatalogos = null;
    private JMenuItem menuItemNuevoCitas = null;
    private JMenuItem menuItemNuevoUsuarios = null;
    private JMenuItem menuItemNuevoPerfiles = null;
    private JMenuItem menuItemNuevoPermisos = null;
	private JMenuItem menuItemAcercaDe = null;
	private JMenuItem menuItemVentanasCerrar = null;
	private JMenuItem menuItemVentanasAbrir = null;
	
	private JDesktopPane jDesktopPane = null;
	
	private ArrayList ventanas = null;

    //Componentes No visuales
    
    private IAlmacen almacenPersonas = null;
    private IAlmacen almacenAgendas = null;
    private IAlmacen almacenCatalogos = null;
    private IAlmacen almacenCitas = null;
    private IAlmacen almacenUsuarios = null;
    private IAlmacen almacenPerfiles = null;
    private IAlmacen almacenPermisos = null;
    
	
    private FrmListadoPersonas frmListadoPersonas = null;
    private FrmListadoAgendas frmListadoAgendas = null;
    private FrmListadoCatalogos frmListadoCatalogos = null;
    private FrmListadoCitas frmListadoCitas = null;
    private FrmListadoUsuarios frmListadoUsuarios = null;
    private FrmListadoPerfiles frmListadoPerfiles = null;
    private FrmListadoPermisos frmListadoPermisos = null;
    
	
    private FrmNuevoPersonas frmNuevoPersonas = null;
    private FrmNuevoAgendas frmNuevoAgendas = null;
    private FrmNuevoCatalogos frmNuevoCatalogos = null;
    private FrmNuevoCitas frmNuevoCitas = null;
    private FrmNuevoUsuarios frmNuevoUsuarios = null;
    private FrmNuevoPerfiles frmNuevoPerfiles = null;
    private FrmNuevoPermisos frmNuevoPermisos = null;



	/**
	 * This is the default constructor
	 */
	public FrmMenuPrincipal() {
		super();
		initialize();
		inicializar();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setPreferredSize(new java.awt.Dimension(ANCHO,ALTO));
		this.setJMenuBar(getJJMenuBar());
		this.setSize(ANCHO, ALTO);
		this.setContentPane(getJContentPane());
		this.setTitle("Nombre_Proyecto");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getJDesktopPane(), java.awt.BorderLayout.CENTER);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jDesktopPane	
	 * 	
	 * @return javax.swing.JDesktopPane	
	 */
	private JDesktopPane getJDesktopPane() {
		if (jDesktopPane == null) {
			jDesktopPane = new JDesktopPane();
		}
		return jDesktopPane;
	}

	/**
	 * This method initializes jJMenuBar	
	 * 	
	 * @return javax.swing.JMenuBar	
	 */
	private JMenuBar getJJMenuBar() {
		if (jJMenuBar == null) {
			jJMenuBar = new JMenuBar();
			jJMenuBar.add(getMenuArchivo());
			jJMenuBar.add(getMenuListado());
			jJMenuBar.add(getMenuNuevo());			
			jJMenuBar.add(getMenuVentanas());						
			jJMenuBar.add(getMenuAyuda());
		}
		return jJMenuBar;
	}

	/**
	 * This method initializes jMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getMenuArchivo() {
		if (menuArchivo == null) {
			menuArchivo = new JMenu();
			menuArchivo.setText("Archivo");
			menuArchivo.add(getMenuItemGuardar());
			menuArchivo.add(getMenuItemSalir());
		}
		return menuArchivo;
	}

	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMenuItemGuardar() {
		if (menuItemGuardar == null) {
			menuItemGuardar = new JMenuItem();
			menuItemGuardar.setText("Guardar");
			menuItemGuardar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G,
					Event.CTRL_MASK, true));
			menuItemGuardar.addActionListener(this);	
			menuItemGuardar.setActionCommand("guardar");				
		}
		return menuItemGuardar;
	}

	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMenuItemSalir() {
		if (menuItemSalir == null) {
			menuItemSalir = new JMenuItem();
			menuItemSalir.setText("Salir");
			menuItemSalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
					Event.CTRL_MASK, true));
			menuItemSalir.addActionListener(this);
			menuItemSalir.setActionCommand("salir");				
		}
		return menuItemSalir;
	}
	
	/**
	 * This method initializes jMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getMenuVentanas() {
		if (menuVentanas == null) {
			menuVentanas = new JMenu();
			menuVentanas.setText("Ventanas");
			menuVentanas.add(getMenuItemVentanasCerrar());
			menuVentanas.add(getMenuItemVentanasAbrir());
		}
		return menuVentanas;
	}

	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMenuItemVentanasCerrar() {
		if (menuItemVentanasCerrar == null) {
			menuItemVentanasCerrar = new JMenuItem();
			menuItemVentanasCerrar.setText("Cerrar ventanas");
			menuItemVentanasCerrar.addActionListener(this);
			menuItemVentanasCerrar.setActionCommand("ventanas.cerrar");				
		}
		return menuItemVentanasCerrar;
	}

	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMenuItemVentanasAbrir() {
		if (menuItemVentanasAbrir == null) {
			menuItemVentanasAbrir = new JMenuItem();
			menuItemVentanasAbrir.setText("Abrir ventanas");
			menuItemVentanasAbrir.addActionListener(this);
			menuItemVentanasAbrir.setActionCommand("ventanas.abrir");				
		}
		return menuItemVentanasAbrir;
	}
	
	/**
	 * This method initializes jMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getMenuAyuda() {
		if (menuAyuda == null) {
			menuAyuda = new JMenu();
			menuAyuda.setText("Ayuda");
			menuAyuda.add(getMenuItemAcercaDe());
		}
		return menuAyuda;
	}

	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMenuItemAcercaDe() {
		if (menuItemAcercaDe == null) {
			menuItemAcercaDe = new JMenuItem();
			menuItemAcercaDe.setText("Acerca de");
			menuItemAcercaDe.addActionListener(this);
			menuItemAcercaDe.setActionCommand("acercade");				
		}
		return menuItemAcercaDe;
	}

	/**
	 * This method initializes jMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getMenuListado() {
		if (menuListado == null) {
			menuListado = new JMenu();
			menuListado.setText("Listado");

			
			menuListado.add(getMenuItemListadoPersonas());
			menuListado.add(getMenuItemListadoAgendas());
			menuListado.add(getMenuItemListadoCatalogos());
			menuListado.add(getMenuItemListadoCitas());
			menuListado.add(getMenuItemListadoUsuarios());
			menuListado.add(getMenuItemListadoPerfiles());
			menuListado.add(getMenuItemListadoPermisos());
		}
		return menuListado;
	}


	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMenuItemListadoPersonas() {
		if (menuItemListadoPersonas == null) {
			menuItemListadoPersonas = new JMenuItem();
			menuItemListadoPersonas.setText("Personas");
			menuItemListadoPersonas.addActionListener(this);
			menuItemListadoPersonas.setActionCommand("personas.listado");
		}
		return menuItemListadoPersonas;
	}

	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMenuItemListadoAgendas() {
		if (menuItemListadoAgendas == null) {
			menuItemListadoAgendas = new JMenuItem();
			menuItemListadoAgendas.setText("Agendas");
			menuItemListadoAgendas.addActionListener(this);
			menuItemListadoAgendas.setActionCommand("agendas.listado");
		}
		return menuItemListadoAgendas;
	}

	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMenuItemListadoCatalogos() {
		if (menuItemListadoCatalogos == null) {
			menuItemListadoCatalogos = new JMenuItem();
			menuItemListadoCatalogos.setText("Catalogos");
			menuItemListadoCatalogos.addActionListener(this);
			menuItemListadoCatalogos.setActionCommand("catalogos.listado");
		}
		return menuItemListadoCatalogos;
	}

	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMenuItemListadoCitas() {
		if (menuItemListadoCitas == null) {
			menuItemListadoCitas = new JMenuItem();
			menuItemListadoCitas.setText("Citas");
			menuItemListadoCitas.addActionListener(this);
			menuItemListadoCitas.setActionCommand("citas.listado");
		}
		return menuItemListadoCitas;
	}

	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMenuItemListadoUsuarios() {
		if (menuItemListadoUsuarios == null) {
			menuItemListadoUsuarios = new JMenuItem();
			menuItemListadoUsuarios.setText("Usuarios");
			menuItemListadoUsuarios.addActionListener(this);
			menuItemListadoUsuarios.setActionCommand("usuarios.listado");
		}
		return menuItemListadoUsuarios;
	}

	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMenuItemListadoPerfiles() {
		if (menuItemListadoPerfiles == null) {
			menuItemListadoPerfiles = new JMenuItem();
			menuItemListadoPerfiles.setText("Perfiles");
			menuItemListadoPerfiles.addActionListener(this);
			menuItemListadoPerfiles.setActionCommand("perfiles.listado");
		}
		return menuItemListadoPerfiles;
	}

	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMenuItemListadoPermisos() {
		if (menuItemListadoPermisos == null) {
			menuItemListadoPermisos = new JMenuItem();
			menuItemListadoPermisos.setText("Permisos");
			menuItemListadoPermisos.addActionListener(this);
			menuItemListadoPermisos.setActionCommand("permisos.listado");
		}
		return menuItemListadoPermisos;
	}

	/**
	 * This method initializes jMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getMenuNuevo() {
		if (menuNuevo == null) {
			menuNuevo = new JMenu();
			menuNuevo.setText("Nuevo");

			
			menuNuevo.add(getMenuItemNuevoPersonas());
			menuNuevo.add(getMenuItemNuevoAgendas());
			menuNuevo.add(getMenuItemNuevoCatalogos());
			menuNuevo.add(getMenuItemNuevoCitas());
			menuNuevo.add(getMenuItemNuevoUsuarios());
			menuNuevo.add(getMenuItemNuevoPerfiles());
			menuNuevo.add(getMenuItemNuevoPermisos());
		}
		return menuNuevo;
	}

	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMenuItemNuevoPersonas() {
		if (menuItemNuevoPersonas == null) {
			menuItemNuevoPersonas = new JMenuItem();
			menuItemNuevoPersonas.setText("Personas");
			menuItemNuevoPersonas.addActionListener(this);
			menuItemNuevoPersonas.setActionCommand("personas.nuevo");
		}
		return menuItemNuevoPersonas;
	}
	
	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMenuItemNuevoAgendas() {
		if (menuItemNuevoAgendas == null) {
			menuItemNuevoAgendas = new JMenuItem();
			menuItemNuevoAgendas.setText("Agendas");
			menuItemNuevoAgendas.addActionListener(this);
			menuItemNuevoAgendas.setActionCommand("agendas.nuevo");
		}
		return menuItemNuevoAgendas;
	}
	
	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMenuItemNuevoCatalogos() {
		if (menuItemNuevoCatalogos == null) {
			menuItemNuevoCatalogos = new JMenuItem();
			menuItemNuevoCatalogos.setText("Catalogos");
			menuItemNuevoCatalogos.addActionListener(this);
			menuItemNuevoCatalogos.setActionCommand("catalogos.nuevo");
		}
		return menuItemNuevoCatalogos;
	}
	
	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMenuItemNuevoCitas() {
		if (menuItemNuevoCitas == null) {
			menuItemNuevoCitas = new JMenuItem();
			menuItemNuevoCitas.setText("Citas");
			menuItemNuevoCitas.addActionListener(this);
			menuItemNuevoCitas.setActionCommand("citas.nuevo");
		}
		return menuItemNuevoCitas;
	}
	
	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMenuItemNuevoUsuarios() {
		if (menuItemNuevoUsuarios == null) {
			menuItemNuevoUsuarios = new JMenuItem();
			menuItemNuevoUsuarios.setText("Usuarios");
			menuItemNuevoUsuarios.addActionListener(this);
			menuItemNuevoUsuarios.setActionCommand("usuarios.nuevo");
		}
		return menuItemNuevoUsuarios;
	}
	
	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMenuItemNuevoPerfiles() {
		if (menuItemNuevoPerfiles == null) {
			menuItemNuevoPerfiles = new JMenuItem();
			menuItemNuevoPerfiles.setText("Perfiles");
			menuItemNuevoPerfiles.addActionListener(this);
			menuItemNuevoPerfiles.setActionCommand("perfiles.nuevo");
		}
		return menuItemNuevoPerfiles;
	}
	
	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMenuItemNuevoPermisos() {
		if (menuItemNuevoPermisos == null) {
			menuItemNuevoPermisos = new JMenuItem();
			menuItemNuevoPermisos.setText("Permisos");
			menuItemNuevoPermisos.addActionListener(this);
			menuItemNuevoPermisos.setActionCommand("permisos.nuevo");
		}
		return menuItemNuevoPermisos;
	}
	

	/**
	 * Este método inicializa otros componentes
	 * 
	 * @return void
	 */
	private void inicializar() {
	   	   
	   
	   almacenPersonas = FabricaAlmacenes.createAlmacen(FabricaAlmacenes.PERSONAS,FabricaAlmacenes.TIPO_TXT);
       almacenPersonas.open(); //Compatibilidad Almacenes TXT y SQL
	   
	   almacenAgendas = FabricaAlmacenes.createAlmacen(FabricaAlmacenes.AGENDAS,FabricaAlmacenes.TIPO_TXT);
       almacenAgendas.open(); //Compatibilidad Almacenes TXT y SQL
	   
	   almacenCatalogos = FabricaAlmacenes.createAlmacen(FabricaAlmacenes.CATALOGOS,FabricaAlmacenes.TIPO_TXT);
       almacenCatalogos.open(); //Compatibilidad Almacenes TXT y SQL
	   
	   almacenCitas = FabricaAlmacenes.createAlmacen(FabricaAlmacenes.CITAS,FabricaAlmacenes.TIPO_TXT);
       almacenCitas.open(); //Compatibilidad Almacenes TXT y SQL
	   
	   almacenUsuarios = FabricaAlmacenes.createAlmacen(FabricaAlmacenes.USUARIOS,FabricaAlmacenes.TIPO_TXT);
       almacenUsuarios.open(); //Compatibilidad Almacenes TXT y SQL
	   
	   almacenPerfiles = FabricaAlmacenes.createAlmacen(FabricaAlmacenes.PERFILES,FabricaAlmacenes.TIPO_TXT);
       almacenPerfiles.open(); //Compatibilidad Almacenes TXT y SQL
	   
	   almacenPermisos = FabricaAlmacenes.createAlmacen(FabricaAlmacenes.PERMISOS,FabricaAlmacenes.TIPO_TXT);
       almacenPermisos.open(); //Compatibilidad Almacenes TXT y SQL
	   
	   
	   ventanas = new ArrayList();
	   
	   
	   frmListadoPersonas = new FrmListadoPersonas(almacenPersonas);
	   this.getJDesktopPane().add(frmListadoPersonas);
	   ventanas.add(frmListadoPersonas);
	   
	   frmListadoAgendas = new FrmListadoAgendas(almacenAgendas);
	   this.getJDesktopPane().add(frmListadoAgendas);
	   ventanas.add(frmListadoAgendas);
	   
	   frmListadoCatalogos = new FrmListadoCatalogos(almacenCatalogos);
	   this.getJDesktopPane().add(frmListadoCatalogos);
	   ventanas.add(frmListadoCatalogos);
	   
	   frmListadoCitas = new FrmListadoCitas(almacenCitas);
	   this.getJDesktopPane().add(frmListadoCitas);
	   ventanas.add(frmListadoCitas);
	   
	   frmListadoUsuarios = new FrmListadoUsuarios(almacenUsuarios);
	   this.getJDesktopPane().add(frmListadoUsuarios);
	   ventanas.add(frmListadoUsuarios);
	   
	   frmListadoPerfiles = new FrmListadoPerfiles(almacenPerfiles);
	   this.getJDesktopPane().add(frmListadoPerfiles);
	   ventanas.add(frmListadoPerfiles);
	   
	   frmListadoPermisos = new FrmListadoPermisos(almacenPermisos);
	   this.getJDesktopPane().add(frmListadoPermisos);
	   ventanas.add(frmListadoPermisos);
	   
	   
	   
	   frmNuevoPersonas = new FrmNuevoPersonas();
	   frmNuevoPersonas.setAlmacen(almacenPersonas);
       frmNuevoPersonas.setContenedor(this);
   	   this.getJDesktopPane().add(frmNuevoPersonas);
	   
	   frmNuevoAgendas = new FrmNuevoAgendas();
	   frmNuevoAgendas.setAlmacen(almacenAgendas);
       frmNuevoAgendas.setContenedor(this);
   	   this.getJDesktopPane().add(frmNuevoAgendas);
	   
	   frmNuevoCatalogos = new FrmNuevoCatalogos();
	   frmNuevoCatalogos.setAlmacen(almacenCatalogos);
       frmNuevoCatalogos.setContenedor(this);
   	   this.getJDesktopPane().add(frmNuevoCatalogos);
	   
	   frmNuevoCitas = new FrmNuevoCitas();
	   frmNuevoCitas.setAlmacen(almacenCitas);
       frmNuevoCitas.setContenedor(this);
   	   this.getJDesktopPane().add(frmNuevoCitas);
	   
	   frmNuevoUsuarios = new FrmNuevoUsuarios();
	   frmNuevoUsuarios.setAlmacen(almacenUsuarios);
       frmNuevoUsuarios.setContenedor(this);
   	   this.getJDesktopPane().add(frmNuevoUsuarios);
	   
	   frmNuevoPerfiles = new FrmNuevoPerfiles();
	   frmNuevoPerfiles.setAlmacen(almacenPerfiles);
       frmNuevoPerfiles.setContenedor(this);
   	   this.getJDesktopPane().add(frmNuevoPerfiles);
	   
	   frmNuevoPermisos = new FrmNuevoPermisos();
	   frmNuevoPermisos.setAlmacen(almacenPermisos);
       frmNuevoPermisos.setContenedor(this);
   	   this.getJDesktopPane().add(frmNuevoPermisos);
	   
	}

   /**
    * Este método actualiza todos los componentes
    */
   public void reset() {
   	   
	   if (frmListadoPersonas.isVisible()) frmListadoPersonas.reset();
	   
	   if (frmListadoAgendas.isVisible()) frmListadoAgendas.reset();
	   
	   if (frmListadoCatalogos.isVisible()) frmListadoCatalogos.reset();
	   
	   if (frmListadoCitas.isVisible()) frmListadoCitas.reset();
	   
	   if (frmListadoUsuarios.isVisible()) frmListadoUsuarios.reset();
	   
	   if (frmListadoPerfiles.isVisible()) frmListadoPerfiles.reset();
	   
	   if (frmListadoPermisos.isVisible()) frmListadoPermisos.reset();
	    
   }

   public String getIdVentana() {
      return ID_VENTANA;
   }

   public void actionPerformed(ActionEvent evt) {
      System.out.println(evt.getActionCommand());
      
      if (evt.getActionCommand().equals("guardar")) {
    	  //TODO: Guardar
	      
          almacenPersonas.close(); //Compatibilidad Almacenes TXT y SQL
	   
          almacenAgendas.close(); //Compatibilidad Almacenes TXT y SQL
	   
          almacenCatalogos.close(); //Compatibilidad Almacenes TXT y SQL
	   
          almacenCitas.close(); //Compatibilidad Almacenes TXT y SQL
	   
          almacenUsuarios.close(); //Compatibilidad Almacenes TXT y SQL
	   
          almacenPerfiles.close(); //Compatibilidad Almacenes TXT y SQL
	   
          almacenPermisos.close(); //Compatibilidad Almacenes TXT y SQL
	   
      } else if (evt.getActionCommand().equals("salir")) {
    	  System.exit(0);
      } else if (evt.getActionCommand().equals("acercade")) {
           new JDialog(FrmMenuPrincipal.this, "Acerca de", true).setVisible(true);
      } else if (evt.getActionCommand().equals("personas.listado")) {
          frmListadoPersonas.reset();
          frmListadoPersonas.moveToFront();
      } else if (evt.getActionCommand().equals("agendas.listado")) {
          frmListadoAgendas.reset();
          frmListadoAgendas.moveToFront();
      } else if (evt.getActionCommand().equals("catalogos.listado")) {
          frmListadoCatalogos.reset();
          frmListadoCatalogos.moveToFront();
      } else if (evt.getActionCommand().equals("citas.listado")) {
          frmListadoCitas.reset();
          frmListadoCitas.moveToFront();
      } else if (evt.getActionCommand().equals("usuarios.listado")) {
          frmListadoUsuarios.reset();
          frmListadoUsuarios.moveToFront();
      } else if (evt.getActionCommand().equals("perfiles.listado")) {
          frmListadoPerfiles.reset();
          frmListadoPerfiles.moveToFront();
      } else if (evt.getActionCommand().equals("permisos.listado")) {
          frmListadoPermisos.reset();
          frmListadoPermisos.moveToFront();
      } else if (evt.getActionCommand().equals("personas.nuevo")) {
          frmNuevoPersonas.reset();
          frmNuevoPersonas.moveToFront();
      } else if (evt.getActionCommand().equals("agendas.nuevo")) {
          frmNuevoAgendas.reset();
          frmNuevoAgendas.moveToFront();
      } else if (evt.getActionCommand().equals("catalogos.nuevo")) {
          frmNuevoCatalogos.reset();
          frmNuevoCatalogos.moveToFront();
      } else if (evt.getActionCommand().equals("citas.nuevo")) {
          frmNuevoCitas.reset();
          frmNuevoCitas.moveToFront();
      } else if (evt.getActionCommand().equals("usuarios.nuevo")) {
          frmNuevoUsuarios.reset();
          frmNuevoUsuarios.moveToFront();
      } else if (evt.getActionCommand().equals("perfiles.nuevo")) {
          frmNuevoPerfiles.reset();
          frmNuevoPerfiles.moveToFront();
      } else if (evt.getActionCommand().equals("permisos.nuevo")) {
          frmNuevoPermisos.reset();
          frmNuevoPermisos.moveToFront();
      }
   }

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FrmMenuPrincipal application = new FrmMenuPrincipal();
		application.setVisible(true);
	}


}

