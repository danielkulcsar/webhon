<?php
require("./member_checklogin.include.php");
?>
<html> 
<head>
<title>처음게시판 > 글쓰기</title>

<link rel="stylesheet" type="text/css" href="bbs.css">
<script language="javascript">
	function goLite(FRM,BTN){
	   window.document.forms[FRM].elements[BTN].style.backgroundColor = "#AAAAAA";
	}

	function goDim(FRM,BTN){
	   window.document.forms[FRM].elements[BTN].style.backgroundColor = "#EEEEEE";
	}
</script>

<script language="javascript">
	function check_submit() {
		if (document.edit_form.name.value == "") {
			alert('제목을 입력해주세요.');
			document.edit_form.name.focus();
			return;
		} else if (document.edit_form.password.value == "") {
			alert('게시물의 수정,삭제를 위해서 비밀번호가 필요합니다.');
			document.edit_form.password.focus();
			return;
		} else if (document.edit_form.subject.value == "") {
			alert('게시물의 제목을 입력해주세요.');
			document.edit_form.subject.focus();
			return;
		} else if (document.edit_form.memo.value == "") {
			alert('게시물의 내용을 입력해주세요.');
			document.edit_form.memo.focus();
			return;
		} else {
			document.edit_form.action = "bbs_insert.php";
			document.edit_form.submit();
		}
}
</script>
</head>
<body bgcolor=white>
<br>

<table border=0 cellspacing=1 cellpadding=0 width=670>
	<tr>
	<td align=right>
		<font color=green><b>처음게시판 > 글쓰기</b></font>
	</td>
	</tr>
</table>

<table width=670 cellspacing=0 cellpadding=5 style="border:2px solid gray">
<form name='edit_form' method='post' >
	<tr>
		<td width=100 align=right bgcolor="#EEEEEE"><b>이름 </b></td>
		<td><input type=text name=name size=20  maxlength=20></td>                        
		<td width=100 align=right bgcolor="#EEEEEE"><b>비밀번호 </b></td>
		<td><input type=password name=password  size=20  maxlength=20></td>
	</tr>

	<tr><td bgcolor="#999999" height=1 colspan=4></td></tr>
	
	<tr>
		<td align=right bgcolor="#EEEEEE"><b>전자우편 </b></td>
		<td colspan="3"> <input type=text name=email size=40  maxlength=200> </td>
	</tr>
	
	<tr><td bgcolor="#999999" height=1 colspan=4></td></tr>

	<tr>
		<td align=right bgcolor="#EEEEEE"><b>홈페이지 </b></td>
		<td colspan="3"> <input type=text name=homepage size=40  maxlength=200> </td>
	</tr>

	<tr><td bgcolor="#999999" height=1 colspan=4></td></tr>

	<tr>
		<td align=right bgcolor="#EEEEEE"><b>제목 </b></td>
		<td colspan="3"> <input type=text name=subject size=87  maxlength=200> </td>
	</tr>

	<tr><td bgcolor="#999999" height=1 colspan=4></td></tr>

	<tr>
		<td align=right bgcolor="#EEEEEE" valign="top"><b>내용 </b></td>
		<td valign=top colspan="3">
			<textarea name=memo cols=85 rows=20></textarea>
		</td>
	</tr>
</form>
</table>
<br>
<table border=0 width=670>
<form name="btn_form">
	<tr><td align="right">
		<input type="button" name="btn_save" class="ahnbutton" value="저장" title="" onMouseOver="goLite(this.form.name,this.name)" onMouseOut="goDim(this.form.name,this.name)" onClick="check_submit();">
		<input type="button" name="btn_cancel" class="ahnbutton" value="취소" title="" onMouseOver="goLite(this.form.name,this.name)" onMouseOut="goDim(this.form.name,this.name)" onClick="history.back()">
	</td></tr>
</form>
</table>
 

</body>
</html>