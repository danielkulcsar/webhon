<?
function a(&$a_val)
  {$a_val.="(Code Division Multiple Access)";}
function b(&$b_val)
  {$b_val="(WIreless BROadband internet)";}
function c($c_val)
  {$c_val.="(International Mobile Telecommunication 2000)";}

$a_name="CDMA ";
a($a_name);
echo ("$a_name"."<br>");

$b_name="WiBro ";
b($b_name);
echo ("$b_name"."<br>");

$c_name="IMT2000 ";
c($c_name);
echo ("$c_name"."<br>");
?>
