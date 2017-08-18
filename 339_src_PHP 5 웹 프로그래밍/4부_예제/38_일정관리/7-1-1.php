<?
$year=Date("Y");
$month=Date("m");
$day=Date("d");
$yoil=Date("D");

switch($yoil)
{
	case "Mon": $y="월"; $fn="./1.jpg"; break;
	case "Tue": $y="화"; $fn="./2.jpg"; break;
	case "Wed": $y="수"; $fn="./3.jpg"; break;
	case "Thu": $y="목"; $fn="./4.jpg"; break;
	case "Fri": $y="금"; $fn="./5.jpg"; break;
	case "Sat": $y="토"; $fn="./6.jpg"; break;
	case "Sun": $y="일"; $fn="./7.jpg"; break;
}

$date_time=$year."년 ".$month."월 ".$day."일 ".$y."요일";

echo "
<html>
<head><title>요일별로 배경화면 바꾸기</title>
</head>

<body background='$fn'>

<p align='left'><font color=white><b>$date_time</b></font></p>

</body>
</html>
";
?>