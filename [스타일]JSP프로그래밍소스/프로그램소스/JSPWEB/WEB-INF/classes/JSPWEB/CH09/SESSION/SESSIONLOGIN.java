package JSPWEB.CH09.SESSION;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class SESSIONLOGIN extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public SESSIONLOGIN() {
        super();

    }

 
    	public void service(HttpServletRequest request, HttpServletResponse response)
    		throws IOException, ServletException{
    		
    		response.setContentType("text/html;charset=euc-kr");
    		PrintWriter out = response.getWriter();
    		
    	
    		HttpSession session = request.getSession();
    	
    		SESSIONMVO mvo = (SESSIONMVO)session.getAttribute("member");
    		
    		if(mvo == null){
    			//로그인 되지 않은 상태
    			out.println("<html><body>");
    			out.println("<form method='post' action='"+request.getContextPath()+"/SESSIONDAO'>");
    			out.println("아이 디     : <input type='text' name='id'><br>");
    			out.println("패스워드 : <input type='password' name='password'><br>");
    			out.println("<input type='submit' value='전송'><br>");
    			out.println("</body></html>");
    		}else{
    		
    			out.println(mvo.getId() +"님 환영합니다.");
    			out.println("<a href="+request.getContextPath()+"/SESSIONLOGOUT>로그아웃</a>");
    		}
    	}
    }

