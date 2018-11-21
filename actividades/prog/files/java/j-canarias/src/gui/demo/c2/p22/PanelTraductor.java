package gui.demo.c2.p22;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

//import david.canarias.gui.nivel21.Traductor;

public class PanelTraductor extends JPanel implements ActionListener {
	static final long serialVersionUID=1;
	
	private Traductor traductor;
	
	private JLabel lblEtiqueta;
	private JTextField txtCampo;
	private JButton btnBoton;
	
	public PanelTraductor(Traductor traductor) {
		this.traductor=traductor;
		btnBoton=new JButton("Traducir");
		lblEtiqueta=new JLabel("Pulsa el botón para traducir");
		txtCampo=new JTextField(20);
		
		btnBoton.addActionListener(this);
		
		this.add(txtCampo);
		this.add(btnBoton);
		this.add(lblEtiqueta);
	}
	
	public void actionPerformed(ActionEvent e) {
		lblEtiqueta.setText(traductor.traduceTexto(txtCampo.getText()));
	}
}
