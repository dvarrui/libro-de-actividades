package numbers;

/**
 * Template for standalone, line-mode main program.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 * @version $Id: BinaryDigits.java,v 1.4 2004/09/08 20:12:46 ian Exp $
 */
public class BinaryDigits {
	public static void main(String[] argv) {
		//+
		String bin = "101010";
		System.out.println(bin + " as an integer is " + Integer.valueOf(bin, 2));
		int i = 42;
		System.out.println(i + " as binary digits (bits) is " + 
			Integer.toBinaryString(i));
		//-
	}
}
