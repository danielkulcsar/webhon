<?php
// 반복되어질 영역에 while 함수를 이용하여 처리
include("connect.php");
	if ($data_type) {
		$sub_query = "and data_type = '".$data_type."'" ;}
		$query = mysql_query("select * from pds_game where allow =1 ".$sub_query." order by upload_timedesc limit 0, 100",$connect);
		while($row = @mysql_fetch_assoc($query)) {
?>
--중간 생략--
<tr>
<td class="hidden-md hidden-sm hidden-xs"><?=$row['num']?></td>
<td class="hidden-md hidden-sm hidden-xs">
<a href="game_board_list.php?game_code=<?=$row['game_code']?>">
<?=$row['game_name']?></a></td>
	<td><a href="pds_board_view.php?num=<?=$row['num']?>"><?=$row['subject']?></a></td>
	<td class="hidden-md hidden-sm hidden-xs"><?=$row['mb_id']?></td>
	<td class="hidden-md hidden-sm hidden-xs"><?=$row['count_total']?></td>
	<td class="hidden-md hidden-sm hidden-xs"><?=substr($row['upload_time'],2,8);?></td>
	<td><?if($row['file_name']){?><a href="#"><i class="fa fa-download"></i></a><?}?>
</td>
</tr>
--하단 생략--