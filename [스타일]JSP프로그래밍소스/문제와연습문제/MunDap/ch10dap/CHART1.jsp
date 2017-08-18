<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
 
    <%@ page import="org.jfree.chart.*,java.io.*" %>
  <%@ page import="org.jfree.chart.ChartFrame" %>
  <%@ page import=" org.jfree.chart.JFreeChart" %>
    <%@ page import="org.jfree.chart.plot.*,org.jfree.chart.axis.*" %>
  <%@ page import="org.jfree.chart.plot.PlotOrientation,javax.servlet.ServletOutputStream" %>
 <%@ page import="org.jfree.data.category.*,org.jfree.chart.ChartUtilities" %>
<%@ page import="org.jfree.chart.entity.StandardEntityCollection,org.jfree.chart.axis.CategoryAxis3D
,org.jfree.chart.axis.NumberAxis3D,org.jfree.chart.renderer.category.*" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%
JFreeChart chart = null; 
DefaultCategoryDataset dataset = new DefaultCategoryDataset(); 
dataset.setValue(10, "분기", "1분기"); 
dataset.setValue(20, "분기", "2분기"); 
dataset.setValue(20, "분기", "3분기"); 
dataset.setValue(68, "분기", "4분기");   

CategoryAxis3D axis    = new CategoryAxis3D("기간구분");            
NumberAxis3D numAxis   = new NumberAxis3D("판매량"); 
BarRenderer3D renderer = new BarRenderer3D(0,0); 
renderer.setAutoPopulateSeriesPaint(true); 
renderer.setShadowVisible(true);   
CategoryPlot plot = new CategoryPlot(dataset,axis,numAxis, renderer);   
CategoryAxis  axis2 = plot.getDomainAxis();   
axis2.setLowerMargin(0.03);    
axis2.setUpperMargin(0.03);    
axis2.setCategoryMargin(0.5);    
         
chart = new JFreeChart( 
"[분기별 판매 현황]", 
JFreeChart.DEFAULT_TITLE_FONT, 
plot, 
false 
);    
chart.setBackgroundPaint(java.awt.Color.WHITE); 
response.setContentType("image/jpeg"); 
ServletOutputStream t=response.getOutputStream();
ChartUtilities.writeChartAsJPEG(t,chart,600,400); 


%>
</body>
</html>