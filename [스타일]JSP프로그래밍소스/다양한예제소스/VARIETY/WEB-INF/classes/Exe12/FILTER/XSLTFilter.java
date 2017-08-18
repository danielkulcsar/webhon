package Exe12.FILTER;



import javax.servlet.*; 
import javax.servlet.http.*; 
import java.io.*; 
 
import javax.xml.transform.TransformerFactory; 
import javax.xml.transform.Transformer; 
import javax.xml.transform.stream.StreamSource; 
import javax.xml.transform.stream.StreamResult; 

import Acme.Serve.servlet.http.HttpServletRequest;


public class XSLTFilter implements Filter {

	 public void init(FilterConfig filterConfig) throws ServletException { 
     } 
      
     public void doFilter(ServletRequest request, 
                          ServletResponse response, 
                          FilterChain chain) 
                 throws java.io.IOException, ServletException { 
        response.setContentType("text/html; charset=euc-kr"); 
        PrintWriter writer = response.getWriter(); 
         
        XSLTResWrapper   responseWrapper =  
                      new XSLTResWrapper((HttpServletResponse)response); 
        chain.doFilter(request, responseWrapper); 
         
      
        try { 
           TransformerFactory factory = TransformerFactory.newInstance(); 
           Reader xslReader = new BufferedReader(new FileReader("C:/WELLBOOKMUN/VARIETY/WebContent/Exe12/AddressList.xsl")); 
           StreamSource xslSource = new StreamSource(xslReader); 
            
           Transformer transformer = factory.newTransformer(xslSource); 
            
           String xmlDocument = responseWrapper.getBufferedString(); 
           Reader xmlReader = new StringReader(xmlDocument); 
           StreamSource xmlSource = new StreamSource(xmlReader); 
            
           StringWriter buffer = new StringWriter(4096); 
            
           transformer.transform( xmlSource, new StreamResult(buffer) ); 
            
           writer.print(buffer.toString()); 
        } catch(Exception ex) { 
           throw new ServletException(ex); 
        } 
         
        writer.flush(); 
        writer.close(); 
     } 
      

     
    public void destroy() { 
    } 
 } 

