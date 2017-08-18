<?
include "./db.php.inc";

$sql="select * from board order by num desc";
$result=mysql_query($sql);
?>

<html>
<head><title>게시물 보기</title>
</head>

<body>

<p align=center>게시물 보기</p>

<table border=0 width=700 align=center cellspacing=0 cellpadding=0>
<tr>
	<td><a href='./form.php'>게시물 작성</a></td>
</tr>
</table><br>

<table border=1 width=700 align=center cellspacing=0 cellpadding=0>
<tr align=center>
	<td width=70>번호</td>
	<td width=100>작성자</td>
	<td width=300>제목</td>
	<td width=160>작성날짜</td>
	<td width=70>조회수</td></tr>

<?
while($row=mysql_fetch_array($result))
{
	echo "<tr align=center>
		<td width=70>$row[num]</td>
		<td width=100><a href='mailto:$row[email]'>$row[irum]</a></td>
		<td width=300>
			<a href='./content.php?num=$row[num]'>$row[title]</a></td>
		<td width=160>$row[r_date]</td>
		<td width=70>$row[cnt]</td></tr>
		";
}

mysql_close($db);
?>

</table>

</body>
</html>