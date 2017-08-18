<?
function x($a,$b)
{
	$div=intval($a/$b);
	$rest=$a%$b;
	return array($div,$rest);
}

list($c,$d)=x(30,7);

echo ("╦Р = ".$c."<br>");
echo ("Ё╙╦саЖ = ".$d."<br>");
?>
