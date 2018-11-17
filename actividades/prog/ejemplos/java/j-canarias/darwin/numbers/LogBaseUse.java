package numbers;

/**
 * Log to arbitrary base
 * @author Ian F. Darwin, http://www.darwinsys.com/
 * @version $Id: LogBaseUse.java,v 1.4 2004/09/08 20:12:45 ian Exp $
 */
public class LogBaseUse {
	//+
	public static void main(String[] argv) {
		double d = LogBase.log_base(10, 10000);
		System.out.println("log10(10000) = " + d);
	}
	//-
}
