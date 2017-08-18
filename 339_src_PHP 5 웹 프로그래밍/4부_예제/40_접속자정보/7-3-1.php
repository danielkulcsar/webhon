<?php
	$browser_1=getenv("HTTP_USER_AGENT");    // 사용자의 브라우저 이름 가져오기

	echo $browser_1."<p>";									// 브라우저의 이름 화면 출력

	$browser_2=$HTTP_USER_AGENT;              // 다른 방법으로 사용자 브라우저의 이름 가져오기

	echo $browser_2;                                         // 브라우저의 이름 화면 출력
?>
