package JNDI;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/** Make an object available.
 * Uses the RMI registry via the JNDI service provider; needs rmiregistry
 * @version $Id: JNDIPublish.java,v 1.5 2004/09/08 20:13:26 ian Exp $
 */
public class JNDIPublish  {

	public static void main(String[] av) throws NamingException {
		JNDIData d = new JNDIData();
		d.setMessage("Qwerty Uiop!");

		Context ctx = new InitialContext();

		ctx.rebind("FiddleSticks", d);
	}
}
