<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.io.*, java.util.*, java.text.*" %>
<html>
<head>
  <title>����  ����Ʈ</title>
</head>
<body>
<form>
<%! String path; %>
<% path = application.getRealPath("/IMAGE"); %>
<%!
    File [] file; 
    String [] flist;

    public String [] getFileList() {
      String [] fl;
      File f = new File(path);
      fl = f.list();
      return fl;
    }

    public File [] getFileObjList() {
      File [] fl;
      File f = new File(path);
      fl = f.listFiles();
      return fl;
    }
%>
<% flist = getFileList(); %>
<% file = getFileObjList(); %>

<table border=3>
<caption><b>�ٿ�ε�</b></caption>
<tr>
  <th>���ϸ�</th>
  <th>�ֱ� ������</th>
  <th>ũ��</th>
</tr>

<%
    for (int i = 0; i < flist.length; i++) {
    	String name=flist[i];
%>
      <tr>
        <td>   
          <a href='<%=request.getContextPath()+ "/FileUploadTest?dir="+path+"&selectfile=" + name %>' ><%= flist[i] %></a>
        
        </td>
        <td>
          <%= DateFormat.getInstance().format
                      (new Date(file[i].lastModified())) %>
        </td>
        <td>
          <%= Long.toString(file[i].length()) %> ����Ʈ
        </td>
      </tr>
<%
    }
%>

</table>
</form>
</body>
</html>
