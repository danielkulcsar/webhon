<?php
include "proc_db.php.inc";

$blood=$blood;

$sql="select * from diet_tbl where blood='$blood'";
$result=mysql_query($sql,$db);
$row=mysql_fetch_array($result);

$cont=$row[content];

mysql_close($db);

echo "&b_result=$cont";
?>