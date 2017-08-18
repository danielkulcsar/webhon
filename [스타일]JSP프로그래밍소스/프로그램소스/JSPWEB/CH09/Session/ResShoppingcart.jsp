
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page errorPage="errorpage.jsp" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="cart" scope="session" class="JSPWEB.CH09.SESSIONCART.ShoppingCart" />

<%@page import="java.text.NumberFormat"%><html>
  <head>  
    <title>상품 선택 목록</title>
  </head>
<h2><a href="AddToShoppingCart.jsp">쇼핑 계속하기 </a> </h2>
 <body>
  <form>
    <center>
    <table width="300" border="1" cellspacing="0"
      cellpadding="2" border="0">
      <caption><b>쇼핑 목록</b></caption>
      <tr>
        <th>목록</th>
         <th>단가</th>
        <th>수량 </th>
         <th>금액 </th>
      </tr>
    <%
      Enumeration e = cart.getEnumeration();
      String[] tmpItem;
        while (e.hasMoreElements()) {
        tmpItem = (String[])e.nextElement();
       %>
        <tr>
          <td><%=tmpItem[1] %></td>
              <td align="center"><%=tmpItem[2] %>원</td>   
             <td align="center"><%=tmpItem[3] %></td>
    <td align="center">
       <%= Integer.parseInt(tmpItem[2])* Integer.parseInt(tmpItem[3]) %>
             </td>
        </tr>
        <%
      }
    %> 
    </table> 
    <br>
    <br> 
   [총 지급 할 금액  ]<%=NumberFormat.getInstance().format(cart.getCost())%>  <br>
    
</center>
</form>
</body>
</html>
