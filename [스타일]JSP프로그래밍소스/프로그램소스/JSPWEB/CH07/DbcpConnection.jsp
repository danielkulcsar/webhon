<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.sql.*,javax.sql.*, javax.naming.*" %>
 <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>DBCDConnection </title>
</head>
<body>
<%
    Connection conn = null; 
    
    
        try { 
       	Context initContext = new InitialContext();
       	Context envContext  = (Context)initContext.lookup("java:/comp/env");
      	DataSource ds = (DataSource)envContext.lookup("jdbc/myoracle");
 	conn = ds.getConnection();          
               out.println("연결 성공!<br>");
        } catch (NamingException e) { 
                throw new ServletException("JNDI  오류", e); 
        } catch (SQLException e) { 
                throw new ServletException("SQL  오류", e); 
        } finally {               
        if (conn != null) { try { conn.close(); } catch (Exception ignored) {} } 
        } 
%>

</body>
</html>