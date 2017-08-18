<? $back_cover; ?>

<body>


<table border=1 width=700 align=center>
<tr>
	<td>

	<p align=center>&nbsp;</p>

	<table border=0 width=500 cellspacing=0 cellpadding=0 align=center>
	<tr>
		<td>
		<? if($back_cover==1) { ?>
		<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://active.macromedia.com/flash4/cabs/swflash.cab#version=4,0,0,0" width="500" height="100">
		<param name="movie" value="./letter01.swf">
		<param name="play" value="true">
		<param name="loop" value="true">
		<param name="quality" value="high">
		<embed src="./letter01.swf" play="true" loop="true" quality="high" pluginspage="http://www.macromedia.com/shockwave/download/index.cgi?P1_Prod_Version=ShockwaveFlash" width="500" height="100"></embed></object>
		<? } else { ?>
		<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://active.macromedia.com/flash4/cabs/swflash.cab#version=4,0,0,0" width="500" height="100">
		<param name="movie" value="./letter02.swf">
		<param name="play" value="true">
		<param name="loop" value="true">
		<param name="quality" value="high">
		<embed src="./letter02.swf" play="true" loop="true" quality="high" pluginspage="http://www.macromedia.com/shockwave/download/index.cgi?P1_Prod_Version=ShockwaveFlash" width="500" height="100"></embed></object>
		<? } ?>
		</td>
	</tr>
	</table><br>

	<form name="frm" action="./ecard_send.php" method="post">
	<table border=1 width=500 cellspacing=0 cellpadding=2 align=center>
	<tr>
		<td width=150 align=right>받는 사람 : </td>
		<td>&nbsp;<input type="text" name="receiver" size="20"></td>
	</tr>
	<tr>
		<td width=150 align=right>내     용 : </td>
		<td colspan=2>&nbsp;<textarea name="content" style="border-style:none; width:350px;" rows="5" cols="70"></textarea></td>
	</tr>
	<tr>
		<td colspan=2 align=center>
			<input type="hidden" name="back_cover" value="<? echo $back_cover; ?>">
			<input type="submit" name="smt" value="보내기"</td>
	</tr>
	</table>
	</form>

	<p align=center>&nbsp;</p>

	</td>
</tr>
</table>


</body>
</html>