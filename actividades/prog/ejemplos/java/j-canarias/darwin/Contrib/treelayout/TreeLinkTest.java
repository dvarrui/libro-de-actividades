package Contrib.treelayout;

import java.applet.Applet;
import java.awt.Button;
import java.awt.Canvas;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.List;
import java.awt.TextField;

public class TreeLinkTest extends Applet {

	TreeLayout tl;

	public void init() 	{
		tl = new TreeLayout();
		setLayout(tl);
		Button root = new Button("This is the root");
		add("Root", root); tl.setRoot(root);
		Component x = new Label("A random label");
		add("label", x);tl.setParent(x, root);
		Component y;
		y = new TextField("Add any component");
		add("comp", y); tl.setParent(y, root);
		x = new List();
		((List)x).add("List entry");
		((List)x).add("Similarly useless list entry");
		add("list", x); tl.setParent(x, root);
		x=new Button("Extremely long and unnecessary button title");
		add("button", x); tl.setParent(x, y);
		x = new MyCanvas(getImage(getDocumentBase(), "icons/tools.gif"));
		add("image", x); tl.setParent(x, y);
	}

	public void paint(Graphics g) 	{
		super.paint(g);
		tl.paintLines(g, getBackground());
	}

	class MyCanvas extends Canvas {
		Image img;

		MyCanvas(Image img) {
			this.img = img;
		}
		public Dimension getPreferredSize() {
			return new Dimension(64, 64);
		}
		public void update(Graphics g) {
			paint(g);
		}
		public void paint(Graphics g) {
			g.drawImage(img, 0, getSize().height/2 - 16, 32, 32, this);
		}
	}
}
