<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ page import="java.io.*" %>
  <%@ page import="com.oreilly.servlet.*" %>
  <%@ page import="com.oreilly.servlet.multipart.*" %>
  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>���� ���ε� ���</title>
</head>
<body>
 <%
  String path="C:\\WELLBOOK\\JSPWEB\\WebContent\\upload";
  int postSize=3*1024*1024;
  try{
	 MultipartRequest MR=new MultipartRequest(request,path,postSize,"euc-kr",new DefaultFileRenamePolicy());
	  String file=MR.getFilesystemName("uploadfile");
	   String file2=MR.getOriginalFileName("uploadfile");
	   out.println("���� ="+ MR.getParameter("subject")+"<br>");
	   out.println("����� �����̸� ="+file+"<br>");
	   out.println("���ε�� �����̸� ="+file2+"<br>");
	      
  }catch(Exception e)
  {e.getStackTrace();}
  
 %>
</body>
</html>