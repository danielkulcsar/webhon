<html>
<head><title>로그인</title>
<script language="javascript">
<!--
function id_chk()
{
	if(frm.id.value.length==0)
	{
		alert("아이디를 입력하세요");
		frm.id.focus();
		return false;
	}
	else if(frm.pass.value.length==0)
	{
		alert("비밀번호를 입력하세요");
		frm.pass.focus();
		return false;
	}
return true;
}
//-->
</script>
</head>

<body>

<form name="frm" action="./log_find.php" method="post" onSubmit="return id_chk()">
<table border=1 width=300 cellspacing=0 cellpadding=0>
<tr>
	<td>아이디</td><td><input type="text" name="id" size="15"></td>
</tr>
<tr>
	<td>비밀번호</td><td><input type="password" name="pass" size="15"></td>
</tr>
<tr>
	<td colspan=2 align=center>
	<input type="submit" name="smt" value="로그인">&nbsp;&nbsp;&nbsp;
	<input type="reset" name="rst" value="취소"></td>
</tr>
</table>
</form>

</body>
</html>
