<?
$tbl_name;

include "./mem_con.php.inc";          //파일 인클루드-서버접속 및 데이터베이스 선택

$sql="create table $tbl_name (".
	"m_num int primary key not null auto_increment,".
	"m_id varchar(30) not null,".
	"m_pass varchar(100) not null,".
	"m_email varchar(30) not null,".
	"m_dat datetime not null,".
	"m_usrip varchar(30) not null".
	")";                           //SQL 쿼리문
$result=mysql_query($sql,$db);        //쿼리 실행

if(!$result)
{
	echo mysql_errno()." : ".mysql_error();
        mysql_close($db);  //서버 연결 닫음
	exit;
}

mysql_close($db);                     //서버 연결 닫음

echo $tbl_name." 테이블이 작성되었습니다.";
?>
