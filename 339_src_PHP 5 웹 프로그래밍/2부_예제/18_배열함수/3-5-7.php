<?

$t="1982/9/20/7/41/15";
list($year, $month, $day, $hour, $min, $time) = split('/', $t);
echo("입력값:" . $year . "년" . $month . "월" . $day . "일" . $hour . "시" . $min . "분" . $time . "초<br>");


$a=time();                              
$b=mktime($hour, $min, $time, $month, $day, $year);       
$c=$a-$b;                                 
$d=intval($c/86400);                      
$e=intval(($c % 86400) / 3600);         
$f=intval((($c % 86400) % 3600) / 60);   
$g=intval((($c % 86400) % 3600) % 60); 
echo("살아온 시간:" . $d . "일" . $e . "시간" . $f . "분" . $g . "초");
?>
