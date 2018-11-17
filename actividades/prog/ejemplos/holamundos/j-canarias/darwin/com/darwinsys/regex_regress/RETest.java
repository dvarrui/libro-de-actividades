package com.darwinsys.regex_regress;

import junit.framework.TestCase;

import com.darwinsys.regex.RE;

/**
 * Not test again, but test RE.
 * No need for exhaustive tests here; this is to test out the
 * different constructors and method invocations.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 * @version $Id: RETest.java,v 1.1 2006/03/16 22:08:50 ian Exp $
 * @see	Test.java, which runs many many RE tests.
 */
public class RETest extends TestCase {
	
	public static void testOne() {
		//+
		RE r = new RE("^A");
		assertTrue(r.match("Apples"));
		assertFalse(r.match("apples"));
		// Quick test of substitution
		RE s = new RE("the *");
		String input = "in the beginning";
		assertEquals("sub(" + input +" ) ", "in MY_beginning", s.sub(input, "MY_"));
		//-
	}
}
