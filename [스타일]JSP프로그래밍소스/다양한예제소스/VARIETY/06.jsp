<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
���� �ʱ� ���� ���� = <%=pageContext.getServletConfig()%> <br>
<hr>

�� ���ø����̼��� �̸��� ����  = <%= application.getServerInfo() %> <br>
<hr>
�� ���ø����̼� ������  ���ýý��� ���= <%= application.getRealPath("/")%><br>
<hr>
        �� ���ø����̼� Context =<%=application.getServletContextName() %>
      
</body>
</html>