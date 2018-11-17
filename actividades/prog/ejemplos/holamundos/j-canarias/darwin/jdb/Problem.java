package jdb;

/**
 * A Program with a Problem
 * @author Ian F. Darwin, http://www.darwinsys.com/
 * @version $Id: Problem.java,v 1.4 2004/09/08 20:13:43 ian Exp $
 */
public class Problem {
	public static void main(String argv[]) {
		//+
		System.out.println(System.getproperties()); // EXPECT COMPILE ERROR 
		//-
	}
}
