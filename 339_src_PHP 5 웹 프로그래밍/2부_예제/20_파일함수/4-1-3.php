<?
$a=file("http://127.0.0.1/");

while(list($line_num,$line)=each($a))
{
	echo "Line $line_num : ".htmlspecialchars($line)."<br>";
}
?>
