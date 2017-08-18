<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" errorPage="error.jsp" %>
   <%@ page import="JSPWEB.CH08.BOARD.*" %>  
   <%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>BOARDLIST</title>
</head>
<body>
<link href="boardlist.css" rel="stylesheet" type="text/css">
<%
 ArrayList<BOARDBean> ar=(ArrayList<BOARDBean>)request.getAttribute("BOARD");
%>

<H2>방명록 LIST </H2> 
<HR>
<form >

<a href="insert.go?action=insert">방명록 쓰기</a>
	<table  border="1" width="700">
	<tr>
	
	<th align="center"   >글번호</th>
	<th align="center"  > 이 름</th>
	<th align="center"  >email</th>
	<th align="center"  >homepage</th>
	<th align="center"  >방문날짜</th>
	<th align="center"  >Contents</th>

	</tr>
			<%
			
				for(BOARDBean beans:ar) {
					
			%>
	 <tr>		
	<td><a href="selectlist.go?action=selectlist&no=<%= beans.getNo() %>">
		 <%= beans.getNo()%>	  </a>
			</td>
			<td> &nbsp; <%=beans.getName() %></td>
	
		<td> <%=beans .getEmail() %></td>
	<td> <%=beans .getHomepage() %></td>
		
		<td> &nbsp;  <%= beans.getDate()  %></td>
	
		
		<td> &nbsp;  <%= beans.getContents() %></td>
	</tr>    
			  
			 <%
				}
			 %>
		</table>
</form>
</body>
</html>