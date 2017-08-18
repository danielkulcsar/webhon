<%@ page import="java.util.*,java.text.*" %> 
<%= new Date().toLocaleString() %> 
<% 
Date now = new Date();      
DateFormat df = DateFormat. getDateInstance();   
String s = df.format(now);     
out.println("Today is " + s);       
%> 
