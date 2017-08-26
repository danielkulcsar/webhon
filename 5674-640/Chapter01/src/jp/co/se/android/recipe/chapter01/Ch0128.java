package jp.co.se.android.recipe.chapter01;

import android.app.Activity;
import android.os.Bundle;
import android.widget.DatePicker;

public class Ch0128 extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch0128_main);

        // 설정하고 싶은 날짜를 2015년 12월 25일로 한다
        int year = 2015;
        int month = 12 - 1;// 0부터 시작하므로 -1을 한다
        int day = 25;

        // 날짜를 설정
        DatePicker dp = (DatePicker) findViewById(R.id.DatePicker);
        dp.updateDate(year, month, day);
    }
}
