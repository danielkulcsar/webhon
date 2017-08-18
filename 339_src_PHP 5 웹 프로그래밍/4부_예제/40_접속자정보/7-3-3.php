<?php
	$browser=$HTTP_USER_AGENT;

	if(eregi("compatible; MSIE 6.0;",$browser)) { 
		$bgcolor="magenta"; 
		//사용자의 브라우저가 인터넷 익스플로러이면 배경색‘자홍색’변경
	}
	else {
		$bgcolor="blue";                             //그 외 브라우저이며 ‘파란색’ 변경함
	}

	echo "
	<html>
	<head><title>배경색 바꾸기</title></head>

	<body bgcolor='$bgcolor'>		                 //브라우저 종류별로 배경색 지정

	</body>
	</html>
	";
?>
