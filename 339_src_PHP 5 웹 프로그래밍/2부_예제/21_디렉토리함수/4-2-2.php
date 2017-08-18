<?php
$dir="./imsi/";

if($dir_handle=opendir($dir))		//디렉토리를 오픈
{
	while(($file_handle=readdir($dir_handle)) != false)//오픈된 디렉토리 파일 읽기
	{
		echo "파일명 : ".$file_handle;
		echo "&nbsp;&nbsp;&nbsp;파일타입 : ";
		echo filetype($dir.$file_handle)."<br>";	//파일타입 출력
	}
}
closedir($dir_handle);		//연 디렉토리 닫음
?>
