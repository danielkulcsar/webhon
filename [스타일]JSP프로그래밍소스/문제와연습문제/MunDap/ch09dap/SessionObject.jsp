<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HTML> 
    <HEAD>
        <TITLE>세션 오브젝트를 알아봅시다. </TITLE>
    </HEAD> 

    <BODY>
        <% 
        Integer counter =  (Integer)session.getAttribute("counter");
        if (counter == null) {
            counter = new Integer(1);
        } else {
            counter = new Integer(counter.intValue() + 1);
        }

        session.setAttribute("counter", counter);
        %>
        <H1> Session을  알아봅시다. </H1>
        Session ID: <%=session.getId()%>
        <BR><BR>
      처음 접속해서 Session이 생성된 시간 : <%=new Date(session.getCreationTime())%>
        <BR><BR>
         최근 접속 시간 : <%=new Date(session.getLastAccessedTime())%>
        <BR><BR>
       현제  사이트를 접속한 총  횟수: <%=counter%> 
    </BODY> 
</HTML>

