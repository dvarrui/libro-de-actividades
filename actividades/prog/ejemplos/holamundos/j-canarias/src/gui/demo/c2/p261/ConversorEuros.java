package gui.demo.c2.p261;

import javax.swing.*;
import java.awt.event.*;

//Quitamos: implements ActionListener
public class ConversorEuros {
	private JLabel lblEtiqueta1;
	private JTextField txtCampoDeTexto;
	private JButton btnBoton;
	private JLabel lblEtiqueta2;
	
	public ConversorEuros() {
		//Creación de la interfaz gráfica
		
		//Crear la ventana
		JFrame ventana=new JFrame("Conversor de Euros a pesetas (2.6.1)");
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
		
		//***Nuevo***
		ActionListener gestorEventos=new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				convierte();
			}
		};
		
		//Asociar un oyente al botón
		btnBoton.addActionListener(gestorEventos);
		
		//Hacer visible la ventana
		ventana.setVisible(true);
	}
	
	public static void main(String[] args) {
		new ConversorEuros();
	}
	
	public void convierte() {
		try {
			double euros=Double.parseDouble(txtCampoDeTexto.getText());
			double pesetas=euros*166.386;
			lblEtiqueta2.setText("Equivale a "+pesetas+" pesetas.");
		} catch(NumberFormatException e2) {
			lblEtiqueta2.setText("En el campo de texto no hay un número!");
		}
	}

}
