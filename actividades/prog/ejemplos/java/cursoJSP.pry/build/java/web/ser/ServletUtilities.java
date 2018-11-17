package web.ser;

import javax.servlet.*;
import javax.servlet.http.*;

/** Some simple time savers. Note that most are static methods.
 *  <P>
 *  Taken from Core Servlets and JavaServer Pages
 *  from Prentice Hall and Sun Microsystems Press,
 *  http://www.coreservlets.com/.
 *  &copy; 2000 Marty Hall; may be freely used or adapted.
 */

public class ServletUtilities 
{
	public static final String DOCTYPE = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">";

  	public static String headWithTitle(String title) 
	{
   	return(DOCTYPE + "\n<HTML>\n<HEAD><TITLE>Curso JSP (Java Server Pages)</TITLE></HEAD>\n" +
				"<body bgcolor=white><table border=\"0\"><tr><td align=center><img src=\"http://localhost:8080/cursoJSP/images/java_logo.gif\">\n" +
				"</td><td><h1>Servlet: "+title+"</h1></td></tr></table>" );
  }

  /** Read a parameter with the specified name, convert it
   *  to an int, and return it. Return the designated default
   *  value if the parameter doesn't exist or if it is an
   *  illegal integer format.
  */
  
	public static int getIntParameter(HttpServletRequest request, String paramName, int defaultValue) 
  	{
    	String paramString = request.getParameter(paramName);
    	int paramValue;
    	try 
		{ paramValue = Integer.parseInt(paramString); } 
		catch(NumberFormatException nfe) 
		{ // null or bad format
      	paramValue = defaultValue;
    	}
    	return(paramValue);
  	}

  /** Given an array of Cookies, a name, and a default value,
   *  this method tries to find the value of the cookie with
   *  the given name. If there is no cookie matching the name
   *  in the array, then the default value is returned instead.
   */
  
  	public static String getCookieValue(Cookie[] cookies, String cookieName, String defaultValue) 
	{
   	if (cookies != null) 
		{
      	for(int i=0; i<cookies.length; i++) 
			{
        		Cookie cookie = cookies[i];
        		if (cookieName.equals(cookie.getName())) return(cookie.getValue());
      	}
    	}
    	return(defaultValue);
  	}

  /** Given an array of cookies and a name, this method tries
   *  to find and return the cookie from the array that has
   *  the given name. If there is no cookie matching the name
   *  in the array, null is returned.
   */
  
  	public static Cookie getCookie(Cookie[] cookies,  String cookieName) 
	{
    	if (cookies != null) 
		{
      	for(int i=0; i<cookies.length; i++) 
			{
        		Cookie cookie = cookies[i];
        		if (cookieName.equals(cookie.getName())) return(cookie);
      	}
    	}
    	return(null);
  	}

  /** Given a string, this method replaces all occurrences of
   *  '<' with '&lt;', all occurrences of '>' with
   *  '&gt;', and (to handle cases that occur inside attribute
   *  values), all occurrences of double quotes with
   *  '&quot;' and all occurrences of '&' with '&amp;'.
   *  Without such filtering, an arbitrary string
   *  could not safely be inserted in a Web page.
   */

  	public static String filter(String input) 
	{
    	StringBuffer filtered = new StringBuffer(input.length());
    	char c;
    	for(int i=0; i<input.length(); i++) 
		{
      	c = input.charAt(i);
      	if (c == '<') 			{ filtered.append("&lt;");} 
			else if (c == '>') 	{  filtered.append("&gt;"); } 
			else if (c == '"') 	{  filtered.append("&quot;");} 
			else if (c == '&') 	{ filtered.append("&amp;"); } 
			else 						{ filtered.append(c);  }
    	}
    	return(filtered.toString());
  	}
}
