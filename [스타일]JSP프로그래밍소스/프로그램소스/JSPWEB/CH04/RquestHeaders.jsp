<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
 <h4>HTTP Request Headers Received</h4>
      <table border="1" cellpadding="4" cellspacing="0">
      <%
         Enumeration eNames = request.getHeaderNames();
         while (eNames.hasMoreElements()) {
            String name = (String) eNames.nextElement();
            String value = Prn(request.getHeader(name));
      %>
         <tr><td><%= name %></td><td><%= value %></td></tr>
      <%
         }
      %>
      </table>
   </body>
</html>
<%!
   private String Prn(String value)
   {
      StringBuffer sb = new StringBuffer();
      for (int i = 0; i < value.length(); i++) {
         char c = value.charAt(i);
         sb.append(c);
         if (c == ';')
            sb.append("<br>");
      }
      return sb.toString();
   }
%>

