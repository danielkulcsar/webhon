<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<% String[] season = {"봄","여름", "가을", "겨울"}; %>
<c:set var="res" value="<%=season%>"/>
<h3>Season</h3>
<c:forEach items="${res}" varStatus="i">
    <c:out value="${i.count}"/>.
    <c:out value="${res[i.index]}"/><br/>
</c:forEach>
</body>
</html>