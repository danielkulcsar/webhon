<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>getConnectionTest</title>
</head>
<body>
<%
Class.forName("oracle.jdbc.driver.OracleDriver");
String url="jdbc:oracle:thin:@127.0.0.1:1521";
String user="WEBPRO";
String password="admin1234";
Connection conn = DriverManager.getConnection(url,user,password);
   out.println("데이타 베이스 연결 성공 했습니다");
%>
</body>
</html>