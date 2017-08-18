<?
$a="<font size='2' color='blue'><b>오늘을 사랑하라 오늘에 정성을 쏟아라</b></font>";
$b=htmlspecialchars($a);
$c=strip_tags($a);
echo($a . "<br>" . $b . "<br>" . $c);
?>


