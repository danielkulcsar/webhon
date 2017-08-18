package JSPWEB.CH10.FILELIST;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;


public class FileUploadTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public FileUploadTest() {
        super();
        // TODO Auto-generated constructor stub
    }

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			response.setContentType("text/html; charset=EUC-KR");
			request.setCharacterEncoding("KSC5601");
			
			String file = request.getParameter("selectfile");	
			String dir = request.getParameter("dir");	
	       
			File f = new File(file);
			if(f.isDirectory()) return;
			if(file.endsWith("html") || file.endsWith("txt") || 
				file.endsWith("xml") || file.endsWith("jsp")) {
				response.setContentType("text/html;charset=KSC5601");
				PrintWriter out = response.getWriter();
				 sendText(dir, file,out); 
		
						 
						
			} else {
				if(file.endsWith("gif"))
					response.setContentType("image/gif");
				else if(file.endsWith("jpg"))
					response.setContentType("image/jpeg");
				else {
					response.setContentType("application/octet");
					response.setHeader("Content-Disposition",
						"attachment;filename=" + file);				
				}

				ServletOutputStream out = response.getOutputStream();
				sendBinary( dir ,file, out);
				return;
			}
			
			
			
	}
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		      doPost(request,response);
		}
	
		protected void sendBinary(String realDir, String file, 
				OutputStream out) throws IOException {
				String path = realDir + "/" + file;
				BufferedInputStream in = new BufferedInputStream(
					new FileInputStream(path));
				byte buf[] = new byte[1024];
				int n=-1;
				while((n = in.read(buf, 0, 1024)) != -1) {
					out.write(buf, 0, n);
					out.flush();
				}
				in.close();
				out.close();			
			}
		
		
		
		protected void sendText(String dir, String file,PrintWriter out) throws IOException {
			
			
			


				String path = dir  + "/" + file;
				File  dataFile = new File(path);
				
				out.println("<html>");
				
			
				
				out.println("<body >");
				out.println("<h3 align=center>"+dir + "/" + file +"</h3>");
				
				 
				BufferedReader in = new BufferedReader(new FileReader(dataFile));
				StringBuilder sb = new StringBuilder(); 
				String line=null;
				while((line = in.readLine()) != null) {
					
					sb.append(line);
					out.println("<br>");
				
				}
				out.println(sb);
				
				out.println("</BODY></HTML>");
				in.close();
				out.close();			
			}
}
