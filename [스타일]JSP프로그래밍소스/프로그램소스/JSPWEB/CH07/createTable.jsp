<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ page import="java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>createTable</title>
</head>
<body>
<%  
            Connection conn = null;
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                String url="jdbc:oracle:thin:@127.0.0.1:1521";
                String user="WEBPRO";
                String password="admin1234";
               conn = DriverManager.getConnection(url,user,password);

                Statement statement = conn.createStatement();
                String command = "CREATE TABLE EMPTEST(ID number(4),Name varchar2(50))";
                statement.executeUpdate(command);
                out.println("테이블 생성이 완료되었습니다");
            } catch (Exception e) {
                out.println(e.getMessage());
            }
        %>
   </body>
</html>