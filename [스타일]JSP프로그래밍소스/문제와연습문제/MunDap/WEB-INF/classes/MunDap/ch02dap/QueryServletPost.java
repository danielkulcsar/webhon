package MunDap.ch02dap;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class QueryServletPost
 */
public class QueryServletPost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
    response.setContentType("text/html; charset=EUC-KR");
     request.setCharacterEncoding("EUC-KR");
	PrintWriter  out=response.getWriter();
String name=request.getParameter("name");
	
	String item[]=request.getParameterValues("myitem");
	out=response.getWriter();
	out.println("<html><body><font color=red><h2>�Է°��</h2></font><hr>");
	out.println("�̸���"+name+"�Դϴ�");
	if(item==null)
		 out.println("<br>������ ����ǰ�� ���׿�...");
	else
		{
		 out.println("<pre>�����Ͻ� ����ǰ��");
		 for(int i=0;i<item.length;i++)
			 out.println(item[i]+"   ");
		 out.println("\n �Դϴ�.</pre><br>");


		}
	}

}
