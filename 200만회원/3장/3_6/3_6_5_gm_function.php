<?

function gm($type, $user1, $user2='', $pds_num='', $pds_type='') {
	global $gm, $basic_dn, $basic_ln, $basic_pn;
	$mb = assoc("SELECT *FROM 'members' WHERE 'mb_id'='$user1'");

	if($user2) $mb2 = assoc("SELECT *FROM 'members' WHERE 'mb_id'='$user2'");
		switch($type) {
			case "modify": 
				mysql_query("UPDATE 'members' SET 'mb_gm'='mb_gm'+{$gm['modify']['value']} WHERE 'mb_id'='{$mb['mb_id']}'");
				mysql_query("INSERT INTO 'gm_log' VALUES (NULL, '{$mb['mb_num']}','{$gm['modify']['code']}', '{$gm['modify']['value']}', NULL,NULL, NULL)");
			break;
			case "user_join":
				mysql_query("UPDATE 'members' SET 'mb_gm'='mb_gm'+{$gm['join']['value']} WHERE 'mb_id'='{$mb['mb_id']}'");
				mysql_query("INSERT INTO 'gm_log' VALUES (NULL, '{$mb['mb_num']}','{$gm['join']['code']}', '{$gm['join']['value']}', NULL, NULL,NULL)");
			break;
		}
}
?>