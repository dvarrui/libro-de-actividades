package david.dir200607.ejemplo0205;

import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;

import java.awt.event.FocusListener;
import java.awt.event.KeyListener;
	
public class VentanaConversor extends JFrame 
	implements FocusListener, KeyListener {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JPanel pnlConversor = null;

	private JLabel lblEuro = null;

	private JTextField txtEuro = null;

	private JLabel lblDolar = null;

	private JTextField txtDolar = null;

	private JLabel lblLibra = null;

	private JTextField txtLibra = null;

	private JLabel lblYen = null;

	private JTextField txtYen = null;
	
	private Conversor conversor = null;

	/**
	 * This method initializes pnlConversor	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPnlConversor() {
		if (pnlConversor == null) {
			GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
			gridBagConstraints7.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints7.gridy = 3;
			gridBagConstraints7.weightx = 1.0;
			gridBagConstraints7.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints7.gridx = 1;
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.gridx = 0;
			gridBagConstraints6.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints6.gridy = 3;
			lblYen = new JLabel();
			lblYen.setText("Yen");
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints5.gridy = 2;
			gridBagConstraints5.weightx = 1.0;
			gridBagConstraints5.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints5.gridx = 1;
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.gridx = 0;
			gridBagConstraints4.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints4.gridy = 2;
			lblLibra = new JLabel();
			lblLibra.setText("Libra");
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints3.gridy = 1;
			gridBagConstraints3.weightx = 1.0;
			gridBagConstraints3.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints3.gridx = 1;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 0;
			gridBagConstraints2.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints2.gridy = 1;
			lblDolar = new JLabel();
			lblDolar.setText("Dolar");
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints1.gridy = 0;
			gridBagConstraints1.weightx = 1.0;
			gridBagConstraints1.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints1.gridx = 1;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints.gridy = 0;
			lblEuro = new JLabel();
			lblEuro.setText("Euros");
			pnlConversor = new JPanel();
			pnlConversor.setLayout(new GridBagLayout());
			pnlConversor.add(lblEuro, gridBagConstraints);
			pnlConversor.add(getTxtEuro(), gridBagConstraints1);
			pnlConversor.add(lblDolar, gridBagConstraints2);
			pnlConversor.add(getTxtDolar(), gridBagConstraints3);
			pnlConversor.add(lblLibra, gridBagConstraints4);
			pnlConversor.add(getTxtLibra(), gridBagConstraints5);
			pnlConversor.add(lblYen, gridBagConstraints6);
			pnlConversor.add(getTxtYen(), gridBagConstraints7);
		}
		return pnlConversor;
	}

	/**
	 * This method initializes txtEuro	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtEuro() {
		if (txtEuro == null) {
			txtEuro = new JTextField();
			txtEuro.addFocusListener(this);
			txtEuro.addKeyListener(this);
		}
		return txtEuro;
	}

	/**
	 * This method initializes txtDolar	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtDolar() {
		if (txtDolar == null) {
			txtDolar = new JTextField();
			txtDolar.addFocusListener(this);
			txtDolar.addKeyListener(this);
		}
		return txtDolar;
	}

	/**
	 * This method initializes txtLibra	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtLibra() {
		if (txtLibra == null) {
			txtLibra = new JTextField();
			txtLibra.addFocusListener(this);
			txtLibra.addKeyListener(this);
		}
		return txtLibra;
	}

	/**
	 * This method initializes txtYen	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtYen() {
		if (txtYen == null) {
			txtYen = new JTextField();
			txtYen.addFocusListener(this);
			txtYen.addKeyListener(this);
		}
		return txtYen;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				VentanaConversor thisClass = new VentanaConversor();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
	}

	/**
	 * This is the default constructor
	 */
	public VentanaConversor() {
		super();
		initialize();
		
		conversor = new Conversor();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(281, 170);
		this.setContentPane(getJContentPane());
		this.setTitle("Aplicación Conversor");
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
			jContentPane.add(getPnlConversor(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

	public void focusLost(java.awt.event.FocusEvent e) {
		int i=0;
		double d;
		d = Double.parseDouble("0"+((JTextField)e.getSource()).getText());
		
		if (e.getSource()==this.txtEuro) i=0;
		if (e.getSource()==this.txtDolar) i=1;
		if (e.getSource()==this.txtLibra) i=2;
		if (e.getSource()==this.txtYen) i=3;
		
		this.txtEuro.setText(""+conversor.convertir(i,0,d));
		this.txtDolar.setText(""+conversor.convertir(i,1,d));
		this.txtLibra.setText(""+conversor.convertir(i,2,d));
		this.txtYen.setText(""+conversor.convertir(i,3,d));
		
		/*this.txtEuro.setEnabled(true);
		this.txtDolar.setEnabled(true);
		this.txtLibra.setEnabled(true);
		this.txtYen.setEnabled(true);*/
		((JTextField)e.getSource()).setBackground(java.awt.Color.white);
	}

	public void focusGained(java.awt.event.FocusEvent e) {
	}
	
	public void keyPressed(java.awt.event.KeyEvent e) {
		/*this.txtEuro.setEnabled(false);
		this.txtDolar.setEnabled(false);
		this.txtLibra.setEnabled(false);
		this.txtYen.setEnabled(false);
		((JTextField)e.getSource()).setEnabled(true);*/
		((JTextField)e.getSource()).setBackground(java.awt.Color.YELLOW);
	}
	public void keyTyped(java.awt.event.KeyEvent e) {
	}
	public void keyReleased(java.awt.event.KeyEvent e) {
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
