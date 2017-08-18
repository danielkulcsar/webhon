<?php
$host="localhost";
$usr="raby";
$pwd="123";


$db=mysql_connect($host,$usr,$pwd) or die("서버 접속 에러");

mysql_select_db("poll_db",$db);
//MySQL Server와 연결되면 사용할 데이터베이스 ‘poll_db’설정
?>
