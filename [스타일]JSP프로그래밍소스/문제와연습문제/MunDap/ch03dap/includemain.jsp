<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
  
   <TABLE BORDER='1' CELLSPACING='0' CELLPADDING='0' WIDTH='640'>
     <TR HEIGHT='100'>
       
      <TD BGCOLOR='#FFCCAA' ALIGN='center' VALIGN='center' WIDTH='480' HEIGHT='100'>
         <!-- 타이틀 시작 -->
         <%@ include file="includetitle.jsp" %>
         <!-- 타이틀 끝 -->
      </TD>
     </TR>

     <TR VALIGN='top'>
       
       <TD WIDTH='480' ALIGN='left'height='200'>
       <!-- 메인 컨텐츠 시작 -->
      
         <%@ include file="includecontent.jsp" %>
       
       <!-- 메인 컨텐츠 끝 -->
       </TD>
     </TR>
    
     <TR> 
       <!-- copyright 시작 -->
       <TD colspan='2' ALIGN='center' bgcolor='CCFFCC'>
         <%@ include file="includecopyright.jsp" %>
       </TD>
       <!-- copyright 끝 -->
     </TR>
  </TABLE>
 </BODY>
</HTML> 

