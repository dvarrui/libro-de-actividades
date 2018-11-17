package web.ser;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

/** Creates a table of the cookies associated with
 *  the current page.
 *  <P>
 *  Taken from Core Servlets and JavaServer Pages
 *  from Prentice Hall and Sun Microsystems Press,
 *  http://www.coreservlets.com/.
 *  &copy; 2000 Marty Hall; may be freely used or adapted.
 */

public class ShowCookies extends HttpServlet 
{
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException 
	{
   	response.setContentType("text/html");
    	PrintWriter out = response.getWriter();
    	String title = "Cookies activas";
    	out.println(ServletUtilities.headWithTitle(title) +
                "<BODY BGCOLOR=\"#FDF5E6\">\n" +
                "<H1 ALIGN=\"CENTER\">" + title + "</H1>\n" +
                "<TABLE BORDER=1 ALIGN=\"CENTER\">\n" +
                "<TR BGCOLOR=\"#FFAD00\">\n" +
                "  <TH>Nombre\n<TH>Valor");
    	Cookie[] cookies = request.getCookies();
    	Cookie cookie;
    	for(int i=0; i<cookies.length; i++) 
		{
      	cookie = cookies[i];
      	out.println("<TR>\n<TD>" + cookie.getName() + "\n<TD>" + cookie.getValue());
    	}
    	out.println("</TABLE></BODY></HTML>");
  	}
}
