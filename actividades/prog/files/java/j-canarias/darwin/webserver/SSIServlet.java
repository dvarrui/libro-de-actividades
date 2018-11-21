package webserver;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;

/** A first stab at adding SSI (Server Side Include) to the server */
public class SSIServlet extends HttpServlet {
	/** The string we look for (not a full parse, obviously) */
	public static String header = "<!--#include";

	public void service(ServletRequest request, ServletResponse response) {

	}
}
