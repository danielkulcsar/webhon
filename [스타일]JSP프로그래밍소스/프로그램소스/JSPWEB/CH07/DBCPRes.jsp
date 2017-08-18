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
ResultSet resultset=null;
Statement statement=null;
        try { 
       	Context initContext = new InitialContext();
       	Context envContext  = (Context)initContext.lookup("java:/comp/env");
      	DataSource ds = (DataSource)envContext.lookup("jdbc/myoracle");
 	conn = ds.getConnection();     
 	 statement = conn.createStatement() ;
 	 resultset = 
 	    statement.executeQuery("select * from ADDRLIST") ; 
 	%>
 	<h2> ADDRLIST 의 내용을 출력해보자 </h2>
<TABLE BORDER="1" width=400>
<TR>
    <TH>Name</TH>
    <TH>AGE</TH>
    <TH>TEL</TH>
    
</TR>
<% while(resultset.next()){ %>
<TR>
    <TD> <%= resultset.getString(1) %></td>
    <TD> <%= resultset.getInt(2) %></TD>
    <TD> <%= resultset.getString(3) %></TD>
    
</TR>
<% } %>
</TABLE>
<%
              
        } catch (NamingException e) { 
                throw new ServletException("JNDI  오류", e); 
        } catch (SQLException e) { 
                throw new ServletException("SQL  오류", e); 
        } finally {               
        if (resultset != null) { try { resultset.close(); } catch (Exception ignored) {} } 
        if (statement != null) { try { statement.close(); } catch (Exception ignored) {} }
        if (conn != null) { try { conn.close(); } catch (Exception ignored) {} }
        } 
%>

</body>
</html>