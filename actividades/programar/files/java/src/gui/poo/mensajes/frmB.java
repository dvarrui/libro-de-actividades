package gui.poo.mensajes;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComponent;
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
public class frmB extends javax.swing.JFrame implements ActionListener {
	static final long serialVersionUID=1;
	
	private JPanel jPanel1;
	private JButton btnVisible;
	private JButton btnEnviar;
	private JTextField txtEntrada;
	private JLabel lblEntrada;
	private JTextField txtSalida;
	private JLabel lblSalida;
	private frmA ventana;
	private JComponent items[];

	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		frmB inst = new frmB();
		inst.setVisible(true);
	}
	
	public frmB() {
		super();
		initGUI();
		items = new JComponent[5];
		items[0] = this.lblSalida;
		items[1] = this.txtSalida;
		items[2] = this.lblEntrada;
		items[3] = this.txtEntrada;
		items[4] = this.btnEnviar;
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			{
				jPanel1 = new JPanel();
				this.getContentPane().add(jPanel1, BorderLayout.CENTER);
				jPanel1.setLayout(null);
				jPanel1.setPreferredSize(new java.awt.Dimension(256, 140));
				jPanel1.setBackground(new java.awt.Color(247,185,28));
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
					btnEnviar = new JButton();
					jPanel1.add(btnEnviar);
					btnEnviar.setText("Enviar mensaje");
					btnEnviar.setBounds(17, 93, 222, 30);
					btnEnviar.addActionListener(this);
				}
				{
					btnVisible = new JButton();
					jPanel1.add(btnVisible);
					btnVisible.setText("Visible");
					btnVisible.setBounds(19, 134, 217, 30);
					btnVisible.addActionListener(this);
				}
			}
			pack();
			this.setSize(276, 208);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setFormularioA(frmA a) {
		ventana = a;
	}
	
	public void actionPerformed(ActionEvent evt) {
		//System.out.println("btnEnviar.actionPerformed, event="+ evt);
		//TODO add your code for btnEnviar.actionPerformed
		if (evt.getActionCommand().equals("Enviar mensaje")) {
			if (ventana==null) {
				txtEntrada.setText("No sé dónde enviar el mensaje");
			} else {
				ventana.enviarMensaje(this.txtSalida.getText());
			}
		}
		else {
			//Visible
			for(int i=0;i<items.length;i++) {
				items[i].setVisible(false);
			}
		}
	}
	
	public void enviarMensaje(String msg) {
		this.txtEntrada.setText(msg);
	}
}
