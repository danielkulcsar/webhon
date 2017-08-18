<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>expressEX</title>
</head>
<body>
문자열을 출력   :  <%= "홍길동" %><br><br>
숫자도 출력      :   <%= 100*200 %><br><br>
객체 생성후 출력 :  <%= new java.util.Date().toString() %><br><br>
<%= "2단 출력 을 해보자" %><br>
<%= "==================="  %><br>
<%
for(int i=1;i<=9;i++){ %>
  
	     <%= "2 * " + i+ " = "+ i*2 %><br>
	     
	     <%}//for %>
	

</body>
</html>