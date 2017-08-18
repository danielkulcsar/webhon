package JSPWEB.CH02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class NetINFO
 */
public class NetINFO extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=euc-kr");
PrintWriter  out;
try{
     out=response.getWriter();
	 out.println("<html>");
	 out.println("<body>");
	 out.println("<h1> 네트워크 정보를 보자</h1>");
	 out.println("<pre>");
	 out.println(" Request Scheme  "+  request.getScheme() );
	 out.println(" Server Name  "+ request.getServerName()     );
	 out.println("Server Port  "+request.getServerPort()    );
	 out.println("Client Address   "+ request.getRemoteAddr()  );
	 out.println(" Client Host    "+  request.getRemoteHost() );
	 out.println("Client  port    "   +  request.getRemotePort()  );
     out.println("</pre>");
	 out.println("</body>");
	 out.println("</html>");
	 out.close();
}catch(IOException e){}
	}

}
