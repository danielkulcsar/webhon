<%@ page 
    info="현제 요청한 페이지는 다양한 예제 03번이랍니다." 
    contentType="text/html; charset=euc-kr" 
%> 
<html> 
<body> 
이 page가 가진 정보를 알아보면 다음과 같아요<br> 
<%= ((HttpJspPage)page).getServletInfo() %> 
</body> 
 </html>