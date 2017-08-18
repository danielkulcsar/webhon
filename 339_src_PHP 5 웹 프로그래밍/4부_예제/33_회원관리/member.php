<?
session_start(); // 세션을 시작한다.

// 현재 사용자가 로그인을 했는지 아닌지를 체크한다.
if($ses_id && $ses_pass && $ses_email)
{
        // 이미 로그인했다면 회원가입을 다시 못하도록 실행을 멈춘다.
	echo $ses_id."님은 회원이십니다.";
	exit;
}
?>

<html>
<head><title>회원 가입</title>
<script language="javascript">
<!--
function chk()
{
	if(frm.id.value.length==0)
	{
		alert("아이디를 입력해주세요.");
		frm.id.focus();
		return false;
	}
	else if(frm.pass.value.length==0)
	{
		alert("비밀번호를 입력해주세요.");
		frm.pass.focus();
		return false;
	}
	else if(frm.pass1.value.length==0)
	{
		alert("비밀번호를 입력해주세요.");
		frm.pass1.focus();
		return false;
	}
	else if(frm.email.value.length==0)
	{
		alert("이메일 주소를 입력해주세요.");
		frm.email.focus();
		return false;
	}
	else if(frm.pass.value!=frm.pass1.value)
	{
		alert("비밀번호는 같아야 합니다.");
		frm.pass.value=""; frm.pass1.value="";
		frm.pass.focus();
		return false;
	}

return true;
}
//-->
</script>
</head>
<body>

<p align=center><font size=15>회원 가입</font></p>

<form name="frm" action="./insert.php" method="post" onSubmit="return chk()">
<table border=1 width=700 align=center cellspacing=0 cellpadding="3">
<tr>
	<td width="696" colspan="2" bgcolor="blue">&nbsp;<font color="white"><b>회원가입</b></font></td>
</tr>
<tr>
	<td width="125"><p align="right">아이디 :</p></td>
	<td width="569"><p>&nbsp;
          <input type="text" name="id" size="20"></p></td>
</tr>
<tr>
	<td width="125"><p align="right">비밀번호 :</p></td>
	<td width="569"><p>&nbsp;
          <input type="password" name="pass" size="20"></p></td>
</tr>
<tr>
	<td width="125"><p align="right">비밀번호 확인</p></td>
	<td width="569"><p>&nbsp;
          <input type="password" name="pass1" size="20"></p></td>
</tr>
<tr>
	<td width="125"><p align="right">이메일 :</p></td>
	<td width="569"><p>&nbsp;
          <input type="text" name="email" size="30"></p></td>
</tr>
<tr>
	<td width="696" colspan="2"><p align="center">
	<input type="submit" name="smt" value="가입">&nbsp;&nbsp;&nbsp;
	<input type="reset" name="rst" value="취소"></p></td>
</tr>
</table>
</form>

</body>
</html>
