<?
include "./lotto_db.php.inc";

$j=0;

for($i=1;$i<=45;$i++)
{
	$lotto_num="lotto_num".$i;

	if($$lotto_num)
	{
		$num[$j]=$$lotto_num;
		$j++;
	}
}

$d_sql="truncate table lotto";
mysql_query($d_sql,$db);

$sql="insert into lotto (".
	"l_lucky1,l_lucky2,l_lucky3,l_lucky4,l_lucky5,l_lucky6".
	") values (".
	"$num[0],$num[1],$num[2],$num[3],$num[4],$num[5]".
	")";
$result=mysql_query($sql,$db);

mysql_close($db);

echo "
<script>
	self.close();
</script>
";
?>