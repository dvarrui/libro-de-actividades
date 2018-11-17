package com.darwinsys.regex;

/** SEccl represents one Character Class, or list of characters
 * inside square brackets.  Construct with a list; the constructor
 * will expand dashes if possible, so that new SEccl("[a-e]") will
 * generate an SECCL for "abcde".
 * <P>
 * Our match return returns true if
 * called to match any one charater that is/is not in that set.
 * @author Ian Darwin, http://www.darwinsys.com/
 * $Id: SEccl.java,v 1.10 2006/04/07 02:33:02 ian Exp $
 */
public class SEccl extends SE {
	boolean negate = false;
	StringBuffer val = new StringBuffer();

	public String toString() {
		return "SEccl[" + (negate ? "^" : "") + val.toString() + "]";
	}

	/** Construct a CCL. Some of this code was in "getCCL()" */
	public SEccl(String arg, Int i) {
		
		i.incr();			/* skip over '[' */
		if (i.get()>=arg.length())
			throw new RESyntaxException("pattern ends with [");
		if (arg.charAt(i.get()) == RE.NEGCCL) {
			negate = true;
			i.incr();
		}
		// Expand the range
		doDash(arg, i);
		if (arg.charAt(i.get()) != RE.CCLEND)
			throw new RESyntaxException("CCL ends without ]");
	}

	/* doDash - expand dash shorthand set at src[i] to end of dest.
	 * @return number of characters appended to dest.
	 */
	protected int doDash(String src, Int i) {

		int startLen = val.length();

		while (src.charAt(i.get()) != RE.CCLEND && i.get()<src.length()) {
			if (src.charAt(i.get()) == RE.LITCHAR)
				val.append(RE.esc(src, i));
			else if (src.charAt(i.get()) != RE.DASH)
				val.append(src.charAt(i.get()));
			else if (val.length() == 0 || src.length() == i.get()+1)
				val.append(RE.DASH);	/* literal - */
			else if (Character.isLetterOrDigit(src.charAt(i.get()-1)) && 
				Character.isLetterOrDigit(src.charAt(i.get()+1)) &&
				src.charAt(i.get()-1) <= src.charAt(i.get()+1)) {
				for (int k = src.charAt(i.get()-1)+1; k <= src.charAt(i.get()+1); k++)
					val.append((char)k);
				i.incr();
			}
			else
				val.append(RE.DASH);
			i.incr();
		}
		return val.length() - startLen;
	}

	/** match CCLs here */
	public boolean amatch(String ln, Int i) {
		if (!negate) {
			if (i.get()<ln.length() && locate(ln.charAt(i.get()))) {
				i.incr();
				return true;
			}
		} else {
			if (i.get()<ln.length() && !locate(ln.charAt(i.get()))) {
				i.incr();
				return true;
			}
		}
		return false;
	}

	/* locate -- look for c in character class */
	protected boolean locate(char c) {
		for (int k = 0; k<val.length(); k++) {
			if (val.charAt(k) == c)
				return true;
		}
		return false;
	}

	/* esc - handle C-like escapes
	 * if a[i] is \, following char may be special.
	 * updates i if so.
	 * In any case, returns the character.
	 * Cut down from esc() in RE.compile(); this version handles
	 * a smaller set of escapes, and returns char instead of SEchar.
	 */
	protected static char esc(String a, Int i) {
		char c = a.charAt(i.get());
		if (c != '\\')
			return c;
		if (i.get() >= a.length())
			return '\\';	/* not special at end */
		i.incr();
		c = a.charAt(i.get());
		switch (c) {
		// 'd' not useful here
		case 'n':
			return ('\n');
		case 'r':
			return ('\r');
		case 't':
			return ('\t');
		case 'u':
			// assume followed by 4-digit hex string for Unicode character.
			String hex = a.substring(i.get()+1, i.get()+5);
			i.incr(4);
			return (char)Integer.parseInt(hex, 16); 
		// 'w' not allowed here
		case '\\':
			return ('\\');
		default:
			return (c);
		}
	}
}
