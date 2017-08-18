<!-- HTML 선언 -->
<html>
<header>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title>게임세상</title>
</header>
<body>
<?php
	$db_ip = "mysql.hostinger.kr"; // 호스팅 업체의 DB 호스트 이름이나 IP
	$db_id = "u520651605_db"; // MySQL 접속 ID
	$db_pwd ="password123"; // MySQL 접속 Password
	$db_name = "u520651605_gs01"; // MySQL DB 이름
// MySQL 접속을 위한 선언
	$connect = mysql_connect("$db_ip","$db_id","$db_pwd");
	mysql_query("set names utf8");
	mysql_select_db("$db_name"); // 접속할 DB 선택

$id = $_POST['id'];
$password = $_POST['password'];
$nickname = $_POST['nickname'];
$email = $_POST['email'];
$query = "INSERT INTO members (id, password, nickname, email) VALUES ('$id','$password','$nickname','$email')";
$result = mysql_query($query);

	if($result){
	?>
	<p>회원가입이 정상적으로 완료되었습니다.</p>
	<?}
	else{?>
	<p>회원가입이 실패하였습니다.</p>
	<?}?>
</body>
</html>
<!-- HTML 종료 -->