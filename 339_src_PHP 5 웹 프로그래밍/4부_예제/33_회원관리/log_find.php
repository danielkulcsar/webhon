<?
include "./mem_con.php.inc";

$id; $pass;

$sql="select * from $tbl_name where m_id='$id' and m_pass=password('$pass')";
$result=mysql_query($sql,$db);
$list=mysql_num_rows($result);

if($list)
{
	$row=mysql_fetch_array($result);
	$ses_id=$row[m_id];
	$ses_pass=$row[m_pass];
	$ses_email=$row[m_email];

	session_start();                     //세션 시작
	session_register("ses_id");          //ses_id 변수의 값을 세션으로 기록
	session_register("ses_pass");       //ses_pass 변수의 값을 세션으로 기록
	session_register("ses_email");      //ses_email 변수의 값을 세션으로 기록

	echo $ses_id." 님은 로그인 되었습니다.";
}
else
{
	echo "아이디와 비밀번호가 틀립니다.<p>";
	echo "<a href='./member.php' target='right'>회원 가입</a>";
}

mysql_close($db);
?>
