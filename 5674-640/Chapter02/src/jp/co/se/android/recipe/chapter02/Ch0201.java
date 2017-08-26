package jp.co.se.android.recipe.chapter02;

import jp.co.se.android.recipe.chapter02.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class Ch0201 extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch0201_main);
        ListView listView = (ListView) findViewById(R.id.ListView);

        // 어댑터 생성
        ListAdapter adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, DAYS);
        // 어댑터 설정
        listView.setAdapter(adapter);
    }

    // ListView에 표시할 문자열
    private static final String[] DAYS = new String[] { "Sunday", "Monday",
            "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };

}
