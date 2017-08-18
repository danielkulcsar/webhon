<?
include "mu_db.php.inc";

$fp=fopen("./music.asx","w");		//파일에 쓰기 위해 연다.
flock($fp,2);						//파일 잠금
fwrite($fp,"");						//music.asx파일 초기화

if($selNum)
{
$sel_sql="select * from music where m_num=$selNum limit 1";
$sel_rst=mysql_query($sel_sql,$db);
$sel_row=mysql_fetch_array($sel_rst);

$mu_path="http://127.0.0.1/7-8/music/".$sel_row[m_fname]."\n";

$fp=fopen("./music.asx","a");		//파일에 쓰기 위해 연다.
flock($fp,2);						//파일 잠금
fwrite($fp,$mu_path);				//곡들의 경로를 music.asx파일에 쓴다

	$sql="select * from music where m_num<>$selNum order by m_num desc";
} else {
	$sql="select * from music order by m_num desc";
}

$result=mysql_query($sql,$db);

while($row=mysql_fetch_array($result))
{
	$mu_path="http://127.0.0.1/7-8/music/".$row[m_fname]."\n";

	$fp=fopen("./music.asx","a");		//파일에 쓰기 위해 연다.
	flock($fp,2);						//파일 잠금
	fwrite($fp,$mu_path);				//곡들의 경로를 music.asx파일에 쓴다
}

fclose($fp); //파일을 닫는다.
?>

<html>
<head><title>미디어플레이어</title>
<script language="javascript">
<!--
function setPos(p) {
	player.currentPosition+=p; }
function setVol(flag)
{
	if(flag=='+') player.volume+=100;
	else player.volume-=100;
}
function mute(state)
{
	if(state) player.mute = true;
	else player.mute = false;
}
//-->
</script>
</head>

<body>

<table border="1" align="center" width="400" height="70" cellpadding="20">
<tr>
	<td align="center" width="400" height="70">

			<table border="0" width="400" cellspacing="0" cellpadding="0">
			<tr>
				<td width="293" height="50" background="./images/back_01.gif" align="center">
<object id="player" classid="clsid:22D6F312-B0F6-11D0-94AB-0080C74C7E95" codebase="http://activex.microsoft.com/activex/controls/mplayer/en/nsmp2inf.cab#Version=6,4,5,715" width="250" height="22">
<param name="FileName" value="./music.asx">
<param name="PlayCount" value="0">
<param name="ShowControls" value="0">
<param name="ShowStatusBar" value="1">
<embed src="./music.asx" pluginspage="http://www.microsoft.com/Windows/Downloads/Contents/Products/MediaPlayer/" width="250" height="22"></embed></object>
				</td>
				<td width="107" height="50">
					<img src="./images/back_02.gif" usemap="#ImageMap1" border="0"></td>
			</tr>
			<tr>
				<td align="center" colspan="2">
<br><input type=button value="▶" onClick="javascript:player.play();">
&nbsp;<input type=button value="||" onClick="javascript:player.pause();">
&nbsp;<input type=button value="■" onClick="javascript:player.stop();">
&nbsp;<input type=button value="|◀" onClick="javascript:player.previous();">
&nbsp;<input type=button value="◀◀" onClick="setPos(-10)">
&nbsp;<input type=button value="▶▶" onClick="setPos(10)">
&nbsp;<input type=button value="▶|" onClick="javascript:player.next();"><p>
<input type=button value="Vol(-)" onClick="setVol('-')">
&nbsp;<input type=button value="Vol(+)" onClick="setVol('+')">
&nbsp;<input type=checkbox onClick="mute(this.checked)">mute<br>
				</td>
			</tr>
			</table>

	</td>
</tr>
</table>

<map name="ImageMap1">
<area shape="rect" coords="1, 9, 38, 39" href="javascript:player.play();">
<area shape="rect" coords="44, 10, 85, 38" href="javascript:player.stop();">
</map></body>
</html>