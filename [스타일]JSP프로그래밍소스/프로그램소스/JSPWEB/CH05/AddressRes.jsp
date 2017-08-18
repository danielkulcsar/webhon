<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<jsp:useBean id="Alist" class="JSPWEB.CH05.AddressList"/>
<jsp:setProperty  name="Alist" property="*"/>

   <jsp:getProperty name="Alist" property="name"/> 님 반갑습니다 <br>
<jsp:getProperty name="Alist" property="name"/> 님 의 주소는   
<jsp:getProperty name="Alist" property="addr"/>   이고 <br>전화번호는
<jsp:getProperty name="Alist" property="tel"/>  입니다.
</body>
</html>