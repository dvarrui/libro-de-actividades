package david.dir200607.ejemplo0301;

import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.BoxLayout;

public class AplicacionAgenda extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;
	private Agenda miAgenda = null;
	private PanelAgregarContacto pnlAgregar = null;
	private PanelConsultarContacto pnlConsultar = null;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				AplicacionAgenda thisClass = new AplicacionAgenda();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
	}

	/**
	 * This is the default constructor
	 */
	public AplicacionAgenda() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(230, 420);
		this.setContentPane(getJContentPane());
		this.setTitle("Ejemplo 03.01");
		
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
