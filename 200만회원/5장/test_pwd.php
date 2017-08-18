<?php
	$key = "gamess.co.kr";
	$mb_pw = "abcde123";
	$md5_mb_pw = md5($mb_pw);
	$sha1_pw = sha1($mb_pw);
	$md5_sha1_pw = sha1($key.$md5_mb_pw);

	echo("평문 : $mb_pw<br>");
	echo("MD5 암호화 : $md5_mb_pw <br>");
	echo("Sha1 암호화 : $sha1_pw <br>");
	echo("MD5+sha1 암호화 : $md5_sha1_pw <br>");
?>