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
<td>�̸�</td>
<td><%=request.getParameter("username")%> </td>
</tr>
<tr>
<td>�����ߴ� E-mail</td>
<td><%=request.getParameter("email")%></td>
</tr>
<tr>
<td>��������</td>
<td><%=request.getParameter("MUN")%></td>
</tr>
<tr>
<td> ������ ���� ��</td>
<td> <%=request.getParameter("dap")%></td>
</table>
<br>
<font size="4"><i> �����Ͻ� E-mail�� �ӽ� ��й�ȣ��  �����Ͽ����ϴ�</i></font><br>
<hr>
<%
Enumeration  eNames = request.getParameterNames();
while (eNames.hasMoreElements()) {
   String name = (String) eNames.nextElement();
    %> <%=name %><%} %>

</body>
</html>