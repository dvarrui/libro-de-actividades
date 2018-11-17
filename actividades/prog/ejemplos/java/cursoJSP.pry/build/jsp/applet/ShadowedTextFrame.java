package applet;

import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;

/** Interactive interface to MessageImage class.
 *  Enter message, font name, and font size on the command
 *  line. Requires Java2.
 *  <P>
 *  Taken from Core Servlets and JavaServer Pages
 *  from Prentice Hall and Sun Microsystems Press,
 *  http://www.coreservlets.com/.
 *  &copy; 2000 Marty Hall; may be freely used or adapted.
 */

public class ShadowedTextFrame extends JPanel {
  private Image messageImage;
  
  public static void main(String[] args) {
    String message = "Shadowed Text";
    if (args.length > 0) {
      message = args[0];
    }
    String fontName = "Serif";
    if (args.length > 1) {
      fontName = args[1];
    }
    int fontSize = 90;
    if (args.length > 2) {
      try {
        fontSize = Integer.parseInt(args[2]);
      } catch(NumberFormatException nfe) {}
    }
    JFrame frame = new JFrame("Shadowed Text");
    frame.addWindowListener(new ExitListener());
    JPanel panel =
      new ShadowedTextFrame(message, fontName, fontSize);
    frame.setContentPane(panel);
    frame.pack();
    frame.setVisible(true);
  }
  
  public ShadowedTextFrame(String message,
                           String fontName,
                           int fontSize) {
    messageImage = MessageImage.makeMessageImage(message,
                                                 fontName,
                                                 fontSize);
    int width = messageImage.getWidth(this);
    int height = messageImage.getHeight(this);
    setPreferredSize(new Dimension(width, height));
  }
    
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawImage(messageImage, 0, 0, this);
  }
}
