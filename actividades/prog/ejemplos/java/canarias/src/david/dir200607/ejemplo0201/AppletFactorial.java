package david.dir200607.ejemplo0201;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JApplet;

public class AppletFactorial extends JApplet {
	private static final long serialVersionUID = 1L;
	
	private JPanel jContentPane = null;
	private PanelFactorial panelFactorial = null;
	

	/**
	 * This is the xxx default constructor
	 */
	public AppletFactorial() {
		super();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	public void init() {
		this.setSize(233, 156);
		this.setContentPane(getJContentPane());
		panelFactorial = new PanelFactorial();
		this.jContentPane.add(panelFactorial);
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

}
