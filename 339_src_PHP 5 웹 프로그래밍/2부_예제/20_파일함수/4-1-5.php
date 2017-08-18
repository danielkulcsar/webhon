<?
$fn="abc.dat";

if(file_exists($fn))
{
	$fp=fopen($fn,"r");
} else {
	echo "파일이 존재하지 않습니다.";
	exit;
}

$content=fread($fp,filesize($fn));

$content=str_replace("\n","<br>",$content);

echo $content;

$fw=fopen("test.txt","w");
fwrite($fw,$content);

fclose($fp);
?>
