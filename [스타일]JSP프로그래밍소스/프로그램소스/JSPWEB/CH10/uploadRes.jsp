<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ page import="java.io.*" %>
  <%@ page import="com.oreilly.servlet.*" %>
  <%@ page import="com.oreilly.servlet.multipart.*" %>
  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>파일 업로드 결과</title>
</head>
<body>
 <%
  String path="C:\\WELLBOOK\\JSPWEB\\WebContent\\upload";
  int postSize=3*1024*1024;
  try{
	 MultipartRequest MR=new MultipartRequest(request,path,postSize,"euc-kr",new DefaultFileRenamePolicy());
	  String file=MR.getFilesystemName("uploadfile");
	   String file2=MR.getOriginalFileName("uploadfile");
	   out.println("제목 ="+ MR.getParameter("subject")+"<br>");
	   out.println("저장된 파일이름 ="+file+"<br>");
	   out.println("업로드된 파일이름 ="+file2+"<br>");
	      
  }catch(Exception e)
  {e.getStackTrace();}
  
 %>
</body>
</html>