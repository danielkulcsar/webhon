<%@ page contentType="text/html; charset=euc-kr" %>

 구구단 입니다.
<%="<table border=1>" %>
<%
int i,j;

for(i=1; i<=9; i++)
{
	%>
<%= "<tr>" %>
<% 
 for(j=2; j <= 9; j++)
 {
%>
  
  <%="<td>"+ j +" * "+ i +"= "+ j * i +"</td>" %>
<% } %>
 
 <%="</tr>" %>
<%} %>

<%="</table>" %>



