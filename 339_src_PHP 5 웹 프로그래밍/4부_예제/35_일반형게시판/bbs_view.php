<?php
require("./member_checklogin.include.php");
require("./bbs_dbconn.include.php");
  
//변수 설정합니다. 
$tablename="mfboard"; //테이블 이름 
  
//테이블에서 글을 가져옵니다. 
$query = "select * from $tablename where number='$number'"; // 글 번호를 가지고 조회를 합니다. 
$result = mysql_query($query) or die (mysql_error()); 
$array = mysql_fetch_array($result); 
  
//백슬래쉬 제거, 특수문자 변환(HTML용), 개행(<br>)처리 등 
$array[name] = stripslashes($array[name]); 
$array[subject] = stripslashes($array[subject]); 
$array[memo] = stripslashes($array[memo]); 
  
$array[subject] = htmlspecialchars($array[subject]); 
$array[memo] = htmlspecialchars($array[memo]); 
  
$array[memo] = nl2br($array[memo]); 
  
// 조회수 카운터 증가 
$query = "update $tablename set count = count + 1 where number='$number'"; 
mysql_query($query); 
  
?> 
  
<html> 
<head> 
<title>처음게시판 > 글읽기</title> 
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
<body bgcolor=white>
<table border=0 cellspacing=1 cellpadding="2" width=670> 
	<tr><td align=right> 
		<font color=green><b>처음게시판 > 글읽기</b></font> 
	</td></tr> 
    <tr> 
	<td bgcolor="gray"> 
		<table border=0 cellspacing=0 cellpadding=5 width=670 bgcolor="white"> 
			<tr> 
				<td width="100" bgcolor="#EEEEEE"><p align="right"><b>작성자 &nbsp;</b></p></td> 
				<td width="400"><p><a href="mailto:<? echo $array[email]; ?>"><? echo $array[name]; ?></a></p></td> 
				<td width="100" bgcolor="#EEEEEE"><p align="right"><b>조회수 &nbsp;</b></p></td> 
				<td width="100"><p><? echo $array[count]; ?></p></td> 
			</tr> 
			<tr><td colspan="4" bgcolor="#999999" height="1"></td></tr>
			<tr> 
				<td width="100" bgcolor="#EEEEEE"><p align="right"><b>전자우편 &nbsp;</b></p></td> 
				<td colspan="3"><p><? echo $array[email]; ?></p></td> 
			</tr> 
			<tr><td colspan="4" bgcolor="#999999" height="1"></td></tr>
			<tr> 
				<td width="100" bgcolor="#EEEEEE"><p align="right"><b>홈페이지 &nbsp;</b></p></td> 
				<td colspan="3"><p><? echo $array[homepage]; ?></p></td> 
			</tr> 
			<tr><td colspan="4" bgcolor="#999999" height="1"></td></tr>
			<tr> 
				<td width="100" bgcolor="#EEEEEE"><p align="right"><b>제목 &nbsp;</b></p></td> 
				<td colspan="3"><p><? echo $array[subject]; ?></p></td> 
			</tr> 
			<tr><td colspan="4" bgcolor="#999999" height="1"></td></tr>
			<tr> 
				<td width="100" bgcolor="#EEEEEE" valign="top"><p align="right"><b>내용 &nbsp;</b></p></td> 
				<td colspan="3" height="300px" valign="top"><p><? echo $array[memo]; ?></p></td> 
			</tr> 
		</table> 
	</td> 
    </tr> 
</table>
<?php
// MySQL 데이터베이스 연결을 닫음
mysql_close();
?>
<table border=0 cellspacing=1 cellpadding="2" width=670><tr><td width="100%">
<form name="btn_form">
<br>
<p align="right">
	<input type="button" name="btn_list" class="ahnbutton" value="목록보기" title="" onMouseOver="goLite(this.form.name,this.name)" onMouseOut="goDim(this.form.name,this.name)" onClick="location.href='bbs_list.php?page=<? echo $page; ?>'">
	<input type="button" name="btn_write" class="ahnbutton" value="글쓰기" title="" onMouseOver="goLite(this.form.name,this.name)" onMouseOut="goDim(this.form.name,this.name)" onClick="location.href='bbs_write.php'">
	<input type="button" name="btn_modify" class="ahnbutton" value="글수정" title="" onMouseOver="goLite(this.form.name,this.name)" onMouseOut="goDim(this.form.name,this.name)" onClick="location.href='bbs_modify.php?number=<? echo $number; ?>&page=<? echo $page; ?>'">
	<input type="button" name="btn_delete" class="ahnbutton" value="글삭제" title="" onMouseOver="goLite(this.form.name,this.name)" onMouseOut="goDim(this.form.name,this.name)" onClick="location.href='bbs_checkpassword.php?number=<? echo $number; ?>&page=<? echo $page; ?>'">
</p> 
</form>
</td><tr></table>
</body> 
</html>