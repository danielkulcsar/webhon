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
	out.println("<html><body><font color=red><h2>입력결과</h2></font><hr>");
	out.println("이름은"+name+"입니다");
	if(item==null)
		 out.println("<br>선택한 소지품이 없네요...");
	else
		{
		 out.println("<pre>선택하신 소지품은");
		 for(int i=0;i<item.length;i++)
			 out.println(item[i]+"   ");
		 out.println("\n 입니다.</pre><br>");


		}
	}

}
