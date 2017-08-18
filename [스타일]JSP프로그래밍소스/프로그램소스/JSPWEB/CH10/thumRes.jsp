<% request.setCharacterEncoding("euc-kr");%>
<% response.setContentType("text/html; charset=euc-kr");%>
<%@ page contentType="text/html; charset=euc-kr" %>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.awt.image.renderable.ParameterBlock"%>
<%@page import="javax.media.jai.RenderedOp"%>
<%@page import="javax.media.jai.JAI"%>
<%@page import="java.awt.image.BufferedImage"%>
<%@page import="java.awt.Graphics2D"%>
<%@page import="java.io.File"%>
<%@page import="javax.imageio.ImageIO"%>
<html>
<%

String imagePath=request.getRealPath("/Thumbnail");



 
 int size=1*1024*1024;
 String filename="";
 
 try{
 MultipartRequest multi=new MultipartRequest(request, imagePath, size, "euc-kr", 
                                       new DefaultFileRenamePolicy());
 
 Enumeration files=multi.getFileNames();
 String file=(String)files.nextElement();
 filename=multi.getFilesystemName(file);
 
 }catch(Exception e){
  e.printStackTrace();
 }
 
 ParameterBlock pb=new ParameterBlock();
 pb.add(imagePath+"/"+filename);
 RenderedOp rOp=JAI.create("fileload", pb);
 
 
 BufferedImage bi=rOp.getAsBufferedImage();
 
 BufferedImage image
=new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
 
 
 Graphics2D g=image
.createGraphics();
 g.drawImage(bi,0,0,100,100,null);

 
 File file=new File(imagePath+"/new_"+filename);
 ImageIO.write(image, "jpg", file);
 
%>

<body>
 - 원본이미지<br>
 <img src="<%=imagePath %>/<%=filename %>"><p>
  

 -썸네일 이미지<br>
 <img src="<%=imagePath %>/new_<%=filename %>"> 
</body>
</html>

 


