<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%
if(request.getMethod().equals("POST"))
{
	  if(session.isNew() || session.getAttribute("memberid")==null)
	  {
		  String id=request.getParameter("id");
		  String pw=request.getParameter("pw");
		  session.setAttribute("memberid",id);
		  session.setAttribute("memberpw",pw);
		  session.setMaxInactiveInterval(60);
	 

%>
회원 인증 처리되었습니다 <br>
<%=session.getAttribute("memberid") %> 님  안녕하세요<br>
<% }else{ %>
 회원인증할 필요가 없습니다 <br>
 이미 인증 하셧습니다  <br>
 <%  }} %>
</body>
</html>