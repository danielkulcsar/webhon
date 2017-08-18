<?
$flower=array("RED"=>"장미","YELLOW"=>"해바라기","PURPLE"=>"튤립");
echo $flower[2];
while(list($key,$value) = each($flower))
	echo "$key -> $value<br>";
?>
