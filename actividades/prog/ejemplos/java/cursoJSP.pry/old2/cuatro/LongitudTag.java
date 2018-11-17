// Longitud.java
import java.io.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

public class LongitudTag extends BodyTagSupport{
	public int doAfterBody() throws JspException {
		try {
			BodyContent bc = getBodyContent();
			String cuerpo = bc.getString();
			JspWriter out = bc.getEnclosingWriter();
			if (cuerpo != null){
				out.print("la longitud de " + cuerpo + "es: " + cuerpo.length());
			}
		} catch (IOException e){
			throw new JspException("Error: IOEXception" + e.getMessage());
		}
		return SKIP_BODY;
	}
}