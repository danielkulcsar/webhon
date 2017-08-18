<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
 <%@ page import="java.io.*" %>
 <%@ page import="java.awt.*" %>
 <%@ page import="java.util.Random"%>
 <%@ page import="java.awt.image.*"%>
 <%@ page import="javax.imageio.ImageIO " %>
 <%@ page import="java.awt.image.BufferedImage" %>
 <%@ page import="com.sun.image.codec.jpeg.JPEGCodec" %>
<%@ page import="com.sun.image.codec.jpeg.JPEGImageEncoder" %>
<%@ page import="com.sun.image.codec.jpeg.JPEGImageDecoder" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>JPEG 동적이미지</title>
</head>
<body>
<%
String path="C:\\WELLBOOK\\JSPWEB\\WebContent\\upload\\test.jpg";
BufferedImage image= new BufferedImage(400,200, BufferedImage.TYPE_INT_RGB);
Graphics g=image.getGraphics(); 
g.setColor(Color.PINK);
g.fillRect(0,0,400,200);
g.setColor(Color.WHITE);
g.setFont(new Font("Serif", Font.ITALIC, 48));
g.drawString("Hello World! JPG", 10, 50);

FileOutputStream fos=new FileOutputStream(path);
JPEGImageEncoder encoder= JPEGCodec.createJPEGEncoder(fos);
encoder.encode(image);

response.setContentType("image/jpeg");
ServletOutputStream outStream = response.getOutputStream();

FileInputStream fi=new FileInputStream(path);
JPEGImageDecoder  De= JPEGCodec.createJPEGDecoder(fi);
 image=De.decodeAsBufferedImage();
ImageIO.write(image, "jpg", outStream);


%> 
</body>
</html>