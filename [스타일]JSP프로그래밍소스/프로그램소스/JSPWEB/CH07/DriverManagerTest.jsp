<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>DrivrManagerTest</title>
</head>
<body>
<%
Class.forName("oracle.jdbc.driver.OracleDriver");
out.println("드라이버 로딩 성공" );
 %>
</body>
</html>