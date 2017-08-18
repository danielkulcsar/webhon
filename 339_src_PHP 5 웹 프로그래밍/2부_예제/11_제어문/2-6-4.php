<?
$a="목";
echo ("$a 요일에는"); 
switch($a)
{
  case "월": 
    echo (" 오전 9시에 수업 시작"); 
    break;
  case "화": 
    echo (" 오전 11시에 수업 시작"); 
    break;
  case "목": 
    echo (" 오전 10시에 수업 시작"); 
    break;
  case "금": 
    echo (" 오전 2시에 수업 시작"); 
    break;
  default: 
    echo " 수업이 없다!"; 
    break;
}
?>
