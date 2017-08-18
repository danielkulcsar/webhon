<?php
include "./poll_con.php.inc";
$offset; $limit;
//사용자가 요청한 페이징 관련 정보들을 ‘$offset, $limit’자동 할당

if(!$offset) { $offset=0; }         //요청된 페이지 번호가 없다면 첫 페이지(0) 조회
$limit=10;                           //페이지당 10건의 내용을 보여 주기

$cnt_sql="select * from poll_tbl order by p_num desc";
//poll 테이블에서 p_num 컬럼값을 역순으로 정렬해서 조회하는 쿼리 생성

$cnt_result=mysql_query($cnt_sql,$db);    //쿼리 실행후 결과를 ‘$cnt_result’ 할당
$count=mysql_num_rows($cnt_result);               //쿼리 결과 건수를 ‘$count’할당
if(!$count) { $count=0; }                      //쿼리 결과 건수가 없으면 ‘0’ 할당

$sql="select * from poll_tbl order by p_num desc limit $offset,$limit";
//사용자가 요청한 페이지에 대한 레코드만 조회하기 위한 limit조건이 포함된 쿼리 생성

$result=mysql_query($sql,$db);                 //쿼리 실행 결과 셋 ‘$result’할당
?>

<html>
<head><title>Poll 목록 보기</title>
</head>
   <body>

  <table align="center" border="0" cellpadding="0" cellspacing="0" width="800">
  <tr>
  <td width="1101"><p align="center"><font size="3"><b>진행중인 Poll</b></font></p>
  <table align="center" border="0" cellpadding="3" cellspacing="0" width="600">
   <tr>
    <td><a href="./admin_poll_1.php"><small>설문 만들기</small></a></td>
   </tr>
 </table>

	<table align="center" border="1" cellpadding="3" cellspacing="0" width="600">
	<tr>
		<td width="790">
			<p><font size="2">▶ 진행중인 Poll 제목</font></p>
		</td>
	</tr>

 <?
while($row=mysql_fetch_array($result)){ 
//쿼리 결과 세 ‘$result’에 존재하는 레코드 수만큼 반복 루프를 실행하면서 현재 진행 중인 설문 항목들을 화면에 <TR> 로 보여줌
	$num=$row[p_num]; $title=$row[p_title];

  echo "
	<tr>
	  <td width='790'>
	   <p><font size='2'>;
       <a href='./poll_read.php?num=$num'>$title</a></font></p>
	  </td>
	</tr>
	";
}

mysql_close($db);
 ?>
  </table>
<table align="center" border="0" cellpadding="3" cellspacing="0" width="600">
<tr>
	<td align=center>
		<small>

<?
$i=($offset/$limit)+1; 
$i=intval(($i-1)/10)*10+1;
if($offset!=0 && $offset>99)
{                                     //페이지 이동에서 최초 페이지‘<<’링크 표시
	$newoffset=(($i-10)-1)*$limit;
	echo "<a href=$PHP_SELF?offset=$newoffset>".
		"<<</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
}
else { echo "<<&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"; }


if($offset!=0)
{ 				//페이지 이동에서 이전 페이지‘이전’링크 표시
	$newoffset=$offset-$limit;
	echo "<a href=$PHP_SELF?offset=$newoffset>이전</a>";
}
else { echo "이전&nbsp;"; }

$pages=intval($count/$limit);
if($count%$limit) { $pages++; }
$loop=0;
  
for($i;$i<=$pages && $loop<10;$i++,$loop++)
{  						//페이지 링크‘[1] [2] [3],...’ 표시
	$newoffset=$limit*($i-1);

	if($offset!=$newoffset)
	{
		echo "<a href=$PHP_SELF?offset=$newoffset>[$i]</a>&nbsp;";
	}
	else
	{
		echo "$i&nbsp;";
	}
}
  if($pages>1
{					       //페이지 이동에서 다음 페이지‘다음’링크 표시
	$last=($offset/$limit)+1;
	if($pages!=$last)
	{
		$newoffset=$offset+$limit;
		echo "<a href=$PHP_SELF?offset=$newoffset>다음</a>";
	}
	else
	{
		echo "다음";
	}
}
else
{
	echo "다음";
}

  //페이지 이동에서 마지막 페이지‘>>’링크 표시
if($pages>1)
{
	$last=($offset/$limit)+1;
	if($pages!=$last && $i<=$pages)
	{
		$newoffset=($i-1)*$limit;
		echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;".
		"<a href=$PHP_SELF?offset=$newoffset>>></a></span>";
	}
	else
	{
		echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;>>";
	}
}
else
{
	echo "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;>>";
}
?>

		</small>
	</td>
</tr>
</table>
		<p>&nbsp;</p>
	</td>
</tr>
</table>

</body>
</html>
