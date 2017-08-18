<?
	$db=mysql_connect('localhost','flashboy','12345') or die("서버 접속 에러");
    if($db) {
        echo 'MySQL 서버에 접속 성공';
    } else {
        echo 'MySQL 서버에 접속 실패';
    }
    mysql_close($db);
?>