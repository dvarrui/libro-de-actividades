package gui;

import javax.swing.JFrame;

/** Show an example of closing a JFrame.
 * @author Ian Darwin
 * @version $Id: WinClose2.java,v 1.6 2007/05/22 12:56:47 ian Exp $
 */
public class WinClose2 {

	/* Main method */
	public static void main(String[] argv) {
		JFrame f = new JFrame("WinClose");
		f.setSize(200, 100);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}
