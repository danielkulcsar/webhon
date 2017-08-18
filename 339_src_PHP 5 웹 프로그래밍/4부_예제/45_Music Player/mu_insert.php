<?
include "./mu_db.php.inc";

$full_path="./music/";

$fname=str_replace(" ","",$fn_name);

$ftype=$fn_type;

$fsize=$fn_size;

$b_bool=true; $cnt=0;



if($fname)
{

	if(!eregi("audio",$ftype))
	{
		echo "
		<script>
			alert('음악 파일이 아닙니다.');

			history.go(-1);
		</script>";
		mysql_close($db); exit;
	}

	if($fsize<20000000)

	{
		while($b_bool==true)
		{
			if(file_exists($full_path.$fname))
			{
				$cnt++;
				$fname=$cnt.$fname;
			}
			else { $b_bool=false; }
		}
		copy($fn,$full_path.$fname);
	}
	else
	{
		echo "
		<script>
			alert('용량 초과입니다.');

			history.go(-1);
		</script>";
		mysql_close($db); exit;
	}
}


$sql="insert into music (".
	"m_fname,m_type,m_size".
	") values (".
	"'$fname','$ftype',$fsize".
	")";
$result=mysql_query($sql,$db);

mysql_close($db);

echo "
<script>
	location.href='./mu_list.php';
</script>
";
?>