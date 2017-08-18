<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>PreparedRes</title>
</head>
<body>
<%
 Class.forName("oracle.jdbc.driver.OracleDriver");
Connection conn=null;
PreparedStatement  pstmt =null;
Statement  stmt=null;
ResultSet rs=null;
try{
conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521","WEBPRO","admin1234");
String sql = "insert into test03 values(?,?)";
pstmt= conn.prepareStatement(sql); 
pstmt.setString(1,request.getParameter("name"));
pstmt.setInt(2,Integer.parseInt(request.getParameter("age")));
pstmt.executeUpdate();
pstmt.close();
stmt=conn.createStatement();
rs=stmt.executeQuery("select * from Test03") ; 
%>
<center>
<h2> Test03Table의 내용을 출력해보자 </h2>
<TABLE BORDER="1" width=400>
<TR>
    
    <TH>Name</TH>
    <TH>AGE</TH>
    
</TR>
<% while(rs.next()){ %>
<TR>
    
    <TD> <%= rs.getString(1) %></TD>
    <TD> <%= rs.getInt(2) %></td>
    
</TR>
<% } 

%>
</TABLE>
<%
              
      
        } catch (SQLException e) { 
                throw new ServletException("SQL  오류", e); 
        } finally {               
        if (rs != null) { try { rs.close(); } catch (Exception ignored) {} } 
        if (pstmt != null) { try { pstmt.close(); } catch (Exception ignored) {} }
        if (stmt != null) { try { stmt.close(); } catch (Exception ignored) {} }
        if (conn != null) { try { conn.close(); } catch (Exception ignored) {} }
        } 
%>
</center>

</body>
</html>