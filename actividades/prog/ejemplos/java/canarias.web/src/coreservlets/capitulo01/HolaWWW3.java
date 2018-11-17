package coreservlets.capitulo01;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class HolaWWW3 extends HttpServlet {
	static final long serialVersionUID=1L;
	public void doGet(HttpServletRequest requets, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>\n");
		out.println("<head><title>Hola WWW 3</title></head>\n");
		out.println("<BODY>\n<h1>Hola WWW 3</h1>\n</body>\n");
		out.println("</html>");
	}
}
