<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>includemain</title>
</head>

	 <BODY BGCOLOR='white'>
	 <%@include file="includecss.css" %>
	   <TABLE  WIDTH='650' height='300'>
	     <TR  >
	       
	      <TD colspan='2' BGCOLOR='#FFAODC' ALIGN='center' VALIGN='center' >
	         <!-- Ÿ��Ʋ ���� -->
	       <%@include file="includetitle.jsp" %>
	         <!-- Ÿ��Ʋ �� -->
	      </TD>
	     </TR>
	
	     <TR VALIGN='top'>
	       <!-- �α��� ���� -->
	       <TD BGCOLOR='#FFAAFF' WIDTH='160' ALIGN='left'>
	        <%@include file="includeLogin.jsp" %> 
	       </TD>
	       <!-- �α��� �� -->
	       <TD >
	       <!-- ���� ������ ���� -->
	         
	        <%@include file="includewelcome.jsp" %> 
	       
	       <!-- ���� ������ �� -->
	       </TD>
	     </TR>
	     <TR>
	      <TD class='box1'>
	        <!-- ���̵� �޴� ���� -->
	       <%@include file="includeside_menu.jsp" %>
	        <!-- ���̵� �޴� ��  -->
	      </TD>
	     </TR>
	     <TR> 
     <!-- �ϴ� �޴� ���� -->
     <TD colspan='2' BGCOLOR='#FFAODC' ALIGN='center' VALIGN='center' WIDTH='400' HEIGHT='40'>
      <%@ include file="includebottom.jsp" %> 
       </TD>
	       <!-- �ϴܸ޴� �� -->
     </TR>
	  </TABLE>
	 </BODY>
	</HTML>

