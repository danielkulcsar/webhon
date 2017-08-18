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
			result = "�̸��̾��� Guest ! ";
		}else if(name.equals("admin")) {
			result = "������";		
		}else result= name + "�� �ݰ�����";

		request.setAttribute("result", result);

		RequestDispatcher rd = request.getRequestDispatcher("mvcgo.jsp");
		rd.forward(request,response);
	}

}
