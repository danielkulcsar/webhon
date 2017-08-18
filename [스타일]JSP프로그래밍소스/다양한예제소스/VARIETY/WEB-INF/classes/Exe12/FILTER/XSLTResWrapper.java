package Exe12.FILTER;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class XSLTResWrapper extends HttpServletResponseWrapper { 
    
    private ResBuffer buffer = null; 
     
    public XSLTResWrapper(HttpServletResponse response) { 
       super(response); 
       buffer = new ResBuffer(); 
    } 
     
    public PrintWriter getWriter() throws java.io.IOException { 
       return buffer; 
    } 
     
        public String getBufferedString() { 
       return buffer.toString(); 
    } 
 } 

