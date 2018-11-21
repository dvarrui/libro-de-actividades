package regex;

import java.util.regex.*;

/**
 * REsubstr -- demonstrate RE Match -> String.substring()
 * @author Ian F. Darwin, http://www.darwinsys.com/
 * @version $Id: REsubstr.java,v 1.5 2007/01/11 18:21:29 ian Exp $
 */
public class REsubstr {
	public static void main(String[] argv) {
		//+
		String patt = "Q[^u]\\d+\\.";
		Pattern r = Pattern.compile(patt);
		String line = "Order QT300. Now!";
		Matcher m = r.matcher(line);
		if (m.find()) {
			System.out.println(patt + " matches \"" +
				line.substring(m.start(0), m.end(0)) +
				"\" in \"" + line + "\"");
		} else {
			System.out.println("NO MATCH");
		}
	}
}
