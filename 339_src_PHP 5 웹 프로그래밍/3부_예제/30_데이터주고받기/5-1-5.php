<?
	$connect = mysql_connect('localhost','flashboy','12345'); 
	mysql_select_db('db_01', $connect);
	$query = "select * from member1";
	$sql_result = mysql_query($query, $connect);
    $rcount = mysql_num_rows($sql_result);
	echo $rcount;
	mysql_close($connect);                        
?>