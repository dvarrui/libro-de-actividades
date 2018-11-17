package com.darwinsys.regex_regress;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import junit.framework.AssertionFailedError;
import junit.framework.Test;
import junit.framework.TestResult;
import junit.framework.TestSuite;

import com.darwinsys.regex.RE;
import com.darwinsys.regex.RESyntaxException;


/*
 * JUnit test runner for running scripted tests for com.darwinsys.regex.
 * Each RE is compiled and printed, .match()ed against the string, and
 * the result is applied to output using regsub().
 *Each of the input files named in "fileNames" must contain of lines with
 * five fields:  a r.e., a string to match it against, a result code, a
 * source string for regsub, and the proper result.  Result codes are 'c'
 * for compile failure, 'y' for match success, 'n' for match failure.
 * Field separator is tab.
 * @author Ian Darwin, http://www.darwinsys.com/ - this program
 * @author Henry Spencer, henry@zoo.toronto.edu - test file of 127 RE tests
 * @version $Id: RunScriptedTests.java,v 1.4 2007/04/30 22:21:21 ian Exp $
 */
public class RunScriptedTests extends TestSuite {

	private String[] fileNames = { "tests.txt", "mytests.txt" };
	
	public static Test suite() throws Exception {
		TestSuite suite = new TestSuite("Test for regress");
		//$JUnit-BEGIN$
		suite.addTest(new RunScriptedTests());
		//$JUnit-END$
		return suite;
	}
	
	private List<TestHolder> tests = new ArrayList<TestHolder>();
	
	static class TestHolder implements Test {
		String patt;
		String input;
		String expect;	// Should make an enum?

		public int countTestCases() {
			return 1;
		}

		public void run(TestResult arg0) {
			throw new IllegalStateException("called run on a TestHolder");
		}

		public TestHolder(String patt, String input, String expect) {
			super();
			this.patt = patt;
			this.input = input;
			this.expect = expect;
		}	
		@Override
		public String toString() {
			return String.format("TestHolder[Patt %s, Input %s, expect %s]%n", patt, input, expect);
		}
	}
	
	
	/**
	 * Constructor, read the test Files so countTestCases will work.
	 */
	public RunScriptedTests() throws Exception {
		for (String fileName : fileNames) {
			readTests(fileName);
		}
	}
	
	private void readTests(String fileName) throws Exception {		
		BufferedReader is = new BufferedReader(new FileReader(fileName));
        String inputLine;

        while ((inputLine = is.readLine()) != null) {
            if (inputLine.length() == 0 ||
				inputLine.startsWith("#"))
					continue;
			StringTokenizer st = new StringTokenizer(inputLine, "\t");
			if (st.countTokens() < 3) {
				System.err.println("INVALID INPUT: " + inputLine);
				continue;
			}
			String patt = st.nextToken();
			String data = st.nextToken();
			String expStr = st.nextToken();
			tests.add(new TestHolder(patt, data, expStr));
        }
        is.close();
	}
	
	@Override
	public int countTestCases() {
		return tests.size();
	}
	
	/* Run all the tests in "tests".
	 * @see junit.framework.Test#run(junit.framework.TestResult)
	 */
	@Override
	public void run(TestResult results) {
		for (TestHolder thisTest : tests) {
			try {
				results.startTest(thisTest);
				one_test(thisTest);
			} catch (AssertionFailedError e) {
				System.out.println("Caught " + e);
				results.addFailure(thisTest, e);
			} catch (Exception t) {
				System.out.println("Caught " + t);
				results.addError(thisTest, t);
			} finally {
				results.endTest(thisTest);
			}
		}
	}

	protected static final String passedMessage = "Success";
	protected static final String failedMessage = "FAILURE";

	/**
	 * Actually run the Test!
	 */
	public static void one_test(TestHolder test) throws Exception {
		System.out.print(test);
		char expect = test.expect.charAt(0);
		RE re;
		try {
			re = new RE(test.patt);
		} catch (RESyntaxException e) {
			boolean success = (expect == 'c');
			// If we expected success but got failure, throw.
			if (!success) {				
				throw(e);
			}
			return;
		}
		if (expect == 'c' /* && we are still her */) {
			throw new AssertionFailedError("Pattern compiled, but expected it to fail(c)");
		}
		boolean matched = re.match(test.input);
		
		if (matched == (expect == 'y')) {
			System.out.println(passedMessage);
		} else {
			throw new AssertionFailedError(failedMessage + ": " + test);
		}

	}

}
