<?
$b=strlen($_POST[a]);
if ($b<=20)
echo "$_POST[a] <br> ";
else echo("약" . $b/2 . "자 입력. 10자만 입력이 가능합니다.");
?>

