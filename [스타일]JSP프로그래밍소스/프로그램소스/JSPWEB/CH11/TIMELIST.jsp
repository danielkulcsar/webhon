<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>

<body>
    <c:set var="now"  value="<%=new java.util.Date()%>" />

    <table border="1" cellpadding="0" cellspacing="0"
    style="border-collapse: collapse" bordercolor="#77771"
    width="100%" id="AutoNumber2">
      <tr>
        <td width="100%" colspan="2" bgcolor="#0000FF">
          <p align="center">
            <b>
              <font color="#FFFFFF" size="4">Formatting: 
              <fmt:formatDate value="${now}" type="both"
              timeStyle="long" dateStyle="long" />
              </font>
            </b>
          </p>
        </td>
      </tr>

      <c:forEach var="zone"
      items="<%=java.util.TimeZone.getAvailableIDs()%>">
        <tr>
          <td width="40%">
            <c:out value="${zone}" />
          </td>

          <td width="60%">
            <fmt:timeZone value="${zone}">
              <fmt:formatDate value="${now}" timeZone="${zn}"
              type="both" />
            </fmt:timeZone>
          </td>
        </tr>
      </c:forEach>
    </table>
  </body>
</html>
