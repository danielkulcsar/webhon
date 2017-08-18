<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<h1>ELÀÇ ³»Àå°´Ã¼</h1>
    <table border="1">
     <tr>
        <td><b>³»Àå°´Ã¼</b></td>
        <td><b>EL Expression</b></td>
        <td><b>°á°ú </b></td>
      </tr>    
<tr>
        <td>³»Àå°´Ã¼[header]</td>
        <td>\${header["user-agent"]}</td>
        <td>${header["user-agent"]}</td>
      </tr> 
      <tr>
      <td>³»Àå°´Ã¼[header.cookie]</td>
       <td>\${header['cookie']} </td>
      <td>    ${header['cookie']}  </td>

      </tr>
      <tr>
      <td>³»Àå°´Ã¼[header.host]</td>
        <td>\${header["host"]}</td>
      <td>     ${header['host']}  </td>
      </tr>
      </table>
</body>
</html>