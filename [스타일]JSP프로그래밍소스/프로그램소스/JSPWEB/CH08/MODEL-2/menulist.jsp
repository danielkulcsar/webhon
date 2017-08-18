<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>menulist</title>
</head>
<body>
    환영합니다     <%=request.getParameter("id") %> 님      오늘의 뉴스를 확인하세요
	 <hr>
	 <A href=a.jsp> 날씨 </a><br>
	<A href=b.jsp> 오늘의 운세 </a><br>
	<A href=c.jsp> 오늘의 주요뉴스 </a><br>
</body>
</html>