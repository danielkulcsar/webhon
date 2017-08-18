<?php
	$yy; $mm; $dd;
?>

<html>
<head><title>Schedule 작성</title>
</head>

<body>

<form name='frm' action='schedule_ins.php' method='post'>
<table border='1' width='500' align='center'>
<input type='hidden' name='dat' value='<? echo $yy."-".$mm."-".$dd; ?>'>
<tr>
	<td colspan='2' align='center'><? echo $yy."년 ".$mm."월 ".$dd."일 "; ?>Schedule 작성</td>
</tr>
<tr>
	<td widht='100' align='center'>일정</td>
	<td width='400'><textarea name='iljung' rows='10' cols='50' style='width:400'></textarea></td>
</tr>
<tr>
	<td colspan='2' align='center'>
		<input type='submit' name='smt' value='저장'>&nbsp;&nbsp;
		<input type='reset' name='rst' value='닫기' onClick='javascript:window.close();'>
	</td>
</table>
</form>

</body>
</html>