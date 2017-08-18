<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HTML>
    <HEAD>
        <TITLE>저장된 쿠키를 읽어보자</TITLE>
    </HEAD>  

    <BODY>
        <H1>저장된 쿠키를 가져옵니다.</H1>  
  
        <%
        Cookie[] cook= request.getCookies();
    
        for(int index = 0; index < cook.length; index++) { 
            if (cook[index].getName().equals("ClientInfo")) {
                out.println("저장된 클라이언트의 정보= " + cook[index].getValue());
            }
        }  
        %>
    </BODY>  
</HTML> 
