<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ page import="java.sql.*" %>
    <%@ page import="java.util.*" %>
<%@ page import="JSPWEB.CH08.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>ADDRESSLISTOL</title>
</head>
<body>
<%
	request.setCharacterEncoding("euc-kr");

	ADDRESSDAO adao = ADDRESSDAO.getInstance();
	ADDRESSBean  ar= adao.searchName(request.getParameter("name"));
%>
 <h3> ������ ���� </h3>
<form action="ADDRESSUPDATERES.jsp" method="post" name="form1">
<table border=1>
<tr>
	<td>�̸�</td><td><input type="text" name="name" value= <%=ar.getName() %>>
	</td>
	</tr>
	<tr>
	<td>
	��ȭ��ȣ</td><td><input type="text" name="tel" value= <%=ar.getTel() %>>
	</td>
	</tr>
	<tr>
	<td>
	��       �� </td><td><input type="text" name="addr" value= <%=ar.getAddr() %>></td>
	</tr>
	</table>
	<table>
		<tr>
	<td>
	
	<input type="submit" value="����">  
	</td>
	<td>
	 <input type="button" value="����" onclick="javascript:window.location='ADDRESSDELETERES.jsp?name=<%=ar.getName() %>'">  

	</td>
	</tr>
	</table>
</form>


</body>
</html>