<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%
pageContext.setAttribute("id","jkh6655");
String id=(String)pageContext.getAttribute("id");
out.println(id);
request.setAttribute("name","Dominico");
String name=(String)request.getAttribute("name");
out.println(name);


session.setAttribute("addr","seoul");
String addr=(String)session.getAttribute("addr");
out.println(addr);

application.setAttribute("tel","070-0000-0000");
String tel=(String)application.getAttribute("tel");
out.println(tel);
%>
<form action='Scoperes.jsp' method='post'>
<br>
<input type="submit" value="확인" > 
</form>
</body>
</html>