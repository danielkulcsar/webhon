<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<h1>EL�� ���尴ü</h1>
    <table border="1">
     <tr>
        <td><b>���尴ü</b></td>
        <td><b>EL Expression</b></td>
        <td><b>��� </b></td>
      </tr>    
<tr>
        <td>���尴ü[header]</td>
        <td>\${header["user-agent"]}</td>
        <td>${header["user-agent"]}</td>
      </tr> 
      <tr>
      <td>���尴ü[header.cookie]</td>
       <td>\${header['cookie']} </td>
      <td>    ${header['cookie']}  </td>

      </tr>
      <tr>
      <td>���尴ü[header.host]</td>
        <td>\${header["host"]}</td>
      <td>     ${header['host']}  </td>
      </tr>
      </table>
</body>
</html>