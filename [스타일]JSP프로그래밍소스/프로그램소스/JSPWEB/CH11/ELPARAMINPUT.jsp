<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<jsp:useBean id="season" class="JSPWEB.CH11.SEASON" scope="session"/>
   <h3>좋아하는 계절을 입력해주세요</h3>
   <hr>
   <form METHOD="post" ACTION="ELVIEW3.jsp">
   

	이름 :<input type="TEXT" Name="name" size="30"><br>
	좋아하는 계절 : <select name="selectSEASON">
	 <%for (String item: season.getSelectSEASON() ){
		 out.println("<option>"+item+"</option>");
	 }
	       %>    
    </select>
 
	<input type="submit" value="확인">
</form>
</body>
</html>