package gui.demo.c2.p21;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;;

public class VentanaTraductor extends JFrame implements ActionListener{
	static final long serialVersionUID=1;
	
	private Traductor traductor;
	
	private JLabel lblEtiqueta;
	private JTextField txtCampo;
	private JButton btnBoton;
	
	public VentanaTraductor(Traductor traductor) {
		this.traductor=traductor;
		
		this.setTitle("Traductor de Español a Inglés (2.1)");
		
		JPanel pnlPanelContenido=new JPanel();
		this.setContentPane(pnlPanelContenido);
		
		btnBoton=new JButton("Traducir");
		lblEtiqueta=new JLabel("Pulsa el botón para traducir");
		txtCampo=new JTextField(20);
		
		btnBoton.addActionListener(this);
		
		pnlPanelContenido.add(txtCampo);
		pnlPanelContenido.add(btnBoton);
		pnlPanelContenido.add(lblEtiqueta);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(300,200);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		lblEtiqueta.setText(traductor.traduceTexto(txtCampo.getText()));
	}
}
