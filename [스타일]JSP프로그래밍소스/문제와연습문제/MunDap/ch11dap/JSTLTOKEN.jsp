<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 <%@ prefix="c" taglib uri="http://java.sun.com/jsp/jstl/core"  %>
 <c:set var="names" value="홍길동;정길동:이길동|최길동" scope="page" />
<html>
  <head>
    <title>forTokens action</title>
  </head>
  <body>
    <c:forTokens items="${pageScope.names}"
                 delims=":;|"
                 var="currentName"
                 varStatus="status"
      >
     이름 순번은 #<c:out value="${status.count}" /> 이고 이름은 
        <c:out value="${currentName}" /> <br />
    </c:forTokens>
  </body></html>
