<?php
include "./poll_con.php.inc";
$id;                             //사용자가 선택한 투표 번호가 자동으로  ‘$id’할당

if(!$usr_id)                          //현재 사용자가 로그인되지 않은 사용자일 때 실행
{
	$usr_id=md5(uniqid(rand()));                          //임의의 사용자 아이디 발급
	setcookie("usr_id",$usr_id);                           //임의의 아이디 쿠키 설정
}
$sql="insert into poll_usr (".
	"poll_usr,poll_num".
	") values (".
	"'$usr_id',$id".
	")";
//투표 사용자로 현재 발급된 임의의 사용자 ID를 저장하는 쿼리 생성

$result=mysql_query($sql,$db);                                     //사용자 정보 저장

mysql_close($db);

echo "
<script>
	location.href='./poll_result.php?id=$id';
</script>
";
?>
