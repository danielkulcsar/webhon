<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<jsp:useBean id="JumsuList" class="JSPWEB.CH05.Score"/>
	        <jsp:setProperty name="JumsuList" property="name" value="Dominico"/>
			<jsp:setProperty name="JumsuList" property="eng" value="90"/>
			<jsp:setProperty name="JumsuList" property="mat" value="80"/>
			<jsp:setProperty name="JumsuList" property="kor" value="100"/>
			
	<pre>
			 <h3>
			 = ScoreList =
			 ==============
			�̸� :<jsp:getProperty name="JumsuList" property="name"/>
			���� : <jsp:getProperty name="JumsuList" property="kor"/> ��
			���� : <jsp:getProperty name="JumsuList" property="eng"/> ��
			���� : <jsp:getProperty name="JumsuList" property="mat"/> ��
			���� : <jsp:getProperty name="JumsuList" property="tot"/> ��
			��� : <jsp:getProperty name="JumsuList" property="avg"/> ��
			���� : <jsp:getProperty name="JumsuList" property="grad"/>
			
			</h3>
	</pre>	
</body>
</html>