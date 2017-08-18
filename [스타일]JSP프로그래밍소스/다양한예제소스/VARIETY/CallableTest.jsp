<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
 <%@ page import="java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>CallableTest</title>
</head>
<body>

<%
 Class.forName("oracle.jdbc.driver.OracleDriver");
Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521","WEBPRO","admin1234");
CallableStatement cstmt = connection.prepareCall("{call pTest01(?,?,?)}");

cstmt.setInt(1,Integer.parseInt(request.getParameter("NO")));
cstmt.registerOutParameter(2,Types.VARCHAR);
cstmt.registerOutParameter(3,Types.DATE);
cstmt.executeQuery();
 String name= cstmt.getString(2);
String hdate= cstmt.getString(3);
%>
<%= request.getParameter("NO")%> 사번의  이름은 <%=name %> 이고 <br>
  입사날짜는 <%= hdate %> 입니다
</body>
</html>