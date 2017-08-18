<?
$a=date("l");
switch($a)
{
case "Monday" : echo " [월요일] 첫 수업은 10시 ";
break;
case "Tuesday" : echo " [화요일] 첫 수업은 9시 ";
break;
case "Wednesday" : echo " [수요일] 첫 수업은 12시 ";
break;
case "Thursday" : echo " [목요일] 첫 수업은 10시 ";
break;
case "Friday" : echo " [금요일] 첫 수업은 9시 ";
break;
case "Saturday" : echo " [토요일] 수업 없음 ";
break;
case "Sunday" : echo " [일요일] 수업 없음 ";
break;
}
?>

