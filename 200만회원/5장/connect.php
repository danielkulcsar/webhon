<?
	include ("/home/config.php");
	$connect = mysql_connect("$db_ip","$db_id","$db_pwd");
	mysql_query("set names utf8");
	mysql_select_db("$db_name");
	$pwd = sha1($pwd_key.md5($pwd));
?>