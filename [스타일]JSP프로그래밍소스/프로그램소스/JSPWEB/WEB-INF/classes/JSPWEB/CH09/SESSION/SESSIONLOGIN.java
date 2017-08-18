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
    			//�α��� ���� ���� ����
    			out.println("<html><body>");
    			out.println("<form method='post' action='"+request.getContextPath()+"/SESSIONDAO'>");
    			out.println("���� ��     : <input type='text' name='id'><br>");
    			out.println("�н����� : <input type='password' name='password'><br>");
    			out.println("<input type='submit' value='����'><br>");
    			out.println("</body></html>");
    		}else{
    		
    			out.println(mvo.getId() +"�� ȯ���մϴ�.");
    			out.println("<a href="+request.getContextPath()+"/SESSIONLOGOUT>�α׾ƿ�</a>");
    		}
    	}
    }

