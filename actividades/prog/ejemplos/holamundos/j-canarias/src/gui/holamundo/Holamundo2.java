package gui.holamundo;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Holamundo2 extends JFrame {
	public static final long serialVersionUID=1;

	public Holamundo2() {
		String s = "Hola Mundo2!";
		JLabel e = new JLabel(s);
		this.getContentPane().add(e);
		
		this.setSize(200, 100);
		this.setTitle(s);
	}
}
