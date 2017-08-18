package servletWEB;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class posthandle extends HttpServlet {
	
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
     doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		  String name = request.getParameter("username");
	        String town = request.getParameter("town");
	        String email = request.getParameter("email");
	        response.setContentType("text/html");
	        java.io.PrintWriter out = response.getWriter();
	      
	        out.println("<html>");
	        out.println("<head>");
	        out.println("<title>Welcome</title>");  
	        out.println("</head>");
	        out.println("<body>");
	        out.println("<h1>Your Identity</h1>");
	        out.println("Your name is: " + ( (name == null ||  name.equals("")) ? "null" : name));
	         out.println("<br><br>");
	         out.println("Your 'lively town' is: " + ( (town == null ||  town.equals("")) ? "null" : town));
	         out.println("<br><br>");
	         out.println("Your email address is: " + ( (email == null ||  email.equals("")) ? "null" : email));	           
	       	        out.println("</body>");
	        out.println("</html>");
	        out.close();

	}

}
