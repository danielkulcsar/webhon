<html>
<head><title>음악 파일 등록</title>
<script language="javascript">
<!--
function chk()
{
	if(frm.fn.value.length==0)
	{
		alert("등록할 파일을 선택하세요.");
		frm.fn.focus();
		return false;
	}
return true;
}
//-->
</script>
</head>

<body>

<p>&nbsp;</p>

<form name="frm" action="./mu_insert.php" enctype="multipart/form-data" method="post" onSubmit="return chk()">
<table align="center" border="1" width="550" cellpadding="3">
<tr>
	<td width="550" height="21" colspan="2">
		<p><font size="3" color="blue"><b>&nbsp;&nbsp;■ 파일 등록하기</b></font></p>
	</td>
</tr>
<tr>
	<td width="150" height="21">
		<p align="center"><font size="2">파일 첨부 :</font></p>
	</td>
	<td width="400" height="21">
		<p>&nbsp;<input type="file" name="fn" size="39"></p>
	</td>
</tr>
<tr>
	<td width="550" colspan="2">
		<p align="center"><input type="submit" name="smt" value="파일 등록">&nbsp;
	    <input type="reset" name="rst" value="등록 취소" onClick="javascript:location.href='./mu_list.php'"></p>
	</td>
</tr>
</table>
</form>

</body>
</html>