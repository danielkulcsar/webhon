<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<h3>
<% request.setCharacterEncoding("euc-kr"); %>
<form action="ch05ex03res.jsp" method="post">
  
   이름을 입력하세요   : <input type="text" name="name"/><br>
     
  
     <input type="submit" value="확인 "/>
 </form>
</body>
</html>