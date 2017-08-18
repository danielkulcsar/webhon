<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"  %>


<html>
 
  <body>
  
  <c:choose>
    <c:when test="${param.cmd!=null}">
      <c:set var="str" value="${param.cmd}" />
    </c:when>

    <c:otherwise>
      <c:set var="str"
      value="select * from  Score" />
    </c:otherwise>
  </c:choose>

  Please enter a query:
  <br />

  <form method="post">
    <textarea name="cmd" cols="40" rows="5">
	<c:out value="${str}" />
    </textarea>

    <br />

    <input type="submit" />
  </form>

  <c:if test="${pageContext.request.method=='POST'}">
    <c:catch var="e">
      <sql:query var="users" dataSource="jdbc/myoracle"
      sql="${param.cmd}" />

      <table border="1">
        <c:forEach var="row" items="${users.rows}"
        varStatus="status">
          <jsp:useBean id="status"  type="javax.servlet.jsp.jstl.core.LoopTagStatus" />

          <c-rt:if test="<%=status.getCount()==1%>">
            <tr>
              <c:forEach var="col" items="${row}">
                <th>
                  <c:out value="${col.key}" />
                </th>
              </c:forEach>
            </tr>
          </c-rt:if>

          <tr>
            <c:forEach var="col" items="${row}">
              <td>
                <c:out value="${col.value}" />
              </td>
            </c:forEach>
          </tr>
        </c:forEach>
      </table>
    </c:catch>

    <c:if test="${e!=null}">
      <h3>Error</h3>

      <c:out value="${e}" />
    </c:if>
  </c:if>
  </body>
</html>



