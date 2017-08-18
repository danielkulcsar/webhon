<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>sessionres</title>
</head>
<body>
<%
String name=request.getParameter("name");
String age=request.getParameter("age");
session.setAttribute("name",name);
session.setAttribute("age",age);
 %>
 
세션이 생성되어 저장된 값을 확인하러
<a href="SessinView.jsp"> 다음 페이지</a>로 이동하세요

</body>
</html>