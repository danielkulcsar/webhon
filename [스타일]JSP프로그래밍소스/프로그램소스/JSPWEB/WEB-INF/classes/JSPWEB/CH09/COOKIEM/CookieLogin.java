package JSPWEB.CH09.COOKIEM;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CookieLogin
 */
public class CookieLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CookieLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=euc-kr");
		//html����ϱ� ���� PrintWriter ��������
		PrintWriter out = response.getWriter();
		
		//��Ű ��������
		Cookie[] cookie = request.getCookies();
		String id = null;
		String pwd = null;
		
		//��Ű�� �����ϴ� ���
		if(cookie != null){
			//��Ű�� ��� keyName�� value ��������
			for(int i=0; i<cookie.length; i++){
				String key = cookie[i].getName();
				if(key.equals("id")){
					id = cookie[i].getValue();
				}
				if(key.equals("password")){
					pwd = cookie[i].getValue();
				}
			}
		}
		
		
		if(id == null){
			//�α��� ���� ���� ����
			out.println("<html><body>");
			out.println("<form method='post' action='"+request.getContextPath()+"/CookieServlet'>");
			out.println("���̵� : <input type='text' name='id'><br>");
			out.println("�н�����: <input type='password' name='password'><br>");
			out.println("<input type='submit' value='����'><br>");
			out.println("</body></html>");
		}else{
			//�α��� ����.
			out.println(id +"�� ȯ���մϴ�.");
			out.println("<a href="+request.getContextPath()+"/CookieLogout>�α׾ƿ�</a>");
		}
		
	}

}
