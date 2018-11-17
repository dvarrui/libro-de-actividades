package gui.demo.c1.p10;

import javax.swing.*;
import java.awt.event.*;

public class ConversorEuros implements ActionListener {
	private JLabel lblEtiqueta1;
	private JTextField txtCampoDeTexto;
	private JButton btnBoton;
	private JLabel lblEtiqueta2;
	
	public ConversorEuros() {
		//Creación de la interfaz gráfica
		
		//Crear la ventana
		JFrame ventana=new JFrame("Conversor de Euros a pesetas");
		ventana.setSize(300,200);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Crear los componentes
		lblEtiqueta1=new JLabel("Importe en Euros");
		txtCampoDeTexto=new JTextField(20);
		btnBoton=new JButton("Convertir");
		lblEtiqueta2=new JLabel("Pulse para obtener el importe en pesetas");
		
		//Crear un contenedor
		JPanel pnlPanelDeContenido=new JPanel();
		
		//Configurar el contenedor
		pnlPanelDeContenido.add(lblEtiqueta1);
		pnlPanelDeContenido.add(txtCampoDeTexto);
		pnlPanelDeContenido.add(btnBoton);
		pnlPanelDeContenido.add(lblEtiqueta2);
		
		//Configurar la ventana para mostrar el panel contenedor
		ventana.setContentPane(pnlPanelDeContenido);
		
		//Asociar un oyente al botón
		btnBoton.addActionListener(this);
		
		//Hacer visible la ventana
		ventana.setVisible(true);
	}
	
	public static void main(String[] args) {
		new ConversorEuros();
	}
	
	public void actionPerformed(ActionEvent e) {
		//Manejar evento
		try {
			double euros=Double.parseDouble(txtCampoDeTexto.getText());
			double pesetas=euros*166.386;
			lblEtiqueta2.setText("Equivale a "+pesetas+" pesetas.");
		} catch(NumberFormatException e2) {
			lblEtiqueta2.setText("En el campo de texto no hay un número!");
		}
	}

}
