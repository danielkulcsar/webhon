package JSPWEB.CH02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetTest
 */
public class GetTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 response.setContentType("text/html; charset=EUC-KR");
	     request.setCharacterEncoding("EUC-KR");
		PrintWriter  out=response.getWriter();
	String name=request.getParameter("tname");
		String alias=request.getParameter("talias");
		
		out=response.getWriter();
		out.println("<html><body><font color=red><h2>�Է°��</h2></font><hr>");
		out.println(name+"���� ��Ī��  "+alias +"�̳׿� �� �Ϳ���~");
		
		
		/*String getquery=request.getQueryString();
		out.println("<br> Get������� ���޵� ��ü Query :"+ getquery);
		*/
		out.println("</body></html>");
		out.close();

	}

}
