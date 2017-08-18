package JSPWEB.CH09.SESSION;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 * Servlet implementation class SESSIONDAO
 */
public class SESSIONDAO extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SESSIONDAO() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
    public void service(HttpServletRequest request, HttpServletResponse response)
	throws IOException, ServletException{
	
	
	request.setCharacterEncoding("euc-kr");
	

	String id = request.getParameter("id");
	String password = request.getParameter("password");
	

	response.setContentType("text/html;charset=euc-kr");
	
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	
	try{
	
		con = getConnection();
		
		pstmt = con.prepareStatement("select * from SESSIONMEMBER where  id = ?");
		
		pstmt.setString(1, id);
		
		rs = pstmt.executeQuery();
		
		
		
		if(rs.next()){
			
			if(rs.getString("password").equals(password.trim())){
				
				SESSIONMVO mvo = new SESSIONMVO(rs.getString("id"), rs.getString("password"));
			
				HttpSession session = request.getSession();
				
				session.setAttribute("member", mvo);
				
				response.sendRedirect(request.getContextPath()+"/SESSIONLOGIN");
			}else{
				
				response.sendRedirect(request.getContextPath()+"/SESSIONLOGIN");
			}
		}else{
			
			response.sendRedirect(request.getContextPath()+"/SESSIONLOGIN");
		}
	}catch(Exception e){e.printStackTrace();} 
		
	
}
    public Connection getConnection() throws Exception{
		Context ctx = new InitialContext();
		DataSource ds =  (DataSource)ctx.lookup("java:comp/env/jdbc/myoracle");
		Connection con = ds.getConnection();
		return con;
	}
}
