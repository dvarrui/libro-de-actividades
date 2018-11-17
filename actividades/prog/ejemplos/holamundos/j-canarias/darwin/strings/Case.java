package strings;

/**
 * Control case.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 * @version $Id: Case.java,v 1.4 2004/09/08 20:12:41 ian Exp $
 */
public class Case {
	public static void main(String[] argv) {
		//+
		String name = "Java Cookbook";
		System.out.println("Normal:\t" + name);
		System.out.println("Upper:\t" + name.toUpperCase());
		System.out.println("Lower:\t" + name.toLowerCase());
		String javaName = "java cookBook"; // If it were Java identifiers :-)
		if (!name.equals(javaName))
			System.err.println("equals() correctly reports false");
		else
			System.err.println("equals() incorrectly reports true");
		if (name.equalsIgnoreCase(javaName))
			System.err.println("equalsIgnoreCase() correctly reports true");
		else
			System.err.println("equalsIgnoreCase() incorrectly reports false");
		//-
	}
}
