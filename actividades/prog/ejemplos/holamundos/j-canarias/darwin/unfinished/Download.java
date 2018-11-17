package unfinished;

/**
 * Download a set of files from a URL containing the list and their checksums.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 * @version $Id: Download.java,v 1.4 2004/09/08 20:13:25 ian Exp $
 */
public class Download {
	public static void main(String[] argv) {
		//+
		System.out.println("Download starting.");
		getListFromURL();
		getListFromDirectory();
		downloadNeededFiles();
		System.out.println("Download done.");
		//-
	}

	class MyURLStreamFactory() {
		getStream(filename f) {
			// TODO error handling
			return new URL(base, f).getInputStream();
		}
	}
}
