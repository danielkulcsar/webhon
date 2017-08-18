package CARTS;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;




public class CART {
	

	
	    Vector<String> v = new Vector<String>();
	    String submit = null;
	    String item = null;
	    
	    private void addItem(String name)throws Exception {
		
	    	v.addElement(name);
	    }

	    private void removeItem(String name) {
		v.removeElement(name);
	    }

	    public void setItem(String name)throws Exception {
	    	
		item =name;
	    }
	    
	    public void setSubmit(String s) {
		submit = s;
	    }

	    public String[] getItems() {
		String[] s = new String[v.size()];
		v.copyInto(s);
		return s;
	    }
	    
	    public void processRequest(HttpServletRequest request) throws Exception {
	    	request.setCharacterEncoding("euc-kr");
		if (submit == null) 
		    addItem(item);

		if (submit.equals("add"))
		    addItem(item);
		else if (submit.equals("remove")) 
		    removeItem(item);
		
		
		reset();
	    }

	   
	    private void reset() {
		submit = null;
		item = null;
	    }
	}


