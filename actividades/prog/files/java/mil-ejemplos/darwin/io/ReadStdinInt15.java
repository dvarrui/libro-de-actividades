package io;

import java.util.Scanner;
/**
 * Read an int from Standard Input, using 1.5
 * @author	Ian F. Darwin, http://www.darwinsys.com/
 * @version 	$Id: ReadStdinInt15.java,v 1.4 2005/05/05 02:24:14 ian Exp $
 */
public class ReadStdinInt15 {
	public static void main(String[] ap) {
		int val;
		try {
			Scanner sc = new Scanner(System.in);      // Requires J2SE 1.5
			val = sc.nextInt();
		} catch (NumberFormatException ex) {
			System.err.println("Not a valid number: " + ex);
			return;
		}
		System.out.println("I read this number: " + val);
	}
}
