package gui.demo.c1.p10;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InterfazSimple {

	public static void main(String[] args) {
		//Crear ventana de la aplicación
		JFrame ventana=new JFrame("Título de la ventana");
		ventana.setSize(300,200);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Crear los componentes
		JLabel etiqueta1=new JLabel("Texto etiqueta");
		JTextField campoDeTexto=new JTextField(20);
		JButton boton=new JButton("Botón");
		
		//Crear un contenedor
		JPanel panelDeContenido=new JPanel();
		
		//Asociar los componentes al contenedor para que los muestre en su interior
		panelDeContenido.add(etiqueta1);
		panelDeContenido.add(campoDeTexto);
		panelDeContenido.add(boton);
		
		//Asociar el contenedor a la ventana para que lo muestre en su interior
		ventana.setContentPane(panelDeContenido);
		
		//Hacer visible la ventana
		ventana.setVisible(true);
	}
}
