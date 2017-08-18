<?
$a = array('ASP', 'JSP', 'PHP');
reset($a);
next($a);
print_r(each($a));
echo "<br>";
print_r(each($a));
echo "<br>";
if(each($a)){print_r(each($a));}
else echo("현재 커서가 배열에 존재하지 않습니다.");
?>  


