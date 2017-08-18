<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ page import = "java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
좋아 하는 계절을 선택한 과일은 
<%
 List style = (List)request.getAttribute("data");
Iterator it = style.iterator();

while(it.hasNext()) {
	out.println(it.next());
}
%>
입니다.
</body>
</html>