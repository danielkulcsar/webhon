package JSPWEB.CH13.Listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class StringContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("StringContextListener 종료 되었습니다");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		  ServletContext ctx=arg0.getServletContext();
 Address ar=new Address("Dominico","seoul","77-7777");
 ctx.setAttribute("address", ar);

 
  System.out.println("StringContextListener 초기화 되었습니다");


	}

}
