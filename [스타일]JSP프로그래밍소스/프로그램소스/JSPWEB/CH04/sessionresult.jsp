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
ȸ�� ���� ó���Ǿ����ϴ� <br>
<%=session.getAttribute("memberid") %> ��  �ȳ��ϼ���<br>
<% }else{ %>
 ȸ�������� �ʿ䰡 �����ϴ� <br>
 �̹� ���� �ϼ˽��ϴ�  <br>
 <%  }} %>
</body>
</html>