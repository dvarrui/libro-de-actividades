package cajas.spdf;

import java.io.*;

import cajas.spdf.MoveTo;
import cajas.spdf.PDF;
import cajas.spdf.Page;
import cajas.spdf.Text;

/** A simple text test of SPDF package
 */
public class TestSimplePDF {
	public static void main(String[] argv) throws IOException {
		PrintWriter pout;
		if (argv.length == 0) {
			pout = new PrintWriter(System.out);
		} else {
			if (new File(argv[0]).exists()) {
				throw new IOException(
				"Output file " + argv[0] + " already exists");
			}
			pout = new PrintWriter(new FileWriter(argv[0]));
		}
		PDF p = new PDF(pout);
		Page p1 = new Page(p);
		p1.add(new MoveTo(p, 100, 600));
		p1.add(new Text(p, "Hello world, live on the web."));
		p1.add(new Text(p, "Hello world, line 2 on the web."));
		p.add(p1);
		p.setAuthor("David Vargas Ruiz");
		p.writePDF();
	}
}
