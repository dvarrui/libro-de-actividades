package gui.demo.c3.p31;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class EjemploBorderLayout {

	private JButton btnNorte;
	private JButton btnSur;
	private JButton btnEste;
	private JButton btnOeste;
	private JButton btnCentro;
	
	public EjemploBorderLayout() {
		JFrame ventana=new JFrame("Ejemplo Border Layout");
		ventana.setSize(300,200);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.btnNorte=new JButton("Norte");
		this.btnSur=new JButton("Sur");
		this.btnEste=new JButton("Este");
		this.btnOeste=new JButton("Oeste");
		this.btnCentro=new JButton("Centro");
		
		JPanel pnlContenido=new JPanel();
		pnlContenido.setLayout(new BorderLayout());
		pnlContenido.add(this.btnNorte,BorderLayout.NORTH);
		pnlContenido.add(this.btnSur,BorderLayout.SOUTH);
		pnlContenido.add(this.btnEste,BorderLayout.EAST);
		pnlContenido.add(this.btnOeste,BorderLayout.WEST);
		pnlContenido.add(this.btnCentro,BorderLayout.CENTER);
		
		ventana.setContentPane(pnlContenido);
		ventana.setVisible(true);
	}
	
	public static void main(String[] args) {
		new EjemploBorderLayout();
	}
}
