package david.dir200607.ejemplo0301;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JApplet;

public class AppletAgenda extends JApplet {
	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private Agenda miAgenda = null;
	private PanelAgregarContacto pnlAgregar = null;
	private PanelConsultarContacto pnlConsultar = null;
	
	/**
	 * This is the xxx default constructor
	 */
	public AppletAgenda() {
		super();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	public void init() {
		this.setSize(230, 420);
		this.setContentPane(getJContentPane());
		
	
		miAgenda = new Agenda();
		pnlAgregar = new PanelAgregarContacto();
		pnlAgregar.setAgenda(miAgenda);
		this.jContentPane.add(pnlAgregar, BorderLayout.NORTH);
		
		pnlConsultar = new PanelConsultarContacto();
		this.pnlConsultar.setEnabled(false);
		pnlConsultar.setAgenda(miAgenda);
		this.jContentPane.add(pnlConsultar, BorderLayout.SOUTH);
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BoxLayout(getJContentPane(), BoxLayout.Y_AXIS));
		}
		return jContentPane;
	}

}
