 <?php
   include "./poll_con.php.inc";
   $title; $rep; $pass, $y; $m; $d; $y1; $m1; $d1;
   //입력한 제목, 답변수, 시작날짜, 종료날짜가 자동으로 각 변수 할당

   $sdat=$y."-".$m."-".$d;                                    //시작일자 조립
   $edat=$y1."-".$m1."-".$d1;                                 //종료일자 조립

  $sql="insert into poll_tbl (".
	"p_title,p_rep,p_sdat,p_edat,p_pass".
	") values (".
	"'$title',$rep,'$sdat','$edat',password('$pass')".
	")";
    //테이블에 삽입하기 위해 쿼리 생성, 비밀번호는 password() 암호화

  $result=mysql_query($sql,$db);          //insert 쿼리 실행 후 결과 ‘$result’ 할당
  $id=mysql_insert_id(); 
  //poll_tbl 테이블의 현재 등록된 레코드의 ‘p_num’ 컬럼 값을 가져 옴
  mysql_close($db); 
 ?>
  <html>
   <head><title>폴 관리자 페이지_2</title>
  </head>
 <body>

  <table align="center" border="0" cellpadding="0" cellspacing="0" width="800">
    <tr>
      <td width="1101">
        <p>&nbsp;</p>
        <p align="center"><font size="3">
        <b>폴 관리자 페이지 2</b></font></p>
  <form name="frm" action="./insert.php" method="post">
   <!-- 답변 항목들을 입력받기 위한 폼 출력 -->
  <table align="center" border="1" cellpadding="2" width="600">
   <tr>
    <td width="150"><p align="right"><font size="2">제목 :</font></p></td>
    <td width="450"><p><font size="2"><? echo $title; ?>
    <input type='hidden' name='id' value='<? echo $id; ?>'></font></p></td>
    <!-- poll 의 id를 hidden으로 전송하기 위한 값 -->
   </tr>
   <tr>
    <td width="150"><p align="right"><font size="2">답변 :</font></p></td>
    <td width="450"><p><font size="2">
      <?
         for($i=1;$i<=$rep;$i++)
	 {
         echo "&nbsp;답변".$i." : <input type='text' name='repstr$i' size='50'><br>";
          //이전 페이지에서 입력한 답변 수만큼 답변 입력상자 출력
  	 }
     ?>
        <input type='hidden' name='rep' value='<? echo $rep; ?>'></font></p>
      </td>
   </tr>
 <tr>
     <td width="150">
         <p align="right"><font size="2">날짜 :</font></p>
     </td>
     <td width="450">
         <p><font size="2">
          <? echo $sdat; ?> &nbsp;~ &nbsp;<? echo $edat; ?></font></p>
     </td>
 </tr>
 <tr>
     <td width="150">
         <p align="right"><font size="2">비밀번호 :</font></p>
     </td>
     <td width="450">
         <p><font size="2">&nbsp;<? echo $pass; ?></font></p>
     </td>
 </tr>
     <tr>
     <td width="588" colspan="2">
     <p align="right"><font size="2">
        <input type="submit" name="smt" value="완료"></font></p>
        <!-- 입력된 값을 서버로 전송하기 위한 [완료] 단추 생성 -->
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
