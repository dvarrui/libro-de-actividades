package gui.demo.c2.p27;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gui.demo.c2.p21.Traductor;

//***Nuevo***
public class PanelTraductor extends JPanel{
	static final long serialVersionUID=1;
	
	private Traductor traductor;
	
	private JLabel lblEtiqueta;
	private JTextField txtCampo;
	private JButton btnBoton;
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
		
		//***Nuevo***
		btnBoton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				traduce();
			}
		});
		txtCampo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				traduce();
			}
		});
		btnCambioIdioma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				invierteSentidoTraduccion();
			}
		});
		
		GestorEventosRollOver gestor=new GestorEventosRollOver();
		
		lblEtiqueta.addMouseListener(gestor);
		txtCampo.addMouseListener(gestor);
		btnBoton.addMouseListener(gestor);
		btnCambioIdioma.addMouseListener(gestor);
		lblIdioma.addMouseListener(gestor);
		
		this.add(txtCampo);
		this.add(btnBoton);
		this.add(lblEtiqueta);
		this.add(btnCambioIdioma);
		this.add(lblIdioma);
	}
	
	protected void invierteSentidoTraduccion() {
		traductor.invierteIdioma();
		this.muestraSentidoTraduccion();
	}
	
	protected void traduce() {
		lblEtiqueta.setText(traductor.traduceTexto(txtCampo.getText()));
	}
	
	private void muestraSentidoTraduccion() {
		lblIdioma.setText(Traductor.getCadenaIdioma(traductor.getIdiomaOrigen())+"-"+Traductor.getCadenaIdioma(traductor.getIdiomaDestino()));
	}
	
}
