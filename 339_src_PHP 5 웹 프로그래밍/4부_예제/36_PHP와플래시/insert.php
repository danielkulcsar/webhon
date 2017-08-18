<?
include "./db.php.inc";

$irum; $pass; $email; $title; $comment;
$usrip=$REMOTE_ADDR;

$sql="insert into board (".
	"irum,pass,email,title,".
	"comment,r_date,usrip,cnt".
	") values (".
	"'$irum',password('$pass'),'$email','$title',".
	"'$comment',now(),'$usrip',0".
	")";
$result=mysql_query($sql,$db);

mysql_close($db);

echo "
<script language='javascript'>
	location.href='./list.php';
</script>
";
?>