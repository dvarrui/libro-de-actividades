package web.ser;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

/** Sets six cookies: three that apply only to the current
 *  session (regardless of how long that session lasts)
 *  and three that persist for an hour (regardless of
 *  whether the browser is restarted).
 *  <P>
 *  Taken from Core Servlets and JavaServer Pages
 *  from Prentice Hall and Sun Microsystems Press,
 *  http://www.coreservlets.com/.
 *  &copy; 2000 Marty Hall; may be freely used or adapted.
 */

public class SetCookies extends HttpServlet 
{
  	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException 
	{
   	for(int i=0; i<3; i++) 
		{
      	// Default maxAge is -1, indicating cookie
      	// applies only to current browsing session.
      	Cookie cookie = new Cookie("Session-Cookie-" + i, "Cookie-Value-S" + i);
      	response.addCookie(cookie);
      	cookie = new Cookie("Persistent-Cookie-" + i,"Cookie-Value-P" + i);
      	// Cookie is valid for an hour, regardless of whether
      	// user quits browser, reboots computer, or whatever.
      	cookie.setMaxAge(500);
      	response.addCookie(cookie);
    	} 
    	response.setContentType("text/html");
    	PrintWriter out = response.getWriter();
    	String title = "Creando Cookies de ejemplo";
    	out.println	(ServletUtilities.headWithTitle(title) +
       "<BODY BGCOLOR=\"#FDF5E6\">\n" +
       "<H1 ALIGN=\"CENTER\">" + title + "</H1>\n" +
       "Se han creado 6 cookies de ejemplo con esta página.<br>" +
       "Para visualizarlas, consultar el siguiente enlace <A HREF=\"/cursoJSP/ShowCookies\">\n" +
       "<CODE>ShowCookies</CODE> servlet</A>.\n" +
       "<P>\n" +
       "Tres cookies están asociadas a la sesión actual y tres son persistentes.<br>" +
       "Cierra el navegador y vuelve a la página <CODE>ShowCookies</CODE> para verificarlo.<br>" +
       "</BODY></HTML>");
  }
}
       
