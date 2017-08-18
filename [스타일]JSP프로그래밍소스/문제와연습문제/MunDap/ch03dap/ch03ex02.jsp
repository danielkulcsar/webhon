<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ page import="java.util.Calendar"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>


    지금은 
    <%
      Calendar calendar = Calendar.getInstance();
      if (calendar.get(Calendar.AM_PM) == Calendar.AM) {
        out.print("신선한 아침"+ calendar.get(Calendar.HOUR_OF_DAY)+ "시  입니다.");
      } else {
        out.print("늘어지는 오후입니다.");
      }
    %>
 

</body>
</html>