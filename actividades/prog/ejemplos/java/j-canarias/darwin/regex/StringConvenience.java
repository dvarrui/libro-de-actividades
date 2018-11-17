package regex;

/**
 * StringConvenience -- demonstrate java.lang.String convenience routine
 * @author Ian F. Darwin
 * @version $Id: StringConvenience.java,v 1.4 2007/01/11 18:21:29 ian Exp $
 */
public class StringConvenience {
	public static void main(String[] argv) {

		String pattern = ".*Q[^u]\\d+\\..*";
		String line = "Order QT300. Now!";
		if (line.matches(pattern)) {
			System.out.println(line + " matches \"" + pattern + "\"");
		} else {
			System.out.println("NO MATCH");
		}
	}
}
