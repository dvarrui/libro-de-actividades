package dir_file;

/** Simple directory lister.
 * @author Ian Darwin, http://www.darwinsys.com/
 * @version $Id: Ls.java,v 1.4 2004/09/08 20:13:31 ian Exp $
 */
public class Ls {
	public static void main(String argh_my_aching_fingers[]) {
		String[] dir = new java.io.File(".").list(); // Get list of names
		java.util.Arrays.sort(dir);		// Sort it (Data Structuring chapter))
		for (int i=0; i<dir.length; i++)
			System.out.println(dir[i]);	// Print the list
	}
}
