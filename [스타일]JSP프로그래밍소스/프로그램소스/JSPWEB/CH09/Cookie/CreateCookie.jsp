<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HTML>
    <HEAD>
        <TITLE>��Ű����</TITLE>
    </HEAD>

    <BODY> 
        <H3>���� ��û �������� Ŭ���̾�Ʈ�� ����� ��Ű�� ���� �˴ϴ�.</H3>
  
        <%
        Cookie cook = new Cookie("ClientInfo", "NewClient");
        cook.setMaxAge(24 * 60 * 60);
        response.addCookie(cook); 
        %> 
    <hr>
    
      ��ũ�� ���� Ŭ���ϸ� ���� ��û���� Ŭ���̾�Ʈ�� ����� ��Ű�� ���� �ֽ��ϴ�.<br>
      <hr>
      <br>
      <br>
        <A HREF="readCookie.jsp"/>Ŭ���̾�Ʈ��  ����� ����</A> 
    </BODY>
</HTML>