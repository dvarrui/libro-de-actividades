package lang;

/**
 * Can you change the .length of an array?
 * @author Ian F. Darwin, http://www.darwinsys.com/
 * @version $Id: ChangeArrayLength.java,v 1.6 2004/09/08 20:13:01 ian Exp $
 */
public class ChangeArrayLength {
	public static void main(String[] argv) {
		//+
		int[] a = new int[4];
		System.out.println(a.length);
		a.length = 5;	// EXPECT COMPILE ERROR
		//-
	}
}
