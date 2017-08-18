<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ page import="java.sql.*" %>
    <%@ page import="java.util.*" %>
<%@ page import="JSPWEB.CH08.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>ADDRESSSUPDATERES</title>
</head>
<body>
<jsp:useBean id="beans" class="JSPWEB.CH08.ADDRESSBean"/>
<!-- 폼에서 넘어온 값들을  ADDRESSBean 클래스의 멤버변수에 넣어줌 -->
<jsp:setProperty property="*" name="beans"/>
<%
int num=0;

ADDRESSDAO adao = ADDRESSDAO.getInstance();
num = adao.updateDAO(beans);
%>

	<%
	if(num==1){%>
		<script type="text/javascript">
		alert("입력하신대로 회원 정보가 수정되었습니다.");
		document.location.href='ADDRESSLIST.jsp';
	</script>

<%}%>
   


</body>
</html>