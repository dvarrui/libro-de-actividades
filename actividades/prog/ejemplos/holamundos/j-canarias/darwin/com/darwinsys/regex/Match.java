package com.darwinsys.regex;

/** A Match describes one position at which a RE matches a String.
 * Note that all constructors are protected, since this class is only
 * meant to be constructed by the RE package itself.
 */
public class Match {
	int start;
	int end;

	/** Construct an empty Match */
	public Match() {
		this(0,0);
	}
	/** Construct a Match object given a start and end. */
	protected Match(int s, int e) {
		start = s;
		end   = e;
	}

	public String toString() {
		return "Match(" + start + "," + end + ")";
	}
}
