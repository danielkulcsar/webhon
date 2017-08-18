<?php
require("./member_checklogin.include.php");
require("./bbs_dbconn.include.php");
  
//수정폼(bbs_modify.php)에서 전송된 내용을 변수에 담습니다. 
$name = addslashes($name); 
$password = addslashes($password); 
$email = addslashes($email); 
$homepage = addslashes($homepage); 
$subject = addslashes($subject); 
$memo = addslashes($memo); 

//디폴트 값이 필요한 변수에는 디폴트 값을 넣습니다. 
$tablename="mfboard"; //테이블 이름 
$writetime = time(); 
  
//비밀번호가 맞는지 확인합니다. 
$sql = "select number from $tablename where number=$number and password='$password'"; 
$result = mysql_query($sql) or die (mysql_error()); 
  
if(mysql_num_rows($result)) {  //반환된 열이 있으면... 
	//수정한 내용을 UPDATE합니다. 
	$sql = "update $tablename set ".
			"name='$name',email='$email',homepage='$homepage', ".
			"subject='$subject',memo='$memo' where number=$number"; 
	mysql_query($sql) or die (mysql_error()); 
	$msg = "수정을 하였습니다."; 
  
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
		</html>"; 
}else{ 
	$msg = "비밀번호가 틀립니다."; 
	echo "
		<html>
		<head> 
		<script name=javascript> 
			if('$msg' != '') { 
				self.window.alert('$msg'); 
			} 
  
			history.go(-1); 
		</script> 
		</head> 
		</html> "; 
}

// MySQL 데이터베이스 연결을 닫음
mysql_close();
?>