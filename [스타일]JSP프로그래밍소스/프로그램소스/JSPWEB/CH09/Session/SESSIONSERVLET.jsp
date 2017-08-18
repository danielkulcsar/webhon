<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>ServletTest</title>
</head>
<body>
 <a href=' <%= request.getContextPath()+"/SESSIONLOGIN" %>'>
       서블릿으로 맵핑된 세션을  테스트 합니다  </a>
</body>
</html>