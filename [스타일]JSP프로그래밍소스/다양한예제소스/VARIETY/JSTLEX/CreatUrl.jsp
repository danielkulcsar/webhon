<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>
<c:url value="ValueView.jsp" var="displayURL">
  <c:param name="nameParam"   value="${param.name}" />
  <c:param name="ageParam"    value="${param.age}" />
</c:url>
<p />
   �Ķ���Ͱ��� ������������  <c:out value="${displayURL}" />. <p/>
    �̵������ <a href='<c:out value="${displayURL}" />'>here</a> �ؼ� Ȯ���ϼ���.
</body>
</html>