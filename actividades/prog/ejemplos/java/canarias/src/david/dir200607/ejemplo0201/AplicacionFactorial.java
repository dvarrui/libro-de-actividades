package david.dir200607.ejemplo0201;

import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class AplicacionFactorial extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;
	private PanelFactorial panelFactorial = null;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				AplicacionFactorial thisClass = new AplicacionFactorial();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
	}

	/**
	 * This is the default constructor
	 */
	public AplicacionFactorial() {
		super();
		initialize();
		panelFactorial = new PanelFactorial();
		this.jContentPane.add(panelFactorial);
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(233, 156);
		this.setContentPane(getJContentPane());
		this.setTitle("Ejemplo 0201");
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
		}
		return jContentPane;
	}

}  //  @jve:decl-index=0:visual-constraint="10,12"
