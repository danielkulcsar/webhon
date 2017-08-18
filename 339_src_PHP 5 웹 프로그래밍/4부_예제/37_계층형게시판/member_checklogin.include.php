<?php
session_start();

if($memberid=="" || $membername==""){
	// 현재 로그인되지 않은 사용자이므로 로그인 페이지로 이동한다
	echo "
		<html>
		<head>
			<script languaeg='javascript'>
				alert('회원만 볼 수 있는 페이지입니다.');
				location.href='member_login.php';
			</script>
		</head>
		<body>
		</body>
		</html>";

	// 로그인되지 않았으므로 더 이상 실행하지 못하도록 멈춘다.
	exit();
}
?>