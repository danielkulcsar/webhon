<?
session_start();                          //세션 시작
session_unregister("ses_id");             //세션 변수 ses_id 값 지움
session_unregister("ses_pass");          //세션 변수 ses_pass 값 지움
session_unregister("ses_email");         //세션 변수 ses_email 값 지움

echo $ses_id." 님은 로그아웃되었습니다.<p>";
echo "<a href='./index.php' target='_parent'>메인 화면</a>";
?>
