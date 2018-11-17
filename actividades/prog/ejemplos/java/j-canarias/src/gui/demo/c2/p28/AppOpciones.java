package gui.demo.c2.p28;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AppOpciones {
	private static final String MENSAJE="Pase sobre alguna opción";
	
	private JLabel lblDesLarga;
	private List<Opcion> opciones;
	
	public AppOpciones() {
		//datos de prueba
		opciones=new ArrayList<Opcion>();
		opciones.add(new Opcion("Opción A","Desc. Larga Opción A"));
		opciones.add(new Opcion("Opción B","Desc. Larga Opción B"));
		opciones.add(new Opcion("Opción C","Desc. Larga Opción C"));
		opciones.add(new Opcion("Opción D","Desc. Larga Opción D"));
		
		//Interfaz gráfico
		JFrame ventana=new JFrame();
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setSize(300,200);
		
		JPanel pnlContenido=new JPanel();
		ventana.setContentPane(pnlContenido);
		
		for(int i=0;i<opciones.size();i++) {
			Opcion opcion=(Opcion) opciones.get(i);
			JButton b=new JButton(opcion.getDesCorta());
			pnlContenido.add(b);
			
			//******
			final int numOpcion=i;
			b.addMouseListener(new MouseListener() {
				public void mouseEntered(MouseEvent e) {
					setDesLarga(numOpcion);
				}
				public void mouseExited(MouseEvent e) {
					resetMensaje();
				}
				public void mouseClicked(MouseEvent e) {}
				public void mousePressed(MouseEvent e) {}
				public void mouseReleased(MouseEvent e) {}
			});
		}
		
		lblDesLarga=new JLabel(MENSAJE);
		pnlContenido.add(lblDesLarga);
		ventana.setVisible(true);
	}
	
	protected void resetMensaje() {
		this.lblDesLarga.setText(MENSAJE);
	}
	
	protected void setDesLarga(int numOpcion) {
		Opcion opcion=(Opcion) opciones.get(numOpcion);
		this.lblDesLarga.setText(opcion.getDesLarga());
	}
	
	public static void main(String[] args) {
		new AppOpciones();
	}
}
