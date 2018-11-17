package david.orotava.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;

import david.orotava.lib.Contenedor;
import david.orotava.lib.Elemento;

public class Principal extends JFrame implements ActionListener {
	public static final long serialVersionUID=1;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Principal application = new Principal();
		application.setVisible(true);
	}

	private JButton btnDirectorio = null;

	private JButton btnIniciar = null;

	private JButton btnSalida = null;

	private JFileChooser dlgDirectorio = null;

	private JFileChooser dlgSalida = null;

	private JPanel jContentPane = null;

	private JTextPane txtCreditos = null;

	private JTextField txtDirectorio = null;

	private JTextField txtSalida = null;

	/**
	 * This is the default constructor
	 */
	public Principal() {
		super();
		initialize();

		dlgDirectorio = new JFileChooser();
		dlgDirectorio.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		dlgSalida = new JFileChooser();
		dlgSalida.setFileSelectionMode(JFileChooser.FILES_ONLY);
	}

	public void actionPerformed(ActionEvent evt) {
		try {
			Contenedor c = new Contenedor();

			if (this.txtSalida.getText().length() == 0) {
				c.setSalida(System.out);
			} else {
				c.setSalida(this.txtSalida.getText());
			}

			if (evt.getActionCommand().equals("Iniciar")) {
				System.out.println("Iniciando...");
				c.setDirRaiz(this.txtDirectorio.getText());
				c.leer();
				if (this.txtSalida.getText().toLowerCase().endsWith(".html")
						|| this.txtSalida.getText().toLowerCase().endsWith(".htm")) {
					c.mostrar(Elemento.MOSTRAR_HTML);
				} else {
					c.mostrar();
				}
				System.out.println("Fin");
				JOptionPane.showMessageDialog(this, "Proceso finalizado");
			}

			if (evt.getActionCommand().equals("Directorio")) {

				int returnVal = dlgDirectorio.showOpenDialog(this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					txtDirectorio.setText(dlgDirectorio.getSelectedFile()
							.getCanonicalPath());
					System.out.println(" * Directorio raíz   : "
							+ dlgDirectorio.getSelectedFile()
									.getCanonicalPath());
				}
			}

			if (evt.getActionCommand().equals("Salida")) {
				int returnVal = dlgSalida.showOpenDialog(this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					txtSalida.setText(dlgSalida.getSelectedFile()
							.getCanonicalPath());
					System.out.println(" * Fichero de salida : "
							+ dlgSalida.getSelectedFile().getCanonicalPath());
				}
			}
		} catch (Exception ex) {
			System.err.println(ex);
		}
	}

	/**
	 * This method initializes jButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBtnDirectorio() {
		if (btnDirectorio == null) {
			btnDirectorio = new JButton();
			btnDirectorio.setBounds(new java.awt.Rectangle(15, 14, 149, 33));
			btnDirectorio.setText("Directorio");
			btnDirectorio.setIcon(new ImageIcon(
					"david/orotava/tango/document-open.png"));
			btnDirectorio.addActionListener(this);
		}
		return btnDirectorio;
	}

	/**
	 * This method initializes jButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBtnIniciar() {
		if (btnIniciar == null) {
			btnIniciar = new JButton();
			btnIniciar.setBounds(new java.awt.Rectangle(15,105,150,29));
			btnIniciar.setText("Iniciar");
			btnIniciar.setIcon(new ImageIcon(
					"david/orotava/tango/emblem-system.png"));
			btnIniciar.addActionListener(this);
		}
		return btnIniciar;
	}

	/**
	 * This method initializes jButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBtnSalida() {
		if (btnSalida == null) {
			btnSalida = new JButton();
			btnSalida.setBounds(new java.awt.Rectangle(14, 60, 150, 33));
			btnSalida.setIcon(new ImageIcon(
					"david/orotava/tango/document-save.png"));
			btnSalida.setText("Salida");
			btnSalida.addActionListener(this);
		}
		return btnSalida;
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getBtnDirectorio(), null);
			jContentPane.add(getTxtDirectorio(), null);
			jContentPane.add(getBtnSalida(), null);
			jContentPane.add(getTxtSalida(), null);
			jContentPane.add(getBtnIniciar(), null);
			jContentPane.add(getTxtCreditos(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jTextPane
	 * 
	 * @return javax.swing.JTextPane
	 */
	private JTextPane getTxtCreditos() {
		if (txtCreditos == null) {
			txtCreditos = new JTextPane();
			txtCreditos.setBounds(new java.awt.Rectangle(15,149,431,371));
			txtCreditos.setContentType("text/html");
			txtCreditos
					.setText("<html><head></head><body>\n <h3>Aplicación</h3>Proyecto Orotava - 1.1.0<br>Este programa realiza la comprobación de un sitio WEB local.<br><h3>Funcionamiento</h3><ol><li>Seleccionar el directorio raíz del proyecto.</li><li>Indicar el fichero que guardará los resultados. <ul><li>Si no se indica nada se mostrarán por la consola.</li><li>Si el fichero tiene extensión <i>.html</i> los datos se guardarán en formato HTML.</li></ul></li><li>Pulsar Iniciar.</li></ol><h3>Créditos</h3>David Vargas Ruiz (dvargas@canarias.org)<br>Tenerife, 13 de diciembre de 2005.<br> </body>\n</html>");
			txtCreditos.setEditable(false);
			txtCreditos.setBackground(java.awt.SystemColor.controlHighlight);
			txtCreditos.setBorder(new LineBorder(new java.awt.Color(0, 0, 0),
					1, false));
		}
		return txtCreditos;
	}

	/**
	 * This method initializes jTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtDirectorio() {
		if (txtDirectorio == null) {
			txtDirectorio = new JTextField();
			txtDirectorio.setBounds(new java.awt.Rectangle(172, 14, 275, 32));
			txtDirectorio.setText("");
		}
		return txtDirectorio;
	}

	/**
	 * This method initializes jTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxtSalida() {
		if (txtSalida == null) {
			txtSalida = new JTextField();
			txtSalida.setBounds(new java.awt.Rectangle(172, 60, 274, 33));
		}
		return txtSalida;
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(464, 561);
		this.setContentPane(getJContentPane());
		this.setTitle("Proyecto OROTAVA");
	}

} // @jve:decl-index=0:visual-constraint="10,10"
