<?php
$str="사랑하는 나의 연인";

$en_str=urlencode($str);

echo "인코드한 문자 : ".$en_str;
echo "<br><br><br>";
echo "디코드한 문자 : ".urldecode($en_str);
?>
