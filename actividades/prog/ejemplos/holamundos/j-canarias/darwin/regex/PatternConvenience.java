package regex;

import java.util.regex.*;

/**
 * PatternConvenience -- demonstrate java.util.regex.Pattern convenience routine
 * @author Ian F. Darwin
 * @version $Id: PatternConvenience.java,v 1.3 2007/01/11 18:21:29 ian Exp $
 */
public class PatternConvenience {

	public static void main(String[] argv) {

		String pattern = ".*Q[^u]\\d+\\..*";
		String line = "Order QT300. Now!";
		if (Pattern.matches(pattern, line)) {
			System.out.println(line + " matches \"" + pattern + "\"");
		} else {
			System.out.println("NO MATCH");
		}
	}
}
