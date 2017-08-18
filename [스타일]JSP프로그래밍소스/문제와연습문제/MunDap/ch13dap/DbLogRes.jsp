<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ page import="MunDap.ch13.dap.LogBean" %>  
     <%@ page import="MunDap.ch13.dap.LogDao" %>
      <%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>DbLog</title>
</head>
<body>
<%
  LogDao  ld=LogDao.getInstance();
  ArrayList<LogBean> ar=(ArrayList<LogBean>) ld.selectList();
%>
<table  border="1" width="700">
	<tr>
	<th align="center"   >request_uri</th>
	<th align="center"  > remote_address</th>
	<th align="center"  >server_name</th>
	<th align="center"  >session_id</th>
	
	
	</tr>
			<%
			
				for(LogBean beans:ar) {
					
			%>
	 <tr>		
	<td>
		 <%= beans.getRequest_uri()%>	 
			</td>
			<td> &nbsp; <%=beans.getRemote_address() %></td>
	
		<td> <%=beans.getServer_name() %></td>
	<td> <%=beans.getSession_id() %></td>
		
	
		
	</tr>    
			  
			 <%
				}
			 %>
		</table>

</body>
</html>
