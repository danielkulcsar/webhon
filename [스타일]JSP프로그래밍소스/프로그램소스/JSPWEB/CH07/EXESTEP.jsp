<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ page import="java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>EXESTEP</title>
</head>
<body>
 <%
 Class.forName("oracle.jdbc.driver.OracleDriver");
Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521","WEBPRO","admin1234");
	Statement stmt = conn.createStatement();
	
 	try {
 		ResultSet rs  = stmt.executeQuery("select * from ADDRLIST");
 		out.println("<center> 이름    "+"  나이   "+ "전화 번호 <br>");
 		out.println("========================<br>");
 	    while(rs.next()){
 		out.println(rs.getString(1)+"   "+rs.getInt(2)+"   "+ rs.getString(3)+"<br>");
 	    }
 	    rs.close();
 	}
 	catch(Exception e){
 	    out.println(e);
 	}finally{
   try{
 	stmt.close();
	conn.close();
 	}catch(SQLException ei)
 	{
 		out.println(ei);
 	
 	}
 	}
 

 %>
  



</body>
</html>