<html>
<head><title>E-Card 보내기</title>
</head>

<body>

<p align="center">&nbsp;</p>
<p align="center"><b>E-Card 보내기</b></p>

<table border="1" align="center" width="600">
<tr>
<td width="600">

	<p>&nbsp;</p>
	
	<form name="frm" action="./ecard_form.php" method="post">
	<table align="center" border="0" cellpadding="0" cellspacing="0" width="300">
	<tr>
		<td width="150">
			<p align="center"><img src="top01.jpg" width="100" height="100" border="0"></p>
		</td>
		<td width="150">
			<p align="center"><img src="top02.jpg" width="100" height="100" border="0"></p>
		</td>
	</tr>
	<tr>
		<td width="150">
			<p align="center"><input type="radio" name="back_cover" value="1" checked>편지지1</p>
		</td>
		<td width="150">
			<p align="center"><input type="radio" name="back_cover" value="2">편지지2</p>
		</td>
	</tr>
	<tr>
		<td width="300" colspan="2">
			<p>&nbsp;</p>
		</td>
	</tr>
	<tr>
		<td width="300" height="39" colspan="2">
			<p align="center"><input type="submit" name="smt" value="선택하기"></p>
		</td>
	</tr>
	</table>
	</form>

        </td>
    </tr>
</table>

</body>
</html>