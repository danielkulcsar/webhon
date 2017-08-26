package jp.co.se.android.recipe.chapter12;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class Ch1202 extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch1202_main);

        findViewById(R.id.buttonStart).setOnClickListener(
                new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        startAlarm();
                    }
                });
    }

    private void startAlarm() {
        // Ch1202Receiver를 호출하는 인스턴스를 작성
        Intent i = new Intent(getApplicationContext(), Ch1202Receiver.class);
        PendingIntent sender = PendingIntent.getBroadcast(this, 0, i, 0);

        // 현재 시각보다 15초 뒤의 시간을 설정
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.SECOND, 15);

        // AlramManager 구하기
        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
        // AlramManager에 PendingIntent을 등록
        am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);
    }
}
