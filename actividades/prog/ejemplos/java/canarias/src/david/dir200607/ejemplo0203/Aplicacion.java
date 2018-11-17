package david.dir200607.ejemplo0203;

import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;

import david.dir200607.ejemplo0201.*;
import david.dir200607.ejemplo0202.*;
import javax.swing.BoxLayout;

public class Aplicacion extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;
	private PanelFactorial pnlFactorial = null;
	private PanelInvertirCadena pnlInvertirCadena = null;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Aplicacion thisClass = new Aplicacion();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
	}

	/**
	 * This is the default constructor
	 */
	public Aplicacion() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(230, 320);
		this.setContentPane(getJContentPane());
		this.setTitle("Ejemplo 02.03");
		
		pnlFactorial = new PanelFactorial();
		pnlInvertirCadena = new PanelInvertirCadena();
		this.jContentPane.add(pnlFactorial, BorderLayout.NORTH);
		this.jContentPane.add(pnlInvertirCadena, BorderLayout.SOUTH);
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
