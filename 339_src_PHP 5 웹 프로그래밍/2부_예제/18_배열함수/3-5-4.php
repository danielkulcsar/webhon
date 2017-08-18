<?
$str="배/감/포도/수박/바나나-토마토";
$a=explode("/",$str);
for($i=0;$i<=5;$i++)
{echo "[" . $a[$i]."]";}

echo "<br>==============<br>"; 

$b=split("[/.-]",$str);
for($i=0;$i<=5;$i++)
{echo "[" . $b[$i]."]";}

echo "<br>==============<br>"; 

$b=split("/",$str, 3);
for($i=0;$i<=5;$i++)
{echo "[" . $b[$i]."]";}
?>
