<?php
require("./member_checklogin.include.php");
require("./bbs_dbconn.include.php");

$tablename = "msboard";

//답변 폼(reply.php)에서 전송된 내용을 변수에 담습니다.
$name = addslashes($name);
$password = addslashes($password);
$email = addslashes($email);
$homepage = addslashes($homepage);
$subject = addslashes($subject);
$memo = addslashes($memo);

//디폴트 값이 필요한 변수에는 디폴트 값을 넣습니다.
$writetime = time();
$ip = getenv("REMOTE_ADDR");
$count = 0;


// 부모글의 내용을 조회하기 위한 쿼리 생성
$sql = "select * from $tablename where number='$number'";
$result = mysql_query($sql) or die (mysql_error().$sql);
$array = mysql_fetch_array($result);

$depth = strpos($array[replylv], "A");

if(!$depth){
	echo "더 이상 응답글을 저장할 수 없습니다.";
}

$likestr = $array[replylv];
$likestr[$depth] = "_";

$sql = "select replylv from $tablename where parentno = $array[parentno] and replylv like '$likestr' order by replylv desc limit 1";

$reply_result = mysql_query($sql) or die (mysql_error.$sql);
$reply_array = mysql_fetch_array($reply_result);
$reply = trim($reply_array[replylv]);

$reply_str=$reply[$depth];
$reply_str = ++$reply_str;

$reply[$depth] = $reply_str;

//SQL 명령을 이용해 입력받은 내용과 디폴트값 등을 MySQL에 입력(insert)합니다.
$sql = "insert into $tablename (name,password,email,homepage,subject,memo,count,ip,writetime,parentno,replylv) 
        values('$name','$password','$email','$homepage','$subject','$memo',$count,'$ip',$writetime,$array[parentno],'$reply')"; 

mysql_query($sql) or die (mysql_error().$sql);

mysql_close();

//글 입력이 완료되면 목록 보기 페이지로 자동 이동하도록 합니다 
if ($msg=='') {
	$msg = "성공적으로 등록되었습니다"; 
	 echo " <html><head> 
                 <script name=javascript> 
                  if('$msg' != '') { 
                         self.window.alert('$msg'); 
                 } 
                 location.href='bbs_list.php?page=$page'; 
                 </script> 
                 </head> 
                 </html> "; 
} else {
	 echo " <html><head> 
                 <script name=javascript> 
                 if('$msg' != '') { 
                         self.window.alert('$msg'); 
                 } 
                 history.go(-1);
                 </script> 
                 </head> 
                 </html> "; 
}

?>