<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>
 <h3>List of query string parameters:</h3>
    <ul>
      <c:forEach items="${param}" var="currentParam">
        <li><c:out value="${currentParam.key}" />
            = <c:out value="${currentParam.value}" /></li>
      </c:forEach>
    </ul>
</body>
</html>