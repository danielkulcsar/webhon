<?php

$url;             			   //사용자가 선택한 URL이 자동으로 ‘$url’변수 할당

if(!$url) { }                           //URL이 비어있으면 아무런 작업도 하지 않음
else {	                      
	$rst=shell_exec("ping $url"); 
 //URL 주소가 있는 경우에는 shell을 통해서 ping 명령을 실행하여 ‘$rst’ 할당

	$rst=str_replace("Reply","<br>Reply",$rst);
	$rst=str_replace("Ping statistics","<br><br>Ping statistics",$rst);
	$rst=str_replace("Packets","<br>&nbsp;&nbsp;&nbsp;Packets",$rst);
	$rst=str_replace("Approximate","<br>Approximate",$rst);
	$rst=str_replace("Minimum","<br>&nbsp;&nbsp;&nbsp;Minimum",$rst);
	$rst=str_replace("Request","<br>Request",$rst);
   //ping 명령의 결과를 보기 편하도록 문자열 변환을 실행함
}
?>

<html>
<head><title></title></head>

<body>
  <form name='frm' action='ping_test.php' method='post'>
   <!-- ping 명령을 실행할 url을 입력하는 form 생성 -->

<table border='1' width='500' align='center'>
<tr><td colspan='2' align='center'>Ping Test</td>  </tr>
<tr><td width='100' align='center'>Target</td>
    <td width='400'><input type='text' name='url' size='30' style='width:400'></td>
                      <!-- ping을 실행할 ip 주소 입력상자는 ‘$url’자동 할당 -->
</tr>
<tr><td colspan='2' align='center'>
    <input type='submit' name='smt' value='Test'>
    <input type='reset' name='rst' value='Cancel'></td>
      <!-- 입력된 정보를 서버로 전송하는 [Test], [Cancel] 단추 생성 -->

</tr>
</table>
</form>
<table border='1' width='500' align='center'>
<tr>
	<td>
	<?
	 echo $rst."<p>";                                    //shell에서 ping 명령을 실행한 결과 출력
	if($rst) {
  	  if(strpos($rst,"Received = 0")){
   	    echo "[$url] : 서버가 다운되었습니다.";
            //ping 대상 서버로부터 아무런 응답이 오지 않을 때 메시지 출력

          }else if(strpos($rst,"could not find")){
 	    echo "[$url] : 서버명을 다시 체크해 주세요.";
           //서버를 찾을 수 없을때 메시지 출력
   	   }else{ 
 	   echo "[$url] : 서버가 운영 중 입니다.";     //정상적으로 운영되었을 때 메시지 출력
          }
	    } else {
	     echo "&nbsp;";
	   }
	?>
	</td>
</tr>
</table>

</body>
</html>
