package JSPWEB.CH05;

import java.io.Serializable;

public class beansMessage implements Serializable {
 private  String message;

  public beansMessage()
  {
 	  message="default �������Դϴ�";
  }
  public beansMessage(String message)
  {
 	this.message=message; 
  }
 
 
public String getMessage() {
	return message;
}

public void setMessage(String message) {
	this.message = message;
}
  
}
