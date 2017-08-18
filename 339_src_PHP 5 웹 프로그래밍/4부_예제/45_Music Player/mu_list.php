<? $selNum; ?>

<html>
<head><title>Juke Box</title>
</head>

<body>

<p align="center"><? include "./mu_player.php"; ?></p>

<table border="0" width="700" align="center">
<tr>
	<td><small><a href="./mu_form.php">음악 등록</a></small></td>
</tr>
</table>

<table border="1" width="700" align="center">
<tr>
	<td colspan=5><font size=3 color=blue><b>■ Music List</b></font></td>
</tr>
<tr align=center>
	<td width=50><small>번호</small></td>
	<td width=350><small>파일명</small></td>
	<td width=150><small>파일타입</small></td>
	<td width=80><small>파일크기</small></td>
	<td width=70><small>파일삭제</small></td>
</tr>

<?
include "mu_db.php.inc";

$offset; $limit;
if(!$offset) { $offset=0; }
$limit=10;

$cnt_sql="select * from music order by m_num desc";
$cnt_rst=mysql_query($cnt_sql,$db);
$count=mysql_num_rows($cnt_rst); if(!$count) { $count=0; }

$sql="select * from music order by m_num desc limit $offset,$limit";
$result=mysql_query($sql,$db);

while($row=mysql_fetch_array($result))
{
	$num=$row[m_num]; $fn=$row[m_fname];
	$ft=$row[m_type]; $fs=round($row[m_size]/1000000,1)."MB";

	echo "
	<tr>
		<td align=center><small>$num</small></td>
		<td><small><a href='$PHP_SELF?offset=$offset&selNum=$num'>$fn</a></small></td>
		<td align=center><small>$ft</small></td>
		<td align=center><small>$fs</small></td>
		<td align=center><small><input type=button value='삭제' onClick='javascript:location.href=\"./mu_del.php?delNum=$num\"'></small></td>
	</tr>
	";
}

mysql_close($db);
?>

</table>

<p align="center"><small>
<?
$i=($offset/$limit)+1;
$i=intval(($i-1)/10)*10+1;

if($offset!=0 && $offset>99)
{
	$newoffset=(($i-10)-1)*$limit;
	echo "<a href=$PHP_SELF?offset=$newoffset&selNum=$selNum><<</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
}
else { echo "<<&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"; }

if($offset!=0)
{
	$newoffset=$offset-$limit;
	echo "<a href=$PHP_SELF?offset=$newoffset&selNum=$selNum>이전</a>&nbsp;";
}
else { echo "이전&nbsp;"; }

$pages=intval($count/$limit);

if($count%$limit) { $pages++; }

$loop=0;

for($i;$i<=$pages && $loop<10;$i++,$loop++)
{
	$newoffset=$limit*($i-1);

	if($offset!=$newoffset)
	{
		echo "<a href=$PHP_SELF?offset=$newoffset&selNum=$selNum>[$i]</a>&nbsp;";
	}
	else
	{
		echo "$i&nbsp;";
	}
}

if($pages>1)
{
	$last=($offset/$limit)+1;
	if($pages!=$last)
	{
		$newoffset=$offset+$limit;
		echo "<a href=$PHP_SELF?offset=$newoffset&selNum=$selNum>다음</a>";
	}
	else
	{
		echo "다음";
	}
}
else
{
	echo "다음";
}

if($pages>1)
{
	$last=($offset/$limit)+1;
	if($pages!=$last && $i<=$pages)
	{
		$newoffset=($i-1)*$limit;
		echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href=$PHP_SELF?offset=$newoffset&selNum=$selNum>>></a></span>";
	}
	else
	{
		echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;>>";
	}
}
else
{
	echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;>>";
}
?>
</small></p>

</body>
</html>