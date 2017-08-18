<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" errorPage="error.jsp" %>
     <%@ page import="JSPWEB.CH08.BOARD.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link href="boardcss.css" rel="stylesheet" type="text/css">
<title>Insert title here</title>
</head>
<body>
<%

BOARDBean  beans=(BOARDBean)request.getAttribute("selectboard");

%>

<form  method=post action="update.go?action=update">

<input type ="hidden" name="no" value='<%=beans.getNo() %>'>

<center>
<H2>방명록 수정과 삭제를 선택해주세요  </H2>
<HR>
<table width="400" border="1" cellspacing="0" cellpadding="0"   align="center">

  <tr>
    <td>이름</td>	
    <td><input type="text" name="name" size="20"  value="<%=beans.getName()%>"></td> 
  </tr>
  <tr>
    <td>email</td> 
    <td><input type="text" name="email" size="20" value="<%=beans.getEmail() %>"></td>
  </tr>
    <tr>
    <td >homepage</td>
    <td><input type="text" name="homepage" size="20"value="<%=beans.getHomepage()%>"></td>
  </tr>
    <tr>
        <td width="70"  >Contents</td>
        <td align="center"><textarea rows="5" name="contents" cols="40" ><%=beans.getContents() %></textarea></td>
  </tr>
  <tr>
    <td colspan="2" align="center">
    <input type="submit" value="수정" >
    <input type="reset" value="취소"  onclick="javascript:history.back();" >
    <input type="button" value="삭제" onclick="javascript:window.location='delete.go?action=delete&no=<%=beans.getNo()%>'">  
    
    </td>
</tr>
</table>
</center>
</form>
</body>
</html>
