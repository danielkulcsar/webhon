<?
include "./mem_con.php.inc";

$id; $pass; $email; $tbl_name; $pg; 
$usrip=$REMOTE_ADDR;

// 정규 표현식을 이용하여 입력된 이메일 주소의 유효성을 검증한다.
function email_func($addr)
{
	return (ereg('^[-!#$%&\'*+\\./0-9=?A-Z^_a-z{|}~]+'.
	'@'.
	'[-!#$%&\'*+\\/0-9=?A-Z^_a-z{|}~]+\.'.
	'[-!#$%&\'*+\\./0-9=?A-Z^_a-z{|}~]+$',
	$addr));
}

$a=email_func($email);

if(!$a)
{
	echo "<script language='javascript'>
		alert('이메일 주소 형식이 아닙니다.');
		history.go(-1);
	      </script>";
	exit;
}

$sql="select * from $tbl_name where m_id='$id'";
$result=mysql_query($sql,$db);
$list=mysql_num_rows($result);

if($list)
{
	echo "
	<script language='javascript'>
		alert('같은 아이디가 존재합니다.');
		history.go(-1);
	</script>
	";
	mysql_close($db);
	exit;
}

$ins_sql="insert into $tbl_name (".
		"m_id,m_pass,m_email,m_dat,m_usrip".
		") values (".
		"'$id',password('$pass'),'$email',now(),'$usrip'".
		")";
mysql_query($ins_sql,$db);
session_start();
$ses_id=$id; $ses_pass=$pass; $ses_email=$email;
session_register("ses_id");
session_register("ses_pass");
session_register("ses_email");

mysql_close($db);

echo $id."님은 회원 가입되었습니다.<br><br>";
echo "<a href='./all_mem.php' target='right'>전체 회원 보기</a>";
?>

