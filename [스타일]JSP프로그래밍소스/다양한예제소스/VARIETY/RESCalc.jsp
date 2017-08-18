<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<HEAD>
</HEAD>
<BODY>
<%@ page language="java" %>

<jsp:useBean id="cm" scope="session" class="beans.CmCalc" />

<jsp:setProperty name="cm" property="*" />

입력하신 금액은 

  <jsp:getProperty name="cm" property="won" />달러 이며 <BR>
계산된 한화는 
<jsp:getProperty name="cm" property="calc" />원 입니다.
</BODY>
</html>


