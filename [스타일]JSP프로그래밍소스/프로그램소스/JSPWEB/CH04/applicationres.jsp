<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<H1>Application을 이용한 페이지 Count</H1>
        <%
        Integer counter = (Integer)session.getAttribute("counter");
        String heading = null;
        if (counter == null) {
            counter = new Integer(1);
        } else {
            counter = new Integer(counter.intValue() + 1);
        }

        session.setAttribute("counter", counter);

        Integer applicationCounter = (Integer)application.getAttribute("applicationCounter");
        if (applicationCounter == null) {
            applicationCounter = new Integer(1);
        } else {
            applicationCounter = new Integer(applicationCounter.intValue() + 1);
        }

        application.setAttribute("applicationCounter", applicationCounter);
        %>

            session을 이용한  페이지 접속 방문수 <%=counter%> 
        <BR>
            application을 이용한  페이지 접속 방문수  <%=applicationCounter%> 

</body>
</html>