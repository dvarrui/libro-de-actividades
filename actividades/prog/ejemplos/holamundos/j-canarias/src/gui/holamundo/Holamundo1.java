package gui.holamundo;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Holamundo1 extends JFrame {
	public static final long serialVersionUID=1;

	public Holamundo1() {
		String s = "Hola Mundo1!";
		JLabel e = new JLabel(s);
		this.getContentPane().add(e);
		
		this.setSize(200, 100);
		this.setTitle(s);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Se crea un objeto del tipo HolaMundo1
		Holamundo1 h = new Holamundo1();
		// Se invoca el método show del objeto referenciado por la variable h
		h.setVisible(true);
	}

}
