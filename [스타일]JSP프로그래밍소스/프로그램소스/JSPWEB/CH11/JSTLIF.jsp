<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% response.setContentType("text/html"); %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h3>����</h3>
�Ķ���� ����:<c:out value="${empty param.name}" />
<c:if test="${empty param.name}">
<form>
�̸��� �����ּ���.<br>
    <input type="text" name="name">
    <input type="submit" value="Ȯ��">
</form>
</c:if>
<c:if test="${!empty param.name}">
    �ȳ��ϼ���. <c:out value="${param.name}"/>��.
</c:if>

