package gui.demo.c2.p22;

import javax.swing.JFrame;

//import david.canarias.gui.nivel21.Traductor;

public class VentanaTraductor extends JFrame{
	static final long serialVersionUID=1;
	
	public VentanaTraductor(Traductor traductor) {

		this.setContentPane(new PanelTraductor(traductor));
		this.setTitle("Traductor de Español a Inglés (2.2)");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(300,200);
		this.setVisible(true);
	}
}
