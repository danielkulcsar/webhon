<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%
  Enumeration er=session.getAttributeNames();
   while(er.hasMoreElements())
   {  String list=(String)er.nextElement();
      String Value=(String)session.getAttribute(list);
      
	   out.println("���ǿ� �����  �̸� "+ list+"�̰�");
	   out.println("���ǿ� �����  �� "+ Value+"�Դϴ�<br>");
   }
  
%>
</body>
</html>