package gui.demo.c3.p32;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Ejemplo323 {

	public Ejemplo323() {
		JFrame ventana=new JFrame("Ejemplo 3.2.3");
		ventana.setSize(300,200);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnBoton=new JButton("Botón");
		JPanel pnlContenido=new JPanel();
		
		pnlContenido.setLayout(new GridBagLayout());
		GridBagConstraints config=new GridBagConstraints();
		config.weightx=1.0;
		config.weighty=1.0;
		
		//***nuevo***
		config.fill=GridBagConstraints.VERTICAL;
		config.ipadx=4;
		
		pnlContenido.add(btnBoton,config);
		
		ventana.setContentPane(pnlContenido);
		ventana.setVisible(true);
	}
	
	public static void main(String[] args) {
		new Ejemplo323();
	}
}
