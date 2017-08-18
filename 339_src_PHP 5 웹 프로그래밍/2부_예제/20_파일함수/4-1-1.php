<?
$fp=fopen("test.dat","r");

if($fp==null) {
	echo "파일이 존재하지 않습니다.";
} else {
echo "파일 열기 완료";
}
fclose($fp);
?>
