package jp.co.se.android.recipe.chapter02;

import java.util.ArrayList;
import java.util.HashMap;

import jp.co.se.android.recipe.chapter02.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

public class Ch0203 extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch0203_main);

        // 부모 리스트
        ArrayList<HashMap<String, String>> groupData = new ArrayList<HashMap<String, String>>();
        // 자식 리스트
        ArrayList<ArrayList<HashMap<String, String>>> childData = new ArrayList<ArrayList<HashMap<String, String>>>();

        // 부모 리스트에 요소를 추가
        HashMap<String, String> groupA = new HashMap<String, String>();
        groupA.put("group", "꽃");
        HashMap<String, String> groupB = new HashMap<String, String>();
        groupB.put("group", "새");

        groupData.add(groupA);
        groupData.add(groupB);

        // 자식 리스트에 요소를 추가(1)
        ArrayList<HashMap<String, String>> childListA = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> childAA = new HashMap<String, String>();
        childAA.put("group", "꽃");
        childAA.put("name", "민들레");
        HashMap<String, String> childAB = new HashMap<String, String>();
	    childAB.put("group", "꽃");
	    childAB.put("name", "코스코스");
	    HashMap<String, String> childAC = new HashMap<String, String>();
	    childAC.put("group", "꽃");
	    childAC.put("name", "장미");
	
	    childListA.add(childAA);
	    childListA.add(childAB);
	    childListA.add(childAC);
	
	    childData.add(childListA);

        // 자식 리스트에 요소를 추가(2)
        ArrayList<HashMap<String, String>> childListB = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> childBA = new HashMap<String, String>();
        childBA.put("group", "새");
        childBA.put("name", "독수리");
        HashMap<String, String> childBB = new HashMap<String, String>();
        childBB.put("group", "새");
        childBB.put("name", "참새");

        childListB.add(childBA);
        childListB.add(childBB);

        childData.add(childListB);

        // 부모 리스트, 자식리스트를 포함한 Adapter 생성
        SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(
                getApplicationContext(), groupData,
                android.R.layout.simple_expandable_list_item_1,
                new String[] { "group" }, new int[] { android.R.id.text1 },
                childData, android.R.layout.simple_expandable_list_item_2,
                new String[] { "name", "group" }, new int[] {
                        android.R.id.text1, android.R.id.text2 });

        // ExpandableListView에 Adapter를 설정
        ExpandableListView listView = (ExpandableListView) findViewById(R.id.ExpandablelistView);
        listView.setAdapter(adapter);
    }
}