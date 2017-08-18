package MunDap.ch13.dap;

import java.util.StringTokenizer;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Application Lifecycle Listener implementation class DBCPCON
 *
 */
public class DBCPCON implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public DBCPCON() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0) {
        // TODO Auto-generated method stub
    	
    	try
    	{
    		ServletContext context=arg0.getServletContext();
    	
    	String drivers=context.getInitParameter("jdbcdriver");
    	StringTokenizer stk=new StringTokenizer(drivers,",");
    	while(stk.hasMoreTokens())
    	{
    		String jdbcdriver=stk.nextToken();
    		Class.forName(jdbcdriver);
       		
    	}
    	Class.forName("oracle.jdbc.pool.OracleDataSourceFactory");
    	}catch(Exception ex)
    	{
    		throw new RuntimeException(ex);
    	}
    	arg0.getServletContext().log("DBCP 초기화가 되었습니다");
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0) {
        // TODO Auto-generated method stub
    }
	
}
