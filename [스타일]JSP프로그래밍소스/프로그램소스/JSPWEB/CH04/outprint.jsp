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
 for(int i=0;i<=100;i++)
 {
out.println("out 연습입니다 출력이 될까요?\n 출력이 된다면 8kb 넘어간 상태랍니다");
 }
out.clearBuffer();
out.println("출력 버퍼의 크기="+out.getBufferSize()+"<br>");
out.println("사용되지 않는 버퍼의 크기 ="+out.getRemaining()+"<br>");
out.flush();
out.println("flush 후 사용되지안은 버퍼의 크기 ="+ out.getRemaining());
%>
</body>
</html>