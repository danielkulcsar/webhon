package JSPWEB.CH13.Listener;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class UsingParameters extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public UsingParameters() {
        super();
       
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         doGet(request,response);
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String firstName = getInitParameter("firstName");

        ServletConfig configuration = getServletConfig();
        String lastName = configuration.getInitParameter("lastName");

        ServletContext context = getServletContext();
        String category = context.getInitParameter("category");

        out.println("<HTML>");
        out.println("<HEAD>");
        out.println("<TITLE>");
        out.println("Using Initialization Parameters");
        out.println("</TITLE>");
        out.println("</HEAD>");
        out.println("<BODY>");
        out.println("<H1>Using Initialization Parameters</H1>");

        out.println("First Name: " + firstName);
        out.println("<BR>");
        out.println("Last Name: " + lastName);
        out.println("<BR>");
        out.println("Category: " + category);
        out.println("<BR>");

        out.println("</BODY>");
        out.println("</HTML>");
	}

}
