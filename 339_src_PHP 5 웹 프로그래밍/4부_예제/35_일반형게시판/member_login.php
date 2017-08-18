<html> 
<head> 
<meta http-equiv="content-type" content="text/html; charset=euc-kr"> 
<title>회원로그인</title> 
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
	// 쿠키에 값을 설정하는 함수
	function setCookie (name, value, expires) {
		document.cookie = name + "=" + escape (value) + "; path=/; expires=" + expires.toGMTString();
	}

	// 쿠키에서 값을 읽어오는 함수
	function getCookie(Name) {
		var search = Name + "="
		if (document.cookie.length > 0) {
			// 쿠키가 설정되어 있다면
			offset = document.cookie.indexOf(search)
			if (offset != -1) {
				// 쿠키가 존재하면
				offset += search.length
				// set index of beginning of value
				end = document.cookie.indexOf(";", offset)
				// 쿠키 값의 마지막 위치 인덱스 번호 설정
				if (end == -1)
					end = document.cookie.length
				return unescape(document.cookie.substring(offset, end))
			}
		}
		return "";
	}

	// 쿠키에 저장할 회원 ID를 준비하는 함수
	// save_flag가 true이면 쿠키에 저장하고
	// save_flag가 false이면 쿠키에서 제거한다.
	function saveid(memberid, save_flag){
		var expdate = new Date();
		// 기본적으로 30일동안 기억하게 함. 일수를 조절하려면 * 30에서 숫자를 조절하면 됨
		if (save_flag){
			expdate.setTime(expdate.getTime() + 1000 * 3600 * 24 * 30); // 30일
		}else{
			expdate.setTime(expdate.getTime() - 1);      // 쿠키 삭제조건
		}
		setCookie("memberid", memberid, expdate);
	}

	// 쿠키에 저장된 회원 ID를 가져온다.
	function getid(){
		return getCookie("memberid");
	}

	function initializeMemberID(){
		var memberid = getid();
		// 쿠키에 저장된 회원 ID가 없으면 중지한다.
		if(memberid=="") return;

		document.forms['edit_form'].memberid.value = memberid;
		document.forms['edit_form'].rememberme.checked = true;
	}
</script>
<script language="javascript"> 
	function check_submit() { 
		if (document.edit_form.memberid.value == "") { 
			alert('회원 ID는 반드시 입력하셔야 합니다.'); 
			document.edit_form.memberid.focus(); 
			return; 
		}else if (document.edit_form.memberpass.value == "") { 
			alert('회원 비밀번호는 반드시 입력하셔야 합니다.'); 
			document.edit_form.memberpass.focus(); 
			return;
		} else { 
			document.edit_form.action = "member_verify.php"; 
			document.edit_form.submit(); 
		} 
	} 
</script> 
</head> 
  
<body onLoad="initializeMemberID()" bgcolor="white" text="black" link="blue" vlink="purple" alink="red"> 
<table align="center" border="0" cellspacing="0" cellpadding="5" width="400">
<tr><th align="center">
	<font size="2">회원 로그인</font>
	<hr size="1" color="gray" width="100%">
</td></tr>
</table>
<table align="center" border="1" cellspacing="0" cellpadding="5" width="400" bordercolordark="white" bordercolorlight="#CCCCCC"> 
<form name="edit_form" method="post"> 
	<tr> 
		<th bgcolor="#EEEEEE"><p align="center">회원 ID</p></th> 
		<td><input type="text" name="memberid" maxlength="8" style="width:100px"></td>
	</tr> 
	<tr> 
		<th bgcolor="#EEEEEE"><p align="center">비밀번호</p></th> 
		<td><input type="password" name="memberpass" maxlength="8" style="width:100px"></td>
	</tr> 
	<tr> 
		<td bgcolor="#EEEEEE" colspan="2">
			<input type="checkbox" name="rememberme" id="rememberme" onClick="saveid(this.form.memberid.value, this.checked)"><label for="rememberme">나의 ID 기억하기</label>
		</td>
	</tr>
</form>
</table>
<table border=0 width=400 align="center" >
<form name="btn_form">
	<tr><td align="right">
		<input type="button" name="btn_apply" class="ahnbutton" value="로그인" title="" onMouseOver="goLite(this.form.name,this.name)" onMouseOut="goDim(this.form.name,this.name)" onClick="check_submit();">
		<input type="button" name="btn_cancel" class="ahnbutton" value="취소" title="" onMouseOver="goLite(this.form.name,this.name)" onMouseOut="goDim(this.form.name,this.name)" onClick="history.back()">

		&nbsp; &nbsp;
		<input type="button" name="btn_memberapply" class="ahnbutton" value="회원가입" title="" onMouseOver="goLite(this.form.name,this.name)" onMouseOut="goDim(this.form.name,this.name)" onClick="location.href='member_apply.php'">
	</td></tr>
</form>
</table>
</body> 
</html>