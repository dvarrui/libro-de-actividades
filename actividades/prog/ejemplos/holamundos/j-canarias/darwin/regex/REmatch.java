package regex;

import java.util.regex.*;

/**
 * REmatch -- demonstrate RE Match -> group()
 * @author Ian F. Darwin, http://www.darwinsys.com/
 * @version $Id: REmatch.java,v 1.7 2007/01/11 18:21:29 ian Exp $
 */
public class REmatch {
	public static void main(String[] argv) {

		String patt = "Q[^u]\\d+\\.";
		Pattern r = Pattern.compile(patt);
		String line = "Order QT300. Now!";
		Matcher m = r.matcher(line);
		if (m.find()) {
			System.out.println(patt + " matches \"" +
				m.group(0) +
				"\" in \"" + line + "\"");
		} else {
			System.out.println("NO MATCH");
		}
	}
}
