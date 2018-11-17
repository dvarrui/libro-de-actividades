package com.darwinsys.regex;

/** SEor represents an "or" condition, as in verdi|wagner
 * @author Ian Darwin, http://www.darwinsys.com/
 * $Id: SEor.java,v 1.6 2006/03/16 22:08:50 ian Exp $
 */
public class SEor extends SE {
	/** What SubExpression is on the left? */
	SE left;
	/** What SubExpression is on the right? */
	SE right;

	/** Retuern a printable representation of this SE */
	public String toString() {
		return new StringBuffer("SEor(").append(left).append('|').append(right).toString();
	}

	/** Construct a SEor */
	public SEor(SE l, SE r) {
		left = l;
		right = r;
	}

	/** Match either left OR right at ln[i]. */
	public boolean amatch(String ln, Int i) {
		return left.amatch(ln, i) || right.amatch(ln, i);
	}
}
