<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>Catch an Exception</title>
  </head>

  <body>
    <c:catch var="e">
    <c:set var="x" value="10" scope="page" />

    <c:set var="y" value="five" scope="page" />

    x 를  y 로 나눈 결과 는
    <c:out value="${x/y}" />

    <br />
    </c:catch>

    <br />
    <c:if test="${e!=null}"> 
    <c:out value="${e}" />

    <br />
    </c:if>

    <c:if test="${e==null}">No 
    <br />
    </c:if>
  </body>
</html>
