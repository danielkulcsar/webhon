<?php
$host="localhost";
$usr="raby";
$pwd="123";

$db=mysql_connect($host,$usr,$pwd) or die("서버 접속 에러");
mysql_select_db("count_db",$db) or die("데이터베이스 연결 에러");

$YY=Date("Y");
$MM=Date("m");
$DD=Date("d");
$dat=$YY."-".$MM."-".$DD;

$sql="select * from count_tbl where regdate='$dat'";
$result=mysql_query($sql,$db);
$list=mysql_num_rows($result);

if(!$list) {
	$counter=0;
}
else {
	$row=mysql_fetch_array($result);
	$counter=$row[counter];
}

if($counter==0) {
	$counter++;
	$to_sql="insert into count_tbl (counter,regdate) values ($counter,'$dat')";
}
else {
	$counter++;
	$to_sql="update count_tbl set counter=$counter where regdate='$dat'";
}

mysql_query($to_sql,$db);

$tot_sql="select sum(counter) from count_tbl";
$tot_rst=mysql_query($tot_sql,$db);
$tot_row=mysql_fetch_array($tot_rst);

$total=$tot_row[0];

mysql_close($db);
?>

<body>
<br><br><br>
<table border='0' width='100' align='center' cellspacing='0' cellpadding='0' style='border-width:1px; border-color:black; border-style:dotted;'>
<tr>
	<td width='35' style='padding-left:2;'>
		<font size='2' color='red'>오늘</font></td>
	<td align='right' style='padding-right:2;'>
		<font size='2' color='blue'><? echo "$counter"; ?></font></td>
</tr>
<tr>
	<td style='padding-left:2;'><font size='2' color='red'>전체</font></td>
	<td align='right' style='padding-right:2;'>
		<font size='2' color='blue'><? echo "$total"; ?></font></td>
</tr>
</table>

</body>