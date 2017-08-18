<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ page import="java.sql.*" %>
<%@ page import="javax.naming.*" %>
<%@ page import="javax.sql.*" %>
<%@ page import="JSPWEB.CH08.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<jsp:useBean id="beans" class="JSPWEB.CH08.ADDRESSBean"/>
<body>
<%
	request.setCharacterEncoding("euc-kr");
	ArrayList<ADDRESSBean> list;
	
	ADDRESSDAO addrdao = ADDRESSDAO.getInstance();
	list = addrdao.selectList();
%>
<table   border="1"  width="450">
<tr>
	<th width="150">이름</th><th width="150">전화번호</th><th width="150">주소</th>
</tr>
<tr>	
<%	
	for(int i=0; i<list.size() ; i++){
		ADDRESSBean beans2 = list.get(i);
		%><tr>
		<td><a href="ADDRESSLISTOL.jsp?name=<%=beans2.getName() %>"><%=beans2.getName() %></a></td>
		<td><%=beans2.getTel() %></td>
		<td><%=beans2.getAddr() %></td>
		</tr>
		
<%
	} 
%>
</table>
</body>
</html>
