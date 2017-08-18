<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>ADDRESSINSERT</title>
</head>
<body>
<h3>주소 입력 하기 </h3>
<form action="ADDRESSINSERTRES.jsp" method="post">
<table>
<tr>
	<td>이름</td><td><input type="text" name="name"><br>
	</td>
	</tr>
	<tr>
	<td>
	전화번호</td><td><input type="text" name="tel">
	</td>
	</tr>
	<tr>
	<td>
	주       소 </td><td><input type="text" name="addr"></td>
	</tr>
	<tr>
	<td>
	<input type="submit" value="입  력">  
	</td>
	</tr>
	</table>
</form>
</body>
</html>