<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
<h3>EL Function(JSTL1.1)</h3>

    <c:set var="name" value="  www.wellbook.net  "/>
    <c:set var="name" value="${fn:trim(name)}"/><br/>
    <c:out value="name: ${name}"/><br/><br/>
    <c:out value="length(name): ${fn:length(name)}"/><br/>
    <c:out value="toUpperCase(name): ${fn:toUpperCase(name)}"/><br/>
    <c:out value="toLowerCase(name): ${fn:toLowerCase(name)}"/><br/>
    <c:out value="substring(name,3,6): ${fn:substring(name,3,6)}"/><br/>
    <c:out value="substringBefore(name,'.'): ${fn:substringBefore(name, '.')}"/><br/>
    <c:out value="substringAfter(name,'.'): ${fn:substringAfter(name, '.')}"/><br/>
  
</body>
</html>