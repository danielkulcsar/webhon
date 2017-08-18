<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page errorPage="errorpage.jsp" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="cart" scope="session" class="JSPWEB.CH09.SESSIONCART.ShoppingCart" />
<html>
  <head>
    <title>상품 목록</title>
  </head>

 <body>
 
 
 
 
 
 
 
 
 <h2><a href="ResShoppingcart.jsp">선택한 상품목록 확인</a> </h2>
 
  <%
  String id = request.getParameter("id");
  if ( id != null ) {
    String desc =request.getParameter("desc");
   int price = Integer.parseInt(request.getParameter("price"));
    
    cart.addItem(id, desc, price, 1);
  }
  
  %>
  
  
  
            
  <center><h3>상품 목록</h3></center>
  
 
  <table border="1" width="300" cellspacing="0"  cellpadding="2" align="center">
    <tr><th>상품</th><th>가격</th></tr>
    <tr>
     
      <form action="AddToShoppingCart.jsp" method="post">
        <td>오렌지 쥬스</td>
        <td>2,800원</td>
         <td>        
         <input type="submit" name="Submit" value="Add">  </td>
        <input type="hidden" name="id" value="1">
        <input type="hidden" name="desc" value="오렌지 쥬스">
        <input type="hidden" name="price" value="2800">
        </form>
  </tr>
    <tr>
       <form action="AddToShoppingCart.jsp" method="post">
        <td>웰다수 </td>
        <td>800원</td>
        <td>
           <input type="submit" name="Submit" value="Add">  </td>
        <input type="hidden" name="id" value="2">
        <input type="hidden" name="desc" value="웰다수">
        <input type="hidden" name="price" value="800">
         </form>
    </tr>
    <tr>
      <form action="AddToShoppingCart.jsp" method="post">
        <td>사과 </td>
        <td>1,500원</td>
      
         <td><input type="submit" name="Submit" value="Add"></td>
        <input type="hidden" name="id" value="3">
        <input type="hidden" name="desc" value="사과">
        <input type="hidden" name="price" value="1500">
         </form>
       </tr>
    <tr>
      <form action="AddToShoppingCart.jsp" method="post" >
        <td>해리포토CD </td>
        <td>18,000원</td>
         <td><input type="submit" name="Submit" value="Add">
        <input type="hidden" name="id" value="4">
        <input type="hidden" name="desc" value="해리포토CD">
        <input type="hidden" name="price" value="18000">
       </td> 
       </form>
       </tr>
    </table>
    <hr>   
   <form>
    <center>
    <table width="300" border="1" cellspacing="0"
      cellpadding="2" border="0">
      <caption><b>쇼핑 수량</b></caption>
      <tr>
        <th>목록</th>
         <th>수량 </th>
           </tr>
    <%
      Enumeration e = cart.getEnumeration();
      String[] tmpItem;
       
      while (e.hasMoreElements()) {
        tmpItem = (String[])e.nextElement();
       %>
        <tr>
          <td><%=tmpItem[1] %></td>
             <td align="center"><%=tmpItem[3] %></td>       
        </tr>
        <%
      }
    %> 
    </table> 
     </center>
    </form>
  </body>
</html>
