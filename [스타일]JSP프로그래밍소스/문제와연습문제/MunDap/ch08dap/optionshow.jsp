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
���� �ϴ� ������ ������ ������ 
<%
 List style = (List)request.getAttribute("data");
Iterator it = style.iterator();

while(it.hasNext()) {
	out.println(it.next());
}
%>
�Դϴ�.
</body>
</html>