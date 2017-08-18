<?
$receiver; $subject; $content;
$content=str_replace("\n","<br>",$content);

// 보내는 사람 이름은 "CodMedia"이며
$sender_name = "CodMedia";
// 보내는 사람의 메일 주소는 “webmaster@codmedia.com”이며
$sender_address = "webmaster@codmedia.com";
// 메일 메시지 내용은 html 타입으로 작성되었다고 수신자에게 알려줍니다.
$body_format = "text/html;charset=EUC-KR";

$re=mail(
		"$receiver",
		"$subject",
		"$content",
		"From: [".$sender_name."] <".$sender_address.">\n".
		"Content-Type: ".$body_format."\n"
		);

echo "메일 전송 완료\n<p>";
echo "<a href='./mail_form.php'>뒤로</a>";
?>
