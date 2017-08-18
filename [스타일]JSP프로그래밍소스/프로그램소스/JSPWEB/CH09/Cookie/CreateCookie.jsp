<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HTML>
    <HEAD>
        <TITLE>쿠키저장</TITLE>
    </HEAD>

    <BODY> 
        <H3>현제 요청 페이지는 클라이언트에 저장될 쿠키를 생성 됩니다.</H3>
  
        <%
        Cookie cook = new Cookie("ClientInfo", "NewClient");
        cook.setMaxAge(24 * 60 * 60);
        response.addCookie(cook); 
        %> 
    <hr>
    
      링크된 곳을 클릭하면 서버 요청으로 클라이언트의 저장된 쿠키를 볼수 있습니다.<br>
      <hr>
      <br>
      <br>
        <A HREF="readCookie.jsp"/>클라이언트의  저장된 정보</A> 
    </BODY>
</HTML>