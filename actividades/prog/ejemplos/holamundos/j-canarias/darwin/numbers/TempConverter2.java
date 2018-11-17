package numbers;

import java.text.*;

/* Print a table of fahrenheit and celsius temperatures, a bit more neatly.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 * @version $Id: TempConverter2.java,v 1.7 2004/09/08 20:12:47 ian Exp $
 */
public class TempConverter2 extends TempConverter {
	protected DecimalFormat df;

	public static void main(String[] args) {
		TempConverter t = new TempConverter2();
		t.start();
		t.data();
		t.end();
	}

	// Constructor
	public TempConverter2() {
		df = new DecimalFormat("#0.00");
	}

	protected void print(float f, float c) {
		System.out.println(df.format(f) + " " + df.format(c));
	}

	protected void start() {
		System.out.println("Fahr    Centigrade.");
	}

	protected void end() {
		System.out.println("-------------------");
	}
}
