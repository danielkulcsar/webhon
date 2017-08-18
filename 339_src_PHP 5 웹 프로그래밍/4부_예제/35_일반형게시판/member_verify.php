<?
require("./bbs_dbconn.include.php");

//입력폼(member_login.php)에서 전송된 내용을 변수에 담습니다.
//addslashes() 함수는 특수기호에 escape 문자를 붙혀주는 함수입니다.
//작은따옴표('), 큰따옴표("), 역슬래쉬(\)와 같은 문자는 
//그대로 사용할 경우 쿼리실행이나 문자열 출력에서 자주 오류를 
//유발시킵니다. 그래서 여기에서는 쿼리를 실행하기 전에 미리
//특수문자에 대해서 escaping처리를 합니다. 
//escaping처리란 특수문자 앞에 역슬래쉬(\)를 하나 붙혀줌으로써
//그 특수문자가 문자열의 일부임을 표시해주는 것입니다.
$memberid = addslashes($memberid);
$memberpass = addslashes($memberpass);
 
//디폴트 값이 필요한 변수에는 디폴트 값을 넣습니다.
//$applytime 변수에 현재의 시간을 저장합니다.
//time() 함수는 현재의 시간을 돌려주는 함수입니다.
$logintime = time();
 
//SQL 명령을 이용해 입력받은 내용과 디폴트값 등을 MySQL에 입력(insert)합니다.
$sql = "select memberid, membername, membermail, memberurl from member ".
		"where memberpass='$memberpass' and memberid='$memberid'";
$result = mysql_query($sql) or die (mysql_error()); 
$array = mysql_fetch_array($result); 
$row_count = mysql_num_rows($result);

if($row_count<=0){
	// MySQL 데이터베이스 연결을 닫음
	mysql_close();

	//회원 로그인이 실패하면 에러메시지를 보여주고, 이전 페이지로 이동합니다.
	$msg = "회원 ID가 없거나 비밀번호가 일치하지 않습니다.";
	echo "
	<html>
	<head>
		<script name=javascript>
			if('$msg' != '') {
				self.window.alert('$msg');
			}

			history.back();
		</script>
	</head>
	</html>
	";
}else{
	$memberid = stripslashes($array[memberid]);
	$membername = stripslashes($array[membername]);
	$membermail = stripslashes($array[membermail]);
	$memberurl = stripslashes($array[memberurl]);

	$sql = "update member set lastlogin = '$logintime' where memberpass='$memberpass' and memberid='$memberid'";
	//SQL 쿼리를 실행합니다. 오류가 발생하면 mysql_error()함수를 실행후 종료합니다.
	mysql_query($sql) or die (mysql_error());

	// MySQL 데이터베이스 연결을 닫음
	mysql_close();

	// 사용자의 로그인 정보를 세션에 저장한다.
	session_start();
	session_register("memberid");
	session_register("membername");
	session_register("membermail");
	session_register("memberurl");

	//사용자 로그인이 완료되면 목록 보기 페이지로 자동 이동하도록 합니다
	$msg = "성공적으로 로그인 되었습니다";
	echo "
	<html>
	<head>
		<script name=javascript>
			if('$msg' != '') {
				self.window.alert('$msg');
			}

			location.href='bbs_list.php';
		</script>
	</head>
	</html>
	";
}
?>