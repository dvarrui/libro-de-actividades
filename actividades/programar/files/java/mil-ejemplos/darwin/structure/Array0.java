package structure;

/** Simple array example
 * @author Ian Darwin
 * @version $Id: Array0.java,v 1.3 2006/03/17 04:26:32 ian Exp $
 */
public class Array0  {
	// Use this initializer form when the data are fixed:
	static int monthLen[] = {
			31, 28, 31, 30,
			31, 30, 31, 31,
			30, 31, 30, 31,
	};
		
	public static void main(String[] argv) {
		for (int i=0; i<monthLen.length; i++) {
			System.out.println("Month " + (i+1) + " has " +
				monthLen[i] + " days.");
		}
	}
}
