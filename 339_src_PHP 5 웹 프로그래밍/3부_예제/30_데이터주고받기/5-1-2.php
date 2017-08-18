<?
    $connect = mysql_connect("localhost","flashboy","12345");
    $result = mysql_select_db("db_01",$connect);
    if($result == 1) {
        echo '데이터베이스 선택 성공';
    } else {
        echo '데이터베이스 선택 성공';
    }
    mysql_close($connect);
?>
