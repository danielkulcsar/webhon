<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
    <h1>EL�� �̿��� ����</h1>
    <table border="1">

      <tr>
        <td><b>ǥ����</b></td>
        <td><b>EL Expression</b></td>
        <td><b>��� </b></td>
      </tr>    
      <tr>
        <td> ǥ�� [���]</td>
        <td>${'${'}10}</td>
        <td>${10}</td>
      </tr>    
      <tr>
        <td>���ϱ� </td>
        <td>${'${'}10 + 10 }</td>
        <td>${10 + 10}</td>
      </tr>  
      <tr>
        <td>����</td>
        <td>${'${'}10 - 10 }</td>
        <td>${10 - 10}</td>
      </tr>    
      <tr>
        <td>���ϱ�</td>
        <td>${'${'}10 * 10 }</td>
        <td>${10 * 10}</td>
      </tr>    
      <tr>
        <td>������  / </td>
        <td>${'${'}10 / 3 }</td>
        <td>${10 / 3}</td>
      </tr>    
      <tr>
        <td>������ DIV</td>
        <td>${'${'}10 div 3 }</td>
        <td>${10 div 3}</td>
      </tr>    
      <tr>
        <td>������[%] </td>
        <td>${'${'}10 % 10 }</td>
        <td>${10 % 3}</td>
      </tr>    
      <tr>
        <td>������[mod]</td>
        <td>${'${'}10 mod 10 }</td>
        <td>${10 mod 3}</td>
      </tr>    
      <tr>
        <td>0���� �������</td>
        <td>${'${'}10 / 0 }</td>
        <td>${10 / 0}</td>
      </tr>    
      <tr>
        <td>���� </td>
        <td>${'${'}2E2}</td>
        <td>${2E2}</td>
      </tr>  
      <tr>
        <td>�� ��</td>
        <td>${'${'}-10}</td>
        <td>${-10}</td>
      </tr>    
    
        
    </table>
    
    
  </body>

</html>


</body>
</html>