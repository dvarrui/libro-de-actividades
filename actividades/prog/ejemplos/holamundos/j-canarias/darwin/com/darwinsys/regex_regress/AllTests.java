package com.darwinsys.regex_regress;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for com.darwinsys.regex_regress");
		//$JUnit-BEGIN$
		suite.addTestSuite(RETest.class);
		suite.addTestSuite(RunScriptedTests.class);
		//$JUnit-END$
		return suite;
	}

}
