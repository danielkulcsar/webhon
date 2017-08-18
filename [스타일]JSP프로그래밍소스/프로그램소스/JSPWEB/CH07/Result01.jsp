<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ page import="java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Result01</title>
</head>
<body>

<%
 Class.forName("oracle.jdbc.driver.OracleDriver");
Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521","WEBPRO","admin1234");
Statement statement = connection.createStatement() ;
ResultSet resultset = 
    statement.executeQuery("select * from Test01") ; 
%>
<center>
<h2> Test01 Table의 내용을 출력해보자 </h2>
<TABLE BORDER="1" width=400>
<TR>
    <TH>NO</TH>
    <TH>Name</TH>
    <TH>HDATE</TH>
    
</TR>
<% while(resultset.next()){ %>
<TR>
    <TD> <%= resultset.getInt(1) %></td>
    <TD> <%= resultset.getString(2) %></TD>
    <TD> <%= resultset.getString(3) %></TD>
    
</TR>
<% } %>
</TABLE>
</center>
</body>
</html>