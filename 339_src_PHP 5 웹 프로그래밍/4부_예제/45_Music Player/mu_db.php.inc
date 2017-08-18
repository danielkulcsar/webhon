<?
$host="localhost";
$usr="raby";
$password="123";

$db=mysql_connect($host,$usr,$password) or die("서버 접속 에러");
mysql_select_db("music_db",$db) or die("데이터베이스 연결 에러");
?>