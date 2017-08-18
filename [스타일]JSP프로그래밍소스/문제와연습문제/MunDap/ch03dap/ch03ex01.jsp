<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<H1>int 배열을 JSP로 나타내보자</H1>
    <%
        int array[] = {1, 2, 3, 4, 5}, sum = 0;

        for (int i = 0;
            i < array.length;
            sum += array[i++]);
        out.println(" The  average = " + sum / array.length);
    %>

</body>
</html>