<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<% request.setCharacterEncoding("euc-kr"); %>
forwardInput.jsp 시작입니다. 
<jsp:forward page="forwardres.jsp">
  <jsp:param name="name" value="Dominico"/>
</jsp:forward>
forward 수행 후   forwardInput.jsp 끝입니다 
</body>
</html>