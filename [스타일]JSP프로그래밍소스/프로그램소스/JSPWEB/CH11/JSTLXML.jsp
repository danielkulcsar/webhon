<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>
<html>
 <body>
 <c:import var="students" url="students.xml" />
    <x:parse var="doc" xml="${students}" />
    <table border="1">
      <tr>
        <td>$doc/students/student/name</td>
        <td>
          <x:out select="$doc/students/student/name" />
        </td>
      </tr>
      <tr>
        <td>$doc/students/student[@id=2]/name</td>
        <td>
          <x:out  select="$doc/students/student[@id=2]/name" />
        </td>
      </tr>
      <tr>
        <td>$doc/students/student[@id=3]/namet</td>
        <td>
          <x:out  select="$doc/students/student[@id=3]/name" />
        </td>
      </tr>
    </table>
     </body>
</html>


