<html>
<head><title>메일 보내기</title>
</head>

<body>


<p>&nbsp;</p>
<p align="center"><font size="3"><b>메일 보내기</b></font></p>

<form name="frm" action="./mail_send.php" method="post">
<table align="center" border="1" cellpadding="2" cellspacing="0" width="500">
    <tr>
        <td width="100">
            <p align="right">받는 사람 :</p>
        </td>
        <td width="400">
            <p>&nbsp;<input type="text" name="receiver" size="20"></p>
        </td>
    </tr>
    <tr>
        <td width="100">
            <p align="right">제목 :</p>
        </td>
        <td width="400">
            <p>&nbsp;<input type="text" name="subject" size="30"></p>
        </td>
    </tr>
    <tr>
        <td width="500" colspan="2">
            <p align="center">내용</p>
        </td>
    </tr>
    <tr>
        <td width="500" colspan="2">
            <p align="center"><textarea name="content" rows="10" cols="65"></textarea></p>
        </td>
    </tr>
    <tr>
        <td width="500" colspan="2">
            <p align=center><input type="submit" name="smt" value="보내기">
			&nbsp;&nbsp;&nbsp;
			<input type="reset" name="rst" value="취소"></p>
        </td>
    </tr>
</table>
</form>

</body>
</html>
