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
	         <!-- 타이틀 시작 -->
	       <%@include file="includetitle.jsp" %>
	         <!-- 타이틀 끝 -->
	      </TD>
	     </TR>
	
	     <TR VALIGN='top'>
	       <!-- 로그인 시작 -->
	       <TD BGCOLOR='#FFAAFF' WIDTH='160' ALIGN='left'>
	        <%@include file="includeLogin.jsp" %> 
	       </TD>
	       <!-- 로그인 끝 -->
	       <TD >
	       <!-- 메인 컨텐츠 시작 -->
	         
	        <%@include file="includewelcome.jsp" %> 
	       
	       <!-- 메인 컨텐츠 끝 -->
	       </TD>
	     </TR>
	     <TR>
	      <TD class='box1'>
	        <!-- 사이드 메뉴 시작 -->
	       <%@include file="includeside_menu.jsp" %>
	        <!-- 사이드 메뉴 끝  -->
	      </TD>
	     </TR>
	     <TR> 
     <!-- 하단 메뉴 시작 -->
     <TD colspan='2' BGCOLOR='#FFAODC' ALIGN='center' VALIGN='center' WIDTH='400' HEIGHT='40'>
      <%@ include file="includebottom.jsp" %> 
       </TD>
	       <!-- 하단메뉴 끝 -->
     </TR>
	  </TABLE>
	 </BODY>
	</HTML>

