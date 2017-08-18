<% response.setContentType("text/html;"); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h3>header</h3>
<c:forEach items="${header}" var="h">
  <c:out value="${h}"/><br/>
</c:forEach>
<h3>header.cookie</h3>
<c:out value="${header.cookie}"/><br/>
<c:out value='${header["cookie"]}'/>
