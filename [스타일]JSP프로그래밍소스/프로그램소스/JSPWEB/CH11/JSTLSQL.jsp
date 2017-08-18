<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<html>
  <head>
    <title>Query Example</title>
  </head>
<body>
<sql:query var = "score" dataSource="jdbc/myoracle">
select name,kor,eng,mat from Score
</sql:query>
<fmt:setLocale value="ko" />

<table border="1">
  <tr>
<%-- �ʵ��� ������ ����Ѵ�.            --%>
  <c:forEach var="columnName" items="${score.columnNames}">
    <th><c:out value="${columnName}"/></th>
  </c:forEach>

<%-- �����͸� �� �پ� ����Ѵ�.         --%>
  <c:forEach var="row" items="${score.rowsByIndex}">
    <tr>
<%-- �ʵ��� ���̸�ŭ �ݺ��Ѵ�.          --%>
      <c:forEach var="column" items="${row}" varStatus="i">
       <td><c:out value="${column}"/></td>
  
  </c:forEach>
  
 </tr>
 </c:forEach>
 </tr>
 </table>
 </body>
 </html>