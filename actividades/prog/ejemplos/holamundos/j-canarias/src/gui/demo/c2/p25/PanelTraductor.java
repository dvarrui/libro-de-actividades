package gui.demo.c2.p25;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gui.demo.c2.p21.Traductor;

public class PanelTraductor extends JPanel implements ActionListener, MouseListener {
	static final long serialVersionUID=1;
	
	private Traductor traductor;
	
	private JLabel lblEtiqueta;
	private JTextField txtCampo;
	private JButton btnBoton;
	private JButton btnCambioIdioma;
	private JLabel lblIdioma;

	//Nuevo tributo
	private Color colorAnterior;
	
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
		
		//Nuevo
		lblEtiqueta.addMouseListener(this);
		txtCampo.addMouseListener(this);
		btnBoton.addMouseListener(this);
		btnCambioIdioma.addMouseListener(this);
		lblIdioma.addMouseListener(this);
		
		this.add(txtCampo);
		this.add(btnBoton);
		this.add(lblEtiqueta);
		this.add(btnCambioIdioma);
		this.add(lblIdioma);
	}
	
	public void actionPerformed(ActionEvent e) {
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
	
	//Nuevos métodos
	public void mouseClicked(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	
	public void mouseExited(MouseEvent e) {
		Component component=e.getComponent();
		component.setForeground(colorAnterior);
	}
	
	public void mouseEntered(MouseEvent e) {
		Component component=e.getComponent();
		colorAnterior=component.getForeground();
		component.setForeground(java.awt.Color.BLUE);
	}
}
