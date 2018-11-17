package david.gomera.gui;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;

import javax.swing.WindowConstants;


/**
* This code was generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* *************************************
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED
* for this machine, so Jigloo or this code cannot be used legally
* for any corporate or commercial purpose.
* *************************************
*/
public class MenuPrincipal extends javax.swing.JFrame {
	static final long serialVersionUID=1;
	
	private JMenuBar jMenuBar1;
	private JMenu mnuPartida;
	private JMenu mnuAcercaDe;
	private JPanel pnlPartida;
	private JMenuItem mnuAyuda;
	private JMenuItem mnuHallOfFame;
	private JMenuItem mnuSalir;
	private JMenuItem mnuCreditos;
	private JSeparator jSeparator1;
	private JMenuItem mnuCargar;
	private JMenuItem mnuGrabar;
	private JMenuItem mnuParar;
	private JMenuItem mnuNuevaPartida;
	private JScrollPane jScrollPane1;

	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		MenuPrincipal inst = new MenuPrincipal();
		inst.setVisible(true);
	}
	
	public MenuPrincipal() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			{
				jMenuBar1 = new JMenuBar();
				setJMenuBar(jMenuBar1);
				jMenuBar1.setPreferredSize(new java.awt.Dimension(392, 19));
				{
					mnuPartida = new JMenu();
					jMenuBar1.add(mnuPartida);
					mnuPartida.setText("Partida");
					{
						mnuNuevaPartida = new JMenuItem();
						mnuPartida.add(mnuNuevaPartida);
						mnuNuevaPartida.setText("Nueva");
					}
					{
						mnuParar = new JMenuItem();
						mnuPartida.add(mnuParar);
						mnuParar.setText("Parar");
					}
					{
						mnuGrabar = new JMenuItem();
						mnuPartida.add(mnuGrabar);
						mnuGrabar.setText("Grabar");
					}
					{
						mnuCargar = new JMenuItem();
						mnuPartida.add(mnuCargar);
						mnuCargar.setText("Cargar");
					}
					{
						jSeparator1 = new JSeparator();
						mnuPartida.add(jSeparator1);
					}
					{
						mnuSalir = new JMenuItem();
						mnuPartida.add(mnuSalir);
						mnuSalir.setText("Salir");
						mnuSalir.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								mnuSalirActionPerformed(evt);
							}
						});
					}
				}
				{
					mnuAcercaDe = new JMenu();
					jMenuBar1.add(mnuAcercaDe);
					mnuAcercaDe.setText("Acerca de");
					{
						mnuAyuda = new JMenuItem();
						mnuAcercaDe.add(mnuAyuda);
						mnuAyuda.setText("Ayuda");
					}
					{
						mnuHallOfFame = new JMenuItem();
						mnuAcercaDe.add(mnuHallOfFame);
						mnuHallOfFame.setText("Hall Of Fame");
					}
					{
						mnuCreditos = new JMenuItem();
						mnuAcercaDe.add(mnuCreditos);
						mnuCreditos.setText("Créditos");
					}
				}
			}
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setTitle("proyecto Gomera");
			{
				jScrollPane1 = new JScrollPane();
				this.getContentPane().add(jScrollPane1, BorderLayout.CENTER);
				{
					pnlPartida = new PanelPartida();
					jScrollPane1.setViewportView(pnlPartida);
				}
			}
			pack();
			this.setSize(488, 339);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void mnuSalirActionPerformed(ActionEvent evt) {
		System.out.println("Salir");
		//TODO add your code for mnuSalir.actionPerformed
		this.dispose();
	}

}
