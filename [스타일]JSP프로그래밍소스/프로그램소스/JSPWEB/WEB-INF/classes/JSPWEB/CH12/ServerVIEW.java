package JSPWEB.CH12;

import java.io.*;
import java.net.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

public class ServerVIEW extends TagSupport
{
	   public int doStartTag() throws JspException
	   {
	      try {
	        
	         HttpServletRequest request =
	            (HttpServletRequest) pageContext.getRequest();

	         String host = request.getServerName();
	         int port = request.getServerPort();

	         URL url = new URL("http", host, port, "/");
	         HttpURLConnection con = (HttpURLConnection)
	            url.openConnection();
	         con.setRequestMethod("OPTIONS");
	         String webserver = con.getHeaderField("server");
	         
	         JspWriter out = pageContext.getOut();
	         out.print(webserver);
	      }
	      catch (IOException e) {
	         throw new JspException(e.getMessage());
	      }
	      return SKIP_BODY;
	   }
	}
