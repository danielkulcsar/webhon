<?
$a=time();
$b=mktime(7, 41, 15, 9, 20, 1982);
$c=$a-$b;
$d=intval($c/86400);
$e=intval(($c % 86400) / 3600);
$f=intval((($c % 86400) % 3600) / 60);
$g=intval((($c % 86400) % 3600) % 60);

echo "1970년1월1일부터 현재까지 <font color=blue>".$a."</font>초가 지났으며<br>";
echo "1970년 부터 당신 생일까지 <font color=red>".$c."</font>초가 지났습니다.<br>";
echo "당신이 살아온 시간은 <font color=green>".$c."</font>초 입니다.<br>";
echo "(" . $d . "일" . $e . "시간" . $f . "분" . $g . "초)";
?>
