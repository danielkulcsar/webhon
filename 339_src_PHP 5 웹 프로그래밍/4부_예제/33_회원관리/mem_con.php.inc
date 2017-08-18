<?
$tbl_name="member";

$host="localhost";
$usr="raby";
$pwd="123";

$db=mysql_connect($host,$usr,$pwd) or die("서버 접속 에러");     //서버 연결
mysql_select_db("mem_db",$db);                                  //데이터베이스 선택
?>
