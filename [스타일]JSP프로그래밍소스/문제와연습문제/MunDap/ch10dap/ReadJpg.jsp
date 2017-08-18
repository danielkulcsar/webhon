<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ page import="java.io.*, java.awt.*,java.awt.image.*,javax.imageio.*,java.awt.geom.AffineTransform" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%
BufferedImage bufferedImage = ImageIO.read(new File(application.getRealPath("/IMAGE/minnie3.jpg")));
BufferedImage destinationBufferedImage = new BufferedImage(300,300, BufferedImage.TYPE_INT_RGB);
Graphics2D g = destinationBufferedImage.createGraphics();
AffineTransform at = AffineTransform.getScaleInstance(2,2);
g.drawRenderedImage(bufferedImage,at);
ImageIO.write(destinationBufferedImage, "JPG",response.getOutputStream());



%>
</body>
</html>