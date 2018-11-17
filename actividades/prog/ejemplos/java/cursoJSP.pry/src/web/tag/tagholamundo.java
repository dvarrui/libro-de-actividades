// HolaMundoTag.java
package web.tag;

import java.io.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

public class tagholamundo extends TagSupport
{
	public int doStartTag() throws JspException 
    {
		try
        { pageContext.getOut().print("Hola Mundo desde la clase");	} 
        catch (IOException e) 
        {
			throw new JspException ("Error: IOException" + e.getMessage());
		}
		return SKIP_BODY;
	}
	
	public int doEndTag() throws JspException 
    {	return EVAL_PAGE;	}
}
