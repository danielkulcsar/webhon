<?php
	@extract($_GET); 
	@extract($_POST); 
	@extract($_SERVER);

	$host="localhost";
	$usr="master";
	$pwd="123";

	$connect=MySQL_connect($host,$usr,$pwd) or die("서버 접속 에러");
 	$result = mysql_select_db("guestboard",$connect);

	if($result == 1) {
		$tb_name = mysql_list_tables("guestboard");
		$tb_count = mysql_num_rows($tb_name);
		for($k = 0; $k < $tb_count; $k++){
			if(mysql_tablename($tb_name, $k) == "memo_tbl") {
				$flag = "OK";
 				break;
 			} 
 		}
		if($flag != "OK") {
			$query = "create table memo_tbl (
 				num int(4) NOT NULL auto_increment,
 				name varchar(20),
 				memo text,
 				ndate int(12),
 				PRIMARY KEY(num)
			)";
			$result = mysql_query($query, $connect) or die("Error : ".mysql_error());               
 		}
 	} else {
 		echo "MySQL 서버와 함께 작업할 데이터베이스에 접속 실패";
		exit;
	}
	$listnum=10;
	if(!$pagenum) {
		$pagenum = 1;
	}
	$query = "select count(num) from memo_tbl";
	$result = mysql_query($query);
	$total = mysql_fetch_array($result);
	$all_data = $total[0];
	$pagesu = ceil($all_data/$listnum);
	$start = $listnum*($pagenum - 1);
	$pagestart=1; 
	$pageend=$pagesu;
	$query = "select * from memo_tbl order by num desc limit $start,$listnum"; 
	$result = mysql_query($query,$connect);
	$rtotal = mysql_num_rows($result);
	echo "<center>
		총 <b> $all_data </b> 개의 메모 
		<table cellspacing=0 cellpadding=4 border=1 bordercolor=#ffffff> 
		<tr align=center bgcolor=#dcdcdc>
    	 	    	<td width=50>번호</td>
			<td width=100>이름</td>	
			<td width=300>메모</td>
			<td width=100>시간</td>
		</tr>"; 
	for($i=0;$i<$rtotal;$i++){
		$data=mysql_fetch_array($result); 
		$data[ndate]=date('y'.'년'.'m'.'월'.'d'.'일',$data[ndate]);
		$data[name]=stripslashes($data[name]); 
		$data[memo]=stripslashes($data[memo]); 
		echo "
			<tr bgcolor=#eeeeee>
				<td align=center>$data[num]</td>
				<td align=center>$data[name]</td>
				<td align=left>$data[memo]</td>
				<td align=center>$data[ndate]</td>
			</tr>";
	}
	echo "<tr>
			<td colspan=4 align=center>";
	if($pagenum > 1){ 
		echo"[<a href=memolist.php?pagenum=$pagestart>시작페이지</a>] "; 
	} 
	if($pagenum > 1){ 
		$prevpage=$pagenum-1; 
		echo"[<a href=memolist.php?pagenum=$prevpage>앞</a>] "; 
	}
	for($i=$pagestart;$i <= $pageend;$i++){ 
		if($i == $pagenum){
			echo "$i ";
		}else{
			echo "[<a href=memolist.php?pagenum=$i>$i</a>] ";
		} 
	} 
	if($pagenum < $pageend){ 
		$nextpage=$pagenum+1; 
		echo"[<a href=memolist.php?pagenum=$nextpage>뒤</a>] "; 
	} 
	if($pagenum < $pageend){
		echo"[<a href=memolist.php?pagenum=$pageend>마지막페이지</a>] ";
	} 
	echo"</td></tr></table>";
	echo("	
		<form method=post action=memowrite.php> 
			이름 <input type=text name=name size=10 maxlength=10>   
			메모 <input type=text name=memo size=30 maxlength=50> 
			<input type=submit value=입력> 
		</form> </center>
	");
 ?>
