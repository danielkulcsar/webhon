<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
 <h3> Request주요 메소드의 리턴값  출력 </h3>
<font size="4">
JSP Request Method: <%=request.getMethod() %>
<br>
Request URI: <%= request.getRequestURI() %>
<br>
Request Protocol: <%= request.getProtocol() %>
<br>
Servlet path: <%= request.getServletPath() %>
<br>
Path info: <%=request.getPathInfo() %>
<br>
Query string: <%=request.getQueryString() %>
<br>
Content length: <%= request.getContentLength() %>
<br>
Content type: <%=request.getContentType() %>
<br>
Server name: <%= request.getServerName() %>
<br>
Server port: <%= request.getServerPort() %>
<br>
Remote user: <%= request.getRemoteUser() %>
<br>
Remote address: <%= request.getRemoteAddr() %>
<br>
Remote host: <%= request.getRemoteHost() %>
Locale: <%= request.getLocale() %>
<hr>
The browser you are using is <%=request.getHeader("User-Agent") %>
<hr>
</font>
</body>
</html>