<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ page import="java.io.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head><title>BufferedReader�� �̿��� ���� �б�</title></head>
<body>
<%
    String Path = "/Test/ex.txt";
%>
���� ���:<br>
<%= application.getRealPath(Path) %>
<br>
<hr><br>
<%=Path %>�� ������ �Ʒ��� �����ϴ�.<br><hr>
<%
    BufferedReader br = null;
    char[] buff = new char[512];
    int len = -1;
    
    try {
        br = new BufferedReader(new InputStreamReader(
                       application.getResourceAsStream(Path) ));

                 
                while ( (len = br.read(buff)) != -1) {
                    out.println(new String(buff, 0, len));
                }
            } catch(IOException ex) {
                out.println("���� �߻�: "+ex.getMessage());
            } finally {
                if (br != null) try { br.close(); } catch(IOException ex) {}
            }
        %>
 </body>
 </html>
         


         

      




