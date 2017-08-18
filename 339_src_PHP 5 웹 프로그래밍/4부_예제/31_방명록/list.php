<?
$host="localhost";
$usr="master";
$pwd="123";

$db=mysql_connect($host,$usr,$pwd) or die("서버 접속 에러");
mysql_select_db("guestboard",$db);


$sql="select * from guest_tbl order by num desc";
$result=mysql_query($sql,$db);
?>

<html>
<head><title>방명록 목록</title>
</head>

<body>

<table border=0 width=700 cellspacing=0 cellpadding=0>
<tr>
	<td>
	<p align=center>방 명 록 목 록</p>
	<table border=0 width=600 cellspacing=0 cellpadding=2 align=center>
	<tr>
		<td align=left><a href="./write.php">방명록 작성</a></td>
	</tr>
	</table><br>

<?
while($row=mysql_fetch_array($result))
{
	$num=$row[num];
	$irum=$row[irum];
	$dat=$row[wdate];
	$usrip=$row[usrip];
	$content=$row[content]; $content=str_replace("\n","<br>",$content);

echo "
	<table border=1 width=600 cellspacing=0 cellpadding=2 align=center>
	<tr align=center>
		<td width=50>번호</td><td width=50>$num</td>
		<td width=100>작성자</td><td width=100>$irum</td>
		<td width=150>작성날짜</td><td width=150>$dat</td>
	</tr>
	<tr align=center>
		<td colspan=6>내 용</td>
	</tr>
	<tr>
		<td colspan=6 style='padding:15; '>$content</td>
	</tr>
	</table><br>";
}
?>

	</td>
</tr>
</table>

</body>
</html>
