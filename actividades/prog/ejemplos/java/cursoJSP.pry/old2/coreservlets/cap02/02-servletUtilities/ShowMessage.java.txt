package coreservlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

/** Example using servlet initialization. Here, the message
 *  to print and the number of times the message should be
 *  repeated is taken from the init parameters.
 *  <P>
 *  Taken from Core Servlets and JavaServer Pages
 *  from Prentice Hall and Sun Microsystems Press,
 *  http://www.coreservlets.com/.
 *  &copy; 2000 Marty Hall; may be freely used or adapted.
 */

public class ShowMessage extends HttpServlet {
  private String message;
  private String defaultMessage = "No message.";
  private int repeats = 1;
  
  public void init(ServletConfig config)
      throws ServletException {
    // Always call super.init
    super.init(config);
    message = config.getInitParameter("message");
    if (message == null) {
      message = defaultMessage;
    }
    try {
      String repeatString = config.getInitParameter("repeats");
      repeats = Integer.parseInt(repeatString);
    } catch(NumberFormatException nfe) {
      // NumberFormatException handles case where repeatString
      // is null *and* case where it is something in an
      // illegal format. Either way, do nothing in catch,
      // as the previous value (1) for the repeats field will
      // remain valid because the Integer.parseInt throws
      // the exception *before* the value gets assigned
      // to repeats.
    }
  }

    public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
        throws ServletException, IOException {
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title = "The ShowMessage Servlet";
      out.println(ServletUtilities.headWithTitle(title) +
                  "<BODY BGCOLOR=\"#FDF5E6\">\n" +
                  "<H1 ALIGN=CENTER>" + title + "</H1>");
      for(int i=0; i<repeats; i++) {
        out.println(message + "<BR>");
      }
      out.println("</BODY></HTML>");
    }
}
