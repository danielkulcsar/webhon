<?

$id = $_POST['id'];
$pw = $_POST['pw'];
// 입력된 정보를 DB에서 찾기

$query = "SELECT * FROM members WHERE id = '".$id."'";
$result = mysql_query($query) or die(mysql_error());
$row = mysql_fetch_array($result);
	if(!$row) { // 아이디 값이 없으면 기존 로그인 페이지 로딩
		header("location:login_page.php");
	} else { // 아이디 값이 존재하면
		if($row['pw'] == $pw) // 입력된 비밀번호와 기존 비밀번호를 비교
		{
		// 로그인 성공시 세션을 생성함
		$_SESSION['id'] = $row['id'];
		$_SESSION['name'] = $row['name'];
		$_SESSION['email'] = $row['email'];
		$_SESSION['num'] = $row['num'];
		} else {
		header("location:http://www.gamess.co.kr/login_page.php");
		}
	}

?>