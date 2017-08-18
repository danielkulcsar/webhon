<?
$receiver;
$content=str_replace("\n","<br>",$content);

$mail01_str="
	<table align='center' border='0' cellpadding='0' cellspacing='0' width='500'>
	<tr>
		<td width='500'>
		<object classid='clsid:D27CDB6E-AE6D-11cf-96B8-444553540000' codebase='http://active.macromedia.com/flash4/cabs/swflash.cab#version=4,0,0,0' width='500' height='100'>
		<param name='movie' value='http://127.0.0.1/7-7/letter01.swf'>
		<param name='play' value='true'>
		<param name='loop' value='true'>
		<param name='quality' value='high'>
		<embed src='http://127.0.0.1/7-7/letter01.swf' play='true' loop='true' quality='high' pluginspage='http://www.macromedia.com/shockwave/download/index.cgi?P1_Prod_Version=ShockwaveFlash' width='500' height='100'></embed></object>
		</td>
	</tr>
	<tr>
		<td width='500' bgcolor='182277' style='padding:10;'>
			<p><font color=white>$content</font></p>
		</td>
	</tr>
	</table>";

$mail02_str="
	<table align='center' border='0' cellpadding='0' cellspacing='0' width='500'>
	<tr>
		<td width='500'>
		<object classid='clsid:D27CDB6E-AE6D-11cf-96B8-444553540000' codebase='http://active.macromedia.com/flash4/cabs/swflash.cab#version=4,0,0,0' width='500' height='100'>
		<param name='movie' value='http://127.0.0.1/7-7/letter02.swf'>
		<param name='play' value='true'>
		<param name='loop' value='true'>
		<param name='quality' value='high'>
		<embed src='http://127.0.0.1/7-7/letter02.swf' play='true' loop='true' quality='high' pluginspage='http://www.macromedia.com/shockwave/download/index.cgi?P1_Prod_Version=ShockwaveFlash' width='500' height='100'></embed></object>
		</td>
	</tr>
	<tr>
		<td width='500' bgcolor='6FCAF9' style='padding:10;'>
			<p>$content</p>
		</td>
	</tr>
	</table>";

function email($ADDR)
{
	return (ereg('^[-!#$%&\'*+\\./0-9=?A-Z^_a-z{|}~]+'.
	'@'.
	'[-!#$%&\'*+\\/0-9=?A-Z^_a-z{|}~]+\.'.
	'[-!#$%&\'*+\\./0-9=?A-Z^_a-z{|}~]+$',
	$ADDR));
}

$a=email("$receiver");

if(!$a)
{
	echo "
	<script>
		alert('올바르지 않은 이메일주소 형식입니다.');
		history.go(-1);
	</script>";
	exit;
}

if($back_cover==1)
{
	$re=mail(
			"$receiver", 
			"E-Card 보내기",
			"$mail01_str",
			"From: [CodMedia] <webmaster@codmedia.com>\n".
			"Content-Type: text/html;charset=EUC-KR\n"
			);
} else {
	$re=mail(
			"$receiver", 
			"E-Card 보내기",
			"$mail02_str",
			"From: [CodMedia] <webmaster@codmedia.com>\n".
			"Content-Type: text/html;charset=EUC-KR\n"
			);
}

if($re)
 $str="E-Card 메일 보내기 성공";
else
 $str="E-Card 메일 보내기 실패";
?>

<html>
<head><title>Ecard 보내기</title>
</head>

<body>


<table align='center' border='1' width='800'>
<tr>
	<td width='1101'>


	<p>&nbsp;</p>
	<p align='center'><b><? echo $str; ?></b></p>
	
<?
if($back_cover==1)
	echo $mail01_str;
else
	echo $mail02_str;
?>

	<p>&nbsp;</p>


		</td>
    </tr>
</table>


</body>
</html>