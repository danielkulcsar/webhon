<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ page import="MunDap.ch12.dap.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
      <%@ taglib uri="/WEB-INF/tlds/IteraterTag.tld" prefix="loop" %>
  <body>
    <%
      pageContext.setAttribute("Day", Day.getInstance().getDay());
    %>

    Days of the week  :
    <ul>
    <loop:iterate  var="day" items="${Day}">
      <li>${day.dayname}
    </li>
    </loop:iterate>
    </ul>

  </body>

</html>