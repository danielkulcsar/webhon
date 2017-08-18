<html>
<head><title>::::::::::::::::::::::::::::::</title>
</head>

<body leftmargin="0" marginwidth="0" topmargin="0" marginheight="0">

<table border="1" width="800" height="300">
<tr>
	<td width="800" align="center">

<?
include "lotto_db.php.inc";

if($a_key==1)
{
	for($i=1;$i<=6;$i++)
	{
		$val="lucky".$i;

		srand(microtime()*1000000);
		$$val=(rand()%50)+1;
	}

		for($i=1;$i<=5;$i++)
		{
			for($j=$i+1;$j<=6;$j++)
			{
				$aaa="lucky".$i; $bbb="lucky".$j;
				if($$aaa>$$bbb)
				{
					$imsi=$$aaa; $$aaa=$$bbb; $$bbb=$imsi;
				}
			}
		}

	$dang=0;

	$sql="select * from lotto";
	$result=mysql_query($sql,$db);
	$list=mysql_num_rows($result);

	if(!$list)
	{
		echo "<script>
			alert('로또 번호를 선택하세요!!!');
			location.href='./lotto.php';
		</script>";
	}

	while($row=mysql_fetch_array($result))
	{
		$sel1=$row[l_lucky1]; $sel2=$row[l_lucky2]; $sel3=$row[l_lucky3];
		$sel4=$row[l_lucky4]; $sel5=$row[l_lucky5]; $sel6=$row[l_lucky6];
	}

	for($i=1;$i<=6;$i++)
	{
		$rrr="lucky".$i; $sss="sel".$i;

		if($$rrr==$$sss) $dang++;
	}

	echo "
	<table border=1 width=600>
	<tr>
		<td width=120>선택한 번호</td>
		<td width=80>$sel1</td><td width=80>$sel2</td><td width=80>$sel3</td>
		<td width=80>$sel4</td><td width=80>$sel5</td><td width=80>$sel6</td>
	</tr>
	<tr bgcolor='cyan'>
		<td>당첨 번호</td>
		<td>$lucky1</td><td>$lucky2</td><td>$lucky3</td>
		<td>$lucky4</td><td>$lucky5</td><td>$lucky6</td>
	</tr>
	</table>
	";

	if($dang==6)
		echo "<img src='images/good.jpg'>";
	else
		echo "<img src='images/no.jpg'>";

} else {
	$d_sql="truncate table lotto";
	mysql_query($d_sql,$db);

	echo "<img src='./images/lotto_logo.jpg'>";
}

mysql_close($db);
?>

	</td>
</tr>
</table>

</body>
</html>