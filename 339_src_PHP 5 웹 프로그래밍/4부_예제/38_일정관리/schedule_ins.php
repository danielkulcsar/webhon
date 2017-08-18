<?php
include "./schedule_db.php.inc";

$dat; $iljung;

$sql="insert into schedule_tbl (schedule,dat) values ('$iljung','$dat')";
$result=mysql_query($sql,$db);

mysql_close($db);

echo "
<script language='javascript'>
<!--
	alert('»ðÀÔ ¿Ï·á');
	self.close();
	opener.document.location.reload();
//-->
</script>
";
?>
