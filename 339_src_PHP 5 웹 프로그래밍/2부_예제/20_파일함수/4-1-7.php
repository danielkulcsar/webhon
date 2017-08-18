<?
$source=file("./abc-1.dat");	
srand(time());		

$sel=$source[rand()%count($source)];

$str=explode("##",$sel);
echo "<a href=$str[1]><img src=$str[0] border=0></a>";
?>
