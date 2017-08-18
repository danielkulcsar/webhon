package JSPWEB.CH09.COOKIEM;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CookieLogout
 */
public class CookieLogout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CookieLogout() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//쿠키 가져오기
		Cookie[] cookie = request.getCookies();
		for(int i=0; i<cookie.length; i++){
			//모든 Cookie의 생존시간을 0으로 지정함
			cookie[i].setMaxAge(0);
			response.addCookie(cookie[i]);
		}
		response.sendRedirect(request.getContextPath()+"/CookieLogin");
		
	}

}
