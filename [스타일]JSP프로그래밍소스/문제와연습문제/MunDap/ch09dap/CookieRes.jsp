<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ page import="javax.servlet.http.Cookie" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>

<h1>Cookies</h1>

<%
  Cookie[] allCookies = request.getCookies();
  Cookie ourCookie = null;
  if (allCookies!=null)
  {
    for (int i=0; i<allCookies.length; i++)
    {
      if (allCookies[i].getName().equals("TestCookie"))
        {
          ourCookie = allCookies[i];
        }
    }
  }

  if (ourCookie == null)
  {
    Cookie cookie = new Cookie("TestCookie", "HI");
     cookie.setMaxAge(60*3);
      
    cookie.setComment("쿠키테스트입니다");
    response.addCookie(cookie);
    
%>
   쿠키정보를 알고 싶다면 <br>
웹브라우저를 새로고침 해보세요
    
<%
  }
  else
  {
%>
   클라이언트의 정보를 가진 쿠키값을 출력합니다 
    <br>Version: <%=ourCookie.getVersion() %>
    <br>Name: <%=ourCookie.getName() %>
    <br>Value: <%=ourCookie.getValue() %>
    <br>MaxAge: <%=ourCookie.getMaxAge() %>
    <br>Domain:  <%= ourCookie.getDomain() %>
   <br> Comment : <%=ourCookie.getComment() %>

<%
  }
%>

</body>
</html>
           
