<?
$a=fopen("a.txt", "r");
$b=filesize("a.txt");
echo fgets($a,3) . "<br>";
echo fread($a,3) . "<br>";


fclose($a);
?>

