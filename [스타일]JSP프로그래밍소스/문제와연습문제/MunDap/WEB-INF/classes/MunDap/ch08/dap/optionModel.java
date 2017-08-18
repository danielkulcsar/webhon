package MunDap.ch08.dap;

import java.util.ArrayList;
import java.util.List;

public class optionModel {
	public List getSeason(String Season) {
		List<String> li = new ArrayList<String>();
		if(Season.equals("a")) {
			li.add("딸기");
			li.add("토마토");			
		}else if(Season.equals("b")) {
			li.add("수박");
			li.add("복숭아");
		}else if(Season.equals("c")) {
			li.add("배");
			li.add("사과");
		}else if(Season.equals("d")) {
			li.add("유자");
			li.add("귤");
		}
		return li;
	}
}
