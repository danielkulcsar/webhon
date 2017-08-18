<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
서블릿 초기 설정 정보 = <%=pageContext.getServletConfig()%> <br>
<hr>

웹 어플리케이션의 이름과 버전  = <%= application.getServerInfo() %> <br>
<hr>
웹 어플리케이션 폴더와  로컬시스템 경로= <%= application.getRealPath("/")%><br>
<hr>
        웹 어플리케이션 Context =<%=application.getServletContextName() %>
      
</body>
</html>