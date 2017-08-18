package MunDap.ch05dap;

import java.io.Serializable;
import java.util.Calendar;

public class HelloToday implements Serializable {
	private String name ;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	Calendar cal = Calendar.getInstance();
	
	public int getMonth()
	{
		return cal.get(Calendar.MONTH)+1;
	}
	public int getDate()
	{
		return cal.get(Calendar.DATE);
	}
}
