package MunDap.ch13.dap;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.*;


public class AuthFilter implements Filter {

	
	    public void init(FilterConfig filterConfig) throws ServletException
	    {
	        
	    }
	    public void destroy()
	    {
	      
	    }
	    public void doFilter(ServletRequest request, ServletResponse response,
	            FilterChain chain) throws IOException, ServletException
	    {
	        HttpSession session = ((HttpServletRequest) request).getSession(false);
	        if (session == null || session.getAttribute("login") == null)
	        {
	            System.out.println("not login");
	           
	            RequestDispatcher dispatcher =request.getRequestDispatcher("Login.jsp");
	            dispatcher.forward(request,response);
	        }
	        else
	        {
	            System.out.println("already login");
	            chain.doFilter(request, response);
	        }
	    }


}
