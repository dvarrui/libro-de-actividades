package Contrib.treelayout;

import java.applet.Applet;
import java.awt.Button;
import java.awt.Color;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Label;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Hashtable;
import java.util.StringTokenizer;

/** Applet GUI demo of Gamelan TreeLayout layout manager.
 * Constructs a tree and, for each entry, makes a button that jumps to it.
 *
 * The input language is a file like this:
 *	R Java Resources				# root
 *	L - Resources at Sun				# label
 *	B http://www.sun.com/foo/bar	Interesting Stuff # URLbutton
 *	B http://javasoft.com/b/c	More Stuff	# URLbutton
 *
 * The result is (supposedly) a beautiful(?) tree.
 * Each L is a top-level label, and each B is in the tree below it.
 *
 * Could be made much fancier with getParameter("FontName"), "FontSize",
 * adjusting width with fontMetrics, etc.  Works adequately for now.
 */
public class TreeLink1_0 extends Applet {
	Label root;
	TreeLayout tl;
	Label l;
	Button b;
	Hashtable h;
	int wid = 300, ht = 0, htIncr = 30;

	// Initialize this TreeLink1_0 applet
	public void init() {

		showStatus("TreeLink1_0 initializing tree...");

		URL origin = getCodeBase();

		h = new Hashtable();

		// Read the configuration file from the URL.
		try {

			setLayout(tl = new TreeLayout());

			String txt;
			String fn;	// Control file name
			if ((fn = getParameter("treelink")) == null)
				fn = "treelink.txt";
			URL ctlFile = new URL(origin, fn);
			DataInputStream is =
				new DataInputStream(ctlFile.openStream());

			// Read the control file a line at a time, parse
			// it, and save the ones that are links in
			// the Hashtable indexed by their pushbutton

			while ((txt = is.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(txt);
				if (st.countTokens() < 3) {
					println("TreeLink1_0: Bad input: " + txt);
					return;
				}
				String type = st.nextToken();
				String bURL = st.nextToken();
				String text = "";
				while (st.hasMoreTokens())
					text += st.nextToken() + " ";
				if (false)
					println("Type " + type + "; link " +
						bURL + "; text " + text);
				ht += htIncr;
				if (type.equals("R")) {
					add("root",
					root = new Label(text));
					root.setBackground(Color.pink);
					tl.setRoot(root);	// required!
				} else if (type.equals("L")) {
					add("label", l = new Label(text));
					l.setBackground(Color.pink);
					tl.setParent(l, root);
				} else if (type.equals("B")) {
					add("button", b = new Button(text));
					if (bURL.indexOf(':') > 0)
						h.put(b, new URL(bURL));
					else
						h.put(b, new URL(origin, bURL));
					tl.setParent(b, l);
				} else {
					println("TreeLink:1_0 Invalid input " + txt);
				}
			}
		} catch(MalformedURLException mfc) {
			println("TreeLink1_0: Error: " + mfc);
		} catch(IOException billg) {
			println("TreeLink1_0: Error: " + billg);
		}

		setSize(wid, ht);
		System.out.println("Size now " + size());

		showStatus("TreeLink1_0 ready");
	}

	public boolean action(Event e, Object o) {
		URL u = (URL)h.get(e.target);
		showStatus("Showing document at URL " + u);

		getAppletContext().showDocument(u);
		// No error checking on showDocument() -- the
		// browser will honk at the user if the link
		// is invalid. We should open "u" ourselves,
		// check the open, and close it. Or not...

		return true;
	}

	// Need to call paintLines from a "paint" method
	// to draw lines at right time...
	public void paint(Graphics g) {
		tl.paintLines(g, getForeground());
	}

	// Convenience Routine
	protected void println(String s) {
		System.out.println(s);
		showStatus("Informational message(s), see Java Console");
	}

	// Convenience Routine
	protected void eprintln(String s) {
		System.err.println(s);
		showStatus("Error, see Java Console");
	}

	/** Return information about this applet. */
	public String getAppletInfo() {
		return "TreeLink1_0 Demo Applet\n" +
			"Copyright Learning Tree International";
	}

	/** Return list of allowable parameters. */
	public String[][] getParameterInfo() {
		String param_info[][] = {
			{"TreeLink1_0",    "filename",    "List of links"},
		};
		return param_info;
	}
}
