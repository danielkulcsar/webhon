<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@page errorPage="ErrorRes.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>ErrorInput</title>
</head>
<body>
 <% String name=request.getParameter("name");
   if(name.equals("홍길동"))
   {
	    name= "이름이 입력되었습니다";
    }
  %>

    <h4> <%=name %> </h4>

</body>
</html>