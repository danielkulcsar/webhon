<?
$a="XDC11 KSD30";
$b="xdc11 ksD30";
$c="XDC11 KSD0";
$d="XDC11KSD30";
$e="XDC11 KSD30";
$val[0]=strcmp($a,$b);
$val[1]=strcmp($a,$c);
$val[2]=strcmp($a,$d);
$val[3]=strcmp($a,$e);

for($i=0;$i<=3;$i++)
{
if($val[$i]) 
echo(($i+1) . " : 인증 실패 ($val[$i]) <br> ");
else echo(($i+1) . " : 인증 성공 ($val[$i]) <br>"); 
}
?>



