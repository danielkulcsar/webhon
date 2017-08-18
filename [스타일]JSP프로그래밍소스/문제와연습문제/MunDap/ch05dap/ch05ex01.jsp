<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
 
  <body>
  <% request.setCharacterEncoding("euc-kr"); %>
  <%
          out.println("<center><b>이곳은 ch05ex01페이지시작 입니다</b></center>");
        %>
    <table width="100%">
      <tr>
        <td>
          
          <jsp:include page="ch05ex02.jsp" >
          <jsp:param value="넘어온  파라미터 값입니다 " name="user"/>
          </jsp:include>
        </td>
      </tr>
      <tr>
        <td>
        <%
          out.println("<center><b>이곳은 ch05ex01페이지 입니다</b></center>");
        %>
        </td>
      </tr>
    </table>
  </body>
</html>
