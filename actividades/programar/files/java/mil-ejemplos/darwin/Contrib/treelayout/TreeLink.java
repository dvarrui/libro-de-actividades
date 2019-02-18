package Contrib.treelayout;

import gui.URLButton;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Label;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
public class TreeLink extends Applet {
	Label root;
	TreeLayout tl;
	Label l;
	URLButton b;
	Hashtable h;
	int wid = 400, ht = 0, htIncr = 30;

	// Initialize this TreeLink applet
	public void init() {

		showStatus("TreeLink initializing tree...");

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
			BufferedReader is =
				new BufferedReader(
					new InputStreamReader(ctlFile.openStream()));

			// Read the control file a line at a time, parse
			// it, and save the ones that are links in
			// the Hashtable indexed by their pushbutton

			while ((txt = is.readLine()) != null) {
				if (txt.startsWith("#"))
					continue;
				StringTokenizer st = new StringTokenizer(txt);
				if (st.countTokens() < 3) {
					println("TreeLink: Bad input: " + txt);
					return;
				}
				String type = st.nextToken();
				String bURL = st.nextToken();
				String text = "";
				while (st.hasMoreTokens())
					text += st.nextToken() + " ";
				// println("Type " + type + "; link " +
				// 	bURL + "; text " + text);
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
					URL u;
					if (bURL.indexOf(':') > 0)
						u = new URL(bURL);
					else
						u = new URL(origin, bURL);
					add("button", b = new URLButton(this, text, u));
					tl.setParent(b, l);
				} else {
					println("TreeLink: Invalid input " + txt);
				}
			}
		} catch(MalformedURLException mfc) {
			println("TreeLink: Error: " + mfc);
		} catch(IOException billg) {
			println("TreeLink: Error: " + billg);
		}

		setSize(wid, ht);
		System.out.println("Size now " + getSize());

		showStatus("TreeLink ready");
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
		return "TreeLink Demo Applet\n" +
			"Copyright Learning Tree International";
	}

	/** Return list of allowable parameters. */
	public String[][] getParameterInfo() {
		String param_info[][] = {
			{"TreeLink",    "filename",    "List of links"},
		};
		return param_info;
	}
}
