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

	g.setColor(Color.white);
    g.fillRect(0, 0, 40,40);

  
    g.setColor(Color.yellow);
    g.fillOval(0, 0, 240, 240);

  
    g.setColor(Color.magenta);
    g.fillOval(160, 160, 240, 240);
    g.setColor(new Color(255, 0, 0, 150));
    g.fillRect(60, 220, 120, 120);

  
    g.setColor(new Color(0, 255, 0, 150));

 
    g.fillOval(140, 140, 120, 120);

  
    g.setColor(new Color(0, 0, 255, 150));

    
    g.fillRect(220, 60, 120, 120);

    g.setColor(Color.black);


	
	ServletOutputStream sout = response.getOutputStream() ; 
 GifEncoder encoder = new GifEncoder(image,sout);
 encoder.encode(); 
 sout.close();

} finally { 

if (g != null) g.dispose(); 
if (frame != null) frame.removeNotify(); 

} 
%>  
