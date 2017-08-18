<?php
	$game_info = mysql_assoc("SELECT * FROM 'game_info' WHERE 'game_code'='$code'");
	$pds_game = mysql_assoc("SELECT * FROM 'pds_game' WHERE 'num'='$num'");
	$query_reply = mysql_query("SELECT * FROM 'pds_reply' WHERE 'parent_num'='$num' AND 'pds_type'='pds_game' ");
	$pds_reply['mb_img'] = result("SELECT 'mb_img' FROM 'members' WHERE 'id'='{$pds_reply['id']}'");
?>