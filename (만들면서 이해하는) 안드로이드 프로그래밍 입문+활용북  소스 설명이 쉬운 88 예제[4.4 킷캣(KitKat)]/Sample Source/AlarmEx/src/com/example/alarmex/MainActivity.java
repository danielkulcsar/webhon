package com.example.alarmex;

import java.util.*;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;

public class MainActivity extends Activity {
    AlarmManager mAlarmMgr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAlarmMgr = (AlarmManager)getSystemService(ALARM_SERVICE);
    }

    public void onBtnAlarm1() {
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);
        mAlarmMgr.set(AlarmManager.RTC, System.currentTimeMillis() + 5000, pIntent);
    }

    public void onBtnAlarm2() {
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.SECOND, 5);
        mAlarmMgr.setRepeating(AlarmManager.RTC, calendar.getTimeInMillis(), 
                5000, pIntent);
    }

    public void onBtnStop() {
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);
        mAlarmMgr.cancel(pIntent);
    }

    public void onClick(View v) {
        switch( v.getId() ) {
        case R.id.btnAlarm1 :
            onBtnAlarm1();
            break;
        case R.id.btnAlarm2 :
            onBtnAlarm2();
            break;
        case R.id.btnStop :
            onBtnStop();
            break;
        }
    }

}
