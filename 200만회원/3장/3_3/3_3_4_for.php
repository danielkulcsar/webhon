<html>
<head>
<meta charset="utf-8">
<title>게임세상</title>
</head>
<body>
<?php
$msg = "게임의 모든 것 게임세상<br>";
?>
<?=$msg?>
<?
	if ($data_type == "manual") echo("매뉴얼");
	else if ($data_type == "cheat") echo("치트");
	else echo("기타");
?>
<br>
<?
	for ($i =0 ; $i< 5 ; $i++){
	echo ("$i $msg");
	}
?>
</body>
</html>