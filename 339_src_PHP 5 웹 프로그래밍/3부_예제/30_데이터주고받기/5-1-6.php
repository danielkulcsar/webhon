<?
	$connect = mysql_connect('localhost','flashboy','12345'); 
	mysql_select_db('db_01', $connect);

	$query = "select * from member1";
	$sql_result = mysql_query($query, $connect);

	$name0 = mysql_result($sql_result, 0, mname);
	$name1 = mysql_result($sql_result, 1, mname);
	$name2 = mysql_result($sql_result, 2, mname);

	echo "$name0 <br>";
	echo "$name1 <br>";
	echo "$name2 <br>";
	mysql_close($connect);                        
?>
