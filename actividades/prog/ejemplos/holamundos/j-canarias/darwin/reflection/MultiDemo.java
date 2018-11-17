package reflection;

/**
 * Utterly trivial class used by "ClassLoaderMultiple"
 * @version $Id: MultiDemo.java,v 1.5 2007/07/22 20:50:31 ian Exp $
 */
public class MultiDemo {
	static {
		System.out.println("MultiDemo loaded");
	}
	public MultiDemo() {
	}
	public static void test() {
		System.out.println("MultiDemo.test invoked");
	}
	@Override
	public String toString() {
		return "A MultiDemo object";
	}
}
