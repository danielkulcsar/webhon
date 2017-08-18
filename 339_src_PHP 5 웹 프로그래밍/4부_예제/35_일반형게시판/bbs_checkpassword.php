<?php
require("./member_checklogin.include.php");
?>
<html> 
<head> 
<meta http-equiv="content-type" content="text/html; charset=euc-kr"> 
<title>처음게시판 > 글삭제</title>
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
		if (document.edit_form.password.value == "") { 
			alert('게시물 삭제를 위해서 비밀번호가 필요합니다.'); 
			document.edit_form.password.focus(); 
			return; 
		} else { 
			document.edit_form.action = "bbs_delete.php"; 
			document.edit_form.submit(); 
		} 
	} 
</script> 
</head> 
  
<body bgcolor="white" text="black" link="blue" vlink="purple" alink="red"> 
<table align="center" border="1" cellspacing="0" cellpadding="5" width="200" bordercolordark="white" bordercolorlight="#CCCCCC"> 
<form name="edit_form" method="post"> 
<input type="hidden" name="page" value="<? echo $page; ?>"> 
<input type="hidden" name="number" value="<? echo $number; ?>"> 
	<tr> 
		<th bgcolor="#EEEEEE"><p align="center">글 삭제 비밀번호</p></th> 
	</tr> 
	<tr> 
		<td align="center"> 
			<input type="password" name="password" maxlength="12" size="12"> 
		</td> 
	</tr> 
</form>
</table>
<table border=0 width=200 align="center" >
<form name="btn_form">
	<tr><td align="right">
		<input type="button" name="btn_delete" class="ahnbutton" value="삭제" title="" onMouseOver="goLite(this.form.name,this.name)" onMouseOut="goDim(this.form.name,this.name)" onClick="check_submit();">
		<input type="button" name="btn_cancel" class="ahnbutton" value="취소" title="" onMouseOver="goLite(this.form.name,this.name)" onMouseOut="goDim(this.form.name,this.name)" onClick="history.back()">
	</td></tr>
</form>
</table>
</body> 
</html>