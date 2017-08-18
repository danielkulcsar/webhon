package JSPWEB.CH11;

public class SEASON {
 private String []selectSEASON ={"Spring", "Summer", "Fall", "Winter"};
 public String[] getSelectSEASON() {
	return selectSEASON;
}
public void setSelectSEASON(String[] selectSEASON) {
	this.selectSEASON = selectSEASON;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public SEASON(){}
 private String name;
}
