<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page errorPage="errorpage.jsp" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="cart" scope="session" class="JSPWEB.CH09.SESSIONCART.ShoppingCart" />
<html>
  <head>
    <title>��ǰ ���</title>
  </head>

 <body>
 
 
 
 
 
 
 
 
 <h2><a href="ResShoppingcart.jsp">������ ��ǰ��� Ȯ��</a> </h2>
 
  <%
  String id = request.getParameter("id");
  if ( id != null ) {
    String desc =request.getParameter("desc");
   int price = Integer.parseInt(request.getParameter("price"));
    
    cart.addItem(id, desc, price, 1);
  }
  
  %>
  
  
  
            
  <center><h3>��ǰ ���</h3></center>
  
 
  <table border="1" width="300" cellspacing="0"  cellpadding="2" align="center">
    <tr><th>��ǰ</th><th>����</th></tr>
    <tr>
     
      <form action="AddToShoppingCart.jsp" method="post">
        <td>������ �꽺</td>
        <td>2,800��</td>
         <td>        
         <input type="submit" name="Submit" value="Add">  </td>
        <input type="hidden" name="id" value="1">
        <input type="hidden" name="desc" value="������ �꽺">
        <input type="hidden" name="price" value="2800">
        </form>
  </tr>
    <tr>
       <form action="AddToShoppingCart.jsp" method="post">
        <td>���ټ� </td>
        <td>800��</td>
        <td>
           <input type="submit" name="Submit" value="Add">  </td>
        <input type="hidden" name="id" value="2">
        <input type="hidden" name="desc" value="���ټ�">
        <input type="hidden" name="price" value="800">
         </form>
    </tr>
    <tr>
      <form action="AddToShoppingCart.jsp" method="post">
        <td>��� </td>
        <td>1,500��</td>
      
         <td><input type="submit" name="Submit" value="Add"></td>
        <input type="hidden" name="id" value="3">
        <input type="hidden" name="desc" value="���">
        <input type="hidden" name="price" value="1500">
         </form>
       </tr>
    <tr>
      <form action="AddToShoppingCart.jsp" method="post" >
        <td>�ظ�����CD </td>
        <td>18,000��</td>
         <td><input type="submit" name="Submit" value="Add">
        <input type="hidden" name="id" value="4">
        <input type="hidden" name="desc" value="�ظ�����CD">
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
      <caption><b>���� ����</b></caption>
      <tr>
        <th>���</th>
         <th>���� </th>
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
