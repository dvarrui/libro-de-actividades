package lang;

/**
 * Can you call a static method of an abstract class?
 * @author Ian F. Darwin, http://www.darwinsys.com/
 * @version $Id: AbstractStatic.java,v 1.5 2004/09/08 20:13:01 ian Exp $
 */
public abstract class AbstractStatic {

	public static void main(String[] argv) {
		//+
		System.out.println("Hello. The answer is yes.");
		// The following is redundant, since we got in here!
		AbstractStatic.foo();
		//-
	}

	public static void foo() {
		System.out.println("Hello from foo. The answer is still yes.");
	}

	/* And if you think about it, of course non-abstract static
	 * methods of an abstract class can be called. Isn't this
	 * how most "factory methods" work?
	 */
}
