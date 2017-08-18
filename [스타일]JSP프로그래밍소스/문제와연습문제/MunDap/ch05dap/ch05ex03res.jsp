<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<jsp:useBean id="day" class="MunDap.ch05dap.HelloToday"/>
<jsp:setProperty  name="day" property="*"/>
<% request.setCharacterEncoding("euc-kr"); %>
 
<center>
			<h1>안녕하세요
			<jsp:getProperty name="day" property="name"/> 님 반갑습니다 <br>

			지금은 <jsp:getProperty name="day" property="month"/>월 
			<jsp:getProperty name="day" property="date"/>일 입니다.
			</h1>
</center>
</body>
</html>