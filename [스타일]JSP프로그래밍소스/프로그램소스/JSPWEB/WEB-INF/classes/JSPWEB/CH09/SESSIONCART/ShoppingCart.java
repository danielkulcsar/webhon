package JSPWEB.CH09.SESSIONCART;

import java.util.Enumeration;
import java.util.Hashtable;



public class ShoppingCart {
	
	  
	  protected Hashtable hash = new Hashtable();

	  public void addItem(String itemId,String desc, int price, int quantity)throws Exception {
	
		    String desci=new String(desc.getBytes("8859_1"),"euc-kr");
		  String[] item = {itemId,desci,   Integer.toString( price),
	    Integer.toString(quantity),Integer.toString(quantity)};

	  if (hash.containsKey(itemId)) {
       
	      String[] tmpItem = (String[])hash.get(itemId);
	      int tmpprice = Integer.parseInt(tmpItem[2]);
	      int tmpQuant = Integer.parseInt(tmpItem[3]);
	      quantity += tmpQuant;
	      tmpItem[3] = Integer.toString(quantity);
	     	hash.put(itemId, tmpItem);
	      }
	    else {

	    	hash.put(itemId, item);
	    }
	 
	  }

	 

	  public Enumeration getEnumeration() {
		
	    return hash.elements();
	  }

	 public int getCost() {

		    Enumeration e = hash.elements();
		    String[] tmpItem;
		  int totalCost = 0;

		    while (e.hasMoreElements()) {

		      tmpItem = (String[])e.nextElement();
		      totalCost += (Integer.parseInt(tmpItem[3]) *
		    		  Integer.parseInt(tmpItem[2]));
		    }
		    return  totalCost;
		  }

				 
	 
}
