package gui.demo.c2.p24;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gui.demo.c2.p21.Traductor;

public class PanelTraductor extends JPanel implements ActionListener {
	static final long serialVersionUID=1;
	
	private Traductor traductor;
	
	private JLabel lblEtiqueta;
	private JTextField txtCampo;
	private JButton btnBoton;
	//Dos nuevos elementos
	private JButton btnCambioIdioma;
	private JLabel lblIdioma;
	
	public PanelTraductor(Traductor traductor) {
		this.traductor=traductor;
		lblEtiqueta=new JLabel("Pulsa el botón para traducir");
		txtCampo=new JTextField(20);
		btnBoton=new JButton("Traducir");
		btnCambioIdioma=new JButton("Invierte sentido traducción");
		lblIdioma=new JLabel();
		
		this.muestraSentidoTraduccion();
		
		btnBoton.addActionListener(this);
		txtCampo.addActionListener(this);
		btnCambioIdioma.addActionListener(this);
				
		this.add(txtCampo);
		this.add(btnBoton);
		this.add(lblEtiqueta);
		this.add(btnCambioIdioma);
		this.add(lblIdioma);
	}
	
	public void actionPerformed(ActionEvent e) {
		//Se amplía el gestor de eventos
		if (e.getSource()==btnCambioIdioma) {
			traductor.invierteIdioma();
			this.muestraSentidoTraduccion();
		} else {
			lblEtiqueta.setText(traductor.traduceTexto(txtCampo.getText()));
		}
	}
	
	private void muestraSentidoTraduccion() {
		lblIdioma.setText(Traductor.getCadenaIdioma(traductor.getIdiomaOrigen())+"-"+Traductor.getCadenaIdioma(traductor.getIdiomaDestino()));
	}
}
