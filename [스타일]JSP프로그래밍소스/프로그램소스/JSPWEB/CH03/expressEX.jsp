<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>expressEX</title>
</head>
<body>
���ڿ��� ���   :  <%= "ȫ�浿" %><br><br>
���ڵ� ���      :   <%= 100*200 %><br><br>
��ü ������ ��� :  <%= new java.util.Date().toString() %><br><br>
<%= "2�� ��� �� �غ���" %><br>
<%= "==================="  %><br>
<%
for(int i=1;i<=9;i++){ %>
  
	     <%= "2 * " + i+ " = "+ i*2 %><br>
	     
	     <%}//for %>
	

</body>
</html>