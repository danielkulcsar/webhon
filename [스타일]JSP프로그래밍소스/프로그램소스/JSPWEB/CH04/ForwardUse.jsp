<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<FORM  method='GET'>
        
        <%= "현제 페이지는  ForwardUse.jsp 입니다" %>
	         <%
         
      pageContext.forward("ForwardOne.jsp");

		%>
	 <%= "현제 페이지는  ForwardOne.jsp를 수행한후의 ForwardUse.jsp 입니다" %>  
 </FORM>
</body>
</html>