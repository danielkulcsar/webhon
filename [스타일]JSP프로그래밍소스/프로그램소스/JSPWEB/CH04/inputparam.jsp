<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<form action="paramres.jsp" method="Post">
   <h3> ����� �����̴ٸ� ���������� �����ּ���</h3>
   <table border=0 cellspacing="1" cellpadding="5">
<tr>
<td>�̸�</td>
<td><input type=text size=20 name=username></td> 
<tr>
<tr>
<td>�����ߴ� E-mail</td>
<td><input type=text size=20 name=email></td> 
<tr>
<td>��������</td>
<td>
<select name=MUN >
	<option selected>����������</option>
	<option>���� �ٴ� �ʵ��б���? </option>
	<option>���� ���� 1ȣ��?</option>
	<option>���� ��̴�?</option>
	<option>���� ������ ��¥��?</option>
	<option>���� ��Ӵ� ������? </option>
</select>
</td></tr>
<tr>
<td> ����������  ���� ���� ��</td>
<td> <input type=text size=20 name=dap></td> 
</tr>
<tr><td colspan=2 align=center><input type=submit value="Ȯ��"><input type=reset value="���"></td></tr>
</table>
</form>
    </body>
</html>