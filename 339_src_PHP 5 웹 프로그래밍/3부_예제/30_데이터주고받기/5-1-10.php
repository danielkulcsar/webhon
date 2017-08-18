<?php
	$name = $_POST["name"];
	$email = $_POST["email"];
	$homepage = $_POST["homepage"];
	$comment = $_POST["comment"];

	$connect = mysql_connect("localhost","flashboy","12345");
	$result = mysql_select_db("db_01",$connect);
	if($result == 1) {
		$tb_name = mysql_list_tables("db_01");
		$tb_count = mysql_num_rows($tb_name);
        for($k = 0; $k < $tb_count; $k++){
			if(mysql_tablename($tb_name, $k) == "guestbook") {
				$flag = "OK";
                break;
            } 
        }
        if($flag != "OK") {
			$query = "create table guestbook (
        		num int(4) NOT NULL auto_increment,
            	name varchar(20),
            	email varchar(50),
            	homepage varchar(50),
            	comment text,
            	PRIMARY KEY(num)
			)";
			$result = mysql_query($query, $connect) or die("Error : ".mysql_error());
		}
		$query = "insert into guestbook (name,email,homepage,comment)  values('$name','$email','$homepage','$comment')";
		$result = mysql_query($query, $connect) or die("Error : ".mysql_error());

	} 
	mysql_close($connect);   
?>