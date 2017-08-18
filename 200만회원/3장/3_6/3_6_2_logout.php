<?php
	session_start();
	session_cache_limiter('no-cache,must-revalidate');
	session_destroy();
	header("location:http://www.gamess.co.kr/index.php");
?>