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
		//응답 컨텐츠의 타입과 CharacterSet 지정
		response.setContentType("text/html;charset=euc-kr");
		
		//HttpServletRequest객체에 들어있는 요청 파라미터값의 한글 처리를 위해 선언함.
		request.setCharacterEncoding("euc-kr");
		//HttpServletRequest객체에 들어있는 요청파라미터들은 getParameter()메소드를 이용해서 가져옴
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		

		try{
			//Connection 가져오기
			con = getConnection();
			//Connection으로 부터 PreparedStatement문 생성
			pstmt = con.prepareStatement("select *from CookieMember where  id = ?");
			//PreparedStatement의 setXXX()메소스들 이용해서 쿼리의 ?에 해당하는 인자값을 지정함
			pstmt.setString(1, id);
			//PreparedStatement의 executeQuery()를 이용해서 Select 쿼리 실행, 결과는 ResultSet의 형태로 받음
			rs = pstmt.executeQuery();
			
			//ResetSet에 select한 결과가 존재하면...
			if(rs.next()){
				
				//로그인 입력창에 입력한 패스워드와 데이타베이스의 패스워드와 비교
				if(rs.getString("password").equals(password.trim())){
					//패스워드가 일치할 경우
					//새로운 Cookie 생성, Cookie는 생성시 new Cookie(KeyName, value)의 형태로 생성함
					Cookie cookie_id = new Cookie("id", id);
					//Cookie의 생존시간 지정, 초단위로 지정
					cookie_id.setMaxAge(60*60);
					//Cookie 전송, web browser가 파일로 기록함
					response.addCookie(cookie_id);
					
					Cookie cookie_pwd = new Cookie("password", password);
					cookie_pwd.setMaxAge(60*60);
					response.addCookie(cookie_pwd);
					
					//로그인 페이지로 이동
					response.sendRedirect(request.getContextPath()+"/CookieLogin");
				}else{
					//id는 존재하지만, 비밀번호 일치하지 않음
					response.sendRedirect(request.getContextPath()+"/CookieLogin");
				}
			}else{
				//존재하지 않는 id
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
