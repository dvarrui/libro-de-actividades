package com.darwinsys.regex;

/** An unchecked Exception representing a syntax error 
 * in an RE pattern.
 */
public class RESyntaxException extends RuntimeException {

	private static final long serialVersionUID = 2025779923950692489L;

	/** Construct an RESyntaxException with a message */
	public RESyntaxException(String mesg) {
		super(mesg);
	}

	/** Construct a user-hostile RESyntaxException with no message. */
	public RESyntaxException() {
		super();
	}
}
