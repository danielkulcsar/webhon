package JSPWEB.CH12;

import java.io.*;
import javax.servlet.jsp.*;

import javax.servlet.jsp.tagext.*;



public class HelloWorldTag extends TagSupport
{
    public int doStartTag() throws JspException
    {
        try
        {
            JspWriter out = pageContext.getOut();
              out.println("성공 나만의 태그 <br>");
              out.println("<b> JSP1.2<b><br>");
           
        }
        catch(Exception e)
        {   
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;

    }
}
