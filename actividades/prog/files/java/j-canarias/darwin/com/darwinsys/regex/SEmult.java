package com.darwinsys.regex;

import com.darwinsys.util.Debug;

/** SEmult represents one multiplier (aka "Closure", "quantifier", repetition).
 * The four forms in the RE input, * ? + and {m,n}, are all
 * represented by an instance of this class, with the
 * minimum and maximum number set appropriately.
 * @author Ian Darwin, http://www.darwinsys.com/
 * $Id: SEmult.java,v 1.12 2006/04/07 02:33:02 ian Exp $
 */
public class SEmult extends SE {
	/** The constant meaning no upper bound. */
	public final static int NOMAX = Integer.MAX_VALUE;
	/** The minimum number of times that must match */
	int minimum = 0;
	/** The maximum number number of times allowed for a match */
	int maximum = 0;
	/** What SubExpression is this a closure of? */
	SE target;

	/** Retuern a printable representation of this SE */
	public String toString() {
		StringBuffer sb = new StringBuffer("SEmult(").append(target).append('{').append(minimum).append(',');
		if (maximum==NOMAX)
			sb.append('*');
		else
			sb.append(maximum);
		sb.append("})");
		return sb.toString();
	}

	/** Construct a Multiplier */
	public SEmult(int min, int max, SE ta) {
		if (minimum < 0)
			throw new IllegalArgumentException(
				"Minimum must be non-negative");
		minimum = min;
		maximum = max;
		target = ta;
	}

	/** Match target at ln[i], multiple times */
	public boolean amatch(String ln, Int i) {
		Debug.println("SEmult", "SEmult amatch() called on " + ln.charAt(i.get()));
		boolean metMin = minimum == 0; // if 0 minimum, already met minimum.
		for (int j=minimum; j<maximum && i.get()<ln.length(); j++) {
			if (!target.amatch(ln, i))
				break;
			// else at least one matched, so we've met our minimum
			metMin = true;
		}
		// Now we either hit end of line or a failed match.
		return metMin;
	}
}
