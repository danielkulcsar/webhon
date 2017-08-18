package JSPWEB.CH09.COOKIEM;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class CookieServlet
 */
public class CookieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CookieServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//���� �������� Ÿ�԰� CharacterSet ����
		response.setContentType("text/html;charset=euc-kr");
		
		//HttpServletRequest��ü�� ����ִ� ��û �Ķ���Ͱ��� �ѱ� ó���� ���� ������.
		request.setCharacterEncoding("euc-kr");
		//HttpServletRequest��ü�� ����ִ� ��û�Ķ���͵��� getParameter()�޼ҵ带 �̿��ؼ� ������
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		

		try{
			//Connection ��������
			con = getConnection();
			//Connection���� ���� PreparedStatement�� ����
			pstmt = con.prepareStatement("select *from CookieMember where  id = ?");
			//PreparedStatement�� setXXX()�޼ҽ��� �̿��ؼ� ������ ?�� �ش��ϴ� ���ڰ��� ������
			pstmt.setString(1, id);
			//PreparedStatement�� executeQuery()�� �̿��ؼ� Select ���� ����, ����� ResultSet�� ���·� ����
			rs = pstmt.executeQuery();
			
			//ResetSet�� select�� ����� �����ϸ�...
			if(rs.next()){
				
				//�α��� �Է�â�� �Է��� �н������ ����Ÿ���̽��� �н������ ��
				if(rs.getString("password").equals(password.trim())){
					//�н����尡 ��ġ�� ���
					//���ο� Cookie ����, Cookie�� ������ new Cookie(KeyName, value)�� ���·� ������
					Cookie cookie_id = new Cookie("id", id);
					//Cookie�� �����ð� ����, �ʴ����� ����
					cookie_id.setMaxAge(60*60);
					//Cookie ����, web browser�� ���Ϸ� �����
					response.addCookie(cookie_id);
					
					Cookie cookie_pwd = new Cookie("password", password);
					cookie_pwd.setMaxAge(60*60);
					response.addCookie(cookie_pwd);
					
					//�α��� �������� �̵�
					response.sendRedirect(request.getContextPath()+"/CookieLogin");
				}else{
					//id�� ����������, ��й�ȣ ��ġ���� ����
					response.sendRedirect(request.getContextPath()+"/CookieLogin");
				}
			}else{
				//�������� �ʴ� id
				response.sendRedirect(request.getContextPath()+"/CookieLogin");
			}
			
		}catch(Exception e){e.printStackTrace();}
	}
	public Connection getConnection() throws Exception{
		Context ctx = new InitialContext();
		DataSource ds =  (DataSource)ctx.lookup("java:comp/env/jdbc/myoracle");
		Connection con = ds.getConnection();
		return con;
	}

}
