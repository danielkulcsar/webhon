<?php
require("./member_checklogin.include.php");
require("./bbs_dbconn.include.php");
  
//변수 
$password = addslashes($password); 
$tablename="mfboard"; //테이블 이름 
  
//비밀번호가 맞는지 확인합니다. 
$sql = "select number from $tablename where number=$number and password='$password'"; 
$result = mysql_query($sql) or die (mysql_error()); 

$msg = "비밀번호가 틀립니다."; 
  
if(mysql_num_rows($result)) {  
	//반환된 열이 있으면, 삭제합니다. 
	$sql = "delete from $tablename where number=$number"; 
	mysql_query($sql) or die (mysql_error()); 
	$msg = "삭제하였습니다."; 
} 

// MySQL 데이터베이스 연결을 닫음
mysql_close();

//메시지를 출력하고 목록 페이지로 이동합니다. 
echo "
<html>
<head> 
<script name=javascript> 
	if('$msg' != '') { 
		self.window.alert('$msg'); 
	} 
  
	location.href='bbs_list.php?page=$page'; 
</script> 
</head> 
</html>
"; 
?> 
