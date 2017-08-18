<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">

<title>Insert title here</title>
</head>
<body>

<form method="POST">
      
 <table border="1"   width="65%" >
        <tr>
          <td width="100%" colspan="2" bgcolor="#0000FF">
            <p align="center">
              <b>
                <font color="#FFFFFF" size="4">Import
                </font>
              </b>
            </p>
          </td>
        </tr>

        <tr>
          <td width="50%">import할 사이트를 입력하세요:</td>

          <td width="53%">
            <input type="text" name="url" size="40" 
              value="http://www.wellbook.net/"/>
          </td>
        </tr>

        <tr>
          <td width="100%" colspan="2">
            <p align="center">
              <input type="submit" value="Submit" name="submit" />

              <input type="reset" value="Reset" name="reset" />
            </p>
          </td>
        </tr>
      </table>

      <p>&#160;</p>
    </form>
    <c:if test="${pageContext.request.method=='POST'}">
      <hr>
      <c:import url="${param.url}"/>
    </c:if>

</body>
</html>