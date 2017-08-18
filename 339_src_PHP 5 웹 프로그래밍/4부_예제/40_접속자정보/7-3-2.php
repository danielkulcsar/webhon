<?php
	$usrip_1=getenv("REMOTE_ADDR");    //접속한 사용자 컴퓨터의 IP주소 가져오기

	echo $usrip_1."<p>";                           //사용자 IP주소 화면 출력

	$usrip_2=$REMOTE_ADDR;             //다른 방법으로 컴퓨터 IP주소 가져오기

	echo $usrip_2;
?>
