<%@ page language="java" contentType="image/jpg"%>
<%@ page import="java.io.*,javax.servlet.ServletOutputStream" %><%
 byte []buffer =new byte[1024];
ServletOutputStream  outt = response.getOutputStream();

  try{
	     String file=application.getRealPath("/IMAGE/mickey2.jpg");
	     BufferedInputStream in =new BufferedInputStream(new FileInputStream(file));
		       int n=0;
	       while((n=in.read(buffer,0,1024))!=-1)
	    		   {
	    	            outt.write(buffer,0,n);
	    		   }
	       outt.close();
	       in.close();
  }catch(Exception e){e.printStackTrace();}
 



%>