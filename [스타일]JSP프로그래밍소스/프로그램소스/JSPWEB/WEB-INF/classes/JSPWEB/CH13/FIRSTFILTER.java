package JSPWEB.CH13;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class FIRSTFILTER implements Filter {

	 private FilterConfig config=null;
	 private String encoding=null;
	public void destroy() {
		// TODO Auto-generated method stub
		this.config=null;
	}

	
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		
	    
		if(arg0.getCharacterEncoding()==null)
			   arg0.setCharacterEncoding(encoding);
	    	arg2.doFilter(arg0,arg1);
	    	}
	    
	

	
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
   this.config=arg0;
   this.encoding=config.getInitParameter("encoding");
	}

}
