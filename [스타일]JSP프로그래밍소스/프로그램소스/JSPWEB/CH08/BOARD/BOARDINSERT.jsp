<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" errorPage="error.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="boardcss.css" rel="stylesheet" type="text/css">
<title>BOARDINSERT</title>
</head>

<body>
<center>
<H2>방명록을 입력해주세요  </H2>
<HR>

<form method=post action="insertres.go?action=insertres">
<table width="400" border="1" cellspacing="0" cellpadding="0"   align="center">

  <tr>
     <td  width="70"   align="center">이  름</td>	
       <td width="330">  <input type="text" name="name" size="30" maxlength="10"></td>
  </tr>
  <tr>
   <td width="70"  align="center">email</td>
        <td width="330"><input type="text" name="email" size="30"></td>
  </tr>
    <tr>
    <td width="70"  align="center">homepage</td>
        <td width="330"><input type="text" name="homepage" size="30"></td>
  </tr>
    <tr>
        <td width="70"  >Contents</td><td><textarea rows="5" name="contents" cols="40"></textarea></td>
  </tr>
  <tr>
    <td colspan=2 align=center><input type=submit value="저장">
    <input type=reset value="취소"></td>
</tr>
</table>
</form>

</center>
</body>
</html>