package coreservlets.WebClient;

import java.awt.*;
import java.awt.event.*;

/** A Frame that you can actually quit. Used as
 *  the starting point for most AWT-based
 *  applications.
 *  <P>
 *  Taken from Core Servlets and JavaServer Pages 2nd Edition
 *  from Prentice Hall and Sun Microsystems Press,
 *  http://www.coreservlets.com/.
 *  &copy; 2003 Marty Hall; may be freely used or adapted.
 */

public class CloseableFrame extends Frame {
	static final long serialVersionUID = 1L;
  public CloseableFrame(String title) {
    super(title);
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
  }

  /** Since we are doing something permanent, we need
   *  to call super.processWindowEvent <B>first</B>.
   */
  
  public void processWindowEvent(WindowEvent event) {
    super.processWindowEvent(event); // Handle listeners
    if (event.getID() == WindowEvent.WINDOW_CLOSING)
      System.exit(0);
  }
}
