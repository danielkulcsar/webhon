#필수 입력사항 점검
if(!($mb_id&& $mb_pw&& $mb_pw2 && $mb_name&& $mb_email1 && $mb_email2
&& $mb_birth_y&& $mb_birth_m&& $mb_birth_d&& $mb_gender&& $mb_pw_
question&& $mb_pw_answer))
alert('모든 필수입력 사항을 입력하셔야 합니다.', -1);
#ID 점검
<?php

if ((strlen($mb_id)<6) || (strlen($mb_id)>13)) {
	alert('아이디는 6~13자만 가능합니다.', -1);
} else if (preg_match("/[^0-9a-z_]+/i", $mb_id)) {
	alert('숫자, 영문, _만 입력하세요.', -1);
} else if (!preg_match("/[a-z]/i", $mb_id)) {
	alert('영문과 숫자를 조합하세요.', -1);
} else if (($exist = @assoc("SELECT 'mb_id' FROM 'members_info' WHERE 'mb_id' = '$mb_id' UNION SELECT 'mb_id' FROM 'members_withdrawal' WHERE  'mb_id' = '$mb_id'")))
	{ alert('이미 사용중인 아이디 입니다.', -1);
}

#이름 점검
if (!$mb_name) {
alert('성명 or 닉네임을 입력 하세요.', -1);
}

#비밀번호를 암호화 하고 members 테이블에 회원정보 입력
$mb_pw = sha1($key2.md5($mb_pw));
$result = mysql_query("INSERT INTO 'members_info' ('mb_id', 'mb_pw', 'mb_name', 'mb_email', 'mb_agree_email', 'mb_pw_question', 'mb_pw_answer', 'mb_mail', 'mb_addr', 'mb_phone', 'mb_agree_sms', 'mb_gender','mb_gm', 'mb_birth', 'mb_recommend') VALUES ('$mb_id', '$mb_pw', '$mb_name', '$mb_email', '$mb_agree_email', '$mb_pw_question', '$mb_pw_answer', '$mb_mail', '$mb_addr', '$mb_phone', '$mb_agree_sms', '$mb_gender', 0, '$mb_birth', '$mb_recommend')");

?>