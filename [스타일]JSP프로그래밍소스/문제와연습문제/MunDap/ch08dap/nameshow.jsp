<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ page import = "java.util.*" %>
    <%@ page import ="MunDap.ch08.dap.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html><body>
<h2>검색의결과는...</h2>
<%
Vector<AddrBean> style = (Vector<AddrBean>)request.getAttribute("data");
%>

<%= style.size() %>

<table width="68%" align="center" border="1">
<tr><td>이름 </td><td>나이 </td><td>전화번호</td></tr>
<%
for(int i = 0;i < style.size();i++) {
	AddrBean sb = (AddrBean)style.get(i);	
%>
<tr><td><%= sb.getName() %></td>
<td><%= sb.getAge() %></td>
<td><%= sb.getTel()%></td>
</tr>
<% } %>
</table>
</body>
</html>