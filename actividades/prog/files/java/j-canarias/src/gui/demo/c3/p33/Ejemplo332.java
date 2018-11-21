package gui.demo.c3.p33;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Ejemplo332 {

	public Ejemplo332() {
		JFrame ventana=new JFrame("Ejemplo 3.3.1");
		ventana.setSize(300,200);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnBoton1=new JButton("Botón 1");
		JButton btnBoton2=new JButton("Botón 2");
		JButton btnBoton3=new JButton("Botón 3");
		JButton btnBoton4=new JButton("Botón 4");
		
		JPanel pnlContenido=new JPanel();
		pnlContenido.setLayout(new GridBagLayout());

		GridBagConstraints config1=new GridBagConstraints();
		config1.gridx=0;
		config1.gridy=0;
		config1.weightx=0.0; //***Nuevo***
		pnlContenido.add(btnBoton1,config1);
		
		GridBagConstraints config2=new GridBagConstraints();
		config2.gridx=1;
		config2.gridy=0;
		config2.weightx=1.0; //***nuevo***
		config2.fill=GridBagConstraints.HORIZONTAL; //***
		pnlContenido.add(btnBoton2,config2);
		
		GridBagConstraints config3=new GridBagConstraints();
		config3.gridx=0;
		config3.gridy=1;
		config3.weightx=0.0; //***
		pnlContenido.add(btnBoton3,config3);

		GridBagConstraints config4=new GridBagConstraints();
		config4.gridx=1;
		config4.gridy=1;
		config4.weightx=1.0; //***
		config4.fill=GridBagConstraints.HORIZONTAL;//***
		pnlContenido.add(btnBoton4,config4);

		ventana.setContentPane(pnlContenido);
		ventana.setVisible(true);
	}
	
	public static void main(String[] args) {
		new Ejemplo332();
	}
}
