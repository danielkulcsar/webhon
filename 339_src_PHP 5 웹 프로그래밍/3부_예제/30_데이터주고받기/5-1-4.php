<?
	$connect = mysql_connect('localhost','flashboy','12345'); 
	mysql_select_db('db_01', $connect);
	$query = "create table test(name char(20), pw char(20))";
	$flag = mysql_query($query, $connect);
	if($flag) {
		echo '테이블이 성공적으로 만들어 졌습니다.';
	}else{
		echo '테이블을 생성하는데 에러가 발생했습니다.';
	}
	mysql_close($connect);                        
?>
