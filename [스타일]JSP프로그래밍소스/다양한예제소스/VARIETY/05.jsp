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
  <h2><%= i %>��° �湮�Դϴ�. <br> 
5�� �Ŀ� ���� ����Ʈ�� �̵��մϴ�. <br>
------->http://www.wellbook.net


</body>
</html>