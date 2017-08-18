package JSPWEB.CH12;

import java.io.*;
import java.text.DateFormat;
import java.util.Date;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

public class DateTag extends SimpleTagSupport {


	public void doTag() throws  IOException {
		
		  DateFormat df = DateFormat.getDateTimeInstance(
			        DateFormat.MEDIUM, DateFormat.MEDIUM);

			    try {
			     
			  getJspContext().getOut().write(df.format(new Date(2010-1900,0,10)));
			    	} catch (Exception e) {
					
					e.printStackTrace();
				}
			    }
           
           
        }
       


	

