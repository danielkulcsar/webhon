<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HTML> 
    <HEAD>
        <TITLE>���� ������Ʈ�� �˾ƺ��ô�. </TITLE>
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
        <H1> Session��  �˾ƺ��ô�. </H1>
        Session ID: <%=session.getId()%>
        <BR><BR>
      ó�� �����ؼ� Session�� ������ �ð� : <%=new Date(session.getCreationTime())%>
        <BR><BR>
         �ֱ� ���� �ð� : <%=new Date(session.getLastAccessedTime())%>
        <BR><BR>
       ����  ����Ʈ�� ������ ��  Ƚ��: <%=counter%> 
    </BODY> 
</HTML>

