<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HTML>
    <HEAD>
        <TITLE>����� ��Ű�� �о��</TITLE>
    </HEAD>  

    <BODY>
        <H1>����� ��Ű�� �����ɴϴ�.</H1>  
  
        <%
        Cookie[] cook= request.getCookies();
    
        for(int index = 0; index < cook.length; index++) { 
            if (cook[index].getName().equals("ClientInfo")) {
                out.println("����� Ŭ���̾�Ʈ�� ����= " + cook[index].getValue());
            }
        }  
        %>
    </BODY>  
</HTML> 
