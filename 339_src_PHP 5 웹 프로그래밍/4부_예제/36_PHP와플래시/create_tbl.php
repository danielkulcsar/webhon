<?
include "./db.php.inc";

$sql="create table board (".
	"num int primary key auto_increment,".
	"irum varchar(30),".
	"email varchar(30),".
	"pass varchar(30),".
	"title varchar(100),".
	"comment text,".
	"r_date datetime,".
	"usrip varchar(30),".
	"cnt int".
	")";
$result=mysql_query($sql);

if(!$result)
{
	echo mysql_errno().")<br>".mysql_error()."<br>";
	exit;
}
mysql_close($db);

echo "board 테이블이 정상적으로 생성되었습니다.";
?>