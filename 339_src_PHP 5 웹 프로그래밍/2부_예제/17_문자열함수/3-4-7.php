<?
$a="You're my friend";
$b="우리는 '독도'를 사랑합니다";

$c=addslashes($a);
$d=addslashes($b);
echo "$c <br> $d <p>";

$e=stripslashes($c);
$f=stripslashes($d);
echo "$e <br> $f ";
?>
