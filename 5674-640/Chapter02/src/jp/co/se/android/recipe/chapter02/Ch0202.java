package jp.co.se.android.recipe.chapter02;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;

public class Ch0202 extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch0202_main);
        GridView gridView = (GridView) findViewById(R.id.gridView);

        // 어댑터 생성
        ListAdapter adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, WEEK);
        // 어댑터 설정
        gridView.setAdapter(adapter);
    }

    // ListView에 표시할 문자열
    private static final String[] WEEK = new String[] { "월", "화", "수", "목", "금", "토", "일" };
}