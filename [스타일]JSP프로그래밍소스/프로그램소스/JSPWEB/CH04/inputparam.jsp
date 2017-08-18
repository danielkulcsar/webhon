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
   <h3> 비번을 잊으셨다면 연상질문에 답해주세요</h3>
   <table border=0 cellspacing="1" cellpadding="5">
<tr>
<td>이름</td>
<td><input type=text size=20 name=username></td> 
<tr>
<tr>
<td>가입했던 E-mail</td>
<td><input type=text size=20 name=email></td> 
<tr>
<td>연상질문</td>
<td>
<select name=MUN >
	<option selected>선택할질문</option>
	<option>내가 다닌 초등학교는? </option>
	<option>나의 보물 1호는?</option>
	<option>나의 취미는?</option>
	<option>내가 가입한 날짜는?</option>
	<option>나의 어머님 성함은? </option>
</select>
</td></tr>
<tr>
<td> 연상질문에  대한 나의 답</td>
<td> <input type=text size=20 name=dap></td> 
</tr>
<tr><td colspan=2 align=center><input type=submit value="확인"><input type=reset value="취소"></td></tr>
</table>
</form>
    </body>
</html>