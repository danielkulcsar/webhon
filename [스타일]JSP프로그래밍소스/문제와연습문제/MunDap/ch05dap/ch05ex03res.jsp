<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<jsp:useBean id="day" class="MunDap.ch05dap.HelloToday"/>
<jsp:setProperty  name="day" property="*"/>
<% request.setCharacterEncoding("euc-kr"); %>
 
<center>
			<h1>�ȳ��ϼ���
			<jsp:getProperty name="day" property="name"/> �� �ݰ����ϴ� <br>

			������ <jsp:getProperty name="day" property="month"/>�� 
			<jsp:getProperty name="day" property="date"/>�� �Դϴ�.
			</h1>
</center>
</body>
</html>