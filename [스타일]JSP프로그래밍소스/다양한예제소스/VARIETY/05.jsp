<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>

<%  
response.setContentType("text/html; charset=euc-kr"); 
response.setHeader("Refresh", "5; URL=http://www.wellbook.net") ; 

%> 

  <%! int i=0; %> 
  <% i++; %> 
  <h2><%= i %>번째 방문입니다. <br> 
5초 후에 웰북 사이트로 이동합니다. <br>
------->http://www.wellbook.net


</body>
</html>