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
   out.println("out은 ");
   out.println(out.getClass().getName());
   out.println("의 객체 변수입니다.<br>");
   out.print("request는 ");
   out.println(request.getClass().getName());
   out.println("의 객체 변수입니다.<br>");
   out.print("response는");
   out.println(response.getClass().getName());
   out.println("의 객체 변수입니다.");
%>
</body>
</html>