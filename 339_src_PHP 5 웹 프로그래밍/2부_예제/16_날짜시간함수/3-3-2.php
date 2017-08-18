<?
$year=date("Y");
$month=date("m");
$day=date("d");

echo "<form>";
echo "<input type='text' value='$year 년 $month 월 $day 일' size='17'>";
echo "<br>==========================<br>";
echo "<input type='text' value='$year' size='4'> 년 ";
echo "<input type='text' value='$month' size='2'> 월 ";
echo "<input type='text' value='$day' size='2'> 일 ";
echo "<br>==========================<br>";
echo "<select name='y'>";
echo "<option>$year";
echo "</select>년 ";
echo "<select name='m'>";
echo "<option selected>$month</p>";
echo "</select>월 ";
echo "<select name='d'>";
echo "<option selected>$day";
echo "</select>일";
echo "</form>";

?>
