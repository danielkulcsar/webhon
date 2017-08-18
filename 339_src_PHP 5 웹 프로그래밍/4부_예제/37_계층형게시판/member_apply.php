<html> 
<head> 
<meta http-equiv="content-type" content="text/html; charset=euc-kr"> 
<title>회원가입</title> 
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
		if (document.edit_form.memberid.value == "") { 
			alert('회원 ID는 반드시 입력하셔야 합니다.'); 
			document.edit_form.memberid.focus(); 
			return; 
		}else if (document.edit_form.membername.value == "") { 
			alert('회원명 반드시 입력하셔야 합니다.'); 
			document.edit_form.membername.focus(); 
			return; 
		}else if (document.edit_form.memberpass.value == "") { 
			alert('회원 비밀번호는 반드시 입력하셔야 합니다.'); 
			document.edit_form.memberpass.focus(); 
			return;
		}else if (document.edit_form.memberpass_confirm.value == "") { 
			alert('회원 비밀번호 확인란은 반드시 입력하셔야 합니다.'); 
			document.edit_form.memberpass_confirm.focus(); 
			return;
		}else if (document.edit_form.memberpass_confirm.value != document.edit_form.memberpass.value) { 
			alert('회원의 비밀번호가 확인란의 비밀번호와 일치하지 않습니다.'); 
			document.edit_form.memberpass_confirm.focus(); 
			return;
		} else { 
			document.edit_form.action = "member_insert.php"; 
			document.edit_form.submit(); 
		} 
	} 
</script> 
</head> 
  
<body bgcolor="white" text="black" link="blue" vlink="purple" alink="red"> 
<table align="center" border="0" cellspacing="0" cellpadding="5" width="600">
<tr><th align="center">
	<font size="2">회원 가입</font>
	<hr size="1" color="gray" width="100%">
</td></tr>
</table>
<table align="center" border="1" cellspacing="0" cellpadding="5" width="600" bordercolordark="white" bordercolorlight="#CCCCCC"> 
<form name="edit_form" method="post"> 
	<tr> 
		<th bgcolor="#EEEEEE"><p align="center">회원 ID</p></th> 
		<td><input type="text" name="memberid" maxlength="8" style="width:100px"></td>
	</tr> 
	<tr> 
		<th bgcolor="#EEEEEE"><p align="center">회원명</p></th> 
		<td><input type="text" name="membername" maxlength="8" style="width:100px"></td>
	</tr> 
	<tr> 
		<th bgcolor="#EEEEEE"><p align="center">비밀번호</p></th> 
		<td><input type="password" name="memberpass" maxlength="8" style="width:100px">
	</tr> 
	<tr> 
		<th bgcolor="#EEEEEE"><p align="center">비밀번호(확인)</p></th> 
		<td><input type="password" name="memberpass_confirm" maxlength="8" style="width:100px">
	</tr> 
	<tr> 
		<th bgcolor="#EEEEEE"><p align="center">전자메일</p></th> 
		<td><input type="text" name="membermail" maxlength="30" style="width:200px"></td>
	</tr> 
	<tr> 
		<th bgcolor="#EEEEEE"><p align="center">홈페이지</p></th> 
		<td><input type="text" name="memberurl" maxlength="30" style="width:200px">
	</tr> 
</form>
</table>
<table border=0 width=200 align="center" >
<form name="btn_form">
	<tr><td align="right">
		<input type="button" name="btn_apply" class="ahnbutton" value="회원 가입" title="" onMouseOver="goLite(this.form.name,this.name)" onMouseOut="goDim(this.form.name,this.name)" onClick="check_submit();">
		<input type="button" name="btn_cancel" class="ahnbutton" value="취소" title="" onMouseOver="goLite(this.form.name,this.name)" onMouseOut="goDim(this.form.name,this.name)" onClick="history.back()">
	</td></tr>
</form>
</table>
</body> 
</html>