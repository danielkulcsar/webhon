<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%session.setMaxInactiveInterval(1*60); %>
<h3>������ ������ �˾ƺ���</h3>
isNew()  :<%= session.isNew() %><br><br>
���� ID :   <%=session.getId() %><br><br>
������ �����ð� <%=new java.util.Date(session.getCreationTime()).toString()%> <br><br>
������ ������ ���ӽð� <%= new java.util.Date(session.getLastAccessedTime()).toString() %><br><br>
������ action �ð�<%= session.getMaxInactiveInterval() %> �� <br>

</body>
</html>