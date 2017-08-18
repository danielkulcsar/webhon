<?
for($i=65;$i<91;$i++)
{
	echo (chr(34) . chr(32) . chr($i). chr(32) . chr(34));
}
echo "<p>======================================<p>";
for($i=0;$i<=9;$i++)
{
	echo (chr(34) . chr(32) . ord($i). chr(32) . chr(34));
}
?>
