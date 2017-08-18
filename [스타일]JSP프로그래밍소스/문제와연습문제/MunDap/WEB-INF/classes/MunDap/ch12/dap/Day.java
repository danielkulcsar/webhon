package MunDap.ch12.dap;

import java.util.*;

public class Day {

  private static Day instance = new Day();

  private List daylist = new ArrayList();

  private Day() {
	
	  daylist.add(new DayList(1, "Sunday"));
	  daylist.add(new DayList(2, "Monday"));
	  daylist.add(new DayList(3, "Thusday"));
	  daylist.add(new DayList(4, "Wendnesday"));
	  daylist.add(new DayList(5, "Thursday "));
	  daylist.add(new DayList(6, "Friday "));
	  daylist.add(new DayList(7, "Saturday "));
  }

  public static Day getInstance() {
	    return instance;
	  }

  public Collection getDay() {
    return Collections.unmodifiableCollection(daylist);
  }

}
