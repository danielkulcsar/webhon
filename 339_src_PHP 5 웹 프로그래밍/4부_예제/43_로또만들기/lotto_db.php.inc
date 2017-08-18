<?
$host="localhost";
$usr="raby";
$pwd="123";

$db=mysql_connect($host,$usr,$pwd) or die("서버 접속 에러");
mysql_select_db("lotto_db",$db) or die("데이터베이스 연결 에러");
?>