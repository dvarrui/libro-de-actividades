package environ;

/** Test for JDK >= 1.1 */
public class TestJDK11 {
	public static void main(String[] a) {
		// Check for JDK >= 1.1
		try {
			Class.forName("java.lang.reflect.Constructor");
		} catch (ClassNotFoundException e) {
			String failure = 
				"Sorry, but this version of MyApp needs \n" +
				"a Java Runtime based on Java JDK 1.1 or later";
			System.err.println(failure);
			throw new IllegalArgumentException(failure);
		}
		System.out.println("Happy to report that this is JDK1.1");
		// rest of program would go here...
		return;
	}
}
