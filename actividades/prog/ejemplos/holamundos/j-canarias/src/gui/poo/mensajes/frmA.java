package gui.poo.mensajes;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import javax.swing.WindowConstants;


/**
* This code was generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* *************************************
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED
* for this machine, so Jigloo or this code cannot be used legally
* for any corporate or commercial purpose.
* *************************************
*/
public class frmA extends javax.swing.JFrame implements ActionListener {
	static final long serialVersionUID=1;
	
	private JPanel jPanel1;
	private JButton btnEnviar;
	private JButton btnNueva;
	private JTextField txtEntrada;
	private JLabel lblEntrada;
	private JTextField txtSalida;
	private JLabel lblSalida;
	private frmB ventana;

	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		frmA inst = new frmA();
		inst.setVisible(true);
	}
	
	public frmA() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			{
				jPanel1 = new JPanel();
				this.getContentPane().add(jPanel1, BorderLayout.CENTER);
				jPanel1.setLayout(null);
				jPanel1.setPreferredSize(new java.awt.Dimension(256, 140));
				jPanel1.setBackground(new java.awt.Color(99,176,195));
				{
					lblSalida = new JLabel();
					jPanel1.add(lblSalida);
					lblSalida.setText("Salida");
					lblSalida.setBounds(14, 12, 60, 30);
				}
				{
					txtSalida = new JTextField();
					jPanel1.add(txtSalida);
					txtSalida.setBounds(83, 12, 159, 30);
				}
				{
					lblEntrada = new JLabel();
					jPanel1.add(lblEntrada);
					lblEntrada.setText("Entrada");
					lblEntrada.setBounds(12, 49, 60, 30);
				}
				{
					txtEntrada = new JTextField();
					jPanel1.add(txtEntrada);
					txtEntrada.setBounds(85, 50, 156, 30);
				}
				{
					btnNueva = new JButton();
					jPanel1.add(btnNueva);
					btnNueva.setText("Abrir ventana B");
					btnNueva.setBounds(17, 133, 222, 30);
					btnNueva.addActionListener(this);
				}
				{
					btnEnviar = new JButton();
					jPanel1.add(btnEnviar);
					btnEnviar.setText("Enviar mensaje");
					btnEnviar.setBounds(19, 96, 219, 25);
					btnEnviar.addActionListener(this);
				}
			}
			pack();
			this.setSize(276, 204);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void actionPerformed(ActionEvent evt) {
		/*System.out
			.println("btnNueva.actionPerformed, event="
				+ evt);*/
		if (evt.getActionCommand().equals("Abrir ventana B")) {
			ventana = new frmB();
			ventana.setFormularioA(this);
			ventana.setVisible(true);
		} else if (evt.getActionCommand().equals("Enviar mensaje")) {
			if (ventana==null) {
				this.txtEntrada.setText("No sé dónde enviar el mensaje");
			} else {
				ventana.enviarMensaje(this.txtSalida.getText());
			}
		}
	}
	
	public void enviarMensaje(String msg) {
		this.txtEntrada.setText(msg);
	}
}
