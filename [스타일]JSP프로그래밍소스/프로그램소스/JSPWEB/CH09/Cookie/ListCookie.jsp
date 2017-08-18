<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.*" %>
<%
  Cookie[] cookies = request.getCookies();
  if (cookies == null)
    cookies = new Cookie[0];
  
  String name = request.getParameter("name");
  String value = request.getParameter("value");
  Cookie added = null;
  if (name!=null && value!=null && name.length()>0) {
    added = new Cookie(name,value);
    response.addCookie(added);
  }
%>
<HTML>
  <HEAD>
     <TITLE>Cookie List</TITLE>
  </HEAD>
  <BODY>
     <%  
    
         if (added != null)
 %>
   
이번 요청에 새로 추가된  쿠키의 이름 =  <%= added.getName() %><br>
이번 요청에 새로 추가된  저장된 쿠키 값 =  <%=  added.getValue() %><br>

<hr>
    <H1>Cookie List</H1>
  

  
 
    <table width="400" border=1>
    <tr>
  
    <th width="150"> 쿠키  이름</th>
    <th>쿠키가 저장된 값</th>
    </tr>
      <%
    for (int i=0; i<cookies.length; i++) {
    	%>
      <tr>
    	<td> <%= cookies[i].getName()%> </td>
    	<td> <%= cookies[i].getValue() %>  </td>
     </tr>
     <%} %>
     
     </table>
   </BODY>
 </HTML>


