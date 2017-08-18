<?
include "./mu_db.php.inc";

$full_path="./music/";

$sql="select * from music where m_num=$delNum limit 1";
$result=mysql_query($sql,$db);
$row=mysql_fetch_array($result);

$del_fn=$row[m_fname];
@unlink($full_path.$del_fn);

$d_sql="delete from music where m_num=$delNum";
$d_rst=mysql_query($d_sql,$db);

mysql_close($db);

echo "
<script>
	location.href='./mu_list.php';
</script>
";
?>