<?php
include "./poll_con.php.inc";

$num; $title;        //사용자가 선택한 poll 번호와 제목이 ‘$num, $title’자동 할당
$t_sql="select * from poll_tbl where p_num=$num";
//사용자가 선택한 poll의 내용을 조회하기 위한 쿼리 생성
$t_ret=mysql_query($t_sql,$db);                 //조회 쿼리 실행 후 결과 셋을 ‘$t_ret’할당
$t_row=mysql_fetch_array($t_ret);               //결과 셋에서 첫 번째 레코드를 ‘$t_row’할당
$title=$t_row[p_title];                         //테이블의 ‘p_title’ 컬럼값을 ‘$title’ 할당

$sql="select * from poll_ret where r_id=$num";
//사용자가 선택한 poll의 답변 목록을 조회하기 위한 쿼리 생성
$result=mysql_query($sql,$db);                //답변 목록 조회 쿼리 실행 후 결과 셋 ‘$result’할당
?>
<html>
<head><title>투표하기</title>
</head>
<body>
 <table align="center" border="0" cellpadding="0" cellspacing="0" width="800">
 <tr>
 <td width="1101">
 <p align="center"><b><font size="3">투표하기</font></b></p>
 <form name="frm" action="./poll_insert.php" method="post">
 <table align="center" border="1" cellpadding="2" width="600">
  <tr>
   <td width="600">  <? echo $title; ?></td>
  </tr>
<?
while($row=mysql_fetch_array($result))            //poll 답변 목록 결과 셋 건수만큼 반복
{
	$id=$row[r_id];                             //답변 테이블의 ‘r_id’ 컬럼값을 ‘$id’ 할당
    $repstr=$row[r_repstr];                  //답변 테이블의 ‘r_repstr’ 컬럼값을 ‘$repstr’할당
	echo "
	<tr>
  	  <td width='600'>  <input type='hidden' name='id' value='$id'>
   	   <input type='radio' name='repstr' value='$repstr'>$repstr</td>
	</tr>
	";
}

mysql_close($db);
?>

<tr>
 <td align=center>
   <input type="submit" name="smt" value="투표/결과"></td>
    <!-- 투표 결과를 서버로 전송하기 위한 전송 단추 표시 -->
</tr>
</table>
</form>

</td>
</tr>
</table>

</body>
</html>
