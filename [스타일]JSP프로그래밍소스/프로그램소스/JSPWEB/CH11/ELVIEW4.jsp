<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<h2>비교 연산자 </h2>
<table >
<tr><td>
<table border="1">
<tr><td> 식   </td><td>결과 </td></tr>
<tr><td> 7 > '5' </td><td> ${7 > '5'}</td></tr>
<tr><td>'7' > 5  </td><td> ${'7' > 5}</td></tr>
<tr><td>'7' > '5' </td><td>${'7' > '5'}</td></tr>
<tr><td>7 >= 5    </td><td>${7 >= 5}</td></tr>
<tr><td>7 <= 5    </td><td> ${7 < 5}</td></tr>
<tr><td>7 == '7'  </td><td> ${7 == 7}</td></tr>
</table>
</td>

<td>

<h2>empty 연산자</h2>
<table border="1">
<tr><td> 식   </td><td>결과 </td></tr>
<tr><td>empty "" </td><td>${empty ""}</td></tr>
<tr><td>empty "abc"</td><td> ${empty "abc"}</td></tr>
<tr><td>empty abc </td><td>${empty abc}</td></tr>
<tr><td>empty def </td><td>${empty def}</td></tr>
</table>
</td>
</tr>
</table>
</body>
</html>
