<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.util.*" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%request.setCharacterEncoding("euc-kr"); %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>

<table border="1" cellspacing="1" cellpadding="5">
<tr>
<td>이름</td>
<td><%=request.getParameter("username")%> </td>
</tr>
<tr>
<td>가입했던 E-mail</td>
<td><%=request.getParameter("email")%></td>
</tr>
<tr>
<td>연상질문</td>
<td><%=request.getParameter("MUN")%></td>
</tr>
<tr>
<td> 질문에 대한 답</td>
<td> <%=request.getParameter("dap")%></td>
</table>
<br>
<font size="4"><i> 가입하신 E-mail로 임시 비밀번호를  전송하였습니다</i></font><br>
<hr>
<%
Enumeration  eNames = request.getParameterNames();
while (eNames.hasMoreElements()) {
   String name = (String) eNames.nextElement();
    %> <%=name %><%} %>

</body>
</html>