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
		//html기록하기 위해 PrintWriter 가져오기
		PrintWriter out = response.getWriter();
		
		//쿠키 가져오기
		Cookie[] cookie = request.getCookies();
		String id = null;
		String pwd = null;
		
		//쿠키가 존재하는 경우
		if(cookie != null){
			//쿠키에 담긴 keyName과 value 가져오기
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
			//로그인 되지 않은 상태
			out.println("<html><body>");
			out.println("<form method='post' action='"+request.getContextPath()+"/CookieServlet'>");
			out.println("아이디 : <input type='text' name='id'><br>");
			out.println("패스워드: <input type='password' name='password'><br>");
			out.println("<input type='submit' value='전송'><br>");
			out.println("</body></html>");
		}else{
			//로그인 성공.
			out.println(id +"님 환영합니다.");
			out.println("<a href="+request.getContextPath()+"/CookieLogout>로그아웃</a>");
		}
		
	}

}
