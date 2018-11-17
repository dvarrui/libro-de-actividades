package hilos.hosts;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.WindowConstants;

/**
 * This code was generated using CloudGarden's Jigloo SWT/Swing GUI Builder,
 * which is free for non-commercial use. If Jigloo is being used commercially
 * (ie, by a corporation, company or business for any purpose whatever) then you
 * should purchase a license for each developer using Jigloo. Please visit
 * www.cloudgarden.com for details. Use of Jigloo implies acceptance of these
 * licensing terms. ************************************* A COMMERCIAL LICENSE
 * HAS NOT BEEN PURCHASED for this machine, so Jigloo or this code cannot be
 * used legally for any corporate or commercial purpose.
 * *************************************
 */
public class FrmControlHosts extends javax.swing.JFrame {
	static final long serialVersionUID=1;
	
	private JPanel pnlPrincipal;

	private JPanel[] pnlPuestos;

	private ControlHosts control;

	/**
	 * Auto-generated main method to display this JFrame
	 */
	public static void main(String[] args) {
		FrmControlHosts inst = new FrmControlHosts();
		inst.setVisible(true);
		//inst.controlarHosts();
	}

	public FrmControlHosts() {
		super();

		// Parte NO gráfica
		control = new ControlHosts("tmp/hosts.txt");
		pnlPuestos = new JPanel[control.getNumHosts()];
		// Parte gráfica
		initGUI();
		//Iniciar proceso
		this.setVisible(true);
		controlarHosts();
	}

	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			{
				pnlPrincipal = new JPanel();
				BoxLayout pnlPrincipalLayout = new BoxLayout(pnlPrincipal,
						javax.swing.BoxLayout.Y_AXIS);
				pnlPrincipal.setLayout(pnlPrincipalLayout);
				this.getContentPane().add(pnlPrincipal, BorderLayout.CENTER);

				for (int i = 0; i < control.getNumHosts(); i++) {
					Host h = control.getHost(i);
					JPanel p = new JPanel();
					//FlowLayout f = new FlowLayout();
					GridLayout f = new GridLayout(1,3);
					p.setLayout(f);
					pnlPrincipal.add(p);
					p.setBackground(new java.awt.Color(144, 238, 144));
					// pnlPuesto1.setBackground(new java.awt.Color(255,0,0));
					// pnlPuesto2.setBackground(new java.awt.Color(144,238,144));

					JLabel e1 = new JLabel();
					p.add(e1);
					e1.setText(h.getIp());

					JLabel e2 = new JLabel();
					p.add(e2);
					e2.setText(h.getDescripcion());
					
					JLabel e3 = new JLabel();
					p.add(e3);
					e3.setText("("+h.getGrupo()+")");
					
					pnlPuestos[i] = p;
				}
			}
			pack();
			this.setSize(300, 300);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void controlarHosts() {
		try {
			for (int i = 0; i < 1000; i++) {
				control.comprobarHosts();

				//Actualizar los colores de los paneles
				for(int j=0;j<control.getNumHosts();j++) {
					Host h = control.getHost(j);
					if (h.isPing())
						pnlPuestos[j].setBackground(new java.awt.Color(144, 238, 144));
					else
						pnlPuestos[j].setBackground(new java.awt.Color(255,0,0));
				}
				// minutos * segundos * milisegundos
				Thread.sleep(1 * 2 * 1000);
			}
		} catch (Exception e) {
			System.err.println(e);
		}

	}
}
