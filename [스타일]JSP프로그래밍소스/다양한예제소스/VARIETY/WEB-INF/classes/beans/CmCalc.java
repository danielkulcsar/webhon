package beans;

import java.io.Serializable;

public class CmCalc implements Serializable{


  int won ;
 String  Calc;

 
  public void setCalc(String calc) {
	Calc = calc;
}


public int getWon() {
	return won;
}


public void setWon(int won) {
	     
	this.won = won;
}

public String getCalc()
{
	return String.valueOf(getWon()*1250);
	
}

public CmCalc() {

  }

 
  
}

