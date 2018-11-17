package io;

/**
 * Read one byte from Standard Input - hardly useful on its own.
 * @author	Ian F. Darwin, http://www.darwinsys.com/
 * @version 	$Id: ReadStdin.java,v 1.5 2007/05/22 12:58:37 ian Exp $
 */
public class ReadStdin {
	/** Simple test case */
	public static void main(String[] ap) {
		//+
		int b = 0;
		try {
			b = System.in.read();
		} catch (Exception e) {
			System.out.println("Caught " + e);
		}
		System.out.println("Read this data: " + (char)b);
		//-
	}
}
