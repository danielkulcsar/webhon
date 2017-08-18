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
      
	   out.println("세션에 저장된  이름 "+ list+"이고");
	   out.println("세션에 저장된  값 "+ Value+"입니다<br>");
   }
  
%>
</body>
</html>