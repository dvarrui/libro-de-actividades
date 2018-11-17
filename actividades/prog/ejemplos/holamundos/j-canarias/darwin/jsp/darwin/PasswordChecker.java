package jsp.darwin;

/** Simple Password Checker Javabean.
 * Does not implement get methods, only set.
 * @version $Id: PasswordChecker.java,v 1.2 2004/09/10 01:53:56 ian Exp $
 */
public class PasswordChecker implements java.io.Serializable {
	protected String login, password;

	/** A public no-argument constructor is required of a javabean. */
	public PasswordChecker() {
	}

	/** Set the login name */
	public void setLogin(String s) {
		login = s;
	}

	/** Set the password */
	public void setPassword(String s) {
		password = s;
	}

	/** Return true if a valid name and password have been set.
	 * Obviously trivial: real version would look in a database. 
	 */
	public boolean isValidPassword() {
		return login!=null && password !=null &&
			login.equals("ian") &&
			password.equals("topSecret");
	}
}
