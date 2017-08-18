<html>
<head><title>방명록 쓰기</title>
</head>

<body leftmargin="0" marginwidth="0" topmargin="0" marginheight="0">

<table border="0" cellpadding="0" cellspacing="0" width="700">
    <tr>
        <td width="700">
            <p>&nbsp;</p>
            <p align="center">방 명 록 작성</p>

            <form name="frm" action="./insert.php" method="post">
            <table align="center" border="1" cellpadding="3" cellspacing="0" width="650">
                <tr>
                    <td width="132">
                        <p align="right">작성자 &nbsp;:</p>
                    </td>
                    <td width="512">
                        <p>&nbsp;<input type="text" name="irum" size="20"></p>
                    </td>
                </tr>
                <tr>
                    <td width="132">
                        <p align="right">내 &nbsp;&nbsp;용 &nbsp;:</p>
                    </td>
                    <td width="512">
                        <p>&nbsp;<textarea name="content" rows="10" cols="69"></textarea></p>
                    </td>
                </tr>
                <tr>
                    <td width="642" colspan="2">
                        <p align="center">
			<input type="submit" name="smt" value="작성하기">
			&nbsp;&nbsp;&nbsp;
			<input type="reset" name="rst" value="초기화"></p>
                    </td>
                </tr>
            </table>
            </form>

            <p>&nbsp;</p>
            <p>&nbsp;</p>
        </td>
    </tr>
</table>
</body>
</html>
