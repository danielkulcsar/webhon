<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
   
 <h3>
<form action="ScoreResult.jsp" method="post">
  이름을 입력하고 각각의 점수를 입력하세요 <br><br>
 
  이          름  : <input type="text" name="name"/><br>
     
 국어  점수 : <input type="text" name="kor"/><br>
   
 수학  점수 : <input type="text" name="mat"/><br>

 영어   점수 : <input type="text" name="eng"/><br><br>
  
  
     <input type="submit" value="확인 "/>
  
 </form>
 </h3>    
    
</body>
</html>