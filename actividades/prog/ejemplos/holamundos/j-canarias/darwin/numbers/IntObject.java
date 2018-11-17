package numbers;

/**
 * IntObject -- convert between int and Integer (needed pre-1.5)
 * @version $Id: IntObject.java,v 1.3 2007/05/30 09:29:36 ian Exp $
 */
public class IntObject {
	public static void main(String[] args) {
		// int to Integer
		Integer i1 = Integer.valueOf(42);
		System.out.println(i1.toString());		// or just i1
		
		// Integer to int
		int i2 = i1.intValue();
		System.out.println(i2);
	}
}
