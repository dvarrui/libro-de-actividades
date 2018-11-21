package strings;

/**
 * Reverse a string by character
 * @author Ian F. Darwin, http://www.darwinsys.com/
 * @version $Id: StringRevChar.java,v 1.4 2004/09/08 20:12:41 ian Exp $
 */
public class StringRevChar {
	public static void main(String[] argv) {
		//+
		String sh = "FCGDAEB";
		System.out.println(sh + " -> " + new StringBuffer(sh).reverse());
		//-
	}
}
