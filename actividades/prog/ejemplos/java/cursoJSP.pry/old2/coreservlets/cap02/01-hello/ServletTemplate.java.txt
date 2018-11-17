import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

/** Servlet template.
*  <P>
 *  Taken from Core Servlets and JavaServer Pages
 *  from Prentice Hall and Sun Microsystems Press,
 *  http://www.coreservlets.com/.
 *  &copy; 2000 Marty Hall; may be freely used or adapted.
 */

public class ServletTemplate extends HttpServlet {
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {
      
    // Use "request" to read incoming HTTP headers
    // (e.g. cookies) and HTML form data (e.g. data the user
    // entered and submitted).
    
    // Use "response" to specify the HTTP response status
    // code and headers (e.g. the content type, cookies).
    
    PrintWriter out = response.getWriter();
    // Use "out" to send content to browser
  }
}
