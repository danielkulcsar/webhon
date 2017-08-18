package Exe12.FILTER;

import java.io.StringWriter;
import java.io.*;

public class ResBuffer  extends PrintWriter { 
    
    public ResBuffer() { 
       super(new StringWriter(4096) ); 
    } 
     
    public String toString() { 
       return ((StringWriter)super.out).toString(); 
    } 
 } 
