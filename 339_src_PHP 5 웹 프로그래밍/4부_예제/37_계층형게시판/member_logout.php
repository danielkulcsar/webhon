<?
require("./bbs_dbconn.include.php");

// 세션에 저장된 사용자의 로그인 정보를 확인한다.
session_start();

$logout_message = "";

if($memberid=="" || $membername==""){
	// 현재 로그인되지 않은 사용자이므로 무시한다.
	$logout_message = "아직 로그인되지 않았습니다.";
}else{
	$logout_message = "로그아웃 되었습니다.\\n\\n"."$membername 님 안녕히 가세요";

	session_unregister("memberid");
	session_unregister("membername");
	session_unregister("membermail");
	session_unregister("memberurl");		
}

//사용자 로그인이 완료되면 목록 보기 페이지로 자동 이동하도록 합니다
$msg = "성공적으로 로그인 되었습니다";
echo "
<html>
<head>
	<script name=javascript>
		if('$logout_message' != '') {
			self.window.alert('$logout_message');
		}

		location.href='member_login.php';
	</script>
</head>
</html>
";

?>