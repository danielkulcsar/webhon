<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page isErrorPage="true" %>
<% response.setContentType("text/html"); %>
<html>
<head>
<title>���� �޽���</title>
<link href="boardlist.css" rel="stylesheet" type="text/css">
</head>

<body>

<div align="center">
  <table width="50%" border="0" cellpadding="0" cellspacing="0">
    <tr>
      <td class="header" align="center">���Ͽ��������� �Դϴ�</td>
    </tr>
  </table>
 
  <table  board=1 width="50%" border="0" cellspacing="0" cellpadding="0">
   
    <tr>
      <td height="60" align="center" class="content">
            ���� �ý����� ������ ���ؼ� ������ �߰ߵǾ����ϴ�<br> 
         <br>
       </td>
       </tr>
       <tr>
      <td height="60" align="center" class="content" bgcolor="pink">  
            ���� �����Դϴ�. �Ʒ��� �����ϴ� <br><br>
	  <%= new String(exception.toString().getBytes("euc-kr"), "8859_1") %>
	  </td>
    </tr>
    <tr>
      <td align="center" class="content">
	  <a href="javascript:history.back()"><b>click</b></a></td>
    </tr>
  </table>
</div>
</body>
</html>
