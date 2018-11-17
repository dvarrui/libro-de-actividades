// BucleTag.java
import java.io.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

public class BucleTag extends BodyTagSupport{
	int veces = 0;
	BodyContent bc;
	public void setVeces(int veces) {
		this.veces = veces;
	}
	public void setBodyContent(BodyContent bc){
		this.bc = bc;
	}
	public int doAfterBody() throws JspException {
		if (veces-->0){
			try {
				JspWriter out = bc.getEnclosingWriter();
				out.println(bc.getString());
				bc.clearBody();
			} catch (IOException e) {
				System.out.println("Error en Tag Bucle" + e.getMessage());
			}
			return EVAL_BODY_TAG;
		} else {
			return SKIP_BODY;
		}
	}
}