<?
$a=microtime();
echo "<<<<처리시작>>>> TIME : " .$a. "<p>";
$num=157;
for($i=1;$i<=$num;$i++)
  $sum+=$i;
echo "========================<br>";
echo "1부터" .$num. "까지의 합 : " .$sum. "<br>";
echo "========================<p>";
$b=microtime();
echo "<<<<처리완료>>>> TIME : " .$b. "<br>";
echo "실행완료시간 : ".($b-$a)." ㎲";
?>
