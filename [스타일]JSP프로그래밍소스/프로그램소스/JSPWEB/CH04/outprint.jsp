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
out.println("out �����Դϴ� ����� �ɱ��?\n ����� �ȴٸ� 8kb �Ѿ ���¶��ϴ�");
 }
out.clearBuffer();
out.println("��� ������ ũ��="+out.getBufferSize()+"<br>");
out.println("������ �ʴ� ������ ũ�� ="+out.getRemaining()+"<br>");
out.flush();
out.println("flush �� ���������� ������ ũ�� ="+ out.getRemaining());
%>
</body>
</html>