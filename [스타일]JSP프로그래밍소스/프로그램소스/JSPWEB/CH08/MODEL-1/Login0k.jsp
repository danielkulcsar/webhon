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
	  out.println("환영합니다  오늘의 뉴스를 확인하세요");
	  out.println("<hr>");
	  out.println("<A href=a.jsp> 날씨 </a><br>");
	  out.println("<A href=b.jsp> 오늘의 운세 </a><br>");
	  out.println("<A href=c.jsp> 오늘의 주요뉴스 </a><br>");
  }else
 {
	 out.println("로그인 오류입니다. <a href=LoginForm.jsp>다시 입력해 주세요</a>"); 
 }
%>
</body>
</html>