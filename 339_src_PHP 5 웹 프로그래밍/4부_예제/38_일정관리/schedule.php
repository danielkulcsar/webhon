<?php
include "./schedule_db.php.inc";

$yy; $mm; $dd;

$sql="select * from schedule_tbl where dat='$yy-$mm-$dd'";
$result=mysql_query($sql,$db);
$row=mysql_fetch_array($result);

$r_iljung=$row["schedule"];
$r_iljung=str_replace("\n","<br>",$r_iljung);
$r_dat=$row["dat"];

mysql_close($db);
?>

<html>
<head><title><? echo $yy."년 ".$mm."월 ".$dd."일 "; ?>Schedule</title>
</head>

<body>

<table border='1' width='500' align='center'>
<tr>
	<td align='center'><small>
		<? echo $yy."년 ".$mm."월 ".$dd."일 "; ?>Schedule</small></td>
</tr>
<tr>
	<td bgcolor='#FFFFCC' style='padding:10px;'><small><? echo $r_iljung; ?></small></td>
</tr>
<table>

</body>
</html>