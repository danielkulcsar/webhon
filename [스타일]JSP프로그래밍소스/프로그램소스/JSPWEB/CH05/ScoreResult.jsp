<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<jsp:useBean id="Jumsu" class="JSPWEB.CH05.Score"/>
	        <jsp:setProperty name="Jumsu" property="*"/>
			<pre>
			 <h3>
			 = ScoreList =
			 ==============
			�̸� :<jsp:getProperty name="Jumsu" property="name"/>
			���� : <jsp:getProperty name="Jumsu" property="kor"/> ��
			���� : <jsp:getProperty name="Jumsu" property="eng"/> ��
			���� : <jsp:getProperty name="Jumsu" property="mat"/> ��
			���� : <jsp:getProperty name="Jumsu" property="tot"/> ��
			��� : <jsp:getProperty name="Jumsu" property="avg"/> ��
			���� : <jsp:getProperty name="Jumsu" property="grad"/>
			
			</h3>
	</pre>	


</body>
</html>