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
<h3>세션의 정보를 알아보자</h3>
isNew()  :<%= session.isNew() %><br><br>
세션 ID :   <%=session.getId() %><br><br>
세션의 생성시간 <%=new java.util.Date(session.getCreationTime()).toString()%> <br><br>
세션의 마지막 접속시간 <%= new java.util.Date(session.getLastAccessedTime()).toString() %><br><br>
세션의 action 시간<%= session.getMaxInactiveInterval() %> 초 <br>

</body>
</html>