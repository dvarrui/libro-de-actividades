package structure;

import java.util.Calendar;
import java.util.Date;

/** Dummy source of Objects, for structure demos.
 * @author Ian Darwin
 * @version $Id: StructureDemo.java,v 1.6 2006/03/17 04:26:32 ian Exp $
 */
public class StructureDemo {

	/** The max number of Objects to return */
	private final int MAX;

	/** Construct a StructureDemo */
	StructureDemo(int m) {
		MAX = m;
	}

	int n;


	/* Dummy method to return a sequence of Calendar references,
	 */
	public Date getDate() {
		if (n++ > MAX)
			return null;
		return Calendar.getInstance().getTime();
	}
}
