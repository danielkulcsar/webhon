<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ page import="java.io.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>파일 업로드 결과</title>
</head>
<body>
<% 
response.setContentType("text/html; charset=EUC-KR");
		
		out.println(" <html> <body> <h3>문서올리기 성공</h3>");
		out.println("업로드 파일의 타입정보" + request.getContentType()+"<br>");
		out.println("업로드 파일의 타입길이" + request.getContentLength()+"<br>");
		out.println("<hr><font color=blue> <br> ");
		InputStreamReader isr=new InputStreamReader(request.getInputStream(),"EUC-KR");
		BufferedReader br=new BufferedReader(isr);
		for (String line;(line=br.readLine())!= null;  )
		{
			out.println(line + " ");
		}
out.println("</font></body></html>");
out.close();
%>
</body>
</html>