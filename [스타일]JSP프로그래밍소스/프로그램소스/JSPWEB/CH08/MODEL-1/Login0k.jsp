<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Login0k.jsp</title>
</head>
<body>
<%
 String id=request.getParameter("id");
String pw=request.getParameter("pw");
 if((id.equals("admin")) && (pw.equals("1234")))
 {
	  out.println("ȯ���մϴ�  ������ ������ Ȯ���ϼ���");
	  out.println("<hr>");
	  out.println("<A href=a.jsp> ���� </a><br>");
	  out.println("<A href=b.jsp> ������ � </a><br>");
	  out.println("<A href=c.jsp> ������ �ֿ䴺�� </a><br>");
  }else
 {
	 out.println("�α��� �����Դϴ�. <a href=LoginForm.jsp>�ٽ� �Է��� �ּ���</a>"); 
 }
%>
</body>
</html>