package numbers;

/**
 * Log to arbitrary base
 * @author Ian F. Darwin, http://www.darwinsys.com/
 * @version $Id: LogBase.java,v 1.4 2004/09/08 20:12:49 ian Exp $
 */
public class LogBase {
	//+
	public static double log_base(double base, double value) {
		return Math.log(value) / Math.log(base);
	}
	//-
}
