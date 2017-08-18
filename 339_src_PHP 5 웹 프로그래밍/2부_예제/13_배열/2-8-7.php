<?
$flower=array("RED"=>"장미","YELLOW"=>"해바라기","PURPLE"=>"튤립");
ksort($flower);		
while(list($key,$value) = each($flower))
	print "$key -> $value<br>";
?>
