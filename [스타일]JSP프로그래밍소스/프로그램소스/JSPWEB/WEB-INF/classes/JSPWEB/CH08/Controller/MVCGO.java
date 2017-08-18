package JSPWEB.CH08.Controller;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MVCGO extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=euc-kr");
		request.setCharacterEncoding("euc-kr");

		String name=request.getParameter("name");
		Object result = null;

		if(name=="") {
			result = "이름이없는 Guest ! ";
		}else if(name.equals("admin")) {
			result = "관리자";		
		}else result= name + "님 반가워요";

		request.setAttribute("result", result);

		RequestDispatcher rd = request.getRequestDispatcher("mvcgo.jsp");
		rd.forward(request,response);
	}

}
