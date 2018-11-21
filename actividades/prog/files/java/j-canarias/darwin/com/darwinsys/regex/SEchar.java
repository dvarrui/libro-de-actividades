package com.darwinsys.regex;

import com.darwinsys.util.Debug;

/* SEchar - represent one ordinary character.
 * @author Ian Darwin, http://www.darwinsys.com/
 * $Id: SEchar.java,v 1.10 2006/03/16 22:08:50 ian Exp $
 */
public class SEchar extends SE {
	char val;

	public SEchar(char ch) { val = ch; }

	public String toString() { return "'" + val + "'"; }

	/** amatch -- match one SE with one sub-input.
	 * @return true iff ln.charAt(i)==the character we were constructed with.
	 */
	public boolean amatch(String ln, Int i) {
		Debug.println("SEchar", "SEchar.amatch("+ln+','+i.get() + "), want " + val);
		if (i.get() < ln.length()) {
			boolean success = (ln.charAt(i.get()) == val);
			Debug.println("SEchar", "SEchar.amatch: success="+success);
			if (success)
				i.incr();
			return success;
		} 
		Debug.println("SEchar", "SEchar.amatch: hit end of string");
		return false;					// end of string
	}
}
