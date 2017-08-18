<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.sql.*,javax.sql.*, javax.naming.*" %>
 <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>BatchTest </title>
</head>
<body>
<%
   Connection conn = null; 
Statement stmt=null;
        try { 
       	Context initContext = new InitialContext();
       	Context envContext  = (Context)initContext.lookup("java:/comp/env");
      	DataSource ds = (DataSource)envContext.lookup("jdbc/myoracle");
 	conn = ds.getConnection();     
 	stmt = conn.createStatement() ;
 	for(int j=1;j<=100;j++)
 	{
 String sql="insert into test02 values("+j+",'EMPID"+j+"','Name"+j+"',"+(j+2)+")";
 	stmt.addBatch(sql);
 	}
     int [] update= stmt.executeBatch();    
  out.println("update count="+update.length);
    conn.commit();
   conn.setAutoCommit(true);
 } catch(BatchUpdateException   b)
		{
                conn.rollback();
		}
%>
</body>
</html>
