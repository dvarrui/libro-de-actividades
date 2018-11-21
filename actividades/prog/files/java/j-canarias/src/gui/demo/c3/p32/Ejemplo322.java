package gui.demo.c3.p32;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Ejemplo322 {

	public Ejemplo322() {
		JFrame ventana=new JFrame("Ejemplo 3.2.2");
		ventana.setSize(300,200);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnBoton=new JButton("Botón");
		JPanel pnlContenido=new JPanel();
		
		pnlContenido.setLayout(new GridBagLayout());
		GridBagConstraints config=new GridBagConstraints();
		config.weightx=1.0;
		config.weighty=1.0;
		//***nuevo***
		config.anchor=GridBagConstraints.EAST;
		config.insets=new Insets(5,5,5,5);
		
		pnlContenido.add(btnBoton,config);
		
		ventana.setContentPane(pnlContenido);
		ventana.setVisible(true);
	}
	
	public static void main(String[] args) {
		new Ejemplo322();
	}
}
