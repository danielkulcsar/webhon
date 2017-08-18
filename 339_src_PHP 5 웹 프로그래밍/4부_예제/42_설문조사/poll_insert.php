<?php
include "./poll_con.php.inc";

$id; $repstr;                  //사용자가 선택한 답변과 답변 내용이 ‘$id, $repstr’할당

if($usr_id)
{ //현재 사용자가 로그인 했을 때 실행
	$l_sql="select * from poll_usr where poll_usr='$usr_id' and poll_num=$id";
   //현재 사용자가 이 투표에 참여했었는지를 조회하기 위한 쿼리 생성

	$l_ret=mysql_query($l_sql,$db);          //쿼리 실행 후 결과 셋을 ‘$l_ret’ 할당
	$list=mysql_num_rows($l_ret);                   //결과 셋의 건수를 ‘$list’ 할당

	if($list) 
	{                                     //한번이라도 이 투표에 참여한 사용자일 때 실행
		echo "<script>
			alert('투표하였습니다.');
			location.href='./poll_result.php?id=$id';
		</script>";
		mysql_close($db);
           exit;
	}
}

if(!$repstr)                                  
{                                               //사용자가 답변을 선택하지 않을 때 실행
	echo "<script>alert('투표하지 않고 결과 페이지로 이동합니다');</script>";
	echo "<meta http-equiv='refresh' content='0;url=./poll_result.php?id=$id'>";
	mysql_close($db);
    exit;
}

$sql="update poll_ret set r_vote=r_vote+1 where r_id=$id and r_repstr='$repstr'";
//사용자가 선택한 답변을 데이터베이스에 업데이트 하는 쿼리 생성

$result=mysql_query($sql,$db);
//업데이트 쿼리 실행 후 결과값을 $result에 할당

mysql_close($db);

echo "<meta http-equiv='refresh' content='0;url=./poll_usr.php?id=$id'>";
?>
