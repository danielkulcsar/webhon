<?

$pass="abcdefghijklmnopqrstuvwxyz0123456789";

for($i=0;$i<6;$i++)
{
	$val.=$pass[rand()%strlen($pass)];
}

echo $val;
?>

