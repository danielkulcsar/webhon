<!-- HTML 선언 -->
<html>
<header>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title>게임세상</title>
</header>
<body>
<?php
	$db_ip = "mysql.hostinger.kr"; // 호스팅 업체의 db 호스트 이름이나 IP
	$db_id = "u520651605_db"; // MySQL 접속 ID
	$db_pwd ="password123"; // MySQL 접속 Password
	$db_name = "u520651605_gs01"; // MySQL DB 이름
// MySQL 접속을 위한 선언

$connect = mysql_connect("$db_ip","$db_id","$db_pwd");
mysql_query("set names utf8");
mysql_select_db("$db_name"); // 접속할 DB 선택

$query = "select * from members";
$result = mysql_query($query, $connect); // 쿼리를 통한 결과 호출
$total = mysql_num_rows($result); // 결과값의 전체 개수 확인

	for($i=0;$i<$total;$i++){ // 전체 개수만큼의 반복 선언
		$id = mysql_result($result,$i,"id"); // 각 행의 ID 값을 $id 변수에 저장
		// 각 행의 password 값을 $pwd 변수에 저장
		$pwd = mysql_result($result,$i,"password");
		// 각 행의 nickname 값을 $nickname 변수에 저장
		$nickname = mysql_result($result,$i,"nickname");
		// 각 행의 email 값을 $email 변수에 저장
		$email = mysql_result($result,$i,"email");
		// HTML로 보여지는 영역 시작
		?>
		<?=$id?> | <?=$pwd?> | <?=$nickname?> | <?=$email?><br>
		<? // HTML로 보여지는 영역 끝
		} // 반복함수가 종료되는 영역 표시
?>
</body>
</html>
<!-- HTML 종료 -->