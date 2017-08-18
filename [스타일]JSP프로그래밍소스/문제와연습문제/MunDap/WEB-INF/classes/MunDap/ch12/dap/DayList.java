package MunDap.ch12.dap;

public class DayList {
	private int no;
	  public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getDayname() {
		return dayname;
	}

	public void setDayname(String dayname) {
		this.dayname = dayname;
	}

	private String dayname;

	  public DayList() {
	  }

	  public DayList(int no, String dayname) {
	    this.no = no;
	    this.dayname = dayname;
	  }

	  
}
