<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ attribute name="from" %>
<%@ attribute name="to" %>
<%@ attribute name="by" %>

<%@ variable name-given="f" %>
<%@ variable name-given="c" %>

<c:forEach var="degrees" begin="${from}" end="${to}" step="${by}">

 
   <c:set var="f" value="${degrees}"/>
   <c:set var="c">
      <fmt:formatNumber pattern="##0.00">
         ${(f - 32) * 5 / 9.0}
      </fmt:formatNumber>
   </c:set>


   <jsp:doBody/>

</c:forEach>
