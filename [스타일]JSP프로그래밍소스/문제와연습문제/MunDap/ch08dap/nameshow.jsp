<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ page import = "java.util.*" %>
    <%@ page import ="MunDap.ch08.dap.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html><body>
<h2>�˻��ǰ����...</h2>
<%
Vector<AddrBean> style = (Vector<AddrBean>)request.getAttribute("data");
%>

<%= style.size() %>

<table width="68%" align="center" border="1">
<tr><td>�̸� </td><td>���� </td><td>��ȭ��ȣ</td></tr>
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