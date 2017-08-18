 <?php
  include "./poll_con.php.inc";

  $id; $rep;               //입력된 답변 내용들이 ‘$id, $rep’자동 할당

 for($i=1;$i<=$rep;$i++){
   $repstr="repstr".$i;
    //답변의 내용을 ‘$rep’ 개수만큼 반복해서 동적으로 변수값 가져오기
  
  if(!${$repstr}) { 
     echo "
	   <script>
	     alert('답변을 작성하세요');       //답변 내용이 없으면 경고 메시지 표시
	     history.go(-1);                     //메시지 출력 후 이전페이지 이동
	   </script>
	  ";
    mysql_close($db); 
    exit; 
  }

	$sql="insert into poll_ret (".
		"r_id,r_repstr,r_vote".
		") values (".
		"$id,'${$repstr}',0".
		")";
      //답변 테이블에 답변 내용을 insert 쿼리 생성
    $result=mysql_query($sql,$db);         //쿼리 실행 후 결과 $result 변수 할당
  }

  mysql_close($db);

  echo " <meta http-equiv='refresh' content='0; url=./list.php'>";
?>
