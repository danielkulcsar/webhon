<?php
//db 연결 부분입니다.
//"or die(mysql_error())"에서 die() 함수는 대부분의 함수 뒤에 사용할 수 있습니다. 
//앞의 함수가 거짓(false)값을 반환하면 die("메시지") 함수가 메시지를 출력하는 역할을 합니다. 
//위와 같은 경우는 mysql_error() 함수를 이용해서 에러 메시지를 보여주는 기능을 합니다.

mysql_connect("localhost", "root", "mysql") or die (mysql_error()); //host,id,passwd
mysql_select_db("test"); //db이름
?>