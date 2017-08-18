<?
$a=fopen("a.txt", "r");   
$b=filesize("a.txt");     
echo " 선택한 파일의 크기는 $b byte 입니다.";
fclose($a);              
?>

