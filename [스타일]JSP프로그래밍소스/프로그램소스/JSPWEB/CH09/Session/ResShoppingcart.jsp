
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page errorPage="errorpage.jsp" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="cart" scope="session" class="JSPWEB.CH09.SESSIONCART.ShoppingCart" />

<%@page import="java.text.NumberFormat"%><html>
  <head>  
    <title>��ǰ ���� ���</title>
  </head>
<h2><a href="AddToShoppingCart.jsp">���� ����ϱ� </a> </h2>
 <body>
  <form>
    <center>
    <table width="300" border="1" cellspacing="0"
      cellpadding="2" border="0">
      <caption><b>���� ���</b></caption>
      <tr>
        <th>���</th>
         <th>�ܰ�</th>
        <th>���� </th>
         <th>�ݾ� </th>
      </tr>
    <%
      Enumeration e = cart.getEnumeration();
      String[] tmpItem;
        while (e.hasMoreElements()) {
        tmpItem = (String[])e.nextElement();
       %>
        <tr>
          <td><%=tmpItem[1] %></td>
              <td align="center"><%=tmpItem[2] %>��</td>   
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
   [�� ���� �� �ݾ�  ]<%=NumberFormat.getInstance().format(cart.getCost())%>  <br>
    
</center>
</form>
</body>
</html>
