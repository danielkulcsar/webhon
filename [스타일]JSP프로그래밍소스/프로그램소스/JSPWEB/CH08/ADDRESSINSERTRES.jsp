
    <%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.sql.*" %>
<%@ page import="JSPWEB.CH08.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>ADDRESSINSERTRES</title>
</head>
<body>
<%
	request.setCharacterEncoding("euc-kr");
%>
<jsp:useBean id="beans" class="JSPWEB.CH08.ADDRESSBean"/>
	<!-- ������ �Ѿ�� ������  ADDRESSBean Ŭ������ ��������� �־��� -->
	<jsp:setProperty property="*" name="beans"/>
<%
	int num=0;
	ADDRESSDAO adao = ADDRESSDAO.getInstance();
	num = adao.insertDAO(beans);
%>
<%
	if(num==1){
   response.sendRedirect("ADDRESSLIST.jsp");

	}else{
 %>
	�Է� ���� �߽��ϴ� �ٽ� �ϼ���
	<br><a href="javascript:history.back();" ></a>
	<% 	}%>

</body>
</html>