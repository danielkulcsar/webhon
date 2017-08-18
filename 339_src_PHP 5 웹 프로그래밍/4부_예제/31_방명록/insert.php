<?
$host="localhost";
$usr="master";
$pwd="123";

$db=mysql_connect($host,$usr,$pwd) or die("서버 접속 에러");
mysql_select_db("guestboard",$db);

$irum; $content;
$usrip=$REMOTE_ADDR; // 사용자의 IP주소를 읽어옵니다.

$sql="insert into guest_tbl (".
	"irum,content,wdate,usrip".
	") values (".
	"'$irum','$content',now(),'$usrip'".
	")";
$result=mysql_query($sql,$db);

mysql_close($db);

echo "
<script language='javascript'>
	location.href='./list.php';
</script>
";
?>
