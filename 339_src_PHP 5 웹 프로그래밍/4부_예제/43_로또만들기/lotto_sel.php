<html>
<head><title>로또 번호 선택</title>
<script language="javascript">
<!--
var sum=0;

function link()
{
	sum+=1;

	if(sum==6)
	{
		frm.action="lotto_sel_insert.php";
		frm.submit();
	}
}
//-->
</script>
</head>

<body leftmargin="0" marginwidth="0" topmargin="0" marginheight="0">

<table border="0" cellpadding="0" cellspacing="0" width="850" height="650" background="images/lotto.jpg">
<tr>
	<td width="850" valign="bottom">


	<form name="frm">
	<table border="0" width="670" align="center">
	<tr>
<?
for($i=1;$i<=45;$i++)
{
	echo "<td align=center style='border-width:1px; border-style:solid;'>";
	echo "<font color=magenta size=3><b>[$i]</b></font><br>";
	echo "<input type='checkbox' name='lotto_num$i' value='$i' onClick='link()'>";
	echo "</td>";

	if($i%7==0)
		echo "</tr>";
}
?>
	</table>
	<table border="0" width="670" align="center">
	<tr>
		<td align=center>
		<input type="button" name="close" value="닫기" onClick="javascript:self.close()">
		</td>
	</tr>
	</table>
	</form>


	</td>
</tr>
</table>

</body>
</html>