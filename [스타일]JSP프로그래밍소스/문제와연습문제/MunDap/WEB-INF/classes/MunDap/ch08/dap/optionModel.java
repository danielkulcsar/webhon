package MunDap.ch08.dap;

import java.util.ArrayList;
import java.util.List;

public class optionModel {
	public List getSeason(String Season) {
		List<String> li = new ArrayList<String>();
		if(Season.equals("a")) {
			li.add("����");
			li.add("�丶��");			
		}else if(Season.equals("b")) {
			li.add("����");
			li.add("������");
		}else if(Season.equals("c")) {
			li.add("��");
			li.add("���");
		}else if(Season.equals("d")) {
			li.add("����");
			li.add("��");
		}
		return li;
	}
}
