<?

echo max(55,35,-78,62)."<br>";   
echo max(0,'연습',true,false,45)."<br>";   
echo max('연습',0)."<br>";   
echo max(0,'연습')."<br>";   
echo max(1000,array(55,35,-78,62))."<br>";   
echo max(array(55,35,-78,62))."<br>";   
echo max(max(3,array(52,98), array(6,8,9,32)))."<br>";   
echo min(array(55,35,-78,62))."<br>";   
echo min('연습',0,-231,false,true)."<br>";   
echo min(min(array(52,98), array(6,8,9,32)));   

?>