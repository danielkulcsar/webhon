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
String id=(String)pageContext.getAttribute("id");
out.println(id);

String name=(String)request.getAttribute("name");
out.println(name);



String addr=(String)session.getAttribute("addr");
out.println(addr);


String tel=(String)application.getAttribute("tel");
out.println(tel);
%>
</body>
</html>