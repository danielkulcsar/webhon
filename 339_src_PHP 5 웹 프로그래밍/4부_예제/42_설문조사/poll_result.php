<?php

include "./poll_con.php.inc";

$id; $title;

$t_sql="select * from poll_tbl where p_num=$id";
//사용자가 선택한 투표 정보를 조회하는 쿼리 생성

$t_ret=mysql_query($t_sql,$db);     //조회 쿼리를 실행 후 결과 셋을 ‘$t_ret’할당
$t_row=mysql_fetch_array($t_ret);      //결과 셑의 첫 번째 레코드를 ‘$t_row’할당

$title=$t_row[p_title];           //투표 테이블의 ‘p_title’ 컬럼값 ‘$title’할당

$sql="select * from poll_ret where r_id=$id";
//투표 답변 테이블에서 답변 내용 목록을 조회하는 쿼리 생성

$result=mysql_query($sql,$db);             //쿼리 실행 후 결과 셋을 ‘$resul’할당
?>
<html>
<head><title>Poll 결과 보기</title>
</head>
<body>

<table align="center" border="0" cellpadding="0" cellspacing="0" width="800">
<tr>
<td width="1101">
  <p align="center"><b><font size="3">Poll 결과 보기</font></b></p>
 <table align="center" border="1" cellpadding="2" width="600">
 <tr>
  <td width="600"> <? echo $title; ?> </td>
 </tr>
 <tr>
  <td>
   <table border=0 width=500 align=center>
   <?
   while($row=mysql_fetch_array($result))
   //답변 목록의 결과 레코드를 반복하면서 ‘$row’ 할당

{ 
$id=$row[r_id]; $repstr=$row[r_repstr]; $vote=$row[r_vote];
//답변 테이블의 ‘r_id, r_repstr, r_vote’ 컬럼 값을 ‘$id, $repstr, $vote’ 할당
echo "
  <tr>
   <td width=250>$repstr</td>
   <td width=250>$vote 명</td>
  </tr>
  ";
}

mysql_close($db);
?>
 </table>
</td>
</tr>
<tr>
  <td><p align=center>현재 날짜 : <? echo Date("Y-m-d"); ?></p></td>
</tr>
<tr><td><p align=center><a href="./list.php">목록</a></p></td>
</tr>
</table>

</td>
</tr>
</table>

</body>
</html>


