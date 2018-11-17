package regex;

/**
 * Simple example of using RE functionality in String class.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 * @version $Id: REString.java,v 1.6 2007/01/11 18:21:29 ian Exp $
 */
public class REString {
	public static void main(String[] argv) {
		String pattern = "^Q[^u]\\d+\\..*";
		String input = "QA777. is the next flight. It is on time.";

		boolean found = input.matches(pattern);

		System.out.println("'" + pattern + "'" +
			(found ? " matches " : " doesn't match '") + input + "'");
	}
}
