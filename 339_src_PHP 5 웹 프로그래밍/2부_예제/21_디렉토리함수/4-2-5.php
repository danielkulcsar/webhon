<?php

$ex_arr=parse_url("http://username:password@hostname/path?arg=value#anchor");

print_r($ex_arr);

echo "<br><br><br>";

$url_arr=parse_url("http://usr:12345@codmedia:88/test?cnt=3#1");

print_r($url_arr);

echo "<br><br><br>";

$ip=$_SERVER['HTTP_HOST'];		//현재 서버의 이름 또는 IP주소
$url=$_SERVER['PHP_SELF'];		//현재 파일이 위치한 경로

$cur_arr=parse_url($ip.$url);

print_r($cur_arr);
?>
