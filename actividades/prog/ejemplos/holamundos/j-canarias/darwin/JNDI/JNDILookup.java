package JNDI;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/** Find an object previously made available.
 * Uses the RMI registry via the JNDI service provider; needs rmiregistry
 * @version $Id: JNDILookup.java,v 1.5 2004/09/08 20:13:26 ian Exp $
 */
public class JNDILookup {

	public static void main(String[] av) throws NamingException {

		Context ctx = new InitialContext();

		Object o = ctx.lookup("FiddleSticks");

		JNDIData d = (JNDIData)o;
		System.out.println("Message: " + d.getMessage());
	}
}
