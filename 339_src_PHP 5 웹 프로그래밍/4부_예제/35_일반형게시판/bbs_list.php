<? 
require("./member_checklogin.include.php");
require("./bbs_dbconn.include.php");

//게시판 목록보기에 필요한 각종 변수 초기값을 설정합니다. 
$tablename="mfboard"; //테이블 이름 
if($page == '') $page = 1; //페이지 번호가 없으면 첫페이지로 설정(1 페이지)
$list_num = 10; //한 페이지에 보여줄 목록 갯수 
$page_num = 10; //한 화면에 보여줄 페이지 링크(묶음) 갯수 
$offset = $list_num*($page-1); //한 페이지의 시작 글 번호(listnum 수만큼 나누었을 때 시작하는 글의 번호) 
  
//전체 글 수를 구합니다. (쿼리문을 사용하여 결과를 배열로 저장하는 일반적 인 방법) 
$query="select count(*) from $tablename"; // SQL 쿼리문을 문자열 변수에 일단 저장하고 
$result=mysql_query($query) or die (mysql_error()); // 위의 쿼리문을 실제로 실행하여 결과를 result에 대입 
$row=mysql_fetch_row($result); //위 결과 값을 하나하나 배열로 저장합니다 . 
$total_no=$row[0]; //배열의 첫번째 요소의 값, 즉 테이블의 전체 글 수를 저장합니다. 
  
//전체 페이지 수와 현재 글 번호를 구합니다. 
$total_page=ceil($total_no/$list_num); // 전체글수를 페이지당글수로 나눈 값의 올림 값을 구합니다. 
$cur_num=$total_no - $list_num*($page-1); //현재 글번호 
  
//bbs테이블에서 목록을 가져옵니다. (위의 쿼리문 사용예와 비슷합니다 .) 
$query="select * from $tablename order by number desc limit $offset, $list_num"; // SQL 쿼리문 
$result=mysql_query($query) or die (mysql_error()); // 쿼리문을 실행 결과 
//쿼리 결과를 하나씩 불러와 실제 HTML에 나타내는 것은 HTML 문 중간에 삽입합니다. 
?> 
  
<html> 
<head> 
<meta http-equiv=content-type content=text/html; charset=euc-kr> 
<title>처음게시판 > 글목록</title> 
<link rel="stylesheet" type="text/css" href="bbs.css">
<script language="javascript">
	function goLite(FRM,BTN){
	   window.document.forms[FRM].elements[BTN].style.backgroundColor = "#AAAAAA";
	}

	function goDim(FRM,BTN){
	   window.document.forms[FRM].elements[BTN].style.backgroundColor = "#EEEEEE";
	}
</script>
</head> 
<body>
<table border=0 width="680">
	<tr><td align=right colspan="5"> 
		<font color=green><b>처음게시판 > 글목록</b></font> 
	</td></tr> 
</table>
<table border=1 cellspacing=0 cellpadding="5" width=680 bordercolordark=white bordercolorlight=#999999> 
	<tr> 
		<th width=50 bgcolor=#CCCCCC>번호</th> 
		<th bgcolor=#CCCCCC width=450>제목</th> 
		<th width=60 bgcolor=#CCCCCC>작성자</th> 
		<th width=70 bgcolor=#CCCCCC>작성일자</th> 
		<th width=60 bgcolor=#CCCCCC>조회수</th> 
	</tr> 

<? 
while ($array=mysql_fetch_array($result)) { 
	$date=date("Y/m/d", $array[writetime]); //글쓴시각을 Y/m/d 형식에 맞게 문자열로 바꿉니다 . 
	echo " 
	<tr> 
		<td width=30>$cur_num</td> 
		<td width=490><a href='bbs_view.php?number=$array[number]'>$array[subject]</a></td> 
		<td width=60>$array[name]</td> 
		<td width=70>$date</td> 
		<td width=30>$array[count]</td> 
	</tr>"; 

	$cur_num --;
}
?>
</table>
<table border=0 width="680">
	<tr> 
		<td width=100%> 
<? 
//여기서부터 각종 페이지 링크 
//먼저, 한 화면에 보이는 블록($page_num 기본값 이상일 때 블록으로 나뉘어짐 ) 
$total_block=ceil($total_page/$page_num); 
$block=ceil($page/$page_num); //현재 블록 
  
$first=($block-1)*$page_num; // 페이지 블록이 시작하는 첫 페이지 
$last=$block*$page_num; //페이지 블록의 끝 페이지 
  
if($block >= $total_block) { 
	$last=$total_page; 
} 


echo "전체 : $total_no 건 ";
echo "&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; ";
echo "&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; ";

//[◀◀] : 첫 페이지로 이동한다.
if($block > 1) { 
	$prev=$first-1; 
	echo "<a href='bbs_list.php?page=1'>[◀◀]</a>&nbsp; &nbsp;"; 
} 
  
//[◀] 이전 페이지로 이동한다.
if($page > 1) { 
	$go_page=$page-1; 
	echo "  <a href='bbs_list.php?page=$go_page'>[◀]</a>&nbsp; &nbsp;"; 
} 
  
//[1] [2] [3], ... : 각 페이지를 표시한다.
for ($page_link=$first+1;$page_link<=$last;$page_link++) { 
	if($page_link==$page) { 
		echo "<font color=green><b>$page_link</b></font>&nbsp"; 
	}else{ 
		echo "<a href='bbs_list.php?page=$page_link'>[$page_link]</a>&nbsp;"; 
	} 
} 
  
//[▶] : 다음 페이지로 이동한다.
if($total_page > $page) { 
	$go_page=$page+1; 
	echo "&nbsp;<a href='bbs_list.php?page=$go_page'>[▶]</a>"; 
} 
  
//[▶▶] : 마지막 페이지로 이동한다.
if($block < $total_block) { 
	$next=$last+1; 
	echo " &nbsp;<a href='bbs_list.php?page=$total_page'>[▶▶]</a>"; 
} 
  
// MySQL 데이터베이스 연결을 닫음
mysql_close();
?> 
		</td> 
	</tr> 
	<tr> 
		<form name="btn_form">
		<td width=100% align="right">
			<input type="button" name="btn_write" class="ahnbutton" value="글쓰기" title="" onMouseOver="goLite(this.form.name,this.name)" onMouseOut="goDim(this.form.name,this.name)" onClick="location.href='bbs_write.php'">
		</td> 
		</form>
	</tr> 
</table> 
</body> 
</html>