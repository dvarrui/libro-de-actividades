package lang;

/**
 * Test if you can use a null reference to find a static method.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 * @version $Id: NullStaticMethod.java,v 1.4 2004/09/08 20:13:02 ian Exp $
 */
public class NullStaticMethod {
	public static void main(String[] argv) {
		System.out.println("XXX ");
		NullStaticMethod2 x = null;
		x.invoke();			// EXPECT COMPILE WARNING
	}
}
