<?php
$dir="./imsi/";
$cnt=0;

$dir_handle=opendir($dir);		//디렉토리 오픈

while(($file_handle=readdir($dir_handle)) != false)    //디렉토리 파일을 읽어 들임
{
	echo "파일명 : ".$file_handle."<br>";         //파일명 출력
	rewinddir($dir_handle);                      //포인터를 처음으로 위치

	$cnt++;
	if($cnt==5) break;
}
closedir($dir_handle);
?>
