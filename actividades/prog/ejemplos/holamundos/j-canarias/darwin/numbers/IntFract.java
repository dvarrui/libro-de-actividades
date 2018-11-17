package numbers;

/**
 * Multiply a decimal fraction, not using floating point
 * @author Ian F. Darwin, http://www.darwinsys.com/
 * @version $Id: IntFract.java,v 1.6 2004/09/08 20:12:45 ian Exp $
 */
public class IntFract {
	public static void main(String[] argv) {
		//+
		int a = 100;
		int b = a*5/7;
		System.out.println("5/7 of " + a + " is " + b);
		// Just for fun, do it again in floating point.
		final double FRACT = 0.7142857132857;
		int c = (int)(a*FRACT);
		System.out.println(FRACT + " of " + a + " is " + c);
		//-
	}
}
