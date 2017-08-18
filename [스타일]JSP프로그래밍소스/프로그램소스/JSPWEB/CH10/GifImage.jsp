<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.io.*, java.awt.*,java.awt.image.*,Acme.JPM.Encoders.GifEncoder" %>
<% 
response.setContentType("image/gif"); 
Graphics g=null;
Frame frame = null; 
try { 
	frame = new Frame(); 
	frame.addNotify(); 
	
	Image image = frame.createImage(400, 400); 
	g = image.getGraphics(); 

	g.setColor(Color.PINK);
	g.fillRect(0,0,400,200);
	g.setColor(Color.WHITE);
	g.setFont(new Font("Serif", Font.ITALIC, 48));
	g.drawString("Hello World! gif", 10, 50);
	response.setContentType("image/gif"); 
	ServletOutputStream sout = response.getOutputStream() ; 
 GifEncoder encoder = new GifEncoder(image,sout);
 encoder.encode(); 
 sout.close();

} finally { 

if (g != null) g.dispose(); 
if (frame != null) frame.removeNotify(); 

} 
%>  
