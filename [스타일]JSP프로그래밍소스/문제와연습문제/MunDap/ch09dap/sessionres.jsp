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
 
������ �����Ǿ� ����� ���� Ȯ���Ϸ�
<a href="SessinView.jsp"> ���� ������</a>�� �̵��ϼ���

</body>
</html>