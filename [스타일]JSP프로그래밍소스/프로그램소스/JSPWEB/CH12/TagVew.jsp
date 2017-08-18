<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page session="false" %>
<%@ taglib prefix="view" tagdir="/WEB-INF/tags" %>
<html>
   
   <body>
      <center>
      
      <h2>(태그파일을 이용한 섭씨 계산표 )</h2>
      <table border="1" cellpadding="3" cellspacing="0">
         <tr>
            <th align="right" width="100">화씨 온도</th>
            <th align="right" width="100">섭씨 온도</th>
         </tr>
         <view:ViewFormat from="32" to="212" by="20">
            <tr>
               <td align="right">${f}</td>
               <td align="right">${c}</td>
            </tr>
         </view:ViewFormat>
      </table>
      </center>
   </body>
</html>
