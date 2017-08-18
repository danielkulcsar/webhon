package MunDap.ch13.dap;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

public class DBLogFilter implements Filter {

        public DBLogFilter() {
        
    }
	
	public void destroy() {
		
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		 HttpServletRequest req = (HttpServletRequest) request;
		    HttpServletResponse res = (HttpServletResponse) response;
		  
		   String request_uri = req.getRequestURI()==null ?"":req.getRequestURI();
		
		    String remote_address = req.getRemoteAddr()==null?"":req.getRemoteAddr();
		    String server_name = req.getServerName()==null?"":req.getServerName();
		   		   	  
		    HttpSession session = req.getSession();
		    String session_id = session.getId();
		  
		    chain.doFilter(request, response);
		    LogDao log=LogDao.getInstance();
		
		    LogBean  Lbean=new LogBean();
		    Lbean.setRequest_uri(request_uri);
		    Lbean.setRemote_address(remote_address);
		    Lbean.setServer_name(server_name);
		    Lbean.setSession_id(session_id);
		   
		 
		    try {
				log.insertDAO(Lbean);
			} catch (Exception e) {
			
				e.printStackTrace();
			}
		  		   
		  }




	
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
