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
   
�̹� ��û�� ���� �߰���  ��Ű�� �̸� =  <%= added.getName() %><br>
�̹� ��û�� ���� �߰���  ����� ��Ű �� =  <%=  added.getValue() %><br>

<hr>
    <H1>Cookie List</H1>
  

  
 
    <table width="400" border=1>
    <tr>
  
    <th width="150"> ��Ű  �̸�</th>
    <th>��Ű�� ����� ��</th>
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


