package unfinished;

/**
 * Test the IMath class
 * @author Ian F. Darwin, http://www.darwinsys.com/
 * @version $Id: IMathTest.java,v 1.5 2004/09/08 20:13:24 ian Exp $
 */
public class IMathTest {
	public static void main(String[] argv) {
		//+
		for (int i=-10; i<=25; i+=2)
			System.out.println("isqrt(" + i + ") = " + IMath.isqrt(i));
		//-
	}
}
