<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
 <%@ page import="org.jfree.chart.ChartFactory" %>
  <%@ page import="org.jfree.chart.ChartFrame" %>
  <%@ page import=" org.jfree.chart.JFreeChart" %>
  <%@ page import="org.jfree.chart.plot.PlotOrientation,javax.servlet.ServletOutputStream" %>
 <%@ page import="org.jfree.data.category.DefaultCategoryDataset,org.jfree.chart.ChartUtilities" %>
<%@ page import="java.sql.*,javax.naming.*,javax.sql.DataSource,java.sql.Date,java.util.*" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%
Context ctx = new InitialContext();
DataSource ds =  (DataSource)ctx.lookup("java:comp/env/jdbc/myoracle");
Connection con = ds.getConnection();
Statement  stmt=null;
ResultSet rs=null;
String query = "select * from Score";
DefaultCategoryDataset dataSet = new DefaultCategoryDataset();

try{
	
	stmt = con.createStatement();
	rs = stmt.executeQuery(query);
	
	
	while(rs.next()){
		dataSet.addValue(rs.getInt("kor"),"kor", rs.getString("name"));
		dataSet.addValue(rs.getInt("eng"),"eng", rs.getString("name"));
		dataSet.addValue(rs.getInt("mat"),"mat", rs.getString("name"));
	}
	
	
	JFreeChart chart = ChartFactory.createBarChart(getClass().getName(),
	        "Student Name", 
	        "Score", 
	        dataSet,
	        PlotOrientation.VERTICAL,
	        true, 
	        true, 
	        false); 
	        response.setContentType("image/jpeg"); 
	        ServletOutputStream t=response.getOutputStream();
	        ChartUtilities.writeChartAsJPEG(t,chart,600,400);  
}catch (Exception e) {
	e.printStackTrace();
	
}

rs.close();
stmt.close();
con.close();



%>
</body>
</html>