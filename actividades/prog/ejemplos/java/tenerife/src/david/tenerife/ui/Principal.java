package david.tenerife.ui;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.JTree;

import javax.swing.WindowConstants;

import java.net.*;

/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class Principal extends javax.swing.JFrame {
	static final long serialVersionUID = 1;
	private JPanel pnlPrincipal;
	private JPanel pnlCreditos;
	private JLabel lblAutor;
	private JSplitPane slpHorizontal;
	private JTree treArbolEstructura;
	private JTextPane txpAyuda;
	private JScrollPane scrArbolEstructura;
	private JSplitPane splVertical;
	private JLabel lblAtributos;
	private JTabbedPane tabAtributos;
	private JPanel pnlTrabajo;

	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		Principal inst = new Principal();
		inst.setVisible(true);
	}
	
	public Principal() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			{
				pnlPrincipal = new JPanel();
				BorderLayout pnlPrincipalLayout = new BorderLayout();
				pnlPrincipal.setLayout(pnlPrincipalLayout);
				this.getContentPane().add(pnlPrincipal, BorderLayout.CENTER);
				{
					pnlCreditos = new JPanel();
					pnlPrincipal.add(pnlCreditos, BorderLayout.NORTH);
					pnlCreditos.setBackground(new java.awt.Color(173,216,230));
					{
						lblAutor = new JLabel();
						pnlCreditos.add(lblAutor);
						lblAutor.setText("David Vargas Ruiz");
					}
				}
				{
					pnlTrabajo = new JPanel();
					BorderLayout pnlTrabajoLayout = new BorderLayout();
					pnlTrabajo.setLayout(pnlTrabajoLayout);
					pnlPrincipal.add(pnlTrabajo, BorderLayout.CENTER);
					{
						slpHorizontal = new JSplitPane();
						pnlTrabajo.add(slpHorizontal, BorderLayout.CENTER);
						slpHorizontal.setPreferredSize(new java.awt.Dimension(159, 438));
						{
							splVertical = new JSplitPane();
							splVertical.setOrientation(JSplitPane.VERTICAL_SPLIT);
							{
								scrArbolEstructura = new JScrollPane();
								splVertical.add(
									scrArbolEstructura,
									JSplitPane.TOP);
								scrArbolEstructura.setPreferredSize(new java.awt.Dimension(218, 245));
								{
									treArbolEstructura = new JTree();
									scrArbolEstructura.setViewportView(treArbolEstructura);
									treArbolEstructura.setPreferredSize(new java.awt.Dimension(200, 97));
								}
							}
							{
								tabAtributos = new JTabbedPane();
								splVertical.add(tabAtributos, JSplitPane.BOTTOM);
								tabAtributos.setPreferredSize(new java.awt.Dimension(5, 100));
								{
									lblAtributos = new JLabel();
									tabAtributos.addTab(
										"Atributos",
										null,
										lblAtributos,
										null);
									lblAtributos.setText("Atributos");
									lblAtributos.setPreferredSize(new java.awt.Dimension(213, 156));
								}
							}
							slpHorizontal.add(splVertical, JSplitPane.RIGHT);
							splVertical.setPreferredSize(new java.awt.Dimension(100, 200));
						}
					}
					{
						txpAyuda = new JTextPane();
						pnlTrabajo.add(txpAyuda, BorderLayout.WEST);
						txpAyuda.setText("ayuda");
						txpAyuda.setPreferredSize(new java.awt.Dimension(325, 200));
						txpAyuda.setMaximumSize(new java.awt.Dimension(325, 200));
						txpAyuda.setEditable(false);
						txpAyuda.setPage(new URL("http://www.google.es"));
						txpAyuda.setSize(325, 200);
						//txpAyuda.setPage("src/david/tenerife/doc/html/index.html");
						/*
						 InputStream in = new FileInputStream("help.htm");
						editor.read(in, null);
						 */
						//txpAyuda.read(new java.io.FileInputStream("david/tenerife/doc/html/index.html"),null);
					}
				}
			}
			pack();
			this.setSize(604, 481);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
