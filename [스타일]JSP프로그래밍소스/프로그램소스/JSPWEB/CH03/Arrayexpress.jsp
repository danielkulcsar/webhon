<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Arrayexpress</title>
</head>


  <BODY>
    <H2>메소드를 이용해서 배수를 구해보자 </H2>
    <%!
    void doubler(int []a)
    {
        for (int i = 0; i < a.length;i++) {
            a[ i ] *= 2;
        }
    }
    %>

    <%
        int array[] = {1, 2, 3, 4, 5};
        out.println("배수를 구하기 전<BR>");
        for (int i = 0; i < array.length; i++) {
            out.println("array[" + i + "] = " + array[i] + "<BR>");
        }
        
        doubler(array);
        
        out.println("<br> 배수를 구한 후 <BR>");
        for (int i = 0; i < array.length; i++) {
            out.println("array[" + i + "] = " +
                array[i] + "<BR>");
        }
    %>
  </BODY>
</HTML>

