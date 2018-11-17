package hilos.remainder;

import java.util.Date;
import java.awt.Toolkit;
import java.awt.Button;
import javax.swing.JOptionPane;

/**
 * Beep cada minuto
 * 
 * @author David Vargas Ruiz
 * @version 1.0.0 - 20051105
 */
public class Reminder {
	public static void main(String[] argv) throws InterruptedException {
		Button b = new Button();
		Toolkit t = b.getToolkit();
		while (true) {
			System.out.println(new Date());
			t.beep();
			Thread.sleep(15 * 60 * 1000);
			JOptionPane.showMessageDialog(null, (new Date().toString()),
					"Alerta", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
