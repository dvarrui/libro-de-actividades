package Contrib;

import java.awt.*;
import java.applet.*;

/** Simple Applet demo, showing a math function.
 * @author Arthur Van Hoff, avh
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
public class Wavelet extends Applet {
	/** Called by AWT when the window needs painting; just recompute
	 * all the data and draw it, since it's a simple calculation and
	 * simpler than precomputing and storing the data.
	 */
    public void paint(Graphics g) {
		Dimension d = getSize();
        for (int x = 0 ; x < d.width ; x++) {
			g.drawLine(x, 20+(int)func(x), x + 1, 20+(int)func(x + 1));
        }
    }

	/** This is the function that is plotted. */
    double func(double x) {
		Dimension d = getSize();
		return (Math.cos(x/9) + Math.sin(x/3) + 1) * d.height / 4;
    }
}
