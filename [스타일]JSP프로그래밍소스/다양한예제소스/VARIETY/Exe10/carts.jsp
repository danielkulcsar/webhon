<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<jsp:useBean id="cart" scope="session" class="CARTS.CART" />
<jsp:setProperty name="cart" property="*" />
<% 
	cart.processRequest(request);
%>


<br>장바구니에 있는 선택한 내용              
<% 

	String[] items = cart.getItems();
	for (int i=0; i<items.length; i++) {
		
%>

<li> <% out.print((items[i])); 
	}
%>
</li>

<hr>
<%@ include file ="cart.html" %>
</body>
</html>
