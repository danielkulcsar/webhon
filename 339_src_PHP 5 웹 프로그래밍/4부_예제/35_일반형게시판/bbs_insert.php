<?
require("./member_checklogin.include.php");
require("./bbs_dbconn.include.php");

//입력폼(bbs_write.php)에서 전송된 내용을 변수에 담습니다.
//addslashes() 함수는 특수기호에 escape 문자를 붙혀주는 함수입니다.
//작은따옴표('), 큰따옴표("), 역슬래쉬(\)와 같은 문자는 
//그대로 사용할 경우 쿼리실행이나 문자열 출력에서 자주 오류를 
//유발시킵니다. 그래서 여기에서는 쿼리를 실행하기 전에 미리
//특수문자에 대해서 escaping처리를 합니다. 
//escaping처리란 특수문자 앞에 역슬래쉬(\)를 하나 붙혀줌으로써
//그 특수문자가 문자열의 일부임을 표시해주는 것입니다.
$name = addslashes($name);
$password = addslashes($password);
$email = addslashes($email);
$homepage = addslashes($homepage);
$subject = addslashes($subject);
$memo = addslashes($memo);
 
//디폴트 값이 필요한 변수에는 디폴트 값을 넣습니다.
//$writetime 변수에 현재의 시간을 저장합니다.
//time() 함수는 현재의 시간을 돌려주는 함수입니다.
$writetime = time();

//getenv() 함수는 서버의 환경 정보를 불러오는 역할을 합니다.
//위와 같이 인자를 "REMOTE_ADDR"이라고 쓰면, 원격지(클라이언트)의 주소를 돌려주는 역할을 합니다.
//게시판에 글 쓴 사람의 ip 주소를 기록하기 위해 사용했습니다.
$ip = getenv("REMOTE_ADDR");

//조회수를 저장하기 위해 $count 변수를 사용했습니다.
$count = 0;
 
//SQL 명령을 이용해 입력받은 내용과 디폴트값 등을 MySQL에 입력(insert)합니다.
$sql = "insert into mfboard(name, password, email, homepage, subject, memo, count, ip, writetime) ".
       "values('$name','$password','$email','$hompage','$subject','$memo',$count,'$ip',$writetime)";

//SQL 쿼리를 실행합니다. 오류가 발생하면 mysql_error()함수를 실행후 종료합니다.
mysql_query($sql) or die (mysql_error());

// MySQL 데이터베이스 연결을 닫음
mysql_close();

//글 입력이 완료되면 목록 보기 페이지로 자동 이동하도록 합니다
$msg = "성공적으로 등록되었습니다";
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
?>