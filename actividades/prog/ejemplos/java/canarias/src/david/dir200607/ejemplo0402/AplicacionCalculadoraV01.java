package david.dir200607.ejemplo0402;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.SoftBevelBorder;

public class AplicacionCalculadoraV01 extends JFrame implements ActionListener,
		KeyListener {

	private static final long serialVersionUID = 1L;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				AplicacionCalculadoraV01 thisClass = new AplicacionCalculadoraV01();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
	}

	private JButton btn0 = null;

	private JButton btn1 = null;

	private JButton btn2 = null;

	private JButton btn3 = null;

	private JButton btn4 = null;

	private JButton btn5 = null;

	private JButton btn6 = null;

	private JButton btn7 = null;

	private JButton btn8 = null;

	private JButton btn9 = null;

	private JButton btnAC = null;

	private JButton btnC = null;

	private JButton btnDecimales = null;

	private JButton btnDividir = null;

	private JButton btnIgual = null;

	private JButton btnMA = null;

	private JButton btnMC = null;

	private JButton btnMR = null;

	private JButton btnMultiplicar = null;

	private JButton btnRestar = null;

	private JButton btnSigno = null;

	private JButton btnSumar = null;

	private Calculadora calculadora = null; // Objeto NO Visual

	private JPanel jContentPane = null;

	private JLabel lblStatus = null;

	private JPanel pnlCenter = null;

	private JPanel pnlStatus = null;

	private JTextField txtEntrada = null;

	/**
	 * This is the default constructor
	 */
	public AplicacionCalculadoraV01() {
		super();
		initialize();

		calculadora = new Calculadora();
	}

	/**
	 * Tratamiento de eventos centralizado
	 * 
	 * @param e
	 */
	public void actionPerformed(java.awt.event.ActionEvent e) {
		this.ejecutarAccion(e.getActionCommand());
	}

	/*private void ejecutarAccion(char accion) {
		this.ejecutarAccion(Character.toString(accion));
	}*/
	
	private void ejecutarAccion(String accion) {
		if ("0123456789".indexOf(accion) >= 0) {
			// Se introduce algún dígito
			if (this.txtEntrada.getText().equals("0")) {
				this.txtEntrada.setText(accion);
			} else {
				this.txtEntrada.setText(this.txtEntrada.getText()
						+ accion);
			}
		} else if ((accion.equals("."))
				&& (this.txtEntrada.getText().indexOf(".") < 0)) {
			// Se introduce el punto de los decimales
			if (this.txtEntrada.getText().length() == 0) {
				this.txtEntrada.setText("0.");
			} else {
				this.txtEntrada.setText(this.txtEntrada.getText()
						+ accion);
			}
		} else if ("+-*/".indexOf(accion) >= 0) {
			// Se pulsa el operador +-*/
			calculadora.setNumero(this.txtEntrada.getText());
			calculadora.setOperador(accion);
			this.txtEntrada.setText("0");
			this.lblStatus.setText(calculadora.getEstado());

		} else if (accion.equals("±")) {
			// Se pulsa el cambio de signo
			String n = this.txtEntrada.getText();
			if (n.indexOf("-") == 0)
				n = n.substring(1);
			else
				n = "-" + n;
			this.txtEntrada.setText(n);
		} else if (accion.equals("=")) {
			// Se pulsa la orden '='
			calculadora.setNumero(this.txtEntrada.getText());
			if (calculadora.getAcumulador() == 0.0) {
				this.txtEntrada.setText("0");
			} else {
				this.txtEntrada.setText("" + calculadora.getAcumulador());
			}
			this.lblStatus.setText(calculadora.getEstado());
		} else if (accion.equals("C")) {
			// Se pulsa la orden C = clear display
			this.txtEntrada.setText("0");
		} else if (accion.equals("AC")) {
			// Se pulsa la orden AC = clear display and acumulador
			this.txtEntrada.setText("0");
			calculadora.setNumero(0.0);
			this.lblStatus.setText(calculadora.getEstado());
		} else if (accion.equals("MR")) {
			// Se pulsa la orden MR
			this.txtEntrada.setText("" + calculadora.ejecutarMR());
		} else if (accion.equals("MA")) {
			// Se pulsa la orden MA
			calculadora.ejecutarMA(this.txtEntrada.getText());
		} else if (accion.equals("MC")) {
			// Se pulsa la orden MC = memory clear
			calculadora.ejecutarMC();
		}
	}

	public void keyTyped(java.awt.event.KeyEvent e) {
		//System.out.println("keyTyped()"+e.getKeyChar()); 
											// keyTyped()
		//this.ejecutarAccion(e.getKeyChar());
	}

	public void keyPressed(java.awt.event.KeyEvent e) {
	}

	public void keyReleased(java.awt.event.KeyEvent e) {
	}

	/**
	 * This method initializes btn0
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBtn0() {
		if (btn0 == null) {
			btn0 = new JButton();
			btn0.setText("0");
			btn0.addActionListener(this);
		}
		return btn0;
	}

	/**
	 * This method initializes btn1
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBtn1() {
		if (btn1 == null) {
			btn1 = new JButton();
			btn1.setText("1");
			btn1.addActionListener(this);
		}
		return btn1;
	}

	/**
	 * This method initializes btn2
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBtn2() {
		if (btn2 == null) {
			btn2 = new JButton();
			btn2.setText("2");
			btn2.addActionListener(this);
		}
		return btn2;
	}

	/**
	 * This method initializes btn3
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBtn3() {
		if (btn3 == null) {
			btn3 = new JButton();
			btn3.setText("3");
			btn3.addActionListener(this);
		}
		return btn3;
	}

	/**
	 * This method initializes btn4
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBtn4() {
		if (btn4 == null) {
			btn4 = new JButton();
			btn4.setText("4");
			btn4.addActionListener(this);
		}
		return btn4;
	}

	/**
	 * This method initializes btn5
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBtn5() {
		if (btn5 == null) {
			btn5 = new JButton();
			btn5.setText("5");
			btn5.addActionListener(this);
		}
		return btn5;
	}

	/**
	 * This method initializes btn6
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBtn6() {
		if (btn6 == null) {
			btn6 = new JButton();
			btn6.setText("6");
			btn6.addActionListener(this);
		}
		return btn6;
	}

	/**
	 * This method initializes btn7
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBtn7() {
		if (btn7 == null) {
			btn7 = new JButton();
			btn7.setText("7");
			btn7.addActionListener(this);
		}
		return btn7;
	}

	/**
	 * This method initializes btn8
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBtn8() {
		if (btn8 == null) {
			btn8 = new JButton();
			btn8.setText("8");
			btn8.addActionListener(this);
		}
		return btn8;
	}

	/**
	 * This method initializes btn9
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBtn9() {
		if (btn9 == null) {
			btn9 = new JButton();
			btn9.setText("9");
			btn9.addActionListener(this);
		}
		return btn9;
	}

	/**
	 * This method initializes btnAC
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBtnAC() {
		if (btnAC == null) {
			btnAC = new JButton();
			btnAC.setText("AC");
			btnAC.setToolTipText("Limpiar el valor acumulado");
			btnAC.addActionListener(this);
		}
		return btnAC;
	}

	/**
	 * This method initializes btnC
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBtnC() {
		if (btnC == null) {
			btnC = new JButton();
			btnC.setText("C");
			btnC.setToolTipText("Limpiar la entrada");
			btnC.addActionListener(this);
		}
		return btnC;
	}

	/**
	 * This method initializes btnDecimales
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBtnDecimales() {
		if (btnDecimales == null) {
			btnDecimales = new JButton();
			btnDecimales.setText(".");
			btnDecimales.addActionListener(this);
		}
		return btnDecimales;
	}

	/**
	 * This method initializes btnDividir
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBtnDividir() {
		if (btnDividir == null) {
			btnDividir = new JButton();
			btnDividir.setText("/");
			btnDividir.addActionListener(this);
		}
		return btnDividir;
	}

	/**
	 * This method initializes btnIgual
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBtnIgual() {
		if (btnIgual == null) {
			btnIgual = new JButton();
			btnIgual.setText("=");
			btnIgual.addActionListener(this);
		}
		return btnIgual;
	}

	/**
	 * This method initializes btnMA
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBtnMA() {
		if (btnMA == null) {
			btnMA = new JButton();
			btnMA.setText("MA");
			btnMA.setToolTipText("Añadir el valor a la memoria");
			btnMA.addActionListener(this);
		}
		return btnMA;
	}

	/**
	 * This method initializes btnMC
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBtnMC() {
		if (btnMC == null) {
			btnMC = new JButton();
			btnMC.setText("MC");
			btnMC.setToolTipText("Borrar la memoria");
			btnMC.addActionListener(this);
		}
		return btnMC;
	}

	/**
	 * This method initializes btnMR
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBtnMR() {
		if (btnMR == null) {
			btnMR = new JButton();
			btnMR.setText("MR");
			btnMR.setToolTipText("Mostar el valor de la memoria");
			btnMR.addActionListener(this);
		}
		return btnMR;
	}

	/**
	 * This method initializes btnMultiplicar
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBtnMultiplicar() {
		if (btnMultiplicar == null) {
			btnMultiplicar = new JButton();
			btnMultiplicar.setText("*");
			btnMultiplicar.addActionListener(this);
		}
		return btnMultiplicar;
	}

	/**
	 * This method initializes btnRestar
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBtnRestar() {
		if (btnRestar == null) {
			btnRestar = new JButton();
			btnRestar.setText("-");
			btnRestar.addActionListener(this);
		}
		return btnRestar;
	}

	/**
	 * This method initializes btnSigno
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBtnSigno() {
		if (btnSigno == null) {
			btnSigno = new JButton();
			btnSigno.setText("±");
			btnSigno.setToolTipText("Cambiar el signo del número");
			btnSigno.addActionListener(this);
		}
		return btnSigno;
	}

	/**
	 * This method initializes btnSumar
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBtnSumar() {
		if (btnSumar == null) {
			btnSumar = new JButton();
			btnSumar.setText("+");
			btnSumar.addActionListener(this);
		}
		return btnSumar;
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
			jContentPane.add(getPnlCenter(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

	/**
	 * This method initializes pnlCenter
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getPnlCenter() {
		if (pnlCenter == null) {
			GridBagConstraints gridBagConstraints16 = new GridBagConstraints();
			gridBagConstraints16.gridx = 0;
			gridBagConstraints16.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints16.gridwidth = 7;
			gridBagConstraints16.fill = GridBagConstraints.BOTH;
			gridBagConstraints16.gridy = 5;
			GridBagConstraints gridBagConstraints81 = new GridBagConstraints();
			gridBagConstraints81.gridx = 3;
			gridBagConstraints81.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints81.gridheight = 2;
			gridBagConstraints81.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints81.gridy = 1;
			GridBagConstraints gridBagConstraints71 = new GridBagConstraints();
			gridBagConstraints71.gridx = 5;
			gridBagConstraints71.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints71.gridy = 2;
			GridBagConstraints gridBagConstraints61 = new GridBagConstraints();
			gridBagConstraints61.gridx = 2;
			gridBagConstraints61.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints61.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints61.gridy = 4;
			GridBagConstraints gridBagConstraints51 = new GridBagConstraints();
			gridBagConstraints51.gridx = 6;
			gridBagConstraints51.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints51.gridy = 4;
			GridBagConstraints gridBagConstraints41 = new GridBagConstraints();
			gridBagConstraints41.gridx = 6;
			gridBagConstraints41.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints41.gridy = 3;
			GridBagConstraints gridBagConstraints31 = new GridBagConstraints();
			gridBagConstraints31.gridx = 6;
			gridBagConstraints31.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints31.gridy = 2;
			GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
			gridBagConstraints21.gridx = 6;
			gridBagConstraints21.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints21.gridy = 1;
			GridBagConstraints gridBagConstraints15 = new GridBagConstraints();
			gridBagConstraints15.gridx = 5;
			gridBagConstraints15.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints15.gridy = 1;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.fill = GridBagConstraints.BOTH;
			gridBagConstraints.gridy = 0;
			gridBagConstraints.weightx = 1.0;
			gridBagConstraints.gridwidth = 7;
			gridBagConstraints.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints.gridx = 0;
			GridBagConstraints gridBagConstraints14 = new GridBagConstraints();
			gridBagConstraints14.gridx = 5;
			gridBagConstraints14.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints14.gridy = 4;
			GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
			gridBagConstraints13.gridx = 5;
			gridBagConstraints13.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints13.gridy = 3;
			GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
			gridBagConstraints12.gridx = 3;
			gridBagConstraints12.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints12.gridy = 4;
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.gridx = 3;
			gridBagConstraints11.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints11.gridy = 3;
			GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
			gridBagConstraints10.gridx = 0;
			gridBagConstraints10.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints10.gridwidth = 2;
			gridBagConstraints10.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints10.gridy = 4;
			GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
			gridBagConstraints9.gridx = 2;
			gridBagConstraints9.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints9.gridy = 3;
			GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
			gridBagConstraints8.gridx = 1;
			gridBagConstraints8.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints8.gridy = 3;
			GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
			gridBagConstraints7.gridx = 0;
			gridBagConstraints7.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints7.gridy = 3;
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.gridx = 2;
			gridBagConstraints6.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints6.gridy = 2;
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.gridx = 1;
			gridBagConstraints5.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints5.gridy = 2;
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.gridx = 0;
			gridBagConstraints4.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints4.gridy = 2;
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.gridx = 2;
			gridBagConstraints3.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints3.gridy = 1;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 1;
			gridBagConstraints2.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints2.gridy = 1;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.gridx = 0;
			gridBagConstraints1.ipadx = 0;
			gridBagConstraints1.ipady = 0;
			gridBagConstraints1.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints1.gridy = 1;
			pnlCenter = new JPanel();
			pnlCenter.setLayout(new GridBagLayout());
			pnlCenter.add(getBtn7(), gridBagConstraints1);
			pnlCenter.add(getBtn8(), gridBagConstraints2);
			pnlCenter.add(getBtn9(), gridBagConstraints3);
			pnlCenter.add(getBtn4(), gridBagConstraints4);
			pnlCenter.add(getBtn5(), gridBagConstraints5);
			pnlCenter.add(getBtn6(), gridBagConstraints6);
			pnlCenter.add(getBtn1(), gridBagConstraints7);
			pnlCenter.add(getBtn2(), gridBagConstraints8);
			pnlCenter.add(getBtn3(), gridBagConstraints9);
			pnlCenter.add(getBtn0(), gridBagConstraints10);
			pnlCenter.add(getBtnSumar(), gridBagConstraints11);
			pnlCenter.add(getBtnRestar(), gridBagConstraints12);
			pnlCenter.add(getBtnMultiplicar(), gridBagConstraints13);
			pnlCenter.add(getBtnDividir(), gridBagConstraints14);
			pnlCenter.add(getTxtEntrada(), gridBagConstraints);
			pnlCenter.add(getBtnC(), gridBagConstraints15);
			pnlCenter.add(getBtnAC(), gridBagConstraints21);
			pnlCenter.add(getBtnMR(), gridBagConstraints31);
			pnlCenter.add(getBtnMA(), gridBagConstraints41);
			pnlCenter.add(getBtnMC(), gridBagConstraints51);
			pnlCenter.add(getBtnDecimales(), gridBagConstraints61);
			pnlCenter.add(getBtnSigno(), gridBagConstraints71);
			pnlCenter.add(getBtnIgual(), gridBagConstraints81);
			pnlCenter.add(getPnlStatus(), gridBagConstraints16);
			pnlCenter.addKeyListener(this);
		}
		return pnlCenter;
	}

	/**
	 * This method initializes pnlStatus
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getPnlStatus() {
		if (pnlStatus == null) {
			lblStatus = new JLabel();
			lblStatus.setText("David Vargas Ruiz");
			pnlStatus = new JPanel();
			pnlStatus.setLayout(new GridBagLayout());
			pnlStatus.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
			pnlStatus.setBackground(SystemColor.activeCaptionBorder);
			pnlStatus.add(lblStatus, new GridBagConstraints());
		}
		return pnlStatus;
	}

	/**
	 * This method initializes txtEntrada
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtEntrada() {
		if (txtEntrada == null) {
			txtEntrada = new JTextField();
			txtEntrada.setBackground(new Color(204, 255, 204));
			txtEntrada.setText("0");
			txtEntrada.setFont(new Font("Dialog", Font.BOLD, 12));
			txtEntrada.setHorizontalAlignment(JTextField.RIGHT);
			txtEntrada.addKeyListener(this);
		}
		return txtEntrada;
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(341, 242);
		this.setContentPane(getJContentPane());
		this.setTitle("Calculadora");
		this.addKeyListener(this);
	}
} // @jve:decl-index=0:visual-constraint="10,10"
