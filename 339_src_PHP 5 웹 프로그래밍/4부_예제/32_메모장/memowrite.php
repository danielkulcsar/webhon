<?php
	$name = $_POST["name"];
	$memo = $_POST["memo"];
	$host="localhost";
	$usr="master";
	$pwd="123";

	$connect=MySQL_connect($host,$usr,$pwd) or die("서버 접속 에러");
	function message($meg) {
		echo"<script language=javascript> 
		alert('$msg'); 
     		echo('<meta http-equiv='Refresh' content='0;URL=memolist.php'>');
		</script>"; 
	}  
	if(!$name){
		$msg = "이름을 입력해 주세요.";
    		message($msg);
	}
	if(!$memo){
		$msg = "메모를 입력해 주세요.";
    		message($msg);
	}  
	$name=addslashes($name);  
	$memo=addslashes($memo);  
	$date=time();
	$connect = mysql_connect($host, $usr, $pwd);
 	$result = mysql_select_db("guestboard",$connect);
	if($result == 1) {
   		$query="insert into memo_tbl(name,memo,ndate) values ('$name','$memo','$date')";
		$result = mysql_query($query, $connect) or die("Error : ".mysql_error());
    	echo("<meta http-equiv='Refresh' content='0;URL=memolist.php'>");
  	}else {
		$msg = "MySQL 서버와 함께 작업할 데이터베이스에 접속 실패";
    	message($msg);
  	}
?>
