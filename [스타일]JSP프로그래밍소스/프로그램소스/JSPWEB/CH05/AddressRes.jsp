<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<jsp:useBean id="Alist" class="JSPWEB.CH05.AddressList"/>
<jsp:setProperty  name="Alist" property="*"/>

   <jsp:getProperty name="Alist" property="name"/> �� �ݰ����ϴ� <br>
<jsp:getProperty name="Alist" property="name"/> �� �� �ּҴ�   
<jsp:getProperty name="Alist" property="addr"/>   �̰� <br>��ȭ��ȣ��
<jsp:getProperty name="Alist" property="tel"/>  �Դϴ�.
</body>
</html>