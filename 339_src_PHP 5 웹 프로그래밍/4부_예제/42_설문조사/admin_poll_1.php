<?php

include "./poll_con.php.inc";

$year=Date("Y");
$month=Date("m");
$day=Date("d");
?>

<html>
<head><title>폴 관리자 페이지_1</title>
<script language="javascript">
<!--
function chk()
{
	if(frm.title.value.length==0)
	{
   alert("폴 제목을 쓰세요");          //입력된 제목 없을 경고 메시지 표시
	  frm.title.focus();                              
   return false;
	}
	else if(frm.rep.value.length==0) 
	{
	 alert("답변수를 쓰세요");       //입력된 답변수 없을 경우 경고 메시지 표시
	 frm.rep.focus();            
  return false;
	}
	if(frm.pass.value.length==0)     
	{
  alert("비밀번호를 쓰세요");        //입력된 비밀번호 없을 경우 경고 메시지 표시
	 frm.pass.focus();
	 return false;
	}
 return true;
}
//-->
</script>
</head>
 <body>
 <table align="center" border="0" cellpadding="0" cellspacing="0" width="800">
  <tr>
    <td width="1101"> <p align="center">
    <font size="3"><b>폴 관리자 페이지 1</b></font></p>
    <form name="frm" action="./admin_poll_2.php" method="post" onSubmit="return chk();">
       <!-- poll을 생성하기 위한 입력 폼을 화면에 출력 -->

  <table align="center" border="1" width="600" cellpadding="2">
   <tr>
    <td width="150"><p align="right"><font size="2">제목 :</font></p></td>
    <td width="450"> <p><font size="2">
    <input type="text" name="title" size="50"></font></p></td>
     <!-- 제목을 입력받기 위한 입력 상자 -->
   </tr>
   <tr>
    <td width="150"><p align="right"><font size="2">답변 수 :</font></p></td>
    <td width="450"><p><font size="2">
     <input type="text" name="rep" size="10"></font></p></td>
      <!-- 답변수를 입력받기 위한 입력 상자 -->
    </tr>
    <tr><
     <td width="150"><p align="right"><font size="2">폴 시작 날짜 :</font></p></td>
     <td width="450"><p><font size="2">
      <? echo "<select name='y'>"; 		//시작 년도를 입력받기 위한 콤보 상자
	$i=$year;
	while($i<=$year){                         
  	  if($year==$i)           			
	     echo "<option selected> $i </option>";     //현재 년도를 디폴트값 선택됨
	  else
	     echo "<option> $i </option>";
		$i--;
  	     if($i<2000) break;  
	   }
	     echo "</select>년";     //현재 년도부터 1씩 줄여가면서 2000년도까지 출력
             
	 echo "<select name='m'>";             //시작 월을 입력받기 위한 콤보 상자
  	  $i=1;
      while($i<13){
	  if($month==$i)                                    //현재 월 디폴트값 설정
 	 echo "<option selected>$i</option>";
      else
	  echo "<option>$i</option>";
	  $i++;
	}
       	  echo "</select>월";
          echo "<select name='d'>";           //시작 일자를 입력받기 위한 콤보 상자
       $i=1;
 	  while($i<32){
	if($day==$i)                       		//현재 일자 디폴트값 설정
          echo "<option selected>$i</option>";
       else
	  echo "<option>$i</option>";
	  $i++;
	}
	  echo "</select>일"; ?></font></p>
   </td>
  </tr>
  <tr>
   <td width="150"><p align="right"><font size="2">폴 종료 날짜 :</font></p></td>
   <td width="450"><p><font size="2">
    <? echo "<select name='y1'>";            //종료 년도를 입력받기 위한 콤보 상자
	$i=$year;
	while($i<=$year){
		if($year==$i)
			echo "<option selected>$i</option>";
		else
			echo "<option>$i</option>";
		$i--;
		
		if($i<2000) break;
	}
	echo "</select>년;";
	echo "<select name='m1'>";            //종료 월을 입력받기 위한 콤보 상자
	$i=1;
	
	while($i<13){
		if($month==$i)
			echo "<option selected>$i</option>";
		else
			echo "<option>$i</option>";
		$i++;
	}
	echo "</select>월";
		echo "<select name='d1'>";    //종료 일자를 입력받기 위한 콤보 상자
		$i=1;
	while($i<32){
		if($day==$i)
			echo "<option selected>$i</option>";
		else
			echo "<option>$i</option>";
		$i++;
	}
	echo "</select>일"; ?></font></p>
    </td>
  </tr>
  <tr>
    <td width="150">
        <p align="right"><font size="2">비밀번호 :</font></p>
    </td>
    <td width="450">
        <p><font size="2">&nbsp;
        <input type="password" name="pass" size="10" style="height=20;">
	      </font></p>
            </td>
        </tr>
        <tr>
            <td width="590" colspan="2">
                <p align="right"><font size="2">
                <input type="submit" name="smt" value="다 음">&nbsp;</font></p>
                    </td>
                </tr>
            </table>
	    </form>
            </form>
        </td>
    </tr>
</table>
</body>
</html>
