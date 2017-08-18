<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<center>
<font size='4'>
<%@ page buffer="5kb" autoFlush="false" %>
<p>Jsp파일 실행시간을 알아보자 <br>
<%@ include file="goal01.jsp" %> <br>
<%@include file="goal02.html" %> <br>
<%@include file="goal01.jsp" %>
</p>
</font>
</center>

</body>
</html>