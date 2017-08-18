<?php
$dir = "./imsi/";

if(is_dir($dir))					
{
	if($dir_handle=opendir($dir))		
	{
		while(($file_handle=readdir($dir_handle)) != false)  
		{
			echo "파일명 : ".$file_handle."<br>";
		}
	}
}
else
{
	echo "디렉토리가 아닙니다.";
}

closedir($dir_handle);			
?>
