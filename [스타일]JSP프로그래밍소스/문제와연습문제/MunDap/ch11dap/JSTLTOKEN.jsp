<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 <%@ prefix="c" taglib uri="http://java.sun.com/jsp/jstl/core"  %>
 <c:set var="names" value="ȫ�浿;���浿:�̱浿|�ֱ浿" scope="page" />
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
     �̸� ������ #<c:out value="${status.count}" /> �̰� �̸��� 
        <c:out value="${currentName}" /> <br />
    </c:forTokens>
  </body></html>
