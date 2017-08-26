package jp.co.se.android.recipe.chapter02;

import jp.co.se.android.recipe.chapter02.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Ch0204 extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch0201_main);

        View header = (View) getLayoutInflater().inflate(
                R.layout.ch0204_list_header_item, null);
        View footer = (View) getLayoutInflater().inflate(
                R.layout.ch0204_list_footer_item, null);

        ListView listView = (ListView) findViewById(R.id.ListView);
        listView.addHeaderView(header);
        listView.addFooterView(footer);

        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        // 항목이 클릭되었을 때 호출될 콜백을 등록
        listView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
                // 헤더인 경우(0)는 아무것도 안 함
                if (position == 0) {
                    return;
                }
            }
        });

        // 어댑터 생성
        listView.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_single_choice, DAYS));
        // 포커스가 없도록 설정
        listView.setItemsCanFocus(false);
    }

    // ListView에 표시할 문자열
    private static final String[] DAYS = new String[] { "Sunday", "Monday",
            "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday",
            "Thursday", "Friday", "Saturday", "Thursday", "Friday", "Saturday",
            "Thursday", "Friday", "Saturday", };

}
