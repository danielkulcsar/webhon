<?
$val='00ff00';    //16코드를 받기
$rgb = array(substr($val,0,2), substr($val,2,2), substr($val,4,2));
$r=hexdec($rgb[0]);
$g=hexdec($rgb[1]);
$b=hexdec($rgb[2]);

echo " <b><font color='rgb($r,$g,$b)'>";
echo "===글자색상===<br>"; 
echo "16코드 :#" . $val ."<br>"; 
echo "RGB : (" . $r.",".$g."," .$b.")</font></b>";

?>