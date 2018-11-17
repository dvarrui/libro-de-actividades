package tar;

/*
 * Exception for TarFile and TarEntry.
 * $Id: TarException.java,v 1.4 2004/09/08 20:12:59 ian Exp $
 */
public class TarException extends java.io.IOException {
	public TarException() {
		super();
	}
	public TarException(String msg) {
		super(msg);
	}
}
