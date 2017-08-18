<?
$a=" I LOVE YOU ";

for($i=0;$i<10000;$i++)
{
	$str.=$a;
}

$ret1=str_replace("I"," ",$str);
echo $ret1;
echo "<br>============================================<br>";
$ret2=ereg_replace("YOU"," ",$str);
echo $ret2;

?>

