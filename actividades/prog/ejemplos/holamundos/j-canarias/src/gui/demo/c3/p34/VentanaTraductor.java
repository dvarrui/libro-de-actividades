package gui.demo.c3.p34;

import javax.swing.JFrame;

public class VentanaTraductor extends JFrame {
	static final long serialVersionUID=1;
	
	public VentanaTraductor(Traductor traductor) {
		this.setContentPane(new PanelTraductor(traductor));
		this.setTitle("Traductor - Ejemplo 3.4");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}

	public static void main(String[] args) {
		Traductor t = new Traductor();
		
		VentanaTraductor v = new VentanaTraductor(t);
		v.setVisible(true);
	}
}
