<?
$flower=array("장미","무궁화","진달래","해바라기","튤립");
echo current($flower)."<p>";
next($flower);
next($flower);
echo current($flower)."<p>";
prev($flower);
echo current($flower)."<p>";
reset($flower);
echo current($flower)."<p>";
?>
