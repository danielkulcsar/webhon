<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ page import="java.sql.*" %>
    <%@ page import="java.util.*" %>
<%@ page import="JSPWEB.CH08.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>ADDRESSSDELETERES</title>
</head>
<body>
<jsp:useBean id="beans" class="JSPWEB.CH08.ADDRESSBean"/>

<%
int num=0;
ADDRESSDAO adao = ADDRESSDAO.getInstance();
num = adao.deleteDAO(request.getParameter("name"));
%>
		<%
	if(num==1){%>
		<script type="text/javascript">
		alert("입력하신대로 회원 정보가 삭제되었습니다.");
		document.location.href='ADDRESSLIST.jsp';
	</script>

<%}%>
   


</body>
</html>